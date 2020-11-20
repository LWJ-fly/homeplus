package pojo;

import java.util.Date;

/**
 * @program: homeplus
 * @description: 订单的实体类
 * @author: ZEK
 * @create: 2019-04-17 16:26
 **/
public class Order {
    private int id;
    private int orderStatus;
    private Date orderBeginTime;
    private Date orderEndTime;
    private String orderPhone;
    private int orderDur;
    private String orderAddress;
    private int orderMoney;
    private int hkID;
    private int cmID;
    private String hkName;
    private String cmName;
    private int typeID;

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getOrderDur() {
        return orderDur;
    }

    public void setOrderDur(int orderDur) {
        this.orderDur = orderDur;
    }


    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderBeginTime() {
        return orderBeginTime;
    }

    public void setOrderBeginTime(Date orderBeginTime) {
        this.orderBeginTime = orderBeginTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getHkID() {
        return hkID;
    }

    public void setHkID(int hkID) {
        this.hkID = hkID;
    }

    public int getCmID() {
        return cmID;
    }

    public void setCmID(int cmID) {
        this.cmID = cmID;
    }

    public String getHkName() {
        return hkName;
    }

    public void setHkName(String hkName) {
        this.hkName = hkName;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }
}
