package com.nhom18.server.controller.teacher_statistic;

import com.nhom18.server.controller.registration.dto.RegistrationDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatRequest;
import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.controller.subject_statistic.service.TeacherService;
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
	public ResponseEntity<List<TeacherStatDTO>> getAll(@Valid @RequestBody TeacherStatRequest t){
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

	//Ngoại lệ khi nhập sai
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		return errors;
	}
}