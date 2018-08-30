package ac.in.iitr.mdg.convocation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.models.ChiefGuestProfile;

public class ChiefGuestAdapter extends RecyclerView.Adapter<ChiefGuestAdapter.MyViewHolder> {

    private final OnItemClickListener listener;
    private List<ChiefGuestProfile> guestList;

    public ChiefGuestAdapter(List<ChiefGuestProfile> guestList, OnItemClickListener listener) {
        this.guestList = guestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chiefguestcard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChiefGuestProfile guest = guestList.get(position);
        holder.name.setText(guest.getName());
        holder.designation.setText(guest.getDesignation());
        holder.date.setText(guest.getDate());
        holder.bind(guestList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ChiefGuestProfile guest);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, designation, date;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.guest_card_name);
            designation = view.findViewById(R.id.guest_card_desg);
            date = view.findViewById(R.id.guest_card_date);
        }

        void bind(final ChiefGuestProfile guest, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(guest);
                }
            });
        }
    }
}

