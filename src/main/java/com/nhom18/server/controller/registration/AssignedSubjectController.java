package com.nhom18.server.controller.registration;

import com.nhom18.server.controller.registration.dto.AssignedSubjectDTO;
import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.exception.OutOfRegistrationTimeException;
import com.nhom18.server.exception.TermNotFoundException;
import com.nhom18.server.controller.registration.service.AssignedSubjectService;
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
@CrossOrigin(origins = "*")
@RequestMapping(path = "/assignedsubject")
public class AssignedSubjectController {
	@Autowired
	private AssignedSubjectService assignedSubjectService;

	//Lấy danh sách các môn đã giao theo giảng viên
	@PostMapping(path = "/tchfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<AssignedSubjectDTO>> findByTeacher(@Valid @RequestBody AssignedSubjectRequest a)
			throws OutOfRegistrationTimeException, TermNotFoundException {
		List<AssignedSubjectDTO> assignedSubjectDTOList = this.assignedSubjectService.findByTeacher(a);
		return new ResponseEntity<>(assignedSubjectDTOList, HttpStatus.OK);
	}

	//Xử lý ngoại lệ
	//Chưa có kì mới
	@ExceptionHandler(TermNotFoundException.class)
	public ResponseEntity<String> handleTermNotFoundException(){
		return new ResponseEntity<>("Chưa có kì mới!",HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn thời gian đăng kí
	@ExceptionHandler(OutOfRegistrationTimeException.class)
	public ResponseEntity<String> handleOutOfRegistrationTimeException(OutOfRegistrationTimeException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
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