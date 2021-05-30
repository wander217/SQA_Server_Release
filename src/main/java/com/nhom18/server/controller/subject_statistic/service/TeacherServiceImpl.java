package com.nhom18.server.controller.subject_statistic.service;

import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherRequest;
import com.nhom18.server.controller.subject_statistic.dto.SubjectTeacherStatDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatDTO;
import com.nhom18.server.controller.teacher_statistic.dto.TeacherStatRequest;
import com.nhom18.server.dao.TeacherDAO;
import com.nhom18.server.entity.user.Fullname;
import com.nhom18.server.entity.user.Teacher;
import com.nhom18.server.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDAO teacherDAO;

	//Tìm kiếm thông tin theo tài khoản
	@Override
	@Transactional
	public long findIdByUsername(String u) throws UsernameNotFoundException {
		Teacher teacher = this.teacherDAO.findByUsername(u)
				.orElseThrow(UsernameNotFoundException::new);
		return teacher.getId();
	}

	private List<SubjectTeacherStatDTO> getAns(Page<Teacher> teacherList){
		return teacherList.stream()
				.map(item->{
					SubjectTeacherStatDTO ans= new SubjectTeacherStatDTO();
					ans.setId(item.getId());
					ans.setCode(item.getTchCode());
					ans.setFullname(this.getFullname(item.getFullname()));
					ans.setTotalItem(teacherList.getTotalElements());
					return ans;
				}).collect(Collectors.toList());
	}

	//Lấy pageable từ thuộc tính request
	private Pageable getPagable(SubjectTeacherRequest  st){
		//Tạo sort
		Sort sort =null;
		if(st.getProperties().equals("fullname")){
			sort = Sort.by("fullname.firstname")
					.and(Sort.by("fullname.middlename"))
					.and(Sort.by("fullname.lastname"))
					.ascending();
		}else{
			sort = Sort.by(st.getProperties()).ascending();
		}
		//Nếu type là desc thì thay thành giảm
		if(st.getProperties().equals("desc")){
			sort=sort.descending();
		}
		//Tạo page để lấy
		return PageRequest.of(st.getPageNum(), st.getRecordPerPage(), sort);
	}

	//Tìm kiếm giảng viên đăng kí đủ
	@Override
	@Transactional
	public List<SubjectTeacherStatDTO> findRemember(SubjectTeacherRequest st) {
		Pageable pageable = this.getPagable(st);
		switch (st.getSearchType()){
			case 1:{
				Page<Teacher> teacherList = this.teacherDAO
						.findRememberByCode(st.getTermSubjectId(),st.getSearchData(),pageable);
				return this.getAns(teacherList);
			}
			default:{
				Page<Teacher> teacherList = this.teacherDAO
						.findRememberByName(st.getTermSubjectId(),st.getSearchData(),pageable);
				return this.getAns(teacherList);
			}
		}
	}

	//Tìm kiếm giảng viên đăng kí thiếu
	@Override
	@Transactional
	public List<SubjectTeacherStatDTO> findForgot(SubjectTeacherRequest st) {
		Pageable pageable = this.getPagable(st);
		switch (st.getSearchType()){
			case 1:{
				Page<Teacher> teacherList = this.teacherDAO
						.findForgotByCode(st.getTermSubjectId(),st.getSearchData(),pageable);
				return this.getAns(teacherList);
			}
			default:{
				Page<Teacher> teacherList = this.teacherDAO
						.findForgotByName(st.getTermSubjectId(),st.getSearchData(),pageable);
				return this.getAns(teacherList);
			}
		}
	}

	@Override
	@Transactional
	public List<TeacherStatDTO> getAll(TeacherStatRequest t) {
 		//Lấy danh sách giảng viên được giao dạy cùng với số môn được giao
		List<Object[]> groupTeacher = this.teacherDAO
				.findTeacherWithNumberOfGroup(t.getSearchData());
		//Nếu danh sách rỗng thì tức là chưa giao cho ai
		if(groupTeacher == null||groupTeacher.isEmpty()){
			return new ArrayList<>();
		}
		//Chuyển danh sách giảng viên về dto
		List<TeacherStatDTO> ans = this.getAssignedTeacher(groupTeacher);
		//Lấy danh sách đăng kí đủ theo giảng viên
		List<Object[]> fullRegTeacher = this.teacherDAO.findTeacherWithNumberOfFullRegGroup();
		//Nếu không ai đăng kí đủ thì trả về luôn
		if(fullRegTeacher == null||fullRegTeacher.isEmpty()){
			return ans;
		}
		//Tạo map lưu lại
		Map<Long,Long> fullRegTeacherMap = this.getMap(fullRegTeacher);
		//Cài đặt số môn đăng kí đủ
		ans.forEach(item->{
			Long count = fullRegTeacherMap.get(item.getId());
			//Nếu không rỗng thì đã đăng kí đủ
			count = count==null?0:count;
			item.setRemember(count);
			item.setForgot(item.getForgot()-count);
		});
		return ans;
	}

	//Chuyển về dto
	private List<TeacherStatDTO> getAssignedTeacher(List<Object[]> groupTeacher) {
		List<TeacherStatDTO> ans = new ArrayList<>();
		try{
			groupTeacher.forEach(item->{
				Teacher teacher = (Teacher) item[0];
				Long count = (Long) item[1];
				TeacherStatDTO teacherStatDTO = new TeacherStatDTO();
				teacherStatDTO.setId(teacher.getId());
				teacherStatDTO.setForgot(count);
				teacherStatDTO.setRemember(0);
				teacherStatDTO.setFullname(this.getFullname(teacher.getFullname()));
				ans.add(teacherStatDTO);
			});
			return ans;
		}catch (Exception e){
			e.printStackTrace();
			return ans;
		}
	}

	//Lấy họ và tên
	private String getFullname(Fullname fullname){
		if(fullname.getMiddlename()==null) fullname.setMiddlename("");
		return fullname.getFirstname()+" "+fullname.getMiddlename()+" "+fullname.getLastname();
	}

	//Lấy Map đến theo giảng viên
	private Map<Long,Long> getMap(List<Object[]> list){
		Map<Long,Long> map = new HashMap<>();
		try{
			for(Object[] o:list){
				Teacher teacher = (Teacher) o[0];
				Long count = (Long) o[1];
				map.put(teacher.getId(),count);
			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
			return map;
		}
	}
}