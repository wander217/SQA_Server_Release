package com.nhom18.server.controller.registration;

import com.nhom18.server.controller.registration.dto.RegisteredGroupRequest;
import com.nhom18.server.controller.registration.dto.RegistrationDTO;
import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.exception.*;
import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.controller.subject_statistic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/registration")
public class RegistrationController{
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private TeacherService teacherService;

	//Lấy danh sách đã đăng kí theo môn được chọn
	@PostMapping(path = "/tsfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<RegistrationDTO>> findAllEnableByTermSubject(@Valid @RequestBody RegisteredGroupRequest rg) {
		List<RegistrationDTO> registrationDTOList = this.registrationService.findAllEnableByTermSubject(rg);
		return new ResponseEntity<>(registrationDTOList, HttpStatus.OK);
	}

	//Admin đăng kí cho giảng viên
	@PostMapping(path = "/adminreg")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<String> adminRegistration(@Valid @RequestBody RegistrationRequest r)
			throws DuplicatedTimetableException, OverLimitGroupException,
			AssignmentException, OverLimitRegistrationException,
			OutOfRegistrationTimeException, TermNotFoundException {
		this.registrationService.doRegistration(r);
		return new ResponseEntity<>("Đăng kí thành công",HttpStatus.OK);
	}

	//Giảng viên tự đăng kí
	//Cần xác thực thêm id bằng cách lấy lên lại từ tên đăng nhập
	@PostMapping(path = "/teacherreg")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<String> teacherRegistration(@Valid @RequestBody RegistrationRequest r,Authentication authentication)
			throws UsernameNotFoundException, DuplicatedTimetableException, OverLimitGroupException,
			AssignmentException, OverLimitRegistrationException,
			OutOfRegistrationTimeException, TermNotFoundException {
		long id = this.teacherService.findIdByUsername(authentication.getName());
		r.setTeacherId(id);
		this.registrationService.doRegistration(r);
		return new ResponseEntity<>("Đăng kí thành công",HttpStatus.OK);
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

	//Đăng kí môn không được giao
	@ExceptionHandler(AssignmentException.class)
	public ResponseEntity<String> handleAssignmentException(){
		return new ResponseEntity<>("Không được phép đăng kí môn học này!",HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn được giao
	@ExceptionHandler(OverLimitRegistrationException.class)
	public ResponseEntity<String> handleOverLimitRegistrationException(OverLimitRegistrationException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn của nhóm
	@ExceptionHandler(OverLimitGroupException.class)
	public ResponseEntity<String> handleOverLimitGroupException(OverLimitGroupException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	//Đăng kí bị trùng lịch với đăng kí trước đó
	@ExceptionHandler(DuplicatedTimetableException.class)
	public ResponseEntity<String> handleDuplicatedTimetableException(DuplicatedTimetableException e){
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