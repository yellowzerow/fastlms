package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.dto.MemberDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.service.TakeCourseService;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TakeCourseService takeCourseService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(
            Model model,
            ResetPasswordInput input
    ) {

        boolean result = false;
        try {
            result = memberService.sendResetPassword(input);
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("result", result);

        return "member/find_password_result";
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
    public String memberInfo(
            Model model,
            Principal principal
    ) {
        String userId = principal.getName();

        MemberDto detail = memberService.detail(userId);
        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(
            Model model,
            MemberInput input,
            Principal principal
    ) {
        String userId = principal.getName();
        input.setUserId(userId);

        ServiceResult result = memberService.updateMember(input);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(
            Model model,
            Principal principal
    ) {
        String userId = principal.getName();

        MemberDto detail = memberService.detail(userId);
        model.addAttribute("detail", detail);

        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(
            Model model,
            MemberInput input,
            Principal principal
    ) {
        String userId = principal.getName();
        input.setUserId(userId);

        ServiceResult result = memberService.updateMemberPassword(input);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/take-course")
    public String memberTakeCourse(
            Model model,
            Principal principal
    ) {
        String userId = principal.getName();
        List<TakeCourseDto> list = takeCourseService.myCourse(userId);

        model.addAttribute("list", list);

        return "member/take-course";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(
            Model model,
            HttpServletRequest request
    ){

        String uuid = request.getParameter("id");

        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "member/reset_password";
    }
    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(
            Model model,
            ResetPasswordInput input
    ) {
        boolean result = false;

        try {
            result = memberService.resetPassword(input.getId(), input.getPassword());
        } catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("result", result);

        return "member/reset_password_result";
    }

    @GetMapping("/member/withdraw")
    public String memberWithdraw(Model model) {
        return "member/withdraw";
    }

    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(
            Model model,
            MemberInput input,
            Principal principal
    ) {
        String userId = principal.getName();

        ServiceResult result = memberService.withdraw(userId, input.getPassword());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/logout";
    }
}
