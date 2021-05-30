package com.nhom18.server.controller.teacher_statistic.dto;

public class TeacherStatDTO {
    private long id;
    private String fullname;
    private long forgot;
    private long remember;

    public TeacherStatDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getForgot() {
        return forgot;
    }

    public void setForgot(long forgot) {
        this.forgot = forgot;
    }

    public long getRemember() {
        return remember;
    }

    public void setRemember(long remember) {
        this.remember = remember;
    }
}
