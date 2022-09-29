package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            MemberInput input
    ) {
        /*String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        System.out.println("userId: " + userId);
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);
        System.out.println("phone: " + phone);*/

        //System.out.println(input.toString());

        boolean result = memberService.register(input);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    // http://www.naver.com/news/list.do
    // https://www.naver.com/cafe/detail.do
    // 프로토콜://도메인(IP)/****/****?쿼리스트링(파라미터)
    @GetMapping("/member/email-auth")
    public String emailAuth(
            Model model,
            HttpServletRequest request
    ) {
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo() {
        return "member/info";
    }
}
