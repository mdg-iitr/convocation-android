package ac.in.iitr.mdg.convocation.responsemodels;

import com.google.gson.annotations.SerializedName;

public class ChiefGuestResponse {

    @SerializedName("designation")
    private String designation;
    @SerializedName("name")
    private String name;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String image;
    @SerializedName("id")
    private int id;
    @SerializedName("subtitle")
    private String subtitle;

    public ChiefGuestResponse(String designation, String name, String bio, String image, int id, String subtitle) {
        this.designation = designation;
        this.name = name;
        this.bio = bio;
        this.image = image;
        this.id = id;
        this.subtitle = subtitle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
