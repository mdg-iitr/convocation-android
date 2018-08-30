package ac.in.iitr.mdg.convocation.views;

import android.media.Image;

public class ChiefGuestProfile {

    private String Name;
    private String Designation;
    private Image imageThumb;
    private Image imageMain;
    private String Data;
    private String date;

    public ChiefGuestProfile() {

    }

    public ChiefGuestProfile(String name, String designation, String date, String data, Image imageMain, Image imageThumb) {
        this.date = date;
        this.Name = name;
        this.Designation = designation;
        this.imageMain = imageMain;
        this.imageThumb = imageThumb;
        this.Data = data;
    }

    public Image getImageMain() {
        return imageMain;
    }

    public void setImageMain(Image imageMain) {
        this.imageMain = imageMain;
    }

    public Image getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(Image imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

