package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="GroupInfo")
public class GroupInfo implements Serializable {
	private static final long serialVersionUID = -4989137091818265513L;

	public GroupInfo() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne		
	@JoinColumn(name="RoomId", referencedColumnName="Id", nullable=false)
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="SubjectGroupId", referencedColumnName="Id", nullable=false)
	private SubjectGroup subjectGroup;
	
	@ManyToOne
	@JoinColumn(name="ShiftId", referencedColumnName="Id", nullable=false)
	private Shift shift;
	
	@OneToMany(mappedBy="groupInfo", cascade = CascadeType.REMOVE)	
	private Set<LearningWeek> learningWeek = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public SubjectGroup getSubjectGroup() {
		return subjectGroup;
	}

	public void setSubjectGroup(SubjectGroup subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
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
