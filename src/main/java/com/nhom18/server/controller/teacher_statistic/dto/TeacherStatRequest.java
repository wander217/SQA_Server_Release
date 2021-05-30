package com.nhom18.server.controller.teacher_statistic.dto;

import javax.validation.constraints.Pattern;

public class TeacherStatRequest {
    @Pattern(
        regexp = ("^[\\p{L}\\d\\s]*$"),
        message = ("Tên giảng viên chỉ được bao gồm chữ,số,khoảng trắng!")
    )
    private String searchData;

    public TeacherStatRequest() {
    }

    public String getSearchData() {
        return searchData;
    }
}
