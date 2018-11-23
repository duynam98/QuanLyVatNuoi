package com.quanlyvatnuoi.admin.quanlyvatnuoi.model;

public class User {

    private String UserName, password, name, phone;

    public User(String userName, String password, String name, String phone) {
        this.UserName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
