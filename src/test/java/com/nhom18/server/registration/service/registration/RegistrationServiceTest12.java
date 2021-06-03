package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.entity.group.*;
import com.nhom18.server.entity.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class RegistrationServiceTest12 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateTimetable(){
        SubjectGroup s = new SubjectGroup();
        s.setLearningDay("Thứ hai");
        List<Registration> registrationList = new ArrayList<>();
        Assertions.assertNull(service.checkDuplicateTimetable(s,registrationList));
    }
}
