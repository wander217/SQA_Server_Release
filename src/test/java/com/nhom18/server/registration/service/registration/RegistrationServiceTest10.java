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
public class RegistrationServiceTest10 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testCheckDuplicateShift(){
        Set<GroupInfo> groupInfo = getParameter1();
        Set<GroupInfo> groupInfo1 = getParameter2();
        Assertions.assertFalse(service.checkDuplicateShift(groupInfo,groupInfo1));
    }

    private Set<GroupInfo> getParameter1(){
        Set<GroupInfo> groupInfo = new HashSet<>();
        Shift shift1 = new Shift();
        shift1.setId(1);
        GroupInfo groupInfo1 = new GroupInfo();
        groupInfo1.setShift(shift1);
        Set<LearningWeek> learningWeekSet1 = new HashSet<>();
        for(int i=1;i<=3;i++){
            LearningWeek tmp =new LearningWeek();
            TermWeek termWeek = new TermWeek();
            termWeek.setId(i);
            tmp.setTermWeek(termWeek);
            learningWeekSet1.add(tmp);
        }
        groupInfo1.setLearningWeek(learningWeekSet1);
        groupInfo.add(groupInfo1);

        Shift shift2 = new Shift();
        shift2.setId(2);
        GroupInfo groupInfo2 = new GroupInfo();
        groupInfo2.setShift(shift2);
        Set<LearningWeek> learningWeekSet2 = new HashSet<>();
        for(int i=4;i<=6;i++){
            LearningWeek tmp =new LearningWeek();
            TermWeek termWeek = new TermWeek();
            termWeek.setId(i);
            tmp.setTermWeek(termWeek);
            learningWeekSet2.add(tmp);
        }
        groupInfo2.setLearningWeek(learningWeekSet2);
        groupInfo.add(groupInfo2);
        return groupInfo;
    }

    private Set<GroupInfo> getParameter2(){
        Set<GroupInfo> groupInfo = new HashSet<>();
        Shift shift1 = new Shift();
        shift1.setId(2);
        GroupInfo groupInfo1 = new GroupInfo();
        groupInfo1.setShift(shift1);
        Set<LearningWeek> learningWeekSet1 = new HashSet<>();
        for(int i=1;i<=3;i++){
            LearningWeek tmp =new LearningWeek();
            TermWeek termWeek = new TermWeek();
            termWeek.setId(i);
            tmp.setTermWeek(termWeek);
            learningWeekSet1.add(tmp);
        }
        groupInfo1.setLearningWeek(learningWeekSet1);
        groupInfo.add(groupInfo1);

        Shift shift2 = new Shift();
        shift2.setId(5);
        GroupInfo groupInfo2 = new GroupInfo();
        groupInfo2.setShift(shift2);
        Set<LearningWeek> learningWeekSet2 = new HashSet<>();
        for(int i=4;i<=6;i++){
            LearningWeek tmp =new LearningWeek();
            TermWeek termWeek = new TermWeek();
            termWeek.setId(i);
            tmp.setTermWeek(termWeek);
            learningWeekSet2.add(tmp);
        }
        groupInfo2.setLearningWeek(learningWeekSet2);
        groupInfo.add(groupInfo2);
        return groupInfo;
    }
}
