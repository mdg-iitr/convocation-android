package ac.in.iitr.mdg.convocation.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.DegreeBranchStudentsActivity;
import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.responsemodels.DegreeBranchModel;

public class DegreeAdapter extends RecyclerView.Adapter<DegreeAdapter.BranchViewHolder> {

    private Context context;
    private ArrayList<DegreeBranchModel> list;

    public DegreeAdapter(Context context, ArrayList<DegreeBranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_degree, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        final DegreeBranchModel branchModel = list.get(position);
        holder.branch.setText(branchModel.getBranchShortName());
        holder.no_of_students.setText("Total Students : " + branchModel.getNoOfStudents());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DegreeBranchStudentsActivity.class);
                intent.putExtra("branch", branchModel.getBranchShortName());
                intent.putParcelableArrayListExtra("students", branchModel.getStudents());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    class BranchViewHolder extends RecyclerView.ViewHolder {
        public TextView branch, no_of_students;
        public CardView cardView;

        public BranchViewHolder(View itemView) {
            super(itemView);
            branch = (TextView) itemView.findViewById(R.id.degree_branch);
            no_of_students = (TextView) itemView.findViewById(R.id.degree_number_of_students);
            cardView = (CardView) itemView.findViewById(R.id.degreeCard);
        }
    }
}
