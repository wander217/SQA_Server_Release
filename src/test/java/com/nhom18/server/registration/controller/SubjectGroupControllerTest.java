package com.nhom18.server.registration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.server.controller.registration.dto.SubjectGroupRequest;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SubjectGroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String getJsonRequest(SubjectGroupRequest request){
        try {
            String JsonRequest = new ObjectMapper().writeValueAsString(request);
            return JsonRequest;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Test
    @WithMockUser(username = "Teacher1",password = "User_1234",roles = "TEACHER")
    public void testFindByTermSubject(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("code");
        request.setOrder("asc");
        request.setPageNum(0);
        request.setRecordPerPage(5);
        request.setSearchType(0);
        request.setSearchData("");
        request.setTermSubjectId(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/subjectgroup/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$",hasSize(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "Teacher1",password = "User_1234",roles = "TEACHER")
    public void testFindByTermSubject1(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("a");
        request.setOrder("asc");
        request.setPageNum(0);
        request.setRecordPerPage(5);
        request.setSearchType(0);
        request.setSearchData("");
        request.setTermSubjectId(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/subjectgroup/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$[0]",
                            containsString("Thuộc tính sắp xếp không hợp lệ!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "Teacher1",password = "User_1234",roles = "TEACHER")
    public void testFindByTermSubject2(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("code");
        request.setOrder("asc");
        request.setPageNum(-1);
        request.setRecordPerPage(5);
        request.setSearchType(0);
        request.setSearchData("");
        request.setTermSubjectId(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/subjectgroup/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$[0]",
                            containsString("Số trang phải là số nguyên không âm")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "Teacher1",password = "User_1234",roles = "TEACHER")
    public void testFindByTermSubject3(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("code");
        request.setOrder("asc");
        request.setPageNum(0);
        request.setRecordPerPage(0);
        request.setSearchType(0);
        request.setSearchData("");
        request.setTermSubjectId(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/subjectgroup/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$[0]",
                            containsString("Số bản ghi trong trang phải là 1 số dương")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "Teacher1",password = "User_1234",roles = "TEACHER")
    public void testFindByTermSubject4(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("code");
        request.setOrder("asc");
        request.setPageNum(0);
        request.setRecordPerPage(5);
        request.setSearchType(0);
        request.setSearchData("");
        request.setTermSubjectId(0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/subjectgroup/tsfind")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonRequest(request));
        try {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$[0]",
                            containsString("Mã môn học phải là số dương")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
