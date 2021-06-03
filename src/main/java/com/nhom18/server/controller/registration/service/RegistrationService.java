package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.*;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.nhom18.server.entity.group.GroupInfo;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.exception.*;

import java.util.List;
import java.util.Set;

public interface RegistrationService{
	//Tìm kiếm tất cả những đăng kí còn hiệu lực theo môn học
	List<RegistrationDTO> findAllEnableByTermSubject(RegisteredGroupRequest rg);

	//

	//Kiểm tra xem có tuần nào bị trùng lịch hay không
	public boolean checkDuplicateLearningWeek(Set<LearningWeek> learningWeek, Set<LearningWeek> learningWeek1);
	//Kiểm tra xem có kíp nào bị trùng lịch hay không
	//Nếu có kíp trùng thì kiểm tra tuần học xem có tuần nào trùng hay không
	public boolean checkDuplicateShift(Set<GroupInfo> groupInfo, Set<GroupInfo> groupInfo1);
	//Kiểm tra xem ngày học có bị trùng hay không
	//Nếu ngày học bị trùng thì kiểm tra tiếp xem kíp học có trùng không
	public Registration checkDuplicateTimetable(SubjectGroup subjectGroup, List<Registration> registrationList);
	//Kiểm tra xem có đăng kí quá số nhóm được giao hay không
	public boolean checkOverRegister(AssignedSubject assignedSubject, List<Registration> registrationList);

	//


	//Thực hiện đăng kí môn học
	void doRegistration(RegistrationRequest r)
            throws OverLimitGroupException, DuplicatedTimetableException,
			AssignmentException, OverLimitRegistrationException,
			TermNotFoundException, OutOfRegistrationTimeException;
	//Thực hiện tìm kiếm tất cả đăng kí theo giảng viên
	//Để show lịch sử đăng kí
	List<RegistrationDTO> findAllByTeacher(TeacherHistoryRequest t);
}