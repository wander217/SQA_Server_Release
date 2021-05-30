package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Admin")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="MemberId", referencedColumnName="Id")
public class Admin extends Member implements Serializable {
	private static final long serialVersionUID = 5599324185082901360L;

	public Admin() {
	}
	
	public String toString() {
		return super.toString();
	}
	
}
