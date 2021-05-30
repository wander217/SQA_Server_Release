package com.nhom18.server.controller.account.dto;

import javax.validation.constraints.Pattern;

public class LoginRequest {
    @Pattern(
        regexp = "^[0-9A-Za-z@\\.\\-_\\/]+$",
        message = "Tên đăng nhập chỉ được bao gồm chữ,số hoặc các kí tự như @.-/_ và không được để trống"
    )
    private String username;

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*_])(?=\\S+).{8,}$",
        message = "Mật khẩu phải gồm ít nhất 8 kí tự bao gồm ít nhất 1 chữ cái in thường,"+
        " 1 chữ cái in hoa, 1 số và 1 kí tự đặc biệt !@#$%^&*_"
    )
    private String password;

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
