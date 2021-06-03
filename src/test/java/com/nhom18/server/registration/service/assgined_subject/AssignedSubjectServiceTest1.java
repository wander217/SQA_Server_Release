package com.nhom18.server.registration.service.assgined_subject;

import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.controller.registration.service.AssignedSubjectService;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.exception.TermNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class AssignedSubjectServiceTest1 {
    @Autowired
    private AssignedSubjectService service;
    @MockBean
    private TermDAO termDAO;


    @BeforeEach
    private void setUp(){
        Mockito.when(termDAO.getLastTerm())
                .thenReturn(Optional.empty());
    }

    @Test
    public void testFindByTeacher(){
        AssignedSubjectRequest request = new AssignedSubjectRequest();
        request.setTeacherId(3);
        request.setSearchData("");
        Assertions.assertThrows(TermNotFoundException.class,()->{
           service.findByTeacher(request);
        });
    }
}
