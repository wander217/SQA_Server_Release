package com.nhom18.server.entity.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Department")
public class Department implements Serializable {
	private static final long serialVersionUID = -7982931197219836612L;

	public Department() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@OneToMany(mappedBy="department",cascade = CascadeType.REMOVE)	
	private Set<Teacher> teacher = new HashSet<>();
	
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

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
