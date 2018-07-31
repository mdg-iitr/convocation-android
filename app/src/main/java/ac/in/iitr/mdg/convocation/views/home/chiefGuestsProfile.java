package ac.in.iitr.mdg.convocation.views.home;

import android.media.Image;

public class chiefGuestsProfile {

            private int serialNumber;
    private String Name;
    private String Designation;
    private Image imageThumb;
    private Image imageMain;
    private String Data;

            public chiefGuestsProfile(){
        
                    }

            public chiefGuestsProfile(int serialNumber,String name,String designation,String data,Image imageMain,Image imageThumb){
                this.serialNumber = serialNumber;
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

            public int getSerialNumber() {
                return serialNumber;
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

            public void setSerialNumber(int serialNumber) {
                this.serialNumber = serialNumber;
            }
}

