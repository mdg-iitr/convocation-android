package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DegreeBranchModel {

    @SerializedName("branch_name")
    private String branchName;
    @SerializedName("branch")
    private String branchShortName;
    @SerializedName("no_of_students")
    private String noOfStudents;
    @SerializedName("id")
    private int id;
    @SerializedName("students")
    private ArrayList<UserResponseModel> students;

    public DegreeBranchModel(String branchName, String branchShortName, String noOfStudents, int id, ArrayList<UserResponseModel> students) {
        this.branchName = branchName;
        this.branchShortName = branchShortName;
        this.noOfStudents = noOfStudents;
        this.id = id;
        this.students = students;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchShortName() {
        return branchShortName;
    }

    public void setBranchShortName(String branchShortName) {
        this.branchShortName = branchShortName;
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<UserResponseModel> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<UserResponseModel> students) {
        this.students = students;
    }
}
