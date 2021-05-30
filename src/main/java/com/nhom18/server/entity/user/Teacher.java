package com.nhom18.server.entity.user;

import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Teacher")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="MemberId", referencedColumnName="Id")
public class Teacher extends Member implements Serializable {
	private static final long serialVersionUID = 1202637592401384088L;

	public Teacher() {
	}
	
	@Column(name="TchCode", nullable=false, length=255)	
	private String tchCode;
	
	@ManyToOne	
	@JoinColumn(name="DepartmentId", referencedColumnName="Id", nullable=false)
	private Department department;
	
	@OneToMany(mappedBy="teacher",cascade = CascadeType.REMOVE)	
	private Set<AssignedSubject> assignedSubject = new HashSet<>();
	
	@OneToMany(mappedBy="teacher",cascade = CascadeType.REMOVE)	
	private Set<Registration> registration = new HashSet<>();
	
	public String getTchCode() {
		return tchCode;
	}

	public void setTchCode(String tchCode) {
		this.tchCode = tchCode;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<AssignedSubject> getAssignedSubject() {
		return assignedSubject;
	}

	public void setAssignedSubject(Set<AssignedSubject> assignedSubject) {
		this.assignedSubject = assignedSubject;
	}

	public Set<Registration> getRegistration() {
		return registration;
	}

	public void setRegistration(Set<Registration> registration) {
		this.registration = registration;
	}

	public String toString() {
		return super.toString();
	}
	
}
