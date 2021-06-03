package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.AssignedSubjectDAO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.exception.AssignmentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
public class RegistrationServiceTest23 {
    @Autowired
    private RegistrationServiceImpl service;


    @Test
    @Transactional
    @Rollback
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(1);
        Assertions.assertDoesNotThrow(()->{
           service.doRegistration(request);
        });
    }
}
