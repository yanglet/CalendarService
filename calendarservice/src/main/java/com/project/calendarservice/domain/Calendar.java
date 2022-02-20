package com.project.calendarservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class Calendar {
    @Id @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Calendar(){}

    public void addDefault(String end, String textColor, String backgroundColor, String borderColor){
        this.end = end;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
    public void addMember(Member member){
        this.member = member;
    }
}
