package ac.in.iitr.mdg.convocation.views;

/**
 * Created by karthik on 1/8/18.
 */

public class MedalModel {

    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_HOLDER = 1;
    private String medalCategory;
    private MedalHolderModel medalHolder;
    private int type;

    MedalModel(int type, String medalCategory) {
        this.type = type;
        this.medalCategory = medalCategory;
    }

    MedalModel(int type, MedalHolderModel medalHolder) {

        this.type = type;
        this.medalHolder = medalHolder;

    }

    public MedalHolderModel getMedalHolder() {
        return medalHolder;
    }

    public String getMedalCategory() {
        return medalCategory;
    }

    public int getType() {
        return type;
    }
}
