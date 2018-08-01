package ac.in.iitr.mdg.convocation.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by suyash on 8/1/18.
 */

public class BranchStudentAdapter extends RecyclerView.Adapter<BranchStudentAdapter.BranchStudentHolder>{
    Context context;
    List<BranchStudentCard> list;

    public BranchStudentAdapter(Context context, List<BranchStudentCard> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BranchStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_branchstudents,parent,false);
        BranchStudentHolder textHolder = new BranchStudentHolder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BranchStudentHolder holder, int position) {
        BranchStudentCard mylist =list.get(position);
        holder.student_name.setText(mylist.getStudent_name());
        holder.student_enrollment.setText(mylist.getStudent_enrollment());
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size()== 0){
                arr =0;
            }
            else {
                arr = list.size();
            }
        }catch (Exception e){

        }
        return arr;
    }

    class BranchStudentHolder extends RecyclerView.ViewHolder{
        public TextView student_name, student_enrollment;
        public BranchStudentHolder(View itemView) {
            super(itemView);
            student_name = (TextView)itemView.findViewById(R.id.branchStudent_name);
            student_enrollment = (TextView)itemView.findViewById(R.id.branchStudent_enrollment);
        }
    }
}
