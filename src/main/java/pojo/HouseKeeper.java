package pojo;

import java.util.List;

/**
 * @program: homeplus
 * @description: 家政人员实体类
 * @author: ZEK
 * @create: 2019-04-10 11:30
 **/
public class HouseKeeper {

    private int id;
    private String hkNickname;
    private String hkName;
    private String hkSex;
    private String hkPhone;
    private String hkPassword;
    private String hkSlogan;
    private String hkDesc;
    private String hkDescDetail;
    private String hkLabel;
    private String hkHeadphoto;
    private String hkEmail;
    private String hkCardID;
    private String typeList;
    private int hkStatus;
    private String hkCardPhoto;
    private int companyID;
    private String companyName;
    private int hkSettle;
    private int hkAppStatus;
    private String hkCertifyPhoto;

    public String getHkCertifyPhoto() {
        return hkCertifyPhoto;
    }

    public void setHkCertifyPhoto(String hkCertifyPhoto) {
        this.hkCertifyPhoto = hkCertifyPhoto;
    }

    public int getHkAppStatus() {
        return hkAppStatus;
    }

    public void setHkAppStatus(int hkAppStatus) {
        this.hkAppStatus = hkAppStatus;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public int getHkSettle() {
        return hkSettle;
    }

    public void setHkSettle(int hkSettle) {
        this.hkSettle = hkSettle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private int typeDetailID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHkNickname() {
        return hkNickname;
    }

    public void setHkNickname(String hkNickname) {
        this.hkNickname = hkNickname;
    }

    public String getHkName() {
        return hkName;
    }

    public void setHkName(String hkName) {
        this.hkName = hkName;
    }

    public String getHkSex() {
        return hkSex;
    }

    public void setHkSex(String hkSex) {
        this.hkSex = hkSex;
    }

    public String getHkPhone() {
        return hkPhone;
    }

    public void setHkPhone(String hkPhone) {
        this.hkPhone = hkPhone;
    }

    public String getHkPassword() {
        return hkPassword;
    }

    public void setHkPassword(String hkPassword) {
        this.hkPassword = hkPassword;
    }

    public String getHkHeadphoto() {
        return hkHeadphoto;
    }

    public void setHkHeadphoto(String hkHeadphoto) {
        this.hkHeadphoto = hkHeadphoto;
    }

    public String getHkLabel() {
        return hkLabel;
    }

    public void setHkLabel(String hkLabel) {
        this.hkLabel = hkLabel;
    }

    public int getHkStatus() {
        return hkStatus;
    }

    public void setHkStatus(int hkStatus) {
        this.hkStatus = hkStatus;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getHkSlogan() {
        return hkSlogan;
    }

    public void setHkSlogan(String hkSlogan) {
        this.hkSlogan = hkSlogan;
    }

    public String getHkDesc() {
        return hkDesc;
    }

    public void setHkDesc(String hkDesc) {
        this.hkDesc = hkDesc;
    }

    public String getHkDescDetail() {
        return hkDescDetail;
    }

    public void setHkDescDetail(String hkDescDetail) {
        this.hkDescDetail = hkDescDetail;
    }

    public String getHkEmail() {
        return hkEmail;
    }

    public void setHkEmail(String hkEmail) {
        this.hkEmail = hkEmail;
    }

    public String getHkCardID() {
        return hkCardID;
    }

    public void setHkCardID(String hkCardID) {
        this.hkCardID = hkCardID;
    }

    public String getHkCardPhoto() {
        return hkCardPhoto;
    }

    public void setHkCardPhoto(String hkCardPhoto) {
        this.hkCardPhoto = hkCardPhoto;
    }

    public int getTypeDetailID() {
        return typeDetailID;
    }

    public void setTypeDetailID(int typeDetailID) {
        this.typeDetailID = typeDetailID;
    }
}
