package com.example.benefitalumni1.model;

import cn.bmob.v3.BmobObject;

//拾物
public class FoundItem extends BmobObject {

    private String foundItemId;//拾物id
    private String foundItemName;//拾物名称
    private String type;//拾物类型
    private String foundTime;//捡到时间
    private String detail;//拾物详情描述
    private String userName;//用户Id
    private String pic;//图片
    private String contact;//联系方式

    public FoundItem() {
    }

    public FoundItem(String foundItemId, String foundItemName, String type, String foundTime, String detail, String userName, String pic, String contact) {
        this.foundItemId = foundItemId;
        this.foundItemName = foundItemName;
        this.type = type;
        this.foundTime = foundTime;
        this.detail = detail;
        this.userName = userName;
        this.pic = pic;
        this.contact = contact;
    }

    public String getFoundItemId() {
        return foundItemId;
    }

    public void setFoundItemId(String foundItemId) {
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