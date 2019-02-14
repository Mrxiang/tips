package instruction.waterworld.com.instruction;

public class Bean {
    private int    imgID;
    private String title;
    private String desc;
    private String phone;

    public Bean() {
    }

    public Bean(int imgID ,String title, String desc, String phone) {
        this.imgID =imgID;
        this.title = title;
        this.desc = desc;
        this.phone = phone;
    }

    public int getImgID(){
        return imgID;
    }
    public void setImageID(int imageId ){
        imgID = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
