package ac.in.iitr.mdg.convocation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.responsemodels.ChiefGuestResponse;

public class ChiefGuestAdapter extends RecyclerView.Adapter<ChiefGuestAdapter.MyViewHolder> {

    private final OnItemClickListener listener;
    private List<ChiefGuestResponse> guestList;

    public ChiefGuestAdapter(List<ChiefGuestResponse> guestList, OnItemClickListener listener) {
        this.guestList = guestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_chief_guest, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChiefGuestResponse guest = guestList.get(position);
        String imageUrl = guest.getImage();
        if (imageUrl.isEmpty()) {
            Picasso.get().load("null").placeholder(R.drawable.grey_card).into(holder.guestImage);
        } else {
            Picasso.get().load(imageUrl).placeholder(R.drawable.grey_card).into(holder.guestImage);
        }
        holder.name.setText(guest.getName());
        holder.designation.setText(guest.getDesignation());
//        holder.subtitle.setText(guest.getSubtitle());
        holder.bind(guestList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ChiefGuestResponse guest);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, designation, subtitle;
        ImageView guestImage;

        MyViewHolder(View view) {
            super(view);
            guestImage = view.findViewById(R.id.guest_card_thumbimage);
            name = view.findViewById(R.id.guest_card_name);
            designation = view.findViewById(R.id.guest_card_desg);
            subtitle = view.findViewById(R.id.guest_card_date);
        }

        void bind(final ChiefGuestResponse guest, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(guest);
                }
            });
        }
    }
}

