package com.nhom18.server.controller.registration.dto;

import javax.validation.constraints.Min;

public class AssignedSubjectRequest {
    @Min(
        value = 1,
        message = "Mã giảng viên phải là số dương"
    )
    private long teacherId;
    private String searchData;

    public AssignedSubjectRequest() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }
}
