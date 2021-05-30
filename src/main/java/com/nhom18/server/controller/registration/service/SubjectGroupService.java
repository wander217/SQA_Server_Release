package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.SubjectGroupDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupRequest;

import java.util.List;

public interface SubjectGroupService{
	//Tìm kiếm nhóm môn học theo môn học được chọn
	List<SubjectGroupDTO> findByTermSubject(SubjectGroupRequest s);
}