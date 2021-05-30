package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.AssignedSubjectDTO;
import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.exception.OutOfRegistrationTimeException;
import com.nhom18.server.exception.TermNotFoundException;

import java.util.List;

public interface AssignedSubjectService{
	List<AssignedSubjectDTO> findByTeacher(AssignedSubjectRequest a)
			throws TermNotFoundException, OutOfRegistrationTimeException;
}