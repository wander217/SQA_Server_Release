package com.nhom18.server.entity.group;

import com.nhom18.server.entity.registration.Registration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="SubjectGroup")
public class SubjectGroup implements Serializable {
	private static final long serialVersionUID = 4909701757209853846L;

	public SubjectGroup() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne	
	@JoinColumn(name="TermSubjectId", referencedColumnName="Id", nullable=false)
	private TermSubject termSubject;
	
	@Column(name="LearningDay", nullable=false)
	private String learningDay;
	
	@Column(name="NumberOfTeacher", nullable=false, length=10)	
	private int numberOfTeacher;

	@Column(name="code", nullable=false,length = 255)
	private String code;

	@OneToMany(mappedBy="subjectGroup",cascade = CascadeType.REMOVE)	
	private Set<GroupInfo> groupInfo = new HashSet<>();
	
	@OneToMany(mappedBy="subjectGroup",cascade = CascadeType.REMOVE)	
	private Set<Registration> registration = new HashSet<>();
	
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

	public String getLearningDay() {
		return learningDay;
	}

	public void setLearningDay(String learningDay) {
		this.learningDay = learningDay;
	}

	public int getNumberOfTeacher() {
		return numberOfTeacher;
	}

	public void setNumberOfTeacher(int numberOfTeacher) {
		this.numberOfTeacher = numberOfTeacher;
	}

	public Set<GroupInfo> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(Set<GroupInfo> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public Set<Registration> getRegistration() {
		return registration;
	}

	public void setRegistration(Set<Registration> registration) {
		this.registration = registration;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
