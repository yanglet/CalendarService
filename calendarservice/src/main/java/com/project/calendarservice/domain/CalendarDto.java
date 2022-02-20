package com.project.calendarservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarDto {
    private Long groupId;
    private String title;
    private String writer;
    private String content;
    private String start;
    private String end;
    private boolean allDay;
    private String textColor;
    private String backgroundColor;
    private String borderColor;

    public CalendarDto(Calendar calendar) {
        this.groupId = calendar.getGroupId();
        this.title = calendar.getTitle();
        this.writer = calendar.getWriter();
        this.content = calendar.getContent();
        this.start = calendar.getStart();
        this.end = calendar.getEnd();
        this.allDay = true;
        this.textColor = calendar.getTextColor();
        this.backgroundColor = calendar.getBackgroundColor();
        this.borderColor = calendar.getBorderColor();
    }
}
