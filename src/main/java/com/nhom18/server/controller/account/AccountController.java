package com.nhom18.server.controller.account;

import com.nhom18.server.controller.account.dto.AccountDTO;
import com.nhom18.server.controller.account.dto.ForgotRequest;
import com.nhom18.server.controller.account.dto.LoginRequest;
import com.nhom18.server.exception.PasswordNotMatchException;
import com.nhom18.server.exception.UsernameNotFoundException;
import com.nhom18.server.controller.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/account")
public class AccountController{
	@Autowired
	private AccountService accountService;

	//Đăng nhập
	@PostMapping(path = "/login")
	public ResponseEntity<AccountDTO> findByUsername(@Valid @RequestBody LoginRequest loginRequest)
			throws UsernameNotFoundException, PasswordNotMatchException{
		AccountDTO ans = accountService.findByUsername(loginRequest);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}

	//Lấy mật khẩu đã mất
	@PostMapping(path = "/forgot")
	public ResponseEntity<String> doForgot(@Valid  @RequestBody ForgotRequest forgotRequest)
			throws UsernameNotFoundException{
		accountService.doForgot(forgotRequest);
		return new ResponseEntity<>("Gửi mail thành công!", HttpStatus.OK);
	}

	//Ngoại lệ khi nhập sai
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		return errors;
	}
}