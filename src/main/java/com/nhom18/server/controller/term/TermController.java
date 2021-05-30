package com.nhom18.server.controller.term;

import com.nhom18.server.controller.term.dto.TermDTO;
import com.nhom18.server.controller.term.dto.TermRequest;

import com.nhom18.server.exception.RegTimeException;
import com.nhom18.server.exception.TermNotFoundException;
import com.nhom18.server.controller.term.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/term")
public class TermController {
    @Autowired
    private TermService termService;

    //Lấy nhóm cuối
    @GetMapping(path = "/last")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<TermDTO> getLast() throws TermNotFoundException {
        TermDTO termDTO = this.termService.getLastTerm();
        return new ResponseEntity<>(termDTO, HttpStatus.OK);
    }

    //Cập nhật thông tin
    @PutMapping(path = "/")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<String> update(@RequestBody TermRequest t)
            throws TermNotFoundException, RegTimeException {
        this.termService.updateTerm(t);
        return new ResponseEntity<>("Cập nhật thành công!",HttpStatus.OK);
    }

    //Xử lý ngoại lệ
    //Khi không có kì mới
    @ExceptionHandler(TermNotFoundException.class)
    public ResponseEntity<String> handleTermNotFoundException(){
        return new ResponseEntity<>("Không có kì mới!",HttpStatus.BAD_REQUEST);
    }

    //Khi thời gian nhâp vào bị lỗi
    @ExceptionHandler(RegTimeException.class)
    public ResponseEntity<String> handleRegTimeException(RegTimeException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
