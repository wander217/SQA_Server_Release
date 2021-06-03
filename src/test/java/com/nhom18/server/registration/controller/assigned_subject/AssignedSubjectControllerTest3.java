package com.nhom18.server.registration.controller.assigned_subject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AssignedSubjectControllerTest3 {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TermDAO termDAO;

    private String getJsonRequest(AssignedSubjectRequest request){
        try {
            String JsonRequest = new ObjectMapper().writeValueAsString(request);
            return JsonRequest;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @BeforeEach
    public void setUp(){
        Term term = new Term();
        term.setId(1);
        term.setStartDate(Date.valueOf("2021-08-02"));
        term.setEndDate(Date.valueOf("2022-01-09"));
        term.setStartRegTime(Timestamp.valueOf("2021-06-04 01:00:00"));
        term.setEndRegTime(Timestamp.valueOf("2021-07-17 12:00:00"));
        Mockito.when(termDAO.getLastTerm()).thenReturn(Optional.of(term));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    public void testFindByTeacher(){
        AssignedSubjectRequest request = new AssignedSubjectRequest();
        request.setTeacherId(3);
        request.setSearchData("");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/assignedsubject/tchfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",
                            containsString("Thời gian đăng kí tín bắt đầu từ 1:0:0 4/6/2021 đến 12:0:0 17/7/2021")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
