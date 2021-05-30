package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Role")
public class Role implements Serializable {
	private static final long serialVersionUID = 5204731687343230156L;

	public Role() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)	
	private Set<Account> account = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	public String toString() {
		return String.valueOf(getId());
	}
}
