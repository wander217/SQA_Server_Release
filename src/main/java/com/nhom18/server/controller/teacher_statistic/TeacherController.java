package com.nhom18.server.controller.teacher_statistic;

import com.wander.sqa.controller.registration.dto.RegistrationDTO;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherStatDTO;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherStatRequest;
import com.wander.sqa.controller.registration.service.RegistrationService;
import com.wander.sqa.controller.subject_statistic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private RegistrationService registrationService;

	//Lấy thống kê đăng kí theo giảng viên
	@PostMapping(path = "/registration")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<List<TeacherStatDTO>> getAll(@RequestBody TeacherStatRequest t){
		List<TeacherStatDTO> teacherDTOList = this.teacherService.getAll(t);
		return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
	}

	//Lấy lịch sử đăng kí của giảng viên
	@PostMapping(path = "/history")
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<RegistrationDTO>> findAllByTeacher(@RequestBody TeacherHistoryRequest t){
		List<RegistrationDTO> registrationDTOList = this.registrationService.findAllByTeacher(t);
		return new ResponseEntity<>(registrationDTOList, HttpStatus.OK);
	}
}