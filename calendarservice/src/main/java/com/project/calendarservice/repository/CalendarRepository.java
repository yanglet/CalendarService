package com.project.calendarservice.repository;

import com.project.calendarservice.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("select c from Calendar c where c.member.loginId=:loginId")
    List<Calendar> calendarListFindByUser(@Param("loginId") String loginId);
}
