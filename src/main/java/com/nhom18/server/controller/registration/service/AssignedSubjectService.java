package com.nhom18.server.controller.registration.service;

import com.wander.sqa.controller.registration.dto.AssignedSubjectDTO;
import com.wander.sqa.controller.registration.dto.AssignedSubjectRequest;
import com.wander.sqa.exception.OutOfRegistrationTimeException;
import com.wander.sqa.exception.TermNotFoundException;

import java.util.List;

public interface AssignedSubjectService{
	List<AssignedSubjectDTO> findByTeacher(AssignedSubjectRequest a)
			throws TermNotFoundException, OutOfRegistrationTimeException;
}