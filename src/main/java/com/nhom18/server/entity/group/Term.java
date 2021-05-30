package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Term")
public class Term implements Serializable {
	private static final long serialVersionUID = -6305730163902920204L;

	public Term() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="StartDate", nullable=false)
	private Date startDate;
	
	@Column(name="EndDate", nullable=false)
	private Date endDate;
	
	@Column(name="StartRegTime", nullable=false)	
	private Timestamp startRegTime;
	
	@Column(name="EndRegTime", nullable=false)	
	private Timestamp endRegTime;
	
	@OneToMany(mappedBy="term", cascade = CascadeType.REMOVE)	
	private Set<TermSubject> termSubject = new HashSet<>();
	
	@OneToMany(mappedBy="term",cascade = CascadeType.REMOVE)	
	private Set<TermWeek> termWeek = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Timestamp getStartRegTime() {
		return startRegTime;
	}

	public void setStartRegTime(Timestamp startRegTime) {
		this.startRegTime = startRegTime;
	}

	public Timestamp getEndRegTime() {
		return endRegTime;
	}

	public void setEndRegTime(Timestamp endRegTime) {
		this.endRegTime = endRegTime;
	}

	public Set<TermSubject> getTermSubject() {
		return termSubject;
	}

	public void setTermSubject(Set<TermSubject> termSubject) {
		this.termSubject = termSubject;
	}

	public Set<TermWeek> getTermWeek() {
		return termWeek;
	}

	public void setTermWeek(Set<TermWeek> termWeek) {
		this.termWeek = termWeek;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
