package ac.in.iitr.mdg.convocation.views;

import java.util.ArrayList;

/**
 * Created by karthik on 1/8/18.
 */

public class MedalModel {

    private String medalCategory;
    private ArrayList<MedalHolderModel> medalHoldersList;

    public ArrayList<MedalHolderModel> getMedalHoldersList() {
        return medalHoldersList;
    }

    public String getMedalCategory() {
        return medalCategory;
    }
}
