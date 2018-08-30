package ac.in.iitr.mdg.convocation.views;

/**
 * Created by suyash on 8/2/18.
 */

public class Schedule {
    public static final int TYPE_DATE = 0;
    public static final int TYPE_SCHEDULE = 1;
    public String schedule_date;
    public ScheduleCard scheduleCard;
    private int type;

    public Schedule(int type, String schedule_date) {
        this.schedule_date = schedule_date;
        this.type = type;
    }

    public Schedule(int type, ScheduleCard scheduleCard) {
        this.scheduleCard = scheduleCard;
        this.type = type;
    }

    public ScheduleCard getScheduleCard() {
        return scheduleCard;
    }

    public String getSchedule_date() {
        return schedule_date;
    }


    public int getType() {
        return type;
    }
}
