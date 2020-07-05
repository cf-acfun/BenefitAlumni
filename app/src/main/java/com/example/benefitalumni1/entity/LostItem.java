package com.example.benefitalumni1.entity;

import java.io.Serializable;

//失物
public class LostItem implements Serializable {
    private int lostItemId;//失物id
    private String lostItemName;//失物名称
    private String type;//失物类型
    private String lostTime;//丢失时间
    private String detail;//失物详情描述
    private int userId;//用户Id
    private String pic;//图片
    private String contact;//联系方式


    public LostItem() {
    }

    public LostItem(String lostItemName, String type, String lostTime, String detail, int userId, String pic,
                    String contact) {
        this.userId = userId;
        this.lostItemName = lostItemName;
        this.type = type;
        this.lostTime = lostTime;
        this.detail = detail;
        this.pic = pic;
        this.contact = contact;
    }

    public LostItem(int lostItemId, int userId, String lostItemName, String type, String lostTime, String detail, String pic, String contact) {
        this.lostItemId = lostItemId;
        this.userId = userId;
        this.lostItemName = lostItemName;
        this.type = type;
        this.lostTime = lostTime;
        this.detail = detail;
        this.pic = pic;
        this.contact = contact;
    }

    public int getLostItemId() {
        return lostItemId;
    }

    public void setLostItemId(int lostItemId) {
        this.lostItemId = lostItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLostItemName() {
        return lostItemName;
    }

    public void setLostItemName(String lostItemName) {
        this.lostItemName = lostItemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLostTime() {
        return lostTime;
    }

    public void setLostTime(String lostTime) {
        this.lostTime = lostTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
