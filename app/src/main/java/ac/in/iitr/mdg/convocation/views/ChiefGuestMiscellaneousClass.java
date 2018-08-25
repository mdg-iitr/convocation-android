package ac.in.iitr.mdg.convocation.views;

import android.media.Image;

public class ChiefGuestMiscellaneousClass {

    private String title;
private String Data;
private Image thumbImage;
private String mainHead;
private Image mainImage;

    public ChiefGuestMiscellaneousClass(){

            }

    public ChiefGuestMiscellaneousClass(String title, String data, String mainHead, Image mainImage, Image thumbImage){
        this.title = title;
        this.mainHead = mainHead;
        this.thumbImage = thumbImage;
        this.mainImage = mainImage;
        this.Data = data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getData() {
        return Data;
    }

    public Image getThumbImage() {
        return thumbImage;
    }

    public String getMainHead() {
        return mainHead;
    }

    public Image getMainImage() {
        return mainImage;
    }

    public String getTitle() {
        return title;
    }

    public void setMainHead(String mainHead) {
        this.mainHead = mainHead;
    }

    public void setMainImage(Image mainImage) {
        this.mainImage = mainImage;
    }

    public void setThumbImage(Image thumbImage) {
        this.thumbImage = thumbImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
