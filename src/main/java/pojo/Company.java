package pojo;

/**
 * @program: homeplus
 * @description: 公司的实体类
 * @author: ZEK
 * @create: 2019-04-15 15:55
 **/
public class Company {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String busRegNum;
    private String busLicPhoto;
    private int companyStatus;

    public int getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(int companyStatus) {
        this.companyStatus = companyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusRegNum() {
        return busRegNum;
    }

    public void setBusRegNum(String busRegNum) {
        this.busRegNum = busRegNum;
    }

    public String getBusLicPhoto() {
        return busLicPhoto;
    }

    public void setBusLicPhoto(String busLicPhoto) {
        this.busLicPhoto = busLicPhoto;
    }
}
