package com.nhom18.server.entity.group;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="LearningWeek")
public class LearningWeek implements Serializable {
	private static final long serialVersionUID = 5237504270425916650L;

	public LearningWeek() {
	}
	
	@Id	
	@Column(name="Id", nullable=false, length=19)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne
	@JoinColumn(name="TermWeekId", referencedColumnName="Id", nullable=false)
	private TermWeek termWeek;
	
	@ManyToOne	
	@JoinColumn(name="GroupInfoId", referencedColumnName="Id", nullable=false)
	private GroupInfo groupInfo;
	
	@Column(name="IsDesist", nullable=false)	
	private boolean isDesist;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TermWeek getTermWeek() {
		return termWeek;
	}

	public void setTermWeek(TermWeek termWeek) {
		this.termWeek = termWeek;
	}

	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}

	public boolean isDesist() {
		return isDesist;
	}

	public void setDesist(boolean isDesist) {
		this.isDesist = isDesist;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
