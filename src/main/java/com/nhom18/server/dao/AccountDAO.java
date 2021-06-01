package com.nhom18.server.dao;

import com.nhom18.server.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Long>{
	Optional<Account> findByUsername(String username);
}