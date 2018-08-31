package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

public class ScheduleEventModel {

    @SerializedName("image")
    private String image;
    @SerializedName("venue")
    private String venue;
    @SerializedName("title")
    private String title;
    @SerializedName("time_end")
    private String timeEnd;
    @SerializedName("time_start")
    private String timeStart;

    public ScheduleEventModel(String image, String venue, String title, String timeEnd, String timeStart) {
        this.image = image;
        this.venue = venue;
        this.title = title;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
}
