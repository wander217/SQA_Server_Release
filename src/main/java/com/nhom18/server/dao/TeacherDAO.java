package com.nhom18.server.dao;

import com.nhom18.server.entity.user.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherDAO extends PagingAndSortingRepository<Teacher,Long> {
	//Lấy lên theo tên đăng nhập
	@Query("SELECT t FROM Teacher t WHERE t.account.username=:u")
    Optional<Teacher> findByUsername(@Param("u") String u);

	//Lấy danh sách giảng viên được đã đăng kí đủ theo mã giảng viên
	@Query("SELECT a.teacher FROM AssignedSubject a " +
			"WHERE a.termSubject.id=:tId AND a.teacher.tchCode " +
			"LIKE %:filter% AND a.numberOfGroup IN (SELECT COUNT(r) " +
			"FROM Registration r WHERE r.isEnable= true " +
			"AND r.teacher.id =a.teacher.id " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	Page<Teacher> findRememberByCode(@Param("tId") long tId
			, @Param("filter") String filter, Pageable pageable);

	//Lấy danh sách giảng viên được đã đăng kí đủ theo tên giảng viên
	@Query("SELECT a.teacher FROM AssignedSubject a WHERE a.termSubject.id=:tId " +
			"AND LOWER(CONCAT(a.teacher.fullname.firstname, " +
			"CASE WHEN a.teacher.fullname.middlename NOT LIKE '' THEN " +
			"CONCAT(' ',a.teacher.fullname.middlename,' ') ELSE ' ' " +
			"END, a.teacher.fullname.lastname)) " +
			"LIKE LOWER(CONCAT('%',:filter,'%')) AND a.numberOfGroup " +
			"IN (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.isEnable= true AND r.teacher.id =a.teacher.id " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	Page<Teacher> findRememberByName(@Param("tId") long tId
			,@Param("filter") String filter, Pageable pageable);

	//Lấy danh sách giảng viên được đã đăng kí thiếu bằng code
	@Query("SELECT a.teacher FROM AssignedSubject a " +
			"WHERE a.termSubject.id=:tId AND a.teacher.tchCode LIKE %:filter% " +
			"AND a.numberOfGroup NOT IN (SELECT COUNT(r) FROM Registration r WHERE r.isEnable= true " +
			"AND r.teacher.id =a.teacher.id AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	Page<Teacher> findForgotByCode(@Param("tId") long tId
			,@Param("filter") String filter, Pageable pageable);

	//Lấy danh sách giảng viên được đã đăng kí thiếu bằng tên
	@Query("SELECT a.teacher FROM AssignedSubject a WHERE a.termSubject.id=:tId " +
			"AND LOWER(CONCAT(a.teacher.fullname.firstname, " +
			"CASE WHEN a.teacher.fullname.middlename NOT LIKE '' THEN " +
			"CONCAT(' ',a.teacher.fullname.middlename,' ') ELSE ' ' " +
			"END, a.teacher.fullname.lastname)) " +
			"LIKE LOWER(CONCAT('%',:filter,'%')) AND a.numberOfGroup " +
			"NOT IN (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.isEnable= true AND r.teacher.id =a.teacher.id " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	Page<Teacher> findForgotByName(@Param("tId") long tId
			, @Param("filter") String filter, Pageable pageable);

	//Lấy tất cả giảng viên đã được giao cùng số lượng môn đã đăng kí đủ
	@Query("SELECT a.teacher,COUNT(a) FROM AssignedSubject a " +
			"WHERE a.termSubject.term.id in (SELECT MAX(t.id) FROM Term t) " +
			"AND a.numberOfGroup IN (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.teacher.id= a.teacher.id AND r.isEnable=true " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id) GROUP BY a.teacher")
    List<Object[]> findTeacherWithNumberOfFullRegGroup();

	//Lấy tất cả giảng viên cùng số môn được giao
	@Query( "SELECT a.teacher,COUNT(a) FROM AssignedSubject a " +
			"WHERE a.termSubject.term.id IN (SELECT MAX(t.id) FROM Term t) " +
			"AND LOWER(CONCAT(a.teacher.fullname.firstname, CASE " +
				"WHEN a.teacher.fullname.middlename NOT LIKE '' THEN " +
					"CONCAT(' ',a.teacher.fullname.middlename,' ') " +
				"ELSE ' ' END, a.teacher.fullname.lastname)) " +
			"LIKE LOWER(CONCAT('%',:filter,'%')) GROUP BY a.teacher")
    List<Object[]> findTeacherWithNumberOfGroup(@Param("filter") String filter);
}