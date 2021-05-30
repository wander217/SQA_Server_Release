package com.nhom18.server.controller.registration;

import com.nhom18.server.controller.registration.dto.SubjectGroupDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupRequest;
import com.nhom18.server.controller.registration.service.SubjectGroupService;
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
@RequestMapping(path = "/subjectgroup")
public class SubjectGroupController {
	@Autowired
	private SubjectGroupService subjectGroupService;

	//Lấy nhóm môn học theo môn học
	@PostMapping(path = "/tsfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<SubjectGroupDTO>> findByTermSubject(@Valid @RequestBody SubjectGroupRequest s) {
		List<SubjectGroupDTO> subjectGroupDTOList = this.subjectGroupService.findByTermSubject(s);
		return new ResponseEntity<>(subjectGroupDTOList, HttpStatus.OK);
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