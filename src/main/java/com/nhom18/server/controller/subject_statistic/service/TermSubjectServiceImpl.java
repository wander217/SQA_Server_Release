package com.nhom18.server.controller.subject_statistic.service;

import com.nhom18.server.controller.subject_statistic.dto.SubjectRequest;
import com.nhom18.server.controller.subject_statistic.dto.TermSubjectStatDTO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.dao.TermSubjectDAO;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.entity.group.TermSubject;
import com.nhom18.server.exception.TermNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TermSubjectServiceImpl implements TermSubjectService{
    @Autowired
    private TermSubjectDAO termSubjectDAO;
    @Autowired
    private TermDAO termDAO;

    @Override
    public List<TermSubjectStatDTO> getAll(SubjectRequest s) throws TermNotFoundException {
        //Lấy term
        Term term = this.termDAO.getLastTerm()
                .orElseThrow(TermNotFoundException::new);
        //Tạo sort
        Sort sort = null;
        if(s.getProperties().equals("remember")||s.getProperties().equals("forgot")){
            sort = Sort.by("termSubject.id").ascending();
        }else {
            sort = Sort.by(s.getProperties()).ascending();
        }
        //Nếu type là desc thì thay thành giảm
        if(s.getOrder().equals("desc")){
            sort=sort.descending();
        }
        //Tạo page để lấy
        Pageable pageable = PageRequest.of(s.getPageNum(), s.getRecordPerPage(), sort);
        //Lấy danh sách môn học đã giao cùng với số lượng giảng viên được giao
        Page<Object[]> teacherAssignedSubject = this.termSubjectDAO
                .getTermSubjectWithTeacherCount(pageable,s.getSearchData(),term.getId());
        //Nếu danh sách rỗng thì tức là chưa giao môn nào
        if(teacherAssignedSubject == null||teacherAssignedSubject.isEmpty()){
            return new ArrayList<>();
        }
        //Tạo ra danh sách môn
        List<TermSubjectStatDTO> ans = this.getAns(teacherAssignedSubject);
        //Lấy danh sách môn học đã giao cùng với số lượng các giảng viên đã đăng kí đủ
        List<Object[]> regAssignedSubject = this.termSubjectDAO
                .getTermSubjectWithRegCount(pageable,s.getSearchData());
        //Nếu danh sách rỗng thì tức là thiếu hết
        if(regAssignedSubject == null || regAssignedSubject.isEmpty()){
            return ans;
        }
        //Tạo map để lưu trữ
        Map<Long,Long> regAssignedSubjectMap = this.getMap(regAssignedSubject);
        //Đếm số giảng viên đã đăng kí đủ , chưa đăng kí đủ
        ans.forEach(item->{
            Long count = regAssignedSubjectMap.get(item.getId());
            //Nếu không tồn tại tức là chưa có ai đăng kí đủ
            count = count==null?0:count;
            item.setRemember(count);
            item.setForgot(item.getForgot()-count);
        });
        //sort lại nếu là remember hoặc forgot
        if(s.getProperties().equals("forgot")){
            Collections.sort(ans,(x,y)->{
                if(x.getForgot()>y.getForgot()){
                    return s.getOrder().equals("asc")?1:-1;
                }
                if(x.getForgot() == y.getForgot()){
                    return 0;
                }
                return s.getOrder().equals("asc")?-1:1;
            });
        }else if(s.getProperties().equals("remember")){
            Collections.sort(ans,(x,y)->{
                if(x.getRemember()>y.getRemember()){
                    return s.getOrder().equals("asc")?1:-1;
                }
                if(x.getRemember() == y.getRemember()){
                    return 0;
                }
                return s.getOrder().equals("asc")?-1:1;
            });
        }
        return ans;
    }

    //Chuyển đổi thành map
    private Map<Long, Long> getMap(List<Object[]> regAssignedSubject) {
        Map<Long,Long> ans = new HashMap<>();
        try{
            regAssignedSubject.forEach(item->{
                TermSubject termSubject = (TermSubject) item[0];
                Long count = (Long) item[1];
                ans.put(termSubject.getId(),count);
            });
            return ans;
        }catch (Exception e){
            e.printStackTrace();
            return ans;
        }
    }

    //Chuyển dạng sang dto
    private List<TermSubjectStatDTO> getAns(Page<Object[]> teacherAssignedSubject) {
        List<TermSubjectStatDTO> ans = new ArrayList<>();
        try{
            teacherAssignedSubject.forEach(item->{
                TermSubject termSubject= (TermSubject) item[0];
                Long count  = (Long) item[1];
                TermSubjectStatDTO tmp = new TermSubjectStatDTO();
                tmp.setId(termSubject.getId());
                tmp.setTermSubjectName(termSubject.getSubject().getName());
                tmp.setForgot(count);
                tmp.setRemember(0);
                tmp.setTotalItem(teacherAssignedSubject.getTotalElements());
                ans.add(tmp);
            });
            return ans;
        }catch (Exception e){
            e.printStackTrace();
            return ans;
        }
    }
}
