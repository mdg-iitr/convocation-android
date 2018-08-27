package ac.in.iitr.mdg.convocation.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(HotelProfile hotel);
    }

    private final HotelAdapter.OnItemClickListener listener;

    private List<HotelProfile> hotelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView serialNo,name,address;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.hotel_card_name);
        }

        public void bind(final HotelProfile hotel, final HotelAdapter.OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(hotel);
                }
            });
        }
    }


    public HotelAdapter(List<HotelProfile> guestList, HotelAdapter.OnItemClickListener listener) {
        this.hotelList = guestList;
        this.listener = listener;
    }

    @Override
    public HotelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotelcard, parent, false);

        return new HotelAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.MyViewHolder holder, int position) {
        HotelProfile hotel = hotelList.get(position);
        holder.name.setText(hotel.getName());
        holder.bind(hotelList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}


