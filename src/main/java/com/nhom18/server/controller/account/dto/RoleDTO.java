package com.nhom18.server.controller.account.dto;

public class RoleDTO{
	private long id;
	private String name;

	public RoleDTO() {
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
}