package ac.in.iitr.mdg.convocation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.responsemodels.UserResponseModel;

public class BranchStudentAdapter extends RecyclerView.Adapter<BranchStudentAdapter.BranchStudentHolder> {
    private Context context;
    private ArrayList<UserResponseModel> list;
    private ArrayList<UserResponseModel> searchList;
    private int FLAG = 0;

    public BranchStudentAdapter(Context context, ArrayList<UserResponseModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BranchStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_branchstudents, parent, false);
        return new BranchStudentHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BranchStudentHolder holder, int position) {
        UserResponseModel student = list.get(position);
        holder.student_name.setText(student.getName().trim());
        holder.student_enrollment.setText(student.getEnrollmentNumber().trim());
        if (student.isRegistered()) {
            holder.isRegisteredImage.setVisibility(View.VISIBLE);
        } else {
            holder.isRegisteredImage.setVisibility(View.GONE);
        }
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

    // Filter method
    public void filter(String charText) {
        if (FLAG == 0) {
            searchList = new ArrayList<>(list);
            FLAG++;
        }
        list.clear();
        charText = charText.toLowerCase(Locale.getDefault());
        if (charText.length() == 0) {
            list.addAll(searchList);
        } else {
            for (UserResponseModel userResponseModel : searchList) {
                if (userResponseModel.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(userResponseModel);
                } else if (userResponseModel.getEnrollmentNumber().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(userResponseModel);
                }
            }
        }
        notifyDataSetChanged();
    }

    class BranchStudentHolder extends RecyclerView.ViewHolder {
        public TextView student_name, student_enrollment;
        ImageView isRegisteredImage;

        public BranchStudentHolder(View itemView) {
            super(itemView);
            student_name = (TextView) itemView.findViewById(R.id.branchStudent_name);
            student_enrollment = (TextView) itemView.findViewById(R.id.branchStudent_enrollment);
            isRegisteredImage = itemView.findViewById(R.id.is_registered_image);
        }
    }
}
