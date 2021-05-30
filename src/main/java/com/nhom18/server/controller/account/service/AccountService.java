package com.nhom18.server.controller.account.service;

import com.wander.sqa.controller.account.dto.AccountDTO;
import com.wander.sqa.controller.account.dto.ForgotRequest;
import com.wander.sqa.controller.account.dto.LoginRequest;
import com.wander.sqa.exception.PasswordNotMatchException;
import com.wander.sqa.exception.UsernameNotFoundException;

public interface AccountService{
	AccountDTO findByUsername(LoginRequest loginRequest)
			throws UsernameNotFoundException,PasswordNotMatchException;
	void doForgot(ForgotRequest forgotRequest)
			throws UsernameNotFoundException;
}