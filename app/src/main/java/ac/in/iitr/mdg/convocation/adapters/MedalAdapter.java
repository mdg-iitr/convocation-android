package ac.in.iitr.mdg.convocation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.viewmodels.MedalViewModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class MedalAdapter extends RecyclerView.Adapter {

    private ArrayList<MedalViewModel> dataSet;

    public MedalAdapter(ArrayList<MedalViewModel> list) {
        dataSet = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        switch (viewType) {
            case MedalViewModel.TYPE_CATEGORY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_category_layout, parent, false);
                return new CategoryViewHolder(v);
            case MedalViewModel.TYPE_HOLDER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_holder_layout, parent, false);
                return new MedalHolderViewHolder(v);
            default:
                return null;

        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).getType()) {
            case 0:
                return MedalViewModel.TYPE_CATEGORY;
            case 1:
                return MedalViewModel.TYPE_HOLDER;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MedalViewModel medalModel = dataSet.get(position);
        if (medalModel != null) {
            switch (medalModel.getType()) {

                case MedalViewModel.TYPE_CATEGORY:
                    ((CategoryViewHolder) holder).textView.setText(medalModel.getMedalCategory());
                    break;
                case MedalViewModel.TYPE_HOLDER:

                    MedalHolderViewHolder medalHolder = (MedalHolderViewHolder) holder;

                    if (medalModel.getUserModel().getProfileImage() == null || medalModel.getUserModel().getProfileImage().isEmpty()) {
                        Picasso.get().load("null").placeholder(R.drawable.image_placeholder).into(medalHolder.image);
                    } else {
                        Picasso.get().load(medalModel.getUserModel().getProfileImage()).placeholder(R.drawable.image_placeholder).into(medalHolder.image);
                    }

                    medalHolder.holderName.setText(medalModel.getUserModel().getName());
                    medalHolder.holderBranchEnr.setText(medalModel.getUserModel().getBranch() + "(" + medalModel.getUserModel().getEnrollmentNumber() + ")");
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {


        TextView textView;

        public CategoryViewHolder(View i) {
            super(i);
            this.textView = (TextView) i;
        }

    }

    public static class MedalHolderViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView holderName, holderBranchEnr;

        public MedalHolderViewHolder(View c) {
            super(c);
            this.image = c.findViewById(R.id.holder_image);
            this.holderName = c.findViewById(R.id.holder_name);
            this.holderBranchEnr = c.findViewById(R.id.holder_branch_en);
        }

    }

}
