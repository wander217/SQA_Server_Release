package com.nhom18.server.registration.controller.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationService;
import com.nhom18.server.exception.*;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RegistrationControllerTest11 {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegistrationService service;

    @BeforeEach
    private void setUp() throws OverLimitGroupException, AssignmentException,
            OutOfRegistrationTimeException, TermNotFoundException,
            DuplicatedTimetableException, OverLimitRegistrationException {
        LocalDateTime start= Timestamp.valueOf("2021-06-04 01:00:00").toLocalDateTime();
        LocalDateTime end =Timestamp.valueOf("2021-07-17 12:00:00").toLocalDateTime();
        Mockito.doThrow(new OutOfRegistrationTimeException(start,end))
                .when(service).doRegistration(any(RegistrationRequest.class));
    }

    private String getJsonRequest(RegistrationRequest request){
        try {
            String JsonRequest = new ObjectMapper().writeValueAsString(request);
            return JsonRequest;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllEnableTermSubject(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(7);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/registration/adminreg")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$"
                            ,containsString("Th???i gian ????ng k?? t??n b???t ?????u t??? 1:0:0 4/6/2021 ?????n 12:0:0 17/7/2021")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
