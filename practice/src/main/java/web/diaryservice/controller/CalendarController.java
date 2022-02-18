package web.diaryservice.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.diaryservice.domain.Calendar;
import web.diaryservice.repository.CalendarRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("calendar.do")
public class CalendarController {

    private final CalendarRepository calendarRepository;

    public CalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @GetMapping(params="method=list")
    public String list() {
        return "basic/calendar";
    }

    @GetMapping(params="method=data")
    public @ResponseBody List<Calendar> data(Model model, HttpServletRequest request) {
        List<Calendar> calendars = calendarRepository.calendarList(request);
        return calendars;
    }

    @GetMapping("/add")
    public String addForm() {return "basic/addcalendar";}
    @PostMapping("/add")
    public String add(@ModelAttribute Calendar calendar, HttpServletRequest request){
        calendarRepository.save(calendar, request);
        return "redirect:/diary/calendar"; //PRG
    }

}

