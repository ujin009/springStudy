package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        // 멤버 생성
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // home 화면으로 보냄
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 모든 회원 정보를 리스트에 담음
        List<Member> members = memberService.findMembers();
        // 리스트를 모델에 담아서 html로 보냄
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
