package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RegistrationServiceTest13 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateTimetable(){
        SubjectGroup s = new SubjectGroup();
        s.setLearningDay("Thứ tư");

        List<Registration> registrationList = new ArrayList<>();

        Registration registration1 = new Registration();
        SubjectGroup subjectGroup1 =new SubjectGroup();
        subjectGroup1.setLearningDay("Thứ hai");
        registration1.setSubjectGroup(subjectGroup1);
        registrationList.add(registration1);

        Registration registration2 = new Registration();
        SubjectGroup subjectGroup2 =new SubjectGroup();
        subjectGroup2.setLearningDay("Thứ ba");
        registration2.setSubjectGroup(subjectGroup2);
        registrationList.add(registration2);

        Assertions.assertNull(service.checkDuplicateTimetable(s,registrationList));
    }
}
