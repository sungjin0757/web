package firstproject.shop.controller;

import firstproject.shop.domain.Member;
import firstproject.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user/signup")
    public String getSignup(Model model){
        model.addAttribute("MemberForm",new MemberForm());
        return "/signup";
    }

    @PostMapping("/user/signup")
    public String postSignup(MemberForm memberForm){
        System.out.println("memberForm = " + memberForm.getName()+ " "+memberForm.getPassword());
        Member member=new Member();
        member.updateNameAndPassword(memberForm.getName(),memberForm.getPassword());
        memberService.join(member);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
}
