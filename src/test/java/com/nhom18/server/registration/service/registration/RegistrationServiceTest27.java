package com.nhom18.server.registration.service.registration;

import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.registration.service.RegistrationServiceImpl;
import com.nhom18.server.exception.DuplicatedTimetableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RegistrationServiceTest27 {
    @Autowired
    private RegistrationServiceImpl service;;

    @Test
    @Transactional
    @Rollback
    public void testDoRegistration(){
        RegistrationRequest request = new RegistrationRequest();
        request.setTeacherId(3);
        request.setSubjectGroupId(9);
        Assertions.assertThrows(DuplicatedTimetableException.class,()->{
           service.doRegistration(request);
        });
    }
}
