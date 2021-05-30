package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Building")
public class Building implements Serializable {
	private static final long serialVersionUID = -1258658494410629841L;

	public Building() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="Name", nullable=false, length=255)	
	private String name;
	
	@OneToMany(mappedBy="building", cascade = CascadeType.REMOVE)	
	private Set<Room> room = new HashSet<>();
	
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

	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
