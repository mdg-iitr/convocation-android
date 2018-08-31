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

import java.util.List;

import ac.in.iitr.mdg.convocation.BranchStudentsActivity;
import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.responsemodels.DegreeCard;

public class DegreeAdapter extends RecyclerView.Adapter<DegreeAdapter.Branchholder> {
    Context context;
    List<DegreeCard> list;

    public DegreeAdapter(Context context, List<DegreeCard> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Branchholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_degree, parent, false);
        Branchholder textHolder = new Branchholder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Branchholder holder, int position) {
        final DegreeCard mylist = list.get(position);
        holder.branch.setText(mylist.getBranchName());
        holder.no_of_students.setText(mylist.getNumberOfStudents());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BranchStudentsActivity.class);
                intent.putExtra("branch", mylist.getBranchName());
                v.getContext().startActivity(intent);
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

        }
        return arr;
    }

    class Branchholder extends RecyclerView.ViewHolder {
        public TextView branch, no_of_students;
        public CardView cardView;

        public Branchholder(View itemView) {
            super(itemView);
            branch = (TextView) itemView.findViewById(R.id.degree_branch);
            no_of_students = (TextView) itemView.findViewById(R.id.degree_number_of_students);
            cardView = (CardView) itemView.findViewById(R.id.degreeCard);
        }
    }
}
