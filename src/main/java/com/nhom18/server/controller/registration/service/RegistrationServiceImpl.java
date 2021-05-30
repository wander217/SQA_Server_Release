package com.nhom18.server.controller.registration.service;

import com.wander.sqa.controller.registration.dto.RegisteredGroupRequest;
import com.wander.sqa.controller.registration.dto.RegistrationDTO;
import com.wander.sqa.controller.registration.dto.RegistrationRequest;
import com.wander.sqa.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.wander.sqa.dao.AssignedSubjectDAO;
import com.wander.sqa.dao.RegistrationDAO;
import com.wander.sqa.dao.SubjectGroupDAO;
import com.wander.sqa.dao.TermDAO;
import com.wander.sqa.entity.group.GroupInfo;
import com.wander.sqa.entity.group.LearningWeek;
import com.wander.sqa.entity.group.SubjectGroup;
import com.wander.sqa.entity.group.Term;
import com.wander.sqa.entity.registration.AssignedSubject;
import com.wander.sqa.entity.registration.Registration;
import com.wander.sqa.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationDAO registrationDAO;
	@Autowired
	private AssignedSubjectDAO assignedSubjectDAO;
	@Autowired
	private SubjectGroupDAO subjectGroupDAO;
	@Autowired
	private TermDAO termDAO;

	//Load tất cả các đăng kí
	@Override
	@Transactional
	public List<RegistrationDTO> findAllEnableByTermSubject(RegisteredGroupRequest rg) {
		List<Registration> registrationList = this.registrationDAO.findAllEnableByTermSubject(rg.getTermSubjectId());
		return registrationList.stream().map(item->convertToDTO(item,0)).collect(Collectors.toList());
	}

	//Load lịch sử
	@Override
	@Transactional
	public List<RegistrationDTO> findAllByTeacher(TeacherHistoryRequest t) {
		//Tạo sort
		Sort  sort = Sort.by(t.getProperties()).ascending();
		//Nếu type là desc thì thay thành giảm
		if(t.getOrder().equals("desc")){
			sort=sort.descending();
		}
		Pageable pageable = PageRequest.of(t.getPageNum(),t.getRecordPerPage(),sort);
		switch(t.getSearchType()){
			case 1:{
				Page<Registration> registrationList = this.registrationDAO
						.findAllBySubjectName(t.getSearchData().toLowerCase()
								,t.getTeacherId(),pageable);
				return registrationList.stream()
						.map(item->convertToDTO(item,registrationList
								.getTotalElements())).collect(Collectors.toList());
			}
			default:{
				List<Registration> registrationList = this.registrationDAO
						.findAllBySubjectGroupCode(t.getSearchData().toLowerCase()
								,t.getTeacherId(),pageable);
				return registrationList.stream()
						.map(item->convertToDTO(item,7))
						.collect(Collectors.toList());
			}
		}

	}

	private RegistrationDTO convertToDTO(Registration item,long total){
		RegistrationDTO registrationDTO = new RegistrationDTO();
		registrationDTO.setEnable(item.isEnable());
		registrationDTO.setRegTime(item.getRegTime());
		registrationDTO.setId(item.getId());
		registrationDTO.setTeacherId(item.getTeacher().getId());
		registrationDTO.setSubjectGroupId(item.getSubjectGroup().getId());
		registrationDTO.setSubjectGroupCode(item.getSubjectGroup().getCode());
		registrationDTO.setSubjectName(item.getSubjectGroup()
				.getTermSubject().getSubject().getName());
		registrationDTO.setTotalItem(total);
		return  registrationDTO;
	}

	//Thực hiện đăng kí
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void doRegistration(RegistrationRequest r)
			throws OverLimitGroupException, DuplicatedTimetableException,
			AssignmentException, OverLimitRegistrationException,
			TermNotFoundException, OutOfRegistrationTimeException {
		//Nếu quá thời gian đăng kí thì báo lỗi
		Term term = this.termDAO.getLastTerm()
				.orElseThrow(TermNotFoundException::new);
		Date now = new Date(System.currentTimeMillis());
		if(now.after(term.getEndRegTime())||now.before(term.getStartRegTime())){
			throw new OutOfRegistrationTimeException(term
					.getStartRegTime().toLocalDateTime(),
					term.getEndRegTime().toLocalDateTime());
		}
		//Nếu không được giao thì báo lỗi
		AssignedSubject assignedSubject = this.assignedSubjectDAO
				.findBySubjectGroupAndTeacher(r.getSubjectGroupId(),r.getTeacherId());
		if(!(assignedSubject instanceof AssignedSubject)){
			throw new AssignmentException();
		}
		//Lấy tất cả đăng kí theo nhóm
		List<Registration> regGroupList = this.registrationDAO
				.findAllBySubjectGroup(r.getSubjectGroupId());
		//Nếu đăng kí rồi thì hủy đăng kí
		for(Registration rg :regGroupList){
			if(rg.getTeacher().getId()==r.getTeacherId()){
				rg.setEnable(false);
				this.registrationDAO.save(rg);
				return;
			}
		}
		//Lấy thông tin của nhóm
		SubjectGroup subjectGroup = this.subjectGroupDAO
				.findById(r.getSubjectGroupId())
				.orElseThrow(AssignmentException::new);
		//Nếu số nhóm đăng kí lớn hơn số quy định cho nhóm thì báo lỗi
		if(regGroupList.size()+1>subjectGroup.getNumberOfTeacher()){
			throw new OverLimitGroupException(subjectGroup);
		}
		//Load những nhóm đã đăng kí
		List<Registration> registrationList = this.registrationDAO
				.findAllEnableByTeacher(r.getTeacherId(),term.getId());
		//Không được đăng kí quá số lượng được giao
		if(checkOverRegister(assignedSubject,registrationList)){
			throw new OverLimitRegistrationException(assignedSubject);
		}
		//Không được đăng kí trùng lịch với các đăng kí khác
		Registration duplicateTimetable = checkDuplicateTimetable(subjectGroup,registrationList);
		if(duplicateTimetable instanceof Registration){
			throw new DuplicatedTimetableException(duplicateTimetable.getSubjectGroup());
		}
		//Tạo đăng kí mới
		Registration registration = new Registration();
		registration.setSubjectGroup(subjectGroup);
		registration.setTeacher(assignedSubject.getTeacher());
		registration.setRegTime(new Timestamp(System.currentTimeMillis()));
		registration.setEnable(true);
		registration.setId(0);
		this.registrationDAO.save(registration);
	}

	//Kiểm tra xem ngày học có bị trùng hay không
	//Nếu ngày học bị trùng thì kiểm tra tiếp xem kíp học có trùng không
	private Registration checkDuplicateTimetable(SubjectGroup subjectGroup, List<Registration> registrationList) {
		for(Registration r:registrationList){
			if(!r.getSubjectGroup().getLearningDay().equals(subjectGroup.getLearningDay())){
				continue;
			}
			if(checkDuplicateShift(r.getSubjectGroup().getGroupInfo(),subjectGroup.getGroupInfo())){
				return r;
			}
		}
		return null;
	}

	//Kiểm tra xem có kíp nào bị trùng lịch hay không
	//Nếu có kíp trùng thì kiểm tra tuần học xem có tuần nào trùng hay không
	private boolean checkDuplicateShift(Set<GroupInfo> groupInfo, Set<GroupInfo> groupInfo1) {
		for(GroupInfo g:groupInfo){
			for (GroupInfo g1:groupInfo1){
				if(g.getShift().getId()!=g1.getShift().getId()){
					continue;
				}
				if(checkDuplicateLearningWeek(g.getLearningWeek(),g1.getLearningWeek())) {
					return true;
				}
			}
		}
		return false;
	}

	//Kiểm tra xem có tuần nào bị trùng lịch hay không
	private boolean checkDuplicateLearningWeek(Set<LearningWeek> learningWeek, Set<LearningWeek> learningWeek1) {
		for(LearningWeek l:learningWeek){
			for(LearningWeek l1 :learningWeek1){
				if(!l.isDesist()&&!l1.isDesist() &&l.getTermWeek().getId()==l1.getTermWeek().getId()){
					return true;
				}
			}
		}
		return false;
	}


	//Kiểm tra xem có đăng kí quá số nhóm được giao hay không
	private boolean checkOverRegister(AssignedSubject assignedSubject, List<Registration> registrationList) {
		return registrationList.stream()
				.filter(item->item.getSubjectGroup().getTermSubject()
						.getId() == assignedSubject.getTermSubject().getId())
				.count()+1>assignedSubject.getNumberOfGroup();
	}
}