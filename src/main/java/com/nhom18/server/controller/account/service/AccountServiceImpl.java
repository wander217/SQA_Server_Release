package com.nhom18.server.controller.account.service;

import com.nhom18.server.controller.account.dto.AccountDTO;
import com.nhom18.server.controller.account.dto.ForgotRequest;
import com.nhom18.server.controller.account.dto.LoginRequest;
import com.nhom18.server.controller.account.dto.RoleDTO;
import com.nhom18.server.dao.AccountDAO;
import com.nhom18.server.dao.MemberDAO;
import com.nhom18.server.entity.user.Account;
import com.nhom18.server.entity.user.Member;
import com.nhom18.server.exception.PasswordNotMatchException;
import com.nhom18.server.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private JavaMailSender sender;
	private final PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	//Đăng nhập
	@Override
	@Transactional
	public AccountDTO findByUsername(LoginRequest loginRequest)
			throws UsernameNotFoundException, PasswordNotMatchException {
		Account account = this.accountDAO.findByUsername(loginRequest.getUsername())
				.orElseThrow(UsernameNotFoundException::new);
		if(passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
			return this.convertToDTO(account);
		}
		throw new PasswordNotMatchException();
	}

	//Chuyển về dạng dto
	private AccountDTO convertToDTO(Account account){
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setUsername(account.getUsername());
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(account.getRole().getId());
		roleDTO.setName(account.getRole().getName());
		accountDTO.setRole(roleDTO);
		return accountDTO;
	}

	//Quên mật khẩu
	@Override
	@Transactional
	public void doForgot(ForgotRequest forgotRequest) throws UsernameNotFoundException {
		Member member = this.memberDAO.findByUsername(forgotRequest.getUsername())
				.orElseThrow(UsernameNotFoundException::new);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("sqanhom18@gmail.com");
		mail.setTo(member.getEmail());
		mail.setSubject("Xác nhặn quên mật khẩu");
		String newPassword = UUID.randomUUID().toString();
		Account account = member.getAccount();
		account.setPassword(this.passwordEncoder.encode(newPassword));
		mail.setText("Mật khẩu mới là:"+ newPassword);
		sender.send(mail);
	}
}