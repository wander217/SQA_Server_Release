package com.nhom18.server.controller.registration.service;

import com.nhom18.server.controller.registration.dto.GroupInfoDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupDTO;
import com.nhom18.server.controller.registration.dto.SubjectGroupRequest;
import com.nhom18.server.dao.SubjectGroupDAO;
import com.nhom18.server.dao.TermWeekDAO;
import com.nhom18.server.entity.group.GroupInfo;
import com.nhom18.server.entity.group.LearningWeek;
import com.nhom18.server.entity.group.SubjectGroup;
import com.nhom18.server.entity.group.TermWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectGroupServiceImpl implements SubjectGroupService {
	@Autowired
	private SubjectGroupDAO subjectGroupDAO;
	@Autowired
	private TermWeekDAO termWeekDAO;

	@Override
	@Transactional
	public List<SubjectGroupDTO> findByTermSubject(SubjectGroupRequest s) {
		//Tạo sort
		Sort sort= null;
		if(!s.getProperties().equals("shift")){
			sort = Sort.by(s.getProperties()).ascending();
		}else{
			sort =Sort.by("code").ascending();
		}

		//Nếu type là desc thì thay thành giảm
		if(s.getOrder().equals("desc")){
			sort=sort.descending();
		}
		//Tạo page để lấy
		Pageable pageable = PageRequest.of(s.getPageNum(), s.getRecordPerPage(),sort);
		//Lấy tất cả group cho môn học lên theo loại tìm kiếm
		Page<SubjectGroup> subjectGroupList = null;
		switch (s.getSearchType()){
			case 1:{
				subjectGroupList = this.subjectGroupDAO
						.findByTermSubjectAndLearningDay(s.getTermSubjectId()
								,s.getSearchData().toLowerCase(),pageable);
				break;
			}
			case 2:{
				subjectGroupList = this.subjectGroupDAO
						.findByTermSubjectAndShift(s.getTermSubjectId()
								,s.getSearchData().toLowerCase(),pageable);
				break;
			}
			default:{
				subjectGroupList = this.subjectGroupDAO
						.findByTermSubjectAndCode(s.getTermSubjectId()
								,s.getSearchData().toLowerCase(),pageable);
			}
		}
		//Tìm tất cả tuần học cho kì cuối
		List<TermWeek> termWeekList  = this.termWeekDAO
				.findAllTermWeekByLastTerm();
		//Tạo map lưu tuần học theo số thứ tự
		Map<Long,Integer> termWeekMap = new TreeMap<>();
		for(int i=0;i<termWeekList.size();i++){
			termWeekMap.put(termWeekList.get(i).getId(),i);
		}
		//Lấy tổng số phần tử tìm được
		final long total = subjectGroupList.getTotalElements();
		//Chuyển đổi theo định dạng dto
		List<SubjectGroupDTO>ans= subjectGroupList.toList().stream().map(item->{
			SubjectGroupDTO subjectGroupDTO = new SubjectGroupDTO();
			subjectGroupDTO.setId(item.getId());
			subjectGroupDTO.setLearningDay(item.getLearningDay());
			subjectGroupDTO.setNumberOfTeacher(item.getNumberOfTeacher());
			subjectGroupDTO.setCode(item.getCode());
			subjectGroupDTO.setTotalItem(total);
			List<GroupInfo> groupInfoList = new ArrayList<>(item.getGroupInfo());
			//Sắp xếp lịch học theo kíp
			Collections.sort(groupInfoList,(x,y)->{
				if(x.getShift().getId()>y.getShift().getId()){
					return 1;
				}
				return -1;
			});
			List<GroupInfoDTO> groupInfoDTOList = new ArrayList<>();
			//Chuyển đổi lịch học về dạng mong muốn
			for(GroupInfo groupInfo:groupInfoList) {
				GroupInfoDTO groupInfoDTO = new GroupInfoDTO();
				//Tên phòng học theo định dạng : tên phòng - tên tòa nhà
				groupInfoDTO.setRoom(groupInfo.getRoom().getName() + "-"
						+ groupInfo.getRoom().getBuilding().getName());
				groupInfoDTO.setShift(groupInfo.getShift().getName());
				//thực hiện chuyển các tuần học về định dạng chỉ lấy chữ số cuối
				int[] weekList = new int[termWeekList.size()];
				for (LearningWeek learningWeek : groupInfo.getLearningWeek()) {
					Integer tmp = termWeekMap.get(learningWeek.getTermWeek().getId());
					if(tmp !=null) weekList[tmp]=1;
				}
				//Chuyển đổi tuần học về định dạng mong muốn
				String sWeek ="";
				for(int i= 0 ;i<weekList.length;i++){
					if(weekList[i]==1){
						sWeek+=((i+1)%10);
					}else {
						sWeek += "_";
					}
				}
				groupInfoDTO.setLearningWeek(sWeek);
				groupInfoDTOList.add(groupInfoDTO);
			}
			subjectGroupDTO.setGroupInfo(groupInfoDTOList);
			return subjectGroupDTO;
		}).collect(Collectors.toList());
		int tmp = s.getOrder().equals("asc")?1:-1;
		if(s.getProperties().equals("shift")){
			Collections.sort(ans,(x,y)->tmp*x.getGroupInfo()
					.get(0).getShift().compareTo(y
							.getGroupInfo().get(0).getShift()));
		}
		return ans;
	}
}