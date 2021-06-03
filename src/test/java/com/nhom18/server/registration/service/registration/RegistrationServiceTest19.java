package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.exception.TermNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RegistrationServiceTest19 {
    @Autowired
    private RegistrationServiceImpl service;
    @MockBean
    private TermDAO dao;

    @BeforeEach
    private void setUp(){
        Mockito.when(dao.getLastTerm())
                .thenReturn(Optional.empty());
    }

    @Test
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(1);
        Assertions.assertThrows(TermNotFoundException.class,()->{
           service.doRegistration(request);
        });
    }
}
