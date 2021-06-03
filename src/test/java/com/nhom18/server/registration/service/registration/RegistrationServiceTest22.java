package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.AssignedSubjectDAO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.exception.AssignmentException;
import com.nhom18.server.exception.OutOfRegistrationTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
public class RegistrationServiceTest22 {
    @Autowired
    private RegistrationServiceImpl service;
    @MockBean
    private TermDAO dao;
    @MockBean
    private AssignedSubjectDAO dao1;

    @BeforeEach
    private void setUp(){
        Term term = new Term();
        term.setId(1);
        term.setStartRegTime(Timestamp.valueOf("2021-05-04 01:00:00"));
        term.setEndRegTime(Timestamp.valueOf("2021-07-17 12:00:00"));
        Mockito.when(dao.getLastTerm())
                .thenReturn(Optional.of(term));

        Mockito.when(dao1.findBySubjectGroupAndTeacher(Mockito.anyLong(),Mockito.anyLong()))
                .thenReturn(null);
    }

    @Test
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(5);
        Assertions.assertThrows(AssignmentException.class,()->{
           service.doRegistration(request);
        });
    }
}
