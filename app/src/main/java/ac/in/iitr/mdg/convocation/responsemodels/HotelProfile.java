package ac.in.iitr.mdg.convocation.responsemodels;

public class HotelProfile {

    private String name;
    private int resId;
    private String mapsUrl;

    public HotelProfile(String name, int resId, String mapsUrl) {
        this.name = name;
        this.resId = resId;
        this.mapsUrl = mapsUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getMapsUrl() {
        return mapsUrl;
    }

    public void setMapsUrl(String mapsUrl) {
        this.mapsUrl = mapsUrl;
    }
}
