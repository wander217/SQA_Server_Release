package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.TermWeek;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RegistrationServiceTest3 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateLearningWeek(){
        Set<LearningWeek> learningWeek = new HashSet<>();
        Set<LearningWeek> learningWeek1 = new HashSet<>();
        for (int i=4;i<=6;i++){
            LearningWeek l = new LearningWeek();
            TermWeek t = new TermWeek();
            t.setId(i);
            l.setTermWeek(t);
            learningWeek1.add(l);
        }
        Assertions.assertFalse(service.checkDuplicateLearningWeek(learningWeek,learningWeek1));
    }
}
