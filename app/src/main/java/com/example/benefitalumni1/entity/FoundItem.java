package com.example.benefitalumni1.entity;

import java.io.Serializable;

//拾物
public class FoundItem implements Serializable {
    private int foundItemId;//拾物id
    private String foundItemName;//拾物名称
    private String type;//拾物类型
    private String foundTime;//捡到时间
    private String detail;//拾物详情描述
    private int userId;//用户Id
    private String pic;//图片
    private String contact;//联系方式

    public FoundItem() {
    }

    public FoundItem(String foundItemName, String type, String foundTime, String detail, int userId, String pic, String contact) {
        this.foundItemName = foundItemName;
        this.type = type;
        this.foundTime = foundTime;
        this.detail = detail;
        this.userId = userId;
        this.pic = pic;
        this.contact = contact;
    }

    public FoundItem(int foundItemId, String foundItemName, String type, String foundTime, String detail, int userId, String pic, String contact) {
        this.foundItemId = foundItemId;
        this.foundItemName = foundItemName;
        this.type = type;
        this.foundTime = foundTime;
        this.detail = detail;
        this.userId = userId;
        this.pic = pic;
        this.contact = contact;
    }

    public int getFoundItemId() {
        return foundItemId;
    }

    public void setFoundItemId(int foundItemId) {
        this.foundItemId = foundItemId;
    }

    public String getFoundItemName() {
        return foundItemName;
    }

    public void setFoundItemName(String foundItemName) {
        this.foundItemName = foundItemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(String foundTime) {
        this.foundTime = foundTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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