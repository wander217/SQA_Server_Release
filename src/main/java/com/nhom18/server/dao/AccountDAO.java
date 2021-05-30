package com.nhom18.server.dao;

import com.wander.sqa.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Long>{
	public Optional<Account> findByUsername(String username);
}