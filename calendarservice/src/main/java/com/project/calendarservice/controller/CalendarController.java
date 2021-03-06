package com.project.calendarservice.controller;

import com.project.calendarservice.domain.Calendar;
import com.project.calendarservice.domain.CalendarDto;
import com.project.calendarservice.domain.Member;
import com.project.calendarservice.repository.CalendarRepository;
import com.project.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("calendar.do")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarRepository calendarRepository;
    private final CalendarService calendarService;

    @GetMapping(params="method=data")
    public @ResponseBody //일정 정보 api
    List<CalendarDto> data(Model model, @SessionAttribute(name = "user", required = false) Member member) {
        return calendarService.calendarListFindByUser(member.getLoginId())
                .stream()
                .map(CalendarDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/add")
    public String addForm() {return "basic/addcalendar";}
    @PostMapping("/add")
    public String add(@ModelAttribute Calendar calendar, @SessionAttribute(name = "user", required = false) Member member){
        calendarService.save(calendar, member);
        return "redirect:/diary/calendar"; //PRG
    }
}
