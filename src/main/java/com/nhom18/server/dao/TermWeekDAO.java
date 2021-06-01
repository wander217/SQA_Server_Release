package com.nhom18.server.dao;

import com.nhom18.server.entity.group.TermWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermWeekDAO extends JpaRepository<TermWeek,Long> {
    @Query("SELECT tw FROM TermWeek tw " +
            "WHERE tw.term.id IN(SELECT MAX(t.id) FROM Term t) " +
            "ORDER BY tw.startDate asc")
    List<TermWeek> findAllTermWeekByLastTerm();
}
