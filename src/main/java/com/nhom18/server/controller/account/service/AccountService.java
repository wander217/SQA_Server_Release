package com.nhom18.server.controller.account.service;

import com.nhom18.server.controller.account.dto.AccountDTO;
import com.nhom18.server.controller.account.dto.ForgotRequest;
import com.nhom18.server.controller.account.dto.LoginRequest;
import com.nhom18.server.exception.PasswordNotMatchException;
import com.nhom18.server.exception.UsernameNotFoundException;

public interface AccountService{
	AccountDTO findByUsername(LoginRequest loginRequest)
			throws UsernameNotFoundException,PasswordNotMatchException;
	void doForgot(ForgotRequest forgotRequest)
			throws UsernameNotFoundException;
}