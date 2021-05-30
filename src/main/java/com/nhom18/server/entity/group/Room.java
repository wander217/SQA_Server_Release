package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Room")
public class Room implements Serializable {
	private static final long serialVersionUID = 5048172898198842758L;

	public Room() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne	
	@JoinColumn(name="BuildingId", referencedColumnName="Id", nullable=false)
	private Building building;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@OneToMany(mappedBy="room", cascade = CascadeType.REMOVE)	
	private Set<GroupInfo> groupInfo = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
