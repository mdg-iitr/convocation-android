package ac.in.iitr.mdg.convocation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ac.in.iitr.mdg.convocation.R;
import ac.in.iitr.mdg.convocation.utils.Utils;
import ac.in.iitr.mdg.convocation.viewmodels.ScheduleViewModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ScheduleViewModel> list;

    public ScheduleAdapter(Context context, List<ScheduleViewModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ScheduleViewModel.TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule_date, parent, false);
                return new ScheduleDateHolder(view);

            case ScheduleViewModel.TYPE_SCHEDULE:
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
                return ScheduleViewModel.TYPE_DATE;
            case 1:
                return ScheduleViewModel.TYPE_SCHEDULE;
            default:
                return -1;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScheduleViewModel model = list.get(position);
        if (model != null) {
            if (model.getType() == ScheduleViewModel.TYPE_SCHEDULE) {
                String imageUrl = model.getScheduleEventModel().getImage();
                if (imageUrl == null || imageUrl.isEmpty()) {
                    Picasso.get().load("null").placeholder(R.drawable.image_placeholder).into(((ScheduleHolder) holder).scheduleEventImage);
                } else {
                    Picasso.get().load(imageUrl).placeholder(R.drawable.image_placeholder).into(((ScheduleHolder) holder).scheduleEventImage);
                }
                ((ScheduleHolder) holder).scheduleEventTitleText.setText(model.getScheduleEventModel().getTitle());
                ((ScheduleHolder) holder).scheduleEventVenueText.setText(model.getScheduleEventModel().getVenue());

                ((ScheduleHolder) holder).scheduleEventTimeText.setText(
                        Utils.convertTimeFormat(model.getScheduleEventModel().getTimeStart())
                                + " - "
                                + Utils.convertTimeFormat(model.getScheduleEventModel().getTimeEnd()));

            } else if (model.getType() == ScheduleViewModel.TYPE_DATE) {
                ((ScheduleDateHolder) holder).scheduleDateText.setText(model.getDate());
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
            e.printStackTrace();
        }
        return arr;
    }


    class ScheduleHolder extends RecyclerView.ViewHolder {
        CircleImageView scheduleEventImage;
        TextView scheduleEventTitleText, scheduleEventVenueText, scheduleEventTimeText;

        ScheduleHolder(View itemView) {
            super(itemView);
            scheduleEventImage = itemView.findViewById(R.id.schedule_image);
            scheduleEventTitleText = itemView.findViewById(R.id.schedule_event_title);
            scheduleEventVenueText = itemView.findViewById(R.id.schedule_event_venue);
            scheduleEventTimeText = itemView.findViewById(R.id.schedule_event_time);
        }
    }

    class ScheduleDateHolder extends RecyclerView.ViewHolder {
        public TextView scheduleDateText;

        ScheduleDateHolder(View itemView) {
            super(itemView);
            scheduleDateText = itemView.findViewById(R.id.schedule_date);
        }
    }
}


