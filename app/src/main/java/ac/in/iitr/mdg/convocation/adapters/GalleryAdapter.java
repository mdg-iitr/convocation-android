package ac.in.iitr.mdg.convocation.adapters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ac.in.iitr.mdg.convocation.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Bitmap[] imageDataSet;
    private String[] textDataSet;

    public GalleryAdapter(Bitmap[] b, String[] s) {
        imageDataSet = b;
        textDataSet = s;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View i = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_image, parent, false);
        return new ViewHolder(i);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mImageView.setImageBitmap(imageDataSet[position]);
        holder.mTextView.setText(textDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return imageDataSet.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        ViewHolder(View i) {
            super(i);
            mImageView = i.findViewById(R.id.gallery_photo);
            mTextView = i.findViewById(R.id.gallery_photo_text);
        }
    }
}
