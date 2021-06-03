package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.dao.AssignedSubjectDAO;
import com.nhom18.server.dao.RegistrationDAO;
import com.nhom18.server.dao.SubjectGroupDAO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.entity.user.Teacher;
import com.nhom18.server.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RegistrationServiceTest25 {
    @Autowired
    private RegistrationServiceImpl service;
    @MockBean
    private RegistrationDAO registrationDAO;
    @MockBean
    private SubjectGroupDAO subjectGroupDAO;
    @MockBean
    private AssignedSubjectDAO assignedSubjectDAO;

    @BeforeEach
    public void setUp() throws OverLimitGroupException, AssignmentException,
            OutOfRegistrationTimeException, TermNotFoundException,
            DuplicatedTimetableException, OverLimitRegistrationException {
        List<Registration> registrations = new ArrayList<>();
        for (int i=0;i<1;i++){
            Registration r = new Registration();
            r.setId(i);
            Teacher tch =new Teacher();
            tch.setId(2);
            r.setTeacher(tch);
            registrations.add(r);
        }
        Mockito.when(this.registrationDAO.findAllBySubjectGroup(Mockito.anyLong()))
                .thenReturn(registrations);

        AssignedSubject a = new AssignedSubject();
        TermSubject t = new TermSubject();
        t.setId(1);
        a.setTermSubject(t);
        a.setNumberOfGroup(2);

        Mockito.when(assignedSubjectDAO.findBySubjectGroupAndTeacher(Mockito.anyLong(),Mockito.anyLong()))
                .thenReturn(a);

        List<Registration> registrationList = new ArrayList<>();
        for(int i=0;i<2;i++){
            Registration registration1 = new Registration();
            SubjectGroup subjectGroup1 =new SubjectGroup();
            subjectGroup1.setTermSubject(t);
            registration1.setSubjectGroup(subjectGroup1);
            registrationList.add(registration1);
        }
        Mockito.when(this.registrationDAO
                .findAllEnableByTeacher(Mockito.anyLong(),Mockito.anyLong()))
                .thenReturn(registrationList);
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
        Assertions.assertThrows(OverLimitRegistrationException.class,()->{
           service.doRegistration(request);
        });
    }
}
