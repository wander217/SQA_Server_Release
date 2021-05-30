package com.nhom18.server.controller.registration.dto;

public class GroupInfoDTO{
	private long id;
	private String room;
	private String shift;
	private String learningWeek ;
	
	public GroupInfoDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getLearningWeek() {
		return learningWeek;
	}

	public void setLearningWeek(String learningWeek) {
		this.learningWeek = learningWeek;
	}
}