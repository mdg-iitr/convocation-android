package ac.in.iitr.mdg.convocation.views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karthik on 1/8/18.
 */

public class MedalAdapter extends RecyclerView.Adapter {

    private ArrayList<MedalModel> dataSet;

    public MedalAdapter(ArrayList<MedalModel> list) {
        dataSet = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        switch (viewType) {
            case MedalModel.TYPE_CATEGORY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_category_layout, parent, false);
                return new CategoryViewHolder(v);
            case MedalModel.TYPE_HOLDER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_holder_layout, parent, false);
                return new MedalHolderViewHolder(v);
            default:
                return null;

        }

    }

//    public static

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).getType()) {
            case 0:
                return MedalModel.TYPE_CATEGORY;
            case 1:
                return MedalModel.TYPE_HOLDER;
            default:
                return -1;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        MedalModel medalModel = dataSet.get(position);
        if (medalModel != null) {
            switch (medalModel.getType()) {

                case MedalModel.TYPE_CATEGORY:
                    ((CategoryViewHolder) holder).textView.setText(medalModel.getMedalCategory());
                    break;
                case MedalModel.TYPE_HOLDER:
                    View view = (((MedalHolderViewHolder) holder).cardView).findViewById(R.id.inCard);

                    ImageView imageView = view.findViewById(R.id.holder_image);
                    imageView.setImageBitmap(medalModel.getMedalHolder().getMedalHolderImage());

                    TextView nameText = view.findViewById(R.id.holder_name);
                    nameText.setText(medalModel.getMedalHolder().getMedalHolderName());

                    TextView branchEno = view.findViewById(R.id.holder_branch_en);
                    branchEno.setText(medalModel.getMedalHolder().getMedalHolderBranch() + "(" + medalModel.getMedalHolder().getMedalHolderEnNo() + ")");
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
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

        CardView cardView;

        public MedalHolderViewHolder(View c) {
            super(c);
            this.cardView = (CardView) c;
        }

    }

}
