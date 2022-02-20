package com.project.calendarservice.service;

import com.project.calendarservice.domain.Member;
import com.project.calendarservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public int login(String loginId, String loginPw, HttpServletRequest request){
        Optional<Member> member = memberRepository.findByLoginId(loginId);

        if (member.isPresent()) {
            if (member.get().getLoginPw().equals(loginPw)) {
                request.getSession().setAttribute("user", loginId); //로그인에 성공할 경우 세션 값 저장
                log.info("login success");
                return 1; //로그인 성공

            } else {
                log.info("login fail");
                return 0; //로그인 실패
            }
        } else {
            log.info("login fail");
            return 0; //로그인 실패
        }
    }
}
