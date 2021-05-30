package com.nhom18.server.security;

import com.nhom18.server.dao.AccountDAO;
import com.nhom18.server.entity.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SQAUserDetailsService implements UserDetailsService{
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		try{
			Account account = this.accountDAO.findByUsername(username).get();
			SQAUserDetails serverUserDetail = new SQAUserDetails(account);
			return serverUserDetail;
		}catch (Exception e){
			e.printStackTrace();
			throw new UsernameNotFoundException("Không tìm thấy tài khoản có tên đăng nhập "+username);
		}
	}
}
