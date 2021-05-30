package com.nhom18.server.controller.registration.dto;

import javax.validation.constraints.Min;

public class RegisteredGroupRequest {
    @Min(
        value = 1,
        message = "Mã môn học phải là một số nguyên dương"
    )
    private long TermSubjectId;

    public RegisteredGroupRequest() {
    }

    public long getTermSubjectId() {
        return TermSubjectId;
    }

    public void setTermSubjectId(long termSubjectId) {
        TermSubjectId = termSubjectId;
    }
}
