package com.example.actprov4.myclass;

import java.io.Serializable;

public class Conclusion implements Serializable {
    int id;
    String branch, typeOfMeetings, conclusion, datecreate, deadline, chair,doer, observer;
    int status;
    String closetime;

    public Conclusion(int id, String branch, String typeOfMeetings, String conclusion, String datecreate, String deadline, String chair, String doer, String observer, int status, String closetime) {
        this.id = id;
        this.branch = branch;
        this.typeOfMeetings = typeOfMeetings;
        this.conclusion = conclusion;
        this.datecreate = datecreate;
        this.deadline = deadline;
        this.chair = chair;
        this.doer = doer;
        this.observer = observer;
        this.status = status;
        this.closetime = closetime;
    }

    public int getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfMeetings() {
        return typeOfMeetings;
    }

    public void setTypeOfMeetings(String typeOfMeetings) {
        this.typeOfMeetings = typeOfMeetings;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getDoer() {
        return doer;
    }

    public void setDoer(String doer) {
        this.doer = doer;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }
}
