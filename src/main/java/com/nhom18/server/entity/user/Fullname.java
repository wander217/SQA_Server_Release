package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Fullname")
public class Fullname implements Serializable {
	private static final long serialVersionUID = -8528391860539660770L;

	public Fullname() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Firstname", nullable=false)
	private String firstname;
	
	@Column(name="Middlename")
	private String middlename;
	
	@Column(name="Lastname", nullable=false)
	private String lastname;
	
	@OneToOne(mappedBy="fullname")	
	private Member member;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
