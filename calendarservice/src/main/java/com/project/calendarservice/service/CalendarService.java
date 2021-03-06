package com.project.calendarservice.service;

import com.project.calendarservice.domain.Calendar;
import com.project.calendarservice.domain.Member;
import com.project.calendarservice.repository.CalendarRepository;
import com.project.calendarservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;

    public void save(Calendar calendar, Member member) {
        calendar.addMember(member);
        calendar.addDefault(calendar.getStart(),"#ffffff","#45c99a","#ffffff");
        calendarRepository.save(calendar);
    }

    @Transactional(readOnly = true)
    public List<Calendar> calendarListFindByUser(String loginId) {
        return calendarRepository.calendarListFindByUser(loginId);
    }
}
