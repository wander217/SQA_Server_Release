package com.nhom18.server.controller.subject_statistic.dto;

public class SubjectTeacherStatDTO{
    private long id;
    private String code;
    private String fullname;
    private long totalItem;

    public SubjectTeacherStatDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }
}
