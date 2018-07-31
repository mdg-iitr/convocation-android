package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;

/**
 * Created by suyash on 7/31/18.
 */

public class Schedule {
    public Bitmap schedule_image;
    public String schedule_event,schedule_venue,schedule_time;

    public Schedule() {
    }

    public Schedule(Bitmap schedule_image, String schedule_event, String schedule_venue, String schedule_time) {
        this.schedule_image = schedule_image;
        this.schedule_event = schedule_event;
        this.schedule_venue = schedule_venue;
        this.schedule_time = schedule_time;
    }

    public Bitmap getSchedule_image() {
        return schedule_image;
    }

    public void setSchedule_image(Bitmap schedule_image) {
        this.schedule_image = schedule_image;
    }

    public String getSchedule_event() {
        return schedule_event;
    }

    public void setSchedule_event(String schedule_event) {
        this.schedule_event = schedule_event;
    }

    public String getSchedule_venue() {
        return schedule_venue;
    }

    public void setSchedule_venue(String schedule_venue) {
        this.schedule_venue = schedule_venue;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }
}
