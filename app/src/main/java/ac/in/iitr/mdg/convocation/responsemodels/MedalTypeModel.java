package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MedalTypeModel {

    @SerializedName("type")
    private String type;
    @SerializedName("students")
    private ArrayList<UserResponseModel> students;

    public MedalTypeModel(String type, ArrayList<UserResponseModel> students) {
        this.type = type;
        this.students = students;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<UserResponseModel> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<UserResponseModel> students) {
        this.students = students;
    }
}
