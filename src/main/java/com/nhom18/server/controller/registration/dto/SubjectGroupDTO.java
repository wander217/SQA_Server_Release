package com.nhom18.server.controller.registration.dto;

import java.util.ArrayList;
import java.util.List;

public class SubjectGroupDTO {
	private long id;
	private long totalItem;
	private String learningDay;
	private int numberOfTeacher;
	private String code;
	private List<GroupInfoDTO> groupInfo =  new ArrayList<>();
	
	public SubjectGroupDTO() {
		super();
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

	public List<GroupInfoDTO> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(List<GroupInfoDTO> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(long totalItem) {
		this.totalItem = totalItem;
	}
}