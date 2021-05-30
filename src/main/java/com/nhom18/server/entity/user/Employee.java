package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Employee")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="MemberId", referencedColumnName="Id")
public class Employee extends Member implements Serializable {
	private static final long serialVersionUID = 6560301819318567942L;

	public Employee() {
	}
	
	@Column(name="EmpCode", nullable=false, length=255)	
	private String empCode;
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String toString() {
		return super.toString();
	}	
}
