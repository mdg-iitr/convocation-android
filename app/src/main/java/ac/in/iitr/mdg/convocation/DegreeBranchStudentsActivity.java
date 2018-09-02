package ac.in.iitr.mdg.convocation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.adapters.BranchStudentAdapter;
import ac.in.iitr.mdg.convocation.responsemodels.UserResponseModel;

public class DegreeBranchStudentsActivity extends AppCompatActivity {

    private BranchStudentAdapter textAdapter;
    private RecyclerView recyclerView;

    private ArrayList<UserResponseModel> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_students);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView textView = findViewById(R.id.title_branch);

        Toolbar toolbar = findViewById(R.id.toolbar_branchStudent);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String branch = bundle.getString("branch");
            toolbar.setTitle("");
            textView.setText(branch);

            students = bundle.getParcelableArrayList("students");
        }
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.branchStudents_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        textAdapter = new BranchStudentAdapter(this, students);
        recyclerView.setAdapter(textAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_branch_students, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Name");
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.hintColor));
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(getResources().getColor(R.color.searchTextColor));
        searchView.setBackgroundColor(getResources().getColor(R.color.white));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                if (s.length() == 0) {
                    textAdapter.filter("");
                    RecyclerView.LayoutManager recycler = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(recycler);
                    recyclerView.setAdapter(textAdapter);
//                    recyclerView.clearTextFilter();
                } else {
                    textAdapter.filter(s);
                    RecyclerView.LayoutManager recycler = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(recycler);
                    recyclerView.setAdapter(textAdapter);
                }
                return true;
            }
        });
        return true;

    }

}
