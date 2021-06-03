package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RegistrationServiceTest16 {
    @Autowired
    private RegistrationServiceImpl service;


    @Test
    public void testCheckOverRegister(){
        AssignedSubject a = new AssignedSubject();
        TermSubject t = new TermSubject();
        t.setId(1);
        a.setTermSubject(t);
        a.setNumberOfGroup(2);

        List<Registration> registrationList = new ArrayList<>();

        Assertions.assertFalse(service.checkOverRegister(a,registrationList));
    }
}
