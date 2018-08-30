package ac.in.iitr.mdg.convocation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.models.Schedule;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Schedule> list;

    public ScheduleAdapter(Context context, List<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Schedule.TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule_date, parent, false);
                return new ScheduleDateHolder(view);

            case Schedule.TYPE_SCHEDULE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule, parent, false);
                return new ScheduleHolder(view);

            default:
                return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()) {
            case 0:
                return Schedule.TYPE_DATE;
            case 1:
                return Schedule.TYPE_SCHEDULE;
            default:
                return -1;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Schedule mylist = list.get(position);
        if (mylist != null) {
            if (mylist.getType() == Schedule.TYPE_SCHEDULE) {
                ((ScheduleHolder) holder).schedule_image.setImageBitmap(mylist.getScheduleCard().getSchedule_image());
                ((ScheduleHolder) holder).schedule_event.setText(mylist.getScheduleCard().getSchedule_event());
                ((ScheduleHolder) holder).schedule_venue.setText(mylist.getScheduleCard().getSchedule_venue());
                ((ScheduleHolder) holder).schedule_time.setText(mylist.getScheduleCard().getSchedule_time());
            } else if (mylist.getType() == Schedule.TYPE_DATE) {
                ((ScheduleDateHolder) holder).date.setText(mylist.getSchedule_date());
            }
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {

        }
        return arr;
    }


    class ScheduleHolder extends RecyclerView.ViewHolder {
        ImageView schedule_image;
        TextView schedule_event, schedule_venue, schedule_time;

        public ScheduleHolder(View itemView) {
            super(itemView);
            schedule_image = itemView.findViewById(R.id.scheduleCard_image);
            schedule_event = itemView.findViewById(R.id.scheduleCard_event);
            schedule_venue = itemView.findViewById(R.id.scheduleCard_venue);
            schedule_time = itemView.findViewById(R.id.scheduleCard_time);
        }
    }

    class ScheduleDateHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public ScheduleDateHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.schedule_date);
        }
    }
}


