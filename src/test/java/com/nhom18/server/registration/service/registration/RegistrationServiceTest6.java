package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.TermWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RegistrationServiceTest6 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateLearningWeek(){
        Set<LearningWeek> learningWeek = new HashSet<>();
        for (int i=1;i<=3;i++){
            LearningWeek l = new LearningWeek();
            TermWeek t = new TermWeek();
            t.setId(i);
            l.setTermWeek(t);
            learningWeek.add(l);
        }
        Set<LearningWeek> learningWeek1 = new HashSet<>();
        Assertions.assertFalse(service.checkDuplicateLearningWeek(learningWeek,learningWeek1));
    }
}
