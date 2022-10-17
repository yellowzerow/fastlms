package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.member.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController extends BaseController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam param) {

        param.init();

        List<MemberDto> members = memberService.list(param);

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = param.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, param.getPageSize(), param.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam param) {

        param.init();

        MemberDto member = memberService.detail(param.getUserId());
        model.addAttribute("member", member);

        return "admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput input) {

        boolean result = memberService.updateStatus(input.getUserId(), input.getUserStatus());

        return "redirect:/admin/member/detail.do?userId=" + input.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String password(Model model, MemberInput input) {

        boolean result = memberService.updatePassword(input.getUserId(), input.getPassword());

        return "redirect:/admin/member/detail.do?userId=" + input.getUserId();
    }


}
