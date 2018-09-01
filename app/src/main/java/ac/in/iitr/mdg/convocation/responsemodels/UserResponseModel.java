package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

public class UserResponseModel {

    @SerializedName("isRegistered")
    private boolean isRegistered;
    @SerializedName("name")
    private String name;
    @SerializedName("ph_no")
    private String phoneNumber;
    @SerializedName("token")
    private String token;
    @SerializedName("enr_no")
    private String enrollmentNumber;
    @SerializedName("branch")
    private String branch;
    @SerializedName("email")
    private String email;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("adults")
    private String numAdults;
    @SerializedName("four_wheeler")
    private boolean isFourWheeler;
    @SerializedName("branch_id")
    private int branchId;

    public UserResponseModel(boolean isRegistered, String name, String phoneNumber, String token, String enrollmentNumber, String branch, String email, String profileImage, String numAdults, boolean isFourWheeler, int branchId) {
        this.isRegistered = isRegistered;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.enrollmentNumber = enrollmentNumber;
        this.branch = branch;
        this.email = email;
        this.profileImage = profileImage;
        this.numAdults = numAdults;
        this.isFourWheeler = isFourWheeler;
        this.branchId = branchId;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(String numAdults) {
        this.numAdults = numAdults;
    }

    public boolean isFourWheeler() {
        return isFourWheeler;
    }

    public void setFourWheeler(boolean fourWheeler) {
        isFourWheeler = fourWheeler;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
