package pojo;

/**
 * @program: homeplus
 * @description: Customer实体类
 * @author: ZEK
 * @create: 2019-04-09 16:37
 **/
public class Customer {

    private int id;
    private String cmNickname;
    private String cmPassword;
    private String cmName;
    private String cmSex;
    private String cmPhone;
    private String cmAddress;
    private String cmEmail;
    private int cmStatus;
    private String cmHeadPhoto;
    private String cmCardID;
    private String cmCardPhoto;

    public int getCmStatus() {
        return cmStatus;
    }

    public void setCmStatus(int cmStatus) {
        this.cmStatus = cmStatus;
    }

    public String getCmCardID() {
        return cmCardID;
    }

    public void setCmCardID(String cmCardID) {
        this.cmCardID = cmCardID;
    }

    public String getCmCardPhoto() {
        return cmCardPhoto;
    }

    public void setCmCardPhoto(String cmCardPhoto) {
        this.cmCardPhoto = cmCardPhoto;
    }

    public String getCmHeadPhoto() {
        return cmHeadPhoto;
    }

    public void setCmHeadPhoto(String cmHeadPhoto) {
        this.cmHeadPhoto = cmHeadPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmNickname() {
        return cmNickname;
    }

    public void setCmNickname(String cmNickname) {
        this.cmNickname = cmNickname;
    }

    public String getCmPassword() {
        return cmPassword;
    }

    public void setCmPassword(String cmPassword) {
        this.cmPassword = cmPassword;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }

    public String getCmSex() {
        return cmSex;
    }

    public void setCmSex(String cmSex) {
        this.cmSex = cmSex;
    }

    public String getCmPhone() {
        return cmPhone;
    }

    public void setCmPhone(String cmPhone) {
        this.cmPhone = cmPhone;
    }

    public String getCmAddress() {
        return cmAddress;
    }

    public void setCmAddress(String cmAddress) {
        this.cmAddress = cmAddress;
    }

    public String getCmEmail() {
        return cmEmail;
    }

    public void setCmEmail(String cmEmail) {
        this.cmEmail = cmEmail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", cmNickname='" + cmNickname + '\'' +
                ", cmPassword='" + cmPassword + '\'' +
                ", cmName='" + cmName + '\'' +
                ", cmSex='" + cmSex + '\'' +
                ", cmPhone='" + cmPhone + '\'' +
                ", cmAddress='" + cmAddress + '\'' +
                ", cmEmail='" + cmEmail + '\'' +
                '}';
    }
}
