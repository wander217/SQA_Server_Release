package com.nhom18.server.controller.registration.dto;

public class AssignedSubjectDTO{
	private long id;
	private String name;
	private int numberOfGroup;
	private long numberOfRegister;

	public AssignedSubjectDTO() {
		super();
	}

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

	public int getNumberOfGroup() {
		return numberOfGroup;
	}

	public void setNumberOfGroup(int numberOfGroup) {
		this.numberOfGroup = numberOfGroup;
	}

	public long getNumberOfRegister() {
		return numberOfRegister;
	}

	public void setNumberOfRegister(long numberOfRegister) {
		this.numberOfRegister = numberOfRegister;
	}
}