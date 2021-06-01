package com.nhom18.server.dao;

import com.nhom18.server.entity.group.TermSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermSubjectDAO extends PagingAndSortingRepository<TermSubject,Long> {
    //Lấy môn học của kì mới cùng với sô nhóm đã đăng kí của giảng viên trong kì đó
    @Query("SELECT r.subjectGroup.termSubject ,COUNT (r)" +
            "FROM Registration r WHERE r.isEnable=true AND r.teacher.id=:tchId " +
            "AND r.subjectGroup.termSubject.term.id =:tId " +
            "GROUP BY r.subjectGroup.termSubject")
    List<Object[]> getTermSubjectWithRegCountByTeacher(@Param("tchId") long tchId,
                                                       @Param("tId") long tId);

    //Lấy môn học đã giao của kì mới với số lượng giảng viên đăng kí đủ
    @Query("SELECT a.termSubject,COUNT(a) FROM AssignedSubject a " +
            "WHERE a.termSubject.term.id IN (SELECT MAX(t.id) FROM Term t) " +
            "AND a.termSubject.subject.name LIKE %:filter% " +
            "AND a.numberOfGroup IN (SELECT COUNT(r) From Registration r " +
            "WHERE r.subjectGroup.termSubject.id=a.termSubject.id " +
            "AND r.teacher.id=a.teacher.id AND r.isEnable=true) GROUP BY a.termSubject")
    List<Object[]> getTermSubjectWithRegCount(Pageable pageable, @Param("filter") String filter);

    //Lấy môn học đã giao cùng tổng số giảng viên được giao
    @Query("SELECT a.termSubject,COUNT(a) FROM AssignedSubject a " +
            "WHERE a.termSubject.subject.name LIKE %:filter% " +
            "AND a.termSubject.term.id = :tId GROUP BY a.termSubject")
    Page<Object[]> getTermSubjectWithTeacherCount(Pageable pageable,
                              @Param("filter")String filter,@Param("tId")long tId);
}
