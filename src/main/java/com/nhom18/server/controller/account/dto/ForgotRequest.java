package com.nhom18.server.controller.account.dto;

import javax.validation.constraints.Pattern;

public class ForgotRequest {
    @Pattern(
            regexp = "^[0-9A-Za-z@\\.\\-_\\/]+$",
            message = "Tên đăng nhập chỉ được bao gồm chữ,số hoặc các kí tự như @.-/_ và không được để trống"
    )
    private String username;

    public ForgotRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
