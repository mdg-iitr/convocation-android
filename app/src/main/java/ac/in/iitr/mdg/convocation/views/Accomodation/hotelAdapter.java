package ac.in.iitr.mdg.convocation.views.Accomodation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ac.in.iitr.mdg.convocation.views.R;

public class hotelAdapter extends RecyclerView.Adapter<hotelAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(hotelProfile hotel);
    }

    private final hotelAdapter.OnItemClickListener listener;

    private List<hotelProfile> hotelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView serialNo,name,address;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.hotel_card_name);
        }

        public void bind(final hotelProfile hotel, final hotelAdapter.OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(hotel);
                }
            });
        }
    }


    public hotelAdapter(List<hotelProfile> guestList,hotelAdapter.OnItemClickListener listener) {
        this.hotelList = guestList;
        this.listener = listener;
    }

    @Override
    public hotelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotelcard, parent, false);

        return new hotelAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(hotelAdapter.MyViewHolder holder, int position) {
        hotelProfile hotel = hotelList.get(position);
        holder.name.setText(hotel.getName());
        holder.bind(hotelList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}


