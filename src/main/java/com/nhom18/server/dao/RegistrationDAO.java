package com.nhom18.server.dao;


import com.nhom18.server.entity.registration.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationDAO extends PagingAndSortingRepository<Registration,Long> {
	//Tìm kiếm cho việc hiển thị ra những đăng ki của nhóm
	@Query("SELECT r FROM Registration r WHERE r.isEnable=true AND r.subjectGroup.termSubject.id=:tsId")
	List<Registration> findAllEnableByTermSubject(@Param("tsId")long tsId);

	//Tìm kiếm đăng kí còn hiệu lực cho giảng viên
	@Query("SELECT r FROM Registration r WHERE r.teacher.id=:tchId " +
			"AND r.isEnable=true AND r.subjectGroup.termSubject.term.id=:tId")
	List<Registration> findAllEnableByTeacher(@Param("tchId")long tchId,@Param("tId")long tId);

	//Tìm tất cả đăng kí của nhóm môn học có hiệu lực
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.id=:sgId AND r.isEnable=true ")
	List<Registration> findAllBySubjectGroup(@Param("sgId")long sgId);

	//Cập nhật lại các đăng kí theo kì
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.termSubject.term.id=:tId AND r.isEnable=true ")
	List<Registration> findAllEnableByTerm(@Param("tId")long tId);

	//Tìm kiếm lịch sử của giảng viên theo mã nhóm môn học
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.termSubject.term.id " +
			"IN (SELECT MAX(t.id) FROM Term t) AND r.teacher.id=:tchId " +
			"AND lower(r.subjectGroup.code) LIKE %:filter%")
	Page<Registration> findAllBySubjectGroupCode(@Param("filter") String filter,
												 @Param("tchId")long tchId, Pageable pageable);

	//Tìm kiếm lịch sử của giảng viên theo tên môn học
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.termSubject.term.id " +
			"IN (SELECT MAX(t.id) FROM Term t) AND r.teacher.id=:tchId " +
			"AND lower(r.subjectGroup.termSubject.subject.name) LIKE %:filter%")
	Page<Registration> findAllBySubjectName(@Param("filter") String filter,
											@Param("tchId")long tchId, Pageable pageable);
}