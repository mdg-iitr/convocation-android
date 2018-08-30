package ac.in.iitr.mdg.convocation.models;

import android.graphics.Bitmap;

public class MedalHolderModel {

    private Bitmap medalHolderImage;
    private String medalHolderName;
    private String medalHolderBranch;
    private String medalHolderEnNo;

    public MedalHolderModel(Bitmap medalHolderImage, String medalHolderName, String medalHolderBranch, String medalHolderEnNo) {
        this.medalHolderImage = medalHolderImage;
        this.medalHolderBranch = medalHolderBranch;
        this.medalHolderEnNo = medalHolderEnNo;
        this.medalHolderName = medalHolderName;
    }

    public String getMedalHolderBranch() {
        return medalHolderBranch;
    }

    public String getMedalHolderEnNo() {
        return medalHolderEnNo;
    }

    public String getMedalHolderName() {
        return medalHolderName;
    }

    public Bitmap getMedalHolderImage() {
        return medalHolderImage;
    }
}
