package com.example.benefitalumni1.model;


import cn.bmob.v3.BmobObject;

//失物
public class LostItem extends BmobObject {

    private String lostItemId;//失物id
    private String lostItemName;//失物名称
    private String type;//失物类型
    private String lostTime;//丢失时间
    private String detail;//失物详情描述
    private String userName;
    private String pic;//图片
    private String contact;//联系方式


    public LostItem() {
    }

    public LostItem(String lostItemId, String lostItemName, String type, String lostTime, String detail, String userName, String pic, String contact) {
        this.lostItemId = lostItemId;
        this.lostItemName = lostItemName;
        this.type = type;
        this.lostTime = lostTime;
        this.detail = detail;
        this.userName = userName;
        this.pic = pic;
        this.contact = contact;
    }

    public String getLostItemId() {
        return lostItemId;
    }

    public void setLostItemId(String lostItemId) {
        this.lostItemId = lostItemId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
