package ac.in.iitr.mdg.convocation.views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BranchStudents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_students);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.branchStudents_recyclerView);
        List<BranchStudentCard> list = new ArrayList<>();
        BranchStudentAdapter textAdapter = new BranchStudentAdapter(this,list);

        for (int i=0;i<50;i++){
            BranchStudentCard branchStudentCard = new BranchStudentCard("Lakshya Kumawat","16113100");
            list.add(branchStudentCard);
            textAdapter.notifyDataSetChanged();
        }

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);
    }

//    @Override
//    public void onBackPressed(){
//        startActivity();
//    }




}
