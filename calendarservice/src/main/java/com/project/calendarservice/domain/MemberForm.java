package com.project.calendarservice.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    @NotEmpty(message = "이메일은 필수 입력란입니다")
    private String loginId;
    @NotNull
    @Size(min = 6, max = 14, message = "비밀번호는 6자리 이상 14자리 이하여야 합니다")
    private String loginPw;
    @NotNull
    @Size(min = 2, message = "이름은 최소 2글자 이상이여야 합니다")
    private String name;
    private int age;
    private String gender;
    private UserType userType;

}
