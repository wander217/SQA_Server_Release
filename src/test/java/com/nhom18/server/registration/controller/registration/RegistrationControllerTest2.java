package com.nhom18.server.registration.controller.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.server.controller.registration.dto.RegisteredGroupRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RegistrationControllerTest2 {
    @Autowired
    private MockMvc mockMvc;

    private String getJsonRequest(RegisteredGroupRequest request){
        try {
            String JsonRequest = new ObjectMapper().writeValueAsString(request);
            return JsonRequest;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    public void testFindAllEnableTermSubject(){
        RegisteredGroupRequest request = new RegisteredGroupRequest();
        request.setTermSubjectId(0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/registration/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$[0]",
                            containsString("Mã môn học phải là một số nguyên dương")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
