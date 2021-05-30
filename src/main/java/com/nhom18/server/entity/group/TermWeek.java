package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TermWeek")
public class TermWeek implements Serializable {
	private static final long serialVersionUID = 1507708429855877054L;

	public TermWeek() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne
	@JoinColumn(name="TermId", referencedColumnName="Id", nullable=false)
	private Term term;
	
	@Column(name="StartDate", nullable=false)	
	private Date startDate;
	
	@Column(name="EndDate", nullable=false)
	private Date endDate;
	
	@OneToMany(mappedBy="termWeek", cascade = CascadeType.REMOVE)	
	private Set<LearningWeek> learningWeek = new HashSet<>();
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<LearningWeek> getLearningWeek() {
		return learningWeek;
	}

	public void setLearningWeek(Set<LearningWeek> learningWeek) {
		this.learningWeek = learningWeek;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
