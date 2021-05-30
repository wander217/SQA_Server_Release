package com.nhom18.server.controller.subject_statistic.dto;

public class TermSubjectStatDTO {
    private long id;
    private String termSubjectName;
    private long remember;
    private long forgot;
    private long totalItem;

    public TermSubjectStatDTO() {
    }

    public String getTermSubjectName() {
        return termSubjectName;
    }

    public void setTermSubjectName(String termSubjectName) {
        this.termSubjectName = termSubjectName;
    }

    public long getRemember() {
        return remember;
    }

    public void setRemember(long remember) {
        this.remember = remember;
    }

    public long getForgot() {
        return forgot;
    }

    public void setForgot(long forgot) {
        this.forgot = forgot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }
}
