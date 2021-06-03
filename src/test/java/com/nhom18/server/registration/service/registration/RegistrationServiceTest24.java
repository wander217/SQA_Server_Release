package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.RegistrationDAO;
import com.nhom18.server.dao.SubjectGroupDAO;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.entity.user.Teacher;
import com.nhom18.server.exception.OverLimitGroupException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RegistrationServiceTest24 {
    @Autowired
    private RegistrationServiceImpl service;
    @MockBean
    private RegistrationDAO registrationDAO;
    @MockBean
    private SubjectGroupDAO subjectGroupDAO;

    @BeforeEach
    public void setUp(){
        List<Registration> registrations = new ArrayList<>();
        for (int i=0;i<2;i++){
            Registration r = new Registration();
            r.setId(i);
            Teacher tch =new Teacher();
            tch.setId(2);
            r.setTeacher(tch);
            registrations.add(r);
        }
        Mockito.when(this.registrationDAO.findAllBySubjectGroup(Mockito.anyLong()))
                .thenReturn(registrations);

        SubjectGroup s = new SubjectGroup();
        s.setNumberOfTeacher(2);
        Mockito.when(this.subjectGroupDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(s));
    }

    @Test
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(2);
        Assertions.assertThrows(OverLimitGroupException.class,()->{
           service.doRegistration(request);
        });
    }
}
