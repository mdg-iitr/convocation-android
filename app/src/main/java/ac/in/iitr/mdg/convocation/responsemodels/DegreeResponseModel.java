package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DegreeResponseModel {

    @SerializedName("id")
    private int id;
    @SerializedName("degree")
    private String degree;
    @SerializedName("branches")
    private ArrayList<DegreeBranchModel> branches;

    public DegreeResponseModel(int id, String degree, ArrayList<DegreeBranchModel> branches) {
        this.id = id;
        this.degree = degree;
        this.branches = branches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public ArrayList<DegreeBranchModel> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<DegreeBranchModel> branches) {
        this.branches = branches;
    }
}
