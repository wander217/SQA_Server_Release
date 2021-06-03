package com.nhom18.server.registration.service.assgined_subject;

import com.nhom18.server.controller.registration.dto.AssignedSubjectDTO;
import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.controller.registration.service.AssignedSubjectService;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.Term;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AssignedSubjectServiceTest6 {
    @Autowired
    private AssignedSubjectService service;
    @MockBean
    private TermDAO termDAO;


    @BeforeEach
    private void setUp(){
        Term term = new Term();
        term.setId(1);
        term.setStartRegTime(Timestamp.valueOf("2021-05-04 01:00:00"));
        term.setEndRegTime(Timestamp.valueOf("2021-07-17 12:00:00"));
        Mockito.when(termDAO.getLastTerm())
                .thenReturn(Optional.of(term));
    }

    @Test
    public void testFindByTeacher(){
        AssignedSubjectRequest request = new AssignedSubjectRequest();
        request.setTeacherId(3);
        request.setSearchData("acbcbac");
        Assertions.assertDoesNotThrow(()->{
            List<AssignedSubjectDTO> ans =service.findByTeacher(request);
            Assertions.assertEquals(0,ans.size());
        });
    }
}
