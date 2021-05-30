package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.*;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.nhom18.server.exception.*;

import java.util.List;

public interface RegistrationService{
	//Tìm kiếm tất cả những đăng kí còn hiệu lực theo môn học
	List<RegistrationDTO> findAllEnableByTermSubject(RegisteredGroupRequest rg);
	//Thực hiện đăng kí môn học
	void doRegistration(RegistrationRequest r)
            throws OverLimitGroupException, DuplicatedTimetableException,
			AssignmentException, OverLimitRegistrationException,
			TermNotFoundException, OutOfRegistrationTimeException;
	//Thực hiện tìm kiếm tất cả đăng kí theo giảng viên
	//Để show lịch sử đăng kí
	List<RegistrationDTO> findAllByTeacher(TeacherHistoryRequest t);
}