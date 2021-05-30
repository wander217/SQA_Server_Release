package com.nhom18.server.security;

import com.wander.sqa.dao.AccountDAO;
import com.wander.sqa.entity.user.Account;
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
			SQAUserDetails sqaUserDetail = new SQAUserDetails(account);
			return sqaUserDetail;
		}catch (Exception e){
			e.printStackTrace();
			throw new UsernameNotFoundException("Không tìm thấy tài khoản có tên đăng nhập "+username);
		}
	}
}
