package com.nhom18.server.entity.group;

import com.nhom18.server.entity.registration.AssignedSubject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TermSubject")
public class TermSubject implements Serializable {
	private static final long serialVersionUID = -8858676959641401050L;

	public TermSubject() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne		
	@JoinColumn(name="TermId", referencedColumnName="Id", nullable=false)
	private Term term;
	
	@ManyToOne	
	@JoinColumn(name="SubjectId", referencedColumnName="Id", nullable=false)
	private Subject subject;
	
	@Column(name="Credit", nullable=false, length=10)	
	private int credit;
	
	@OneToMany(mappedBy="termSubject",cascade = CascadeType.REMOVE)	
	private Set<SubjectGroup> subjectGroup = new HashSet<>();
	
	@OneToMany(mappedBy="termSubject",cascade = CascadeType.REMOVE)	
	private Set<AssignedSubject> assignedSubject = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Set<SubjectGroup> getSubjectGroup() {
		return subjectGroup;
	}

	public void setSubjectGroup(Set<SubjectGroup> subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	public Set<AssignedSubject> getAssignedSubject() {
		return assignedSubject;
	}

	public void setAssignedSubject(Set<AssignedSubject> assignedSubject) {
		this.assignedSubject = assignedSubject;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
