package ac.in.iitr.mdg.convocation.viewmodels;

import ac.in.iitr.mdg.convocation.responsemodels.UserResponseModel;

public class MedalViewModel {

    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_HOLDER = 1;

    private int type;
    private String medalCategory;
    private UserResponseModel userModel;

    public MedalViewModel(int type, String medalCategory, UserResponseModel userModel) {
        this.type = type;
        this.medalCategory = medalCategory;
        this.userModel = userModel;
    }

    public int getType() {
        return type;
    }

    public String getMedalCategory() {
        return medalCategory;
    }

    public UserResponseModel getUserModel() {
        return userModel;
    }
}
