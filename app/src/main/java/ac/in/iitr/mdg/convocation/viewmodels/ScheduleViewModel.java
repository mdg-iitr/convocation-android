package ac.in.iitr.mdg.convocation.viewmodels;

import ac.in.iitr.mdg.convocation.responsemodels.ScheduleEventModel;

public class ScheduleViewModel {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_SCHEDULE = 1;

    private int type;
    private String date;
    private ScheduleEventModel scheduleEventModel;

    public ScheduleViewModel(int type, String date, ScheduleEventModel scheduleEventModel) {
        this.type = type;
        this.date = date;
        this.scheduleEventModel = scheduleEventModel;
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public ScheduleEventModel getScheduleEventModel() {
        return scheduleEventModel;
    }
}
