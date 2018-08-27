package ac.in.iitr.mdg.convocation.views.Accomodation;

import android.media.Image;

public class hotelProfile {

    private String Name;
    private String Address;
    private Image imageThumb;
    private Image imageMain;
    private String Data;

    public hotelProfile(){

    }

    public hotelProfile(String name,String address,String data,Image imageMain,Image imageThumb){
        this.Name = name;
        this.Address = address;
        this.imageMain = imageMain;
        this.imageThumb = imageThumb;
        this.Data = data;
    }

    public Image getImageMain() {
        return imageMain;
    }

    public Image getImageThumb() {
        return imageThumb;
    }

    public String getData() {
        return Data;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }


    public void setData(String data) {
        Data = data;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setImageMain(Image imageMain) {
        this.imageMain = imageMain;
    }

    public void setImageThumb(Image imageThumb) {
        this.imageThumb = imageThumb;
    }

    public void setName(String name) {
        Name = name;
    }
}
