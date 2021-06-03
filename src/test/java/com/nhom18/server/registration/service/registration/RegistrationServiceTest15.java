package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class RegistrationServiceTest15 {
    @MockBean
    private RegistrationServiceImpl service;

    @BeforeEach
    private void setUp(){
        Mockito.when(service.checkDuplicateShift(any(Set.class),any(Set.class)))
                .thenReturn(true);
        Mockito.when(service.checkDuplicateTimetable(any(SubjectGroup.class),any(List.class)))
                .thenCallRealMethod();
    }

    @Test
    public void testCheckDuplicateTimetable(){
        SubjectGroup s = new SubjectGroup();
        s.setLearningDay("Thứ hai");

        List<Registration> registrationList = new ArrayList<>();

        Registration registration1 = new Registration();
        registration1.setId(1);
        SubjectGroup subjectGroup1 =new SubjectGroup();
        subjectGroup1.setLearningDay("Thứ hai");
        registration1.setSubjectGroup(subjectGroup1);
        registrationList.add(registration1);

        Registration registration2 = new Registration();
        registration1.setId(2);
        SubjectGroup subjectGroup2 =new SubjectGroup();
        subjectGroup2.setLearningDay("Thứ ba");
        registration2.setSubjectGroup(subjectGroup2);
        registrationList.add(registration2);

        Assertions.assertEquals(registration1.getSubjectGroup().getLearningDay()
                ,service.checkDuplicateTimetable(s,registrationList).getSubjectGroup().getLearningDay());
    }
}
