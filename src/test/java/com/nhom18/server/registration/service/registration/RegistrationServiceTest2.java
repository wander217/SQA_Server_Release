package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegisteredGroupRequest;
import com.nhom18.server.controller.registration.dto.RegistrationDTO;
import com.nhom18.server.controller.registration.service.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegistrationServiceTest2 {
    @Autowired
    private RegistrationService service;

    @Test
    public void testFindAllEnableByTermSubject(){
        RegisteredGroupRequest request = new RegisteredGroupRequest();
        request.setTermSubjectId(10);

        List<RegistrationDTO> ans = this.service.findAllEnableByTermSubject(request);
        Assertions.assertEquals(0,ans.size());
    }
}
