package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleModel {

    @SerializedName("date")
    private String date;
    @SerializedName("events")
    private ArrayList<ScheduleEventModel> scheduleEventModels;

    public ScheduleModel(String date, ArrayList<ScheduleEventModel> scheduleEventModels) {
        this.date = date;
        this.scheduleEventModels = scheduleEventModels;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ScheduleEventModel> getScheduleEventModels() {
        return scheduleEventModels;
    }

    public void setScheduleEventModels(ArrayList<ScheduleEventModel> scheduleEventModels) {
        this.scheduleEventModels = scheduleEventModels;
    }
}
