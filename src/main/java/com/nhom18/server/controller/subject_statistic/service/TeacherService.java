package com.nhom18.server.controller.subject_statistic.service;

import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherRequest;
import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherStatDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatRequest;
import com.nhom18.server.exception.UsernameNotFoundException;

import java.util.List;

public interface TeacherService{
	long findIdByUsername(String u) throws UsernameNotFoundException;
	List<SubjectTeacherStatDTO> findRemember(SubjectTeacherRequest st);
	List<SubjectTeacherStatDTO> findForgot(SubjectTeacherRequest st);
	List<TeacherStatDTO> getAll(TeacherStatRequest t);
}