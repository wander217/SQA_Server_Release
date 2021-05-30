package com.nhom18.server.controller.subject_statistic.service;

import com.wander.sqa.controller.subject_statistic.dto.SubjectRequest;
import com.wander.sqa.controller.subject_statistic.dto.TermSubjectStatDTO;

import java.util.List;

public interface TermSubjectService {
    public List<TermSubjectStatDTO> getAll(SubjectRequest s);
}
