package ac.in.iitr.mdg.convocation.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class BranchStudents extends AppCompatActivity {
    public BranchStudentAdapter textAdapter;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_students);

        ImageButton imageButton = (ImageButton) findViewById(R.id.back_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView textView = (TextView) findViewById(R.id.title_branch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_branchStudent);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String branch = bundle.getString("branch");
            toolbar.setTitle("");
            textView.setText(branch);
        }
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.branchStudents_recyclerView);
        ArrayList<BranchStudentCard> list = new ArrayList<>();
        textAdapter = new BranchStudentAdapter(this, list);

        BranchStudentCard branchStudentCard1 = new BranchStudentCard("Kaustubh Trivedi", "17114044");
        BranchStudentCard branchStudentCard2 = new BranchStudentCard("Suyash Jain", "17115100");
        BranchStudentCard branchStudentCard3 = new BranchStudentCard("Karthik Iyer", "17112036");
        BranchStudentCard branchStudentCard4 = new BranchStudentCard("Shashank Kashyap", "17114070");
        BranchStudentCard branchStudentCard5 = new BranchStudentCard("Utkarsh Mishra", "17117093");
        BranchStudentCard branchStudentCard6 = new BranchStudentCard("Nikhil Agrawal", "17117050");
        BranchStudentCard branchStudentCard7 = new BranchStudentCard("Rohit Ashiwal", "17114064");
        BranchStudentCard branchStudentCard8 = new BranchStudentCard("Saurabh Singh", "17114068");
        BranchStudentCard branchStudentCard9 = new BranchStudentCard("Piyush Makwana", "17114056");
        BranchStudentCard branchStudentCard10 = new BranchStudentCard("Bhavye Jain", "17114020");

        list.add(branchStudentCard1);
        list.add(branchStudentCard2);
        list.add(branchStudentCard3);
        list.add(branchStudentCard4);
        list.add(branchStudentCard5);
        list.add(branchStudentCard6);
        list.add(branchStudentCard7);
        list.add(branchStudentCard8);
        list.add(branchStudentCard9);
        list.add(branchStudentCard10);
        textAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_branch_students, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
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
