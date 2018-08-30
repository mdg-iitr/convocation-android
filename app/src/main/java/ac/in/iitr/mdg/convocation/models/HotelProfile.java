package ac.in.iitr.mdg.convocation.models;

import android.media.Image;

public class HotelProfile {

    private String Name;
    private String Address;
    private Image imageThumb;
    private Image imageMain;
    private String Data;

    public HotelProfile() {

    }

    public HotelProfile(String name, String address, String data, Image imageMain, Image imageThumb) {
        this.Name = name;
        this.Address = address;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
