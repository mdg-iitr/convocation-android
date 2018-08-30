package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;

/**
 * Created by karthik on 1/8/18.
 */

public class MedalHolderModel {

    public Bitmap medalHolderImage;
    public String medalHolderName;
    public String medalHolderBranch;
    public String medalHolderEnNo;

    MedalHolderModel(Bitmap medalHolderImage, String medalHolderName, String medalHolderBranch, String medalHolderEnNo) {

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
