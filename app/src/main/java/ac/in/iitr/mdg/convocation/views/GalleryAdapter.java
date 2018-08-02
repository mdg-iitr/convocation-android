package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by karthik on 1/8/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Bitmap[] imageDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public ViewHolder(View i){
            super(i);
            mImageView = i.findViewById(R.id.galleryImage);
        }

    }

    public GalleryAdapter(Bitmap[] imageData){
        imageDataSet = imageData;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //create a new View
        View i =  LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(i);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImageView.setImageBitmap(imageDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return imageDataSet.length;
    }

}
