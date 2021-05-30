package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1106176563302836920L;

	public Account() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne	
	@JoinColumn(name="RoleId", referencedColumnName="Id", nullable=false)
	private Role role;
	
	@OneToOne(mappedBy = "account")	
	private Member member;
	
	@Column(name="Username", nullable=false, length=255)	
	private String username;
	
	@Column(name="Password", nullable=false, length=255)	
	private String password;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public String toString() {
		return String.valueOf(getId());
	}
	
}
