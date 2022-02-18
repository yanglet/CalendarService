package web.diaryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.diaryservice.domain.Member;
import web.diaryservice.domain.MemberForm;
import web.diaryservice.repository.MemberRepository;
import web.diaryservice.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class MemberController {

    MemberRepository memberRepository;
    MemberService memberService;

    //@Autowired 생략가능
    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

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
        Member member = new Member(memberForm.getMem_id(),memberForm.getMem_pw(),
                memberForm.getMem_name(), memberForm.getMem_age(), memberForm.getMem_gender(),
                memberForm.getMem_usertype());

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
    public String login(@RequestParam String mem_id,
                        @RequestParam String mem_pw,
                        HttpServletRequest request){
        int result = memberService.login(mem_id, mem_pw, request);
        HttpSession session = request.getSession();

        if(result == 1) log.info("login success");
        else log.info("login fail");

        log.info("{}",session.getAttribute("mem_id"));

        return "redirect:/diary/main"; //PRG
    }

    @GetMapping("/diary/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return "basic/main";
    }

    @GetMapping("/diary/calendar")
    public String calendar(){
        return "basic/calendar";
    }

    private boolean validateDuplicateMember(MemberForm memberForm) {
        if(memberRepository.findById(memberForm.getMem_id())
                .isPresent()) return true; //중복이면 true
        else return false;
    }
}
