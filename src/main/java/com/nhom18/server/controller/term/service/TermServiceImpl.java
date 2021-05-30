package com.nhom18.server.controller.term.service;

import com.nhom18.server.controller.term.dto.TermDTO;
import com.nhom18.server.controller.term.dto.TermRequest;
import com.nhom18.server.dao.RegistrationDAO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.exception.RegTimeException;
import com.nhom18.server.exception.TermNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService{
    @Autowired
    private TermDAO termDAO;
    @Autowired
    private RegistrationDAO registrationDAO;

    @Override
    public TermDTO getLastTerm() throws TermNotFoundException {
        Term term = this.termDAO.getLastTerm().orElseThrow(TermNotFoundException::new);
        TermDTO termDTO = new TermDTO();
        termDTO.setEndDate(term.getEndDate());
        termDTO.setEndRegTime(term.getEndRegTime());
        termDTO.setStartDate(term.getStartDate());
        termDTO.setStartRegTime(term.getStartRegTime());
        return termDTO;
    }

    @Override
    @Transactional
    public void updateTerm(TermRequest t) throws TermNotFoundException, RegTimeException {
        Term term = this.termDAO.getLastTerm()
                .orElseThrow(TermNotFoundException::new);
        //Nếu thời gian kết thúc đăng kí nhỏ hơn thời gian bắt đầu thì báo lỗi
        if(t.getStartRegTime().after(t.getEndRegTime())){
            throw new RegTimeException("Thời gian bắt đầu đăng kí phải nhỏ hơn thời gian kết thúc đăng kí!");
        }
        //Nếu thời gian kết thúc đăng kí lớn hơn thời gian bắt đầu kì thì báo lỗi
        if(t.getEndRegTime().after(term.getStartDate())){
            throw new RegTimeException("Thời gian kết thúc đăng kí phải nhỏ hơn thời gian bắt đầu kì mới");
        }
        //Cập nhật kì mới
        term.setStartRegTime(t.getStartRegTime());
        term.setEndRegTime(t.getEndRegTime());
        this.termDAO.save(term);
        //Vô hiệu hóa tất cả đăng kí trước đó
        List<Registration> registrationList= this.registrationDAO
                .findAllEnableByTerm(term.getId());
        this.registrationDAO.saveAll(registrationList
                .stream().map(item->{
                    item.setEnable(false);
                    return item;
                }).collect(Collectors.toList()));
    }
}
