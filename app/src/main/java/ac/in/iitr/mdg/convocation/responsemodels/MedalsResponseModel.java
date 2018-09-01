package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MedalsResponseModel {

    @SerializedName("level")
    private String level;
    @SerializedName("medals")
    private ArrayList<MedalTypeModel> medals;

    public MedalsResponseModel(String level, ArrayList<MedalTypeModel> medals) {
        this.level = level;
        this.medals = medals;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<MedalTypeModel> getMedals() {
        return medals;
    }

    public void setMedals(ArrayList<MedalTypeModel> medals) {
        this.medals = medals;
    }
}
