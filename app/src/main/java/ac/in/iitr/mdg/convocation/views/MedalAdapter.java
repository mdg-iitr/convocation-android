package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;
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

public class MedalAdapter extends RecyclerView.Adapter<MedalAdapter.ViewHolder> {

    private ArrayList<MedalModel> dataSet;
    private ArrayList<View> views;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public ViewHolder(View i){
            super(i);
            view = i.findViewById(R.id.medal_block);
        }

    }

    public MedalAdapter(ArrayList<MedalModel> list){
        dataSet = list;
    }

    @Override
    public MedalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //create a new View
        View i =  LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(i);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        View v = holder.view;

        ((TextView)(v.findViewById(R.id.medal_category))).setText(dataSet.get(position).getMedalCategory());

        int s = dataSet.get(position).getMedalHoldersList().size();

        for (int i = 0; i<s; i++ ){

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
