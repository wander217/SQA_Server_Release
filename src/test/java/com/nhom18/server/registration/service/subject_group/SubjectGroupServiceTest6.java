package com.nhom18.server.registration.service.subject_group;

import com.nhom18.server.controller.registration.dto.GroupInfoDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupRequest;
import com.nhom18.server.controller.registration.service.SubjectGroupService;
import com.nhom18.server.entity.group.GroupInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

@SpringBootTest
public class SubjectGroupServiceTest6 {
    @Autowired
    private SubjectGroupService service;


    @Test
    public void testFindByTermSubject(){
        SubjectGroupRequest request = new SubjectGroupRequest();
        request.setProperties("code");
        request.setOrder("asc");
        request.setPageNum(0);
        request.setRecordPerPage(5);
        request.setSearchType(2);
        request.setSearchData("Kíp 1");
        request.setTermSubjectId(1);

        List<SubjectGroupDTO> ans =service.findByTermSubject(request);
        Assertions.assertEquals(2,ans.size());
        Assertions.assertTrue(ans.get(0).getCode().compareTo(ans.get(1).getCode())<=0);
        ans.forEach(item->{
            boolean check =false;
            for(GroupInfoDTO g:item.getGroupInfo()){
                if(g.getShift().toLowerCase().contains("Kíp 1".toLowerCase())){
                    check=true;
                    break;
                }
            }
            Assertions.assertTrue(check);
        });
    }
}
