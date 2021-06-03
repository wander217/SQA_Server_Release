package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.RegisteredGroupRequest;
import com.nhom18.server.controller.registration.dto.RegistrationDTO;
import com.nhom18.server.controller.registration.dto.RegistrationRequest;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherHistoryRequest;
import com.nhom18.server.dao.AssignedSubjectDAO;
import com.nhom18.server.dao.RegistrationDAO;
import com.nhom18.server.dao.SubjectGroupDAO;
import com.nhom18.server.dao.TermDAO;
import com.nhom18.server.entity.group.GroupInfo;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.Term;
import com.nhom18.server.entity.registration.AssignedSubject;
import com.nhom18.server.entity.registration.Registration;
import com.nhom18.server.exception.*;
import org.hibernate.loader.plan.build.internal.returns.CollectionFetchableElementCompositeGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collector;
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
				Page<Registration> registrationList = this.registrationDAO
						.findAllBySubjectGroupCode(t.getSearchData().toLowerCase()
								,t.getTeacherId(),pageable);
				return registrationList.stream()
						.map(item->convertToDTO(item,registrationList.
								getTotalElements())).collect(Collectors.toList());
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
		List<Registration> rTmp = regGroupList.stream()
				.filter(item-> item.getTeacher().getId()==r.getTeacherId())
				.collect(Collectors.toList());
		if(!rTmp.isEmpty()){
			rTmp.get(0).setEnable(false);
			this.registrationDAO.save(rTmp.get(0));
			return;
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
	public Registration checkDuplicateTimetable(SubjectGroup subjectGroup, List<Registration> registrationList) {
		for(Registration r:registrationList){
			if(r.getSubjectGroup().getLearningDay().equals(subjectGroup.getLearningDay())
					&&checkDuplicateShift(r.getSubjectGroup().getGroupInfo(),subjectGroup.getGroupInfo())){
				return r;
			}
		}
		return null;
	}

	//Kiểm tra xem có kíp nào bị trùng lịch hay không
	//Nếu có kíp trùng thì kiểm tra tuần học xem có tuần nào trùng hay không
	public boolean checkDuplicateShift(Set<GroupInfo> groupInfo, Set<GroupInfo> groupInfo1) {
		Map<Long,GroupInfo> check = new HashMap<>();
		for(GroupInfo g:groupInfo){
			check.put(g.getShift().getId(),g);
		}
		for(GroupInfo g:groupInfo1){
			if(check.containsKey(g.getShift().getId())
					&&checkDuplicateLearningWeek(check.get(g.getShift()
					.getId()).getLearningWeek(),g.getLearningWeek())){
				return true;
			}
		}
		return false;
	}

	//Kiểm tra xem có tuần nào bị trùng lịch hay không
	public boolean checkDuplicateLearningWeek(Set<LearningWeek> learningWeek, Set<LearningWeek> learningWeek1) {
		Set<Long> check = new HashSet<>();
		for(LearningWeek l:learningWeek){
			check.add(l.getTermWeek().getId());
		}
		for(LearningWeek l:learningWeek1){
			if(check.contains(l.getTermWeek().getId())){
				return true;
			}
		}
		return false;
	}


	//Kiểm tra xem có đăng kí quá số nhóm được giao hay không
	public boolean checkOverRegister(AssignedSubject assignedSubject, List<Registration> registrationList) {
		return registrationList.stream()
				.filter(item->item.getSubjectGroup().getTermSubject()
						.getId() == assignedSubject.getTermSubject().getId())
				.count()+1>assignedSubject.getNumberOfGroup();
	}
}