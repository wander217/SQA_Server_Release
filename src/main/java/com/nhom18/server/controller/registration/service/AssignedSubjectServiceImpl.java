package com.nhom18.server.controller.registration.service;

import com.wander.sqa.controller.registration.dto.AssignedSubjectDTO;
import com.wander.sqa.controller.registration.dto.AssignedSubjectRequest;
import com.wander.sqa.dao.AssignedSubjectDAO;
import com.wander.sqa.dao.TermDAO;
import com.wander.sqa.dao.TermSubjectDAO;
import com.wander.sqa.entity.group.Term;
import com.wander.sqa.entity.group.TermSubject;
import com.wander.sqa.entity.registration.AssignedSubject;
import com.wander.sqa.exception.OutOfRegistrationTimeException;
import com.wander.sqa.exception.TermNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssignedSubjectServiceImpl implements AssignedSubjectService {
	@Autowired
	private AssignedSubjectDAO assignedSubjectDAO;
	@Autowired
	private TermSubjectDAO termSubjectDAO;
	@Autowired
	private TermDAO termDAO;

	@Override
	@Transactional
	public List<AssignedSubjectDTO> findByTeacher(AssignedSubjectRequest a)
			throws TermNotFoundException, OutOfRegistrationTimeException {
		//Kiểm tra xem có phải thời gian đăng kí không
		Term term = this.termDAO.getLastTerm()
				.orElseThrow(TermNotFoundException::new);
		Date now = new Date(System.currentTimeMillis());
		if(now.after(term.getEndRegTime())||now.before(term.getStartRegTime())){
			throw new OutOfRegistrationTimeException(term.getStartRegTime()
					.toLocalDateTime(), term.getEndRegTime().toLocalDateTime());
		}
		//Lấy tất cả môn được giao cho giảng viên
		List<AssignedSubject> assignedSubjectList = this
				.assignedSubjectDAO.findByTeacher(a.getTeacherId(),a.getSearchData(),term.getId());
		//Tính số nhóm đã đăng kí theo các môn được giao
		List<Object[]> regTermSubject = this.termSubjectDAO
				.getTermSubjectWithRegCountByTeacher(a.getTeacherId(),term.getId());
		//Tạo map để lưu trữ
		Map<Long,Long> regTermSubjectMap = this.getMap(regTermSubject);
		return assignedSubjectList.stream().map(item->this
				.convertToDTO(item,regTermSubjectMap))
				.collect(Collectors.toList());
	}

	//Chyển về dto
	private AssignedSubjectDTO convertToDTO(AssignedSubject item, Map<Long,Long> registrationMap){
		AssignedSubjectDTO assignedSubjectDTO = new AssignedSubjectDTO();
		assignedSubjectDTO.setId(item.getTermSubject().getId());
		assignedSubjectDTO.setName(item.getTermSubject().getSubject().getName());
		assignedSubjectDTO.setNumberOfGroup(item.getNumberOfGroup());
		Long count = registrationMap.get(item.getTermSubject().getId());
		assignedSubjectDTO.setNumberOfRegister(count==null?0:count);
		return assignedSubjectDTO;
	}

	//Chuyển lại thành map
	private Map<Long, Long> getMap(List<Object[]> list) {
		Map<Long,Long> map = new HashMap<>();
		try{
			for(Object[] o:list){
				TermSubject termSubject = (TermSubject)o[0];
				Long count = (Long) o[1];
				map.put(termSubject.getId(),count);
			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
			return map;
		}
	}
}