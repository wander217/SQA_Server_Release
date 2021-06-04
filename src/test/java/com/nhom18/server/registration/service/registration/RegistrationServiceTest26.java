package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.AssignedSubjectDAO;
import com.nhom18.server.dao.RegistrationDAO;
import com.nhom18.server.dao.SubjectGroupDAO;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.entity.user.Teacher;
import com.nhom18.server.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RegistrationServiceTest26 {
    @Autowired
    private RegistrationServiceImpl service;
    @Autowired
    private RegistrationDAO dao;

    @Test
    @Transactional
    @Rollback
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(2);
        request.setSubjectGroupId(3);
        Assertions.assertDoesNotThrow(()->{
           service.doRegistration(request);
           Sort s = Sort.by("id").descending();
           Pageable page = PageRequest.of(0,1,s);
           Registration r = dao.findAll(page).toList().get(0);
           Assertions.assertEquals(2,r.getTeacher().getId());
           Assertions.assertEquals(3,r.getSubjectGroup().getId());
        });
    }
}
