package com.nhom18.server.dao;

import com.nhom18.server.entity.group.SubjectGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface SubjectGroupDAO extends PagingAndSortingRepository<SubjectGroup,Long> {
	//Tìm kiếm theo môn học và mã nhóm
	@Query("SELECT s FROM SubjectGroup s WHERE s.termSubject.id=:tId " +
			"AND lower(s.code) LIKE %:filter%")
	Page<SubjectGroup> findByTermSubjectAndCode(@Param("tId")long tId
			,@Param("filter")String filter,Pageable pageable);

	//Tìm kiếm theo môn học và ngày học
	@Query("SELECT s FROM SubjectGroup s WHERE s.termSubject.id=:tId " +
			"AND lower(s.learningDay) LIKE %:filter%")
	Page<SubjectGroup> findByTermSubjectAndLearningDay(@Param("tId")long tId
			,@Param("filter")String filter,Pageable pageable);

	//Tìm kiếm theo môn học và kíp học
	@Query("Select s FROM  SubjectGroup s  WHERE s.id " +
			"IN (SELECT g.subjectGroup.id From GroupInfo g " +
			"WHERE g.subjectGroup.termSubject.id=:tId " +
			"AND g.shift.name LIKE %:filter%)")
	Page<SubjectGroup> findByTermSubjectAndShift(@Param("tId")long tId
			,@Param("filter")String filter,Pageable pageable);
}