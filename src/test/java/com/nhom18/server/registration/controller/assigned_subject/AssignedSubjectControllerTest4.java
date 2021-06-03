package com.nhom18.server.registration.controller.assigned_subject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.server.controller.registration.dto.AssignedSubjectRequest;
import com.nhom18.server.dao.TermDAO;
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

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AssignedSubjectControllerTest4 {
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
        Mockito.when(termDAO.getLastTerm()).thenReturn(Optional.empty());
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
                            containsString("Chưa có kì mới")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
