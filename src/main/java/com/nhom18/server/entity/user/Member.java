package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Member")
@Inheritance(strategy=InheritanceType.JOINED)
public class Member implements Serializable {
	private static final long serialVersionUID = -1903353401730174842L;

	public Member() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@OneToOne(cascade = CascadeType.REMOVE)	
	@JoinColumn(name="AddressId", referencedColumnName="Id", nullable=false)	
	private Address address;
	
	@OneToOne(cascade = CascadeType.REMOVE)	
	@JoinColumn(name="FullnameId", referencedColumnName="Id", nullable=false)
	private Fullname fullname;
	
	@Column(name="Email", nullable=false, length=255)	
	private String email;
	
	@Column(name="Phone", nullable=false, length=255)	
	private String phone;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="AccountId", referencedColumnName="Id", nullable=false)
	private Account account;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Fullname getFullname() {
		return fullname;
	}

	public void setFullname(Fullname fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
