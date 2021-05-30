package com.nhom18.server.entity.registration;

import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.entity.user.Teacher;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="AssignedSubject")
public class AssignedSubject implements Serializable {
	private static final long serialVersionUID = 7938125699064917036L;

	public AssignedSubject() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne
	@JoinColumn(name="TermSubjectId", referencedColumnName="Id", nullable=false)
	private TermSubject termSubject;
	
	@ManyToOne
	@JoinColumn(name="TeacherMemberId", referencedColumnName="MemberId", nullable=false)
	private Teacher teacher;
	
	@Column(name="NumberOfGroup", nullable=false, length=10)	
	private int numberOfGroup;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TermSubject getTermSubject() {
		return termSubject;
	}

	public void setTermSubject(TermSubject termSubject) {
		this.termSubject = termSubject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getNumberOfGroup() {
		return numberOfGroup;
	}

	public void setNumberOfGroup(int numberOfGroup) {
		this.numberOfGroup = numberOfGroup;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
