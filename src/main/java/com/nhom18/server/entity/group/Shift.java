package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Shift")
public class Shift implements Serializable {
	private static final long serialVersionUID = 3404869153093592942L;

	public Shift() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@Column(name="StartTime", nullable=false)	
	private Time startTime;
	
	@Column(name="EndTime", nullable=false)	
	private Time endTime;
	
	@OneToMany(mappedBy="shift", cascade = CascadeType.REMOVE)	
	private Set<GroupInfo> groupInfo = new  HashSet<>();
	
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Set<GroupInfo> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(Set<GroupInfo> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
