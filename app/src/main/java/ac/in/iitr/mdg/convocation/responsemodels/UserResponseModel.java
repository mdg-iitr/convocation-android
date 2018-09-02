package ac.in.iitr.mdg.convocation.responsemodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserResponseModel implements Parcelable {

    public static final Creator<UserResponseModel> CREATOR = new Creator<UserResponseModel>() {
        @Override
        public UserResponseModel createFromParcel(Parcel in) {
            return new UserResponseModel(in);
        }

        @Override
        public UserResponseModel[] newArray(int size) {
            return new UserResponseModel[size];
        }
    };
    @SerializedName("registered")
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
    @SerializedName("tshirt_size")
    private int tShirtSize;
    @SerializedName("address")
    private String address;
    @SerializedName("transaction_id")
    private String transactionId;

    public UserResponseModel(boolean isRegistered, String name, String phoneNumber, String token, String enrollmentNumber, String branch, String email, String profileImage, String numAdults, boolean isFourWheeler, int branchId, int tShirtSize, String address, String transactionId) {
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
        this.tShirtSize = tShirtSize;
        this.address = address;
        this.transactionId = transactionId;
    }

    protected UserResponseModel(Parcel in) {
        isRegistered = in.readByte() != 0;
        name = in.readString();
        phoneNumber = in.readString();
        token = in.readString();
        enrollmentNumber = in.readString();
        branch = in.readString();
        email = in.readString();
        profileImage = in.readString();
        numAdults = in.readString();
        isFourWheeler = in.readByte() != 0;
        branchId = in.readInt();
        tShirtSize = in.readInt();
        address = in.readString();
        transactionId = in.readString();
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

    public int gettShirtSize() {
        return tShirtSize;
    }

    public void settShirtSize(int tShirtSize) {
        this.tShirtSize = tShirtSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isRegistered ? 1 : 0));
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeString(token);
        parcel.writeString(enrollmentNumber);
        parcel.writeString(branch);
        parcel.writeString(email);
        parcel.writeString(profileImage);
        parcel.writeString(numAdults);
        parcel.writeByte((byte) (isFourWheeler ? 1 : 0));
        parcel.writeInt(branchId);
        parcel.writeInt(tShirtSize);
        parcel.writeString(address);
        parcel.writeString(transactionId);
    }
}
