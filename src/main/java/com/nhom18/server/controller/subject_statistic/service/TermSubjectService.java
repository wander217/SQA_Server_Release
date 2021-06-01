package com.nhom18.server.controller.subject_statistic.service;

import com.nhom18.server.controller.subject_statistic.dto.SubjectRequest;
import com.nhom18.server.controller.subject_statistic.dto.TermSubjectStatDTO;
import com.nhom18.server.exception.TermNotFoundException;

import java.util.List;

public interface TermSubjectService {
    List<TermSubjectStatDTO> getAll(SubjectRequest s) throws TermNotFoundException;
}
