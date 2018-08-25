package ac.in.iitr.mdg.convocation.views.home;

import android.media.Image;

public class chiefGuestsProfile {

    private String Name;
    private String Designation;
    private Image imageThumb;
    private Image imageMain;
    private String Data;
    private String date;

            public chiefGuestsProfile(){
        
                    }

            public chiefGuestsProfile(String name,String designation,String date,String data, Image imageMain,Image imageThumb){
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

            public Image getImageThumb() {
                return imageThumb;
            }


            public String getData() {
                return Data;
            }

            public String getDesignation() {
                return Designation;
            }

            public String getName() {
                return Name;
            }

        
            public void setData(String data) {
                Data = data;
            }

            public void setDesignation(String designation) {
                Designation = designation;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

