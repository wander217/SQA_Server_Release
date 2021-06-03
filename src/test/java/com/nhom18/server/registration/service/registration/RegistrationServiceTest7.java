package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.entity.group.GroupInfo;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.Shift;
import com.nhom18.server.entity.group.TermWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RegistrationServiceTest7 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateShift(){
        Set<GroupInfo> groupInfo = new HashSet<>();
        for (int i=1;i<=3;i++){
            Shift shift = new Shift();
            shift.setId(i);
            GroupInfo tmp = new GroupInfo();
            tmp.setShift(shift);
            groupInfo.add(tmp);
        }
        Set<GroupInfo> groupInfo1 = new HashSet<>();
        Assertions.assertFalse(service.checkDuplicateShift(groupInfo,groupInfo1));
    }
}
