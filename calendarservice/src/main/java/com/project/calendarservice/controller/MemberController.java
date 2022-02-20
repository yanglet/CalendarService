package com.project.calendarservice.controller;

import com.project.calendarservice.domain.Member;
import com.project.calendarservice.domain.MemberForm;
import com.project.calendarservice.repository.MemberRepository;
import com.project.calendarservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/diary/main")
    public String mainform(){
        return "basic/main";
    }

    @GetMapping("/diary/join")
    public String joinform(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "basic/join";
    }
    @PostMapping("/diary/join")
    public String join(@Valid MemberForm memberForm, BindingResult bindingResult, Model model){
        if(validateDuplicateMember(memberForm)){
            model.addAttribute("validate",true);
            log.info("중복되는 아이디입니다.");
            return "basic/join";
        }
        if (bindingResult.hasErrors()) {
            log.info("binding result = {}", bindingResult);
            return "basic/join"; // 현재 폼을 다시 랜더링
        }
        Member member = new Member(memberForm.getLoginId(),memberForm.getLoginPw(),
                memberForm.getName(), memberForm.getAge(), memberForm.getGender(),
                memberForm.getUserType());

        memberRepository.save(member);
        return "redirect:/diary/main"; //PRG
    }

    @GetMapping("/diary/members")
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "basic/members";
    }

    @GetMapping("/diary/login")
    public String loginform(){
        return "basic/login";
    }
    @PostMapping("/diary/login")
    public String login(@RequestParam String loginId,
                        @RequestParam String loginPw,
                        HttpServletRequest request){
        int result = memberService.login(loginId, loginPw, request);

        if(result == 1) log.info("login success");
        else log.info("login fail");

        return "redirect:/diary/main"; //PRG
    }

    @GetMapping("/diary/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "basic/main";
    }

    @GetMapping("/diary/calendar")
    public String calendar(){
        return "basic/calendar";
    }

    private boolean validateDuplicateMember(MemberForm memberForm) {
        if(memberRepository.findByLoginId(memberForm.getLoginId())
                .isPresent()) return true; //중복이면 true
        else return false;
    }

}
