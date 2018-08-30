package ac.in.iitr.mdg.convocation.models;

import android.graphics.Bitmap;

public class ScheduleCard {
    public Bitmap schedule_image;
    public String schedule_event, schedule_venue, schedule_time;

    public ScheduleCard(Bitmap schedule_image, String schedule_event, String schedule_venue, String schedule_time) {
        this.schedule_image = schedule_image;
        this.schedule_event = schedule_event;
        this.schedule_venue = schedule_venue;
        this.schedule_time = schedule_time;
    }

    public Bitmap getSchedule_image() {
        return schedule_image;
    }


    public String getSchedule_event() {
        return schedule_event;
    }


    public String getSchedule_venue() {
        return schedule_venue;
    }


    public String getSchedule_time() {
        return schedule_time;
    }

}
