package pojo;

/**
 * @program: homeplus
 * @description: 管理员的实体类
 * @author: ZEK
 * @create: 2019-04-10 15:06
 **/
public class Admin {

    private int id;
    private String adName;
    private String adPassword;
    private String adPhone;
    private String adSex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    public String getAdPhone() {
        return adPhone;
    }

    public void setAdPhone(String adPhone) {
        this.adPhone = adPhone;
    }

    public String getAdSex() {
        return adSex;
    }

    public void setAdSex(String adSex) {
        this.adSex = adSex;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adName='" + adName + '\'' +
                ", adPassword='" + adPassword + '\'' +
                ", adPhone='" + adPhone + '\'' +
                ", adSex='" + adSex + '\'' +
                '}';
    }
}
