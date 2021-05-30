package com.nhom18.server.entity.registration;

import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.user.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="Registration")
public class Registration implements Serializable {
	private static final long serialVersionUID = -3497322625102664837L;

	public Registration() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne		
	@JoinColumn(name="TeacherMemberId", referencedColumnName="MemberId", nullable=false)
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name="SubjectGroupId", referencedColumnName="Id", nullable=false)
	private SubjectGroup subjectGroup;
	
	@Column(name="RegTime", nullable=false)	
	private Timestamp regTime;
	
	@Column(name="IsEnable", nullable=false)	
	private boolean isEnable;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public SubjectGroup getSubjectGroup() {
		return subjectGroup;
	}

	public void setSubjectGroup(SubjectGroup subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
