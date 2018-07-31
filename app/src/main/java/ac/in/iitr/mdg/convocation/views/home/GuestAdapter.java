package ac.in.iitr.mdg.convocation.views.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

        import java.util.List;

import ac.in.iitr.mdg.convocation.views.R;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyViewHolder> {

            public interface OnItemClickListener {
        void onItemClick(chiefGuestsProfile guest);
    }

            private final OnItemClickListener listener;
        
            private List<chiefGuestsProfile> guestList;
        
            public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView serialNo,name,designation;

                public MyViewHolder(View view) {
                    super(view);
                    serialNo = (TextView) view.findViewById(R.id.chief_guest_card_sl);
                    name = (TextView) view.findViewById(R.id.chief_guest_card_name);
                    designation = (TextView) view.findViewById(R.id.chief_guest_card_desig);
                }

                public void bind(final chiefGuestsProfile guest, final OnItemClickListener listener){
                    itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                                    listener.onItemClick(guest);
                                }
            });
                }
    }

        
            public GuestAdapter(List<chiefGuestsProfile> guestList,OnItemClickListener listener) {
                this.guestList = guestList;
                this.listener = listener;
            }
        
            @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chiefguestcard, parent, false);
        
                return new MyViewHolder(itemView);
            }
        
            @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
                chiefGuestsProfile guest = guestList.get(position);
                holder.serialNo.setText(String.valueOf(guest.getSerialNumber()));
                holder.name.setText(guest.getName());
                holder.designation.setText(guest.getDesignation());
                holder.bind(guestList.get(position), listener);
            }
        
            @Override
    public int getItemCount() {
                return guestList.size();
            }
        }

