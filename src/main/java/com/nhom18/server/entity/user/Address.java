package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Address")
public class Address implements Serializable {
	private static final long serialVersionUID = -2298109358174803808L;

	public Address() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Street", nullable=false, length=255)	
	private String street;
	
	@Column(name="District", nullable=false, length=255)	
	private String district;
	
	@Column(name="City", nullable=false, length=255)	
	private String city;
	
	@Column(name="Description", nullable=true, length=255)	
	private String description;
	
	@OneToOne(mappedBy="address")	
	private Member member;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
