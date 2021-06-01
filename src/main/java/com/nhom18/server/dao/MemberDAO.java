package com.nhom18.server.dao;

import com.nhom18.server.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberDAO extends JpaRepository<Member,Long> {
	@Query("SELECT m FROM Member m WHERE m.account.username=:u")
    Optional<Member> findByUsername(@Param("u") String u);
}