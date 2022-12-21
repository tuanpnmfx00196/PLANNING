package com.example.actprov4.myclass;

import java.io.Serializable;

public class Passport implements Serializable {
    int id;
    String user, pass, fullname, position;
    String branch;
    String mobile;
    int decentralization;

    public Passport(int id, String user, String pass, String fullname, String position, String branch, String mobile, int decentralization) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.fullname = fullname;
        this.position = position;
        this.branch = branch;
        this.mobile = mobile;
        this.decentralization = decentralization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDecentralization() {
        return decentralization;
    }

    public void setDecentralization(int decentralization) {
        this.decentralization = decentralization;
    }
}
