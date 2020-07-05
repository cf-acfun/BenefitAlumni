package com.example.benefitalumni1.entity;

import java.io.Serializable;

//用户
public class User implements Serializable {
    private int userId;// 用户id
    private String userName;// 用户名
    private String pwd;// 密码
    private String name;// 姓名

    public User() {
        super();
    }

    public User(int userId, String userName, String pwd, String name) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.name = name;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}