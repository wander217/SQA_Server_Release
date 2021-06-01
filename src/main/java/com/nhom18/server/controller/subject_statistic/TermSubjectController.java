package com.nhom18.server.controller.subject_statistic;

import com.nhom18.server.controller.subject_statistic.dto.SubjectRequest;
import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherRequest;
import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherStatDTO;
import com.nhom18.server.controller.subject_statistic.dto.TermSubjectStatDTO;
import com.nhom18.server.controller.subject_statistic.service.TermSubjectService;
import com.nhom18.server.controller.subject_statistic.service.TeacherService;
import com.nhom18.server.exception.TermNotFoundException;
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
@RequestMapping(path = "/termsubject")
public class TermSubjectController {
    @Autowired
    private TermSubjectService termSubjectService;
    @Autowired
    private TeacherService teacherService;

    //Lấy danh sách các môn
    @PostMapping (path = "/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<TermSubjectStatDTO>> getAll(@Valid @RequestBody SubjectRequest s)
            throws TermNotFoundException {
        List<TermSubjectStatDTO> termSubjectDTOList = this.termSubjectService.getAll(s);
        return new ResponseEntity<>(termSubjectDTOList, HttpStatus.OK);
    }

    //Lấy danh sách đăng ký đủ theo môn
    @PostMapping(path = "/rfind")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<SubjectTeacherStatDTO>> findRemember(@Valid @RequestBody SubjectTeacherRequest st) {
        List<SubjectTeacherStatDTO> teacherDTOList = this.teacherService.findRemember(st);
        return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
    }

    //Lấy danh sách đăng kí thiếu theo môn
    @PostMapping(path = "/ffind")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<SubjectTeacherStatDTO>> findForgot(@Valid @RequestBody SubjectTeacherRequest st) {
        List<SubjectTeacherStatDTO> teacherDTOList = this.teacherService.findForgot(st);
        return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
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

    //Chưa có kì mới
    @ExceptionHandler(TermNotFoundException.class)
    public ResponseEntity<String> handleTermNotFoundException(){
        return new ResponseEntity<>("Chưa có kì mới!",HttpStatus.BAD_REQUEST);
    }
}
