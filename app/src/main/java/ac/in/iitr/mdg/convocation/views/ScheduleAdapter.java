package ac.in.iitr.mdg.convocation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by suyash on 7/31/18.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>{
    Context context;
    List<Schedule> list;

    public ScheduleAdapter(Context context, List<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule,parent,false);
        ScheduleHolder textHolder = new ScheduleHolder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        Schedule mylist = list.get(position);
        holder.schedule_image.setImageBitmap(mylist.getSchedule_image());
        holder.schedule_event.setText(mylist.getSchedule_event());
        holder.schedule_venue.setText(mylist.getSchedule_venue());
        holder.schedule_time.setText(mylist.getSchedule_time());
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size()== 0){
                arr =0;
            }
            else {
                arr = list.size();
            }
        }catch (Exception e){

        }
        return arr;
    }




    class ScheduleHolder extends RecyclerView.ViewHolder{
        public ImageView schedule_image;
        public TextView schedule_event,schedule_venue,schedule_time;
        public ScheduleHolder(View itemView) {
            super(itemView);
            schedule_image = (ImageView)itemView.findViewById(R.id.scheduleCard_image);
            schedule_event = (TextView)itemView.findViewById(R.id.scheduleCard_event);
            schedule_venue = (TextView)itemView.findViewById(R.id.scheduleCard_venue);
            schedule_time = (TextView)itemView.findViewById(R.id.scheduleCard_time);
        }
    }
}


