package com.nhom18.server.controller.subject_statistic.service;

import com.wander.sqa.controller.subject_statistic.dto.SubjectTeacherRequest;
import com.wander.sqa.controller.subject_statistic.dto.SubjectTeacherStatDTO;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherStatDTO;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherStatRequest;
import com.wander.sqa.exception.UsernameNotFoundException;

import java.util.List;

public interface TeacherService{
	long findIdByUsername(String u) throws UsernameNotFoundException;
	List<SubjectTeacherStatDTO> findRemember(SubjectTeacherRequest st);
	List<SubjectTeacherStatDTO> findForgot(SubjectTeacherRequest st);
	List<TeacherStatDTO> getAll(TeacherStatRequest t);
}