package web.diaryservice.repository;

import web.diaryservice.domain.Calendar;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface CalendarRepository {
    List<Calendar> calendarList(HttpServletRequest request);

    void save(Calendar calendar, HttpServletRequest request);
}
