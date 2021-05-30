package com.nhom18.server.controller.term.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TermRequest {
    private Date startDate;
    private Date endDate;
    private Timestamp startRegTime;
    private Timestamp endRegTime;

    public TermRequest() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Timestamp getStartRegTime() {
        return startRegTime;
    }

    public void setStartRegTime(Timestamp startRegTime) {
        this.startRegTime = startRegTime;
    }

    public Timestamp getEndRegTime() {
        return endRegTime;
    }

    public void setEndRegTime(Timestamp endRegTime) {
        this.endRegTime = endRegTime;
    }
}
