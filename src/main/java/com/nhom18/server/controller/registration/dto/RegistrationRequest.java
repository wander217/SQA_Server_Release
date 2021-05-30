package com.nhom18.server.controller.registration.dto;

import javax.validation.constraints.Min;

public class RegistrationRequest {
    @Min(
        value = 1,
        message = "Mã giảng viên phải là số dương"
    )
    private long teacherId;
    @Min(
        value = 1,
        message = "Mã nhóm môn học phải là số dương"
    )
    private long subjectGroupId;

    public RegistrationRequest() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(long subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }
}
