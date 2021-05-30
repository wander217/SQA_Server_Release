package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Subject")
public class Subject implements Serializable {
	private static final long serialVersionUID = 8091935293386540694L;

	public Subject() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@OneToMany(mappedBy="subject", cascade = CascadeType.REMOVE)	
	private Set<TermSubject> termSubject = new HashSet<>();
	
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
	
	public Set<TermSubject> getTermSubject() {
		return termSubject;
	}

	public void setTermSubject(Set<TermSubject> termSubject) {
		this.termSubject = termSubject;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
