package com.example.benefitalumni1.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String realName;
    private String userClass;
    private String QQ;
    private String Tel;

    public User() {
    }

    public User(String realName, String userClass, String QQ, String tel) {
        this.realName = realName;
        this.userClass = userClass;
        this.QQ = QQ;
        Tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
