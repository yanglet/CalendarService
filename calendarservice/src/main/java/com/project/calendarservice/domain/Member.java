package com.project.calendarservice.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String loginPw;
    private String name;
    private int age;
    private String gender;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    protected Member(){}
    public Member(String loginId, String loginPw, String name, int age, String gender, UserType userType) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.userType = userType;
    }
}
