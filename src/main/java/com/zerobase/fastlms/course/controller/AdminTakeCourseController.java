package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import com.zerobase.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController{

    private final TakeCourseService takeCourseService;
    private final CourseService courseService;

    @GetMapping("/admin/take-course/list.do")
    public String list(Model model, TakeCourseParam param,
                       BindingResult bindingResult
    ) {
        param.init();

        List<TakeCourseDto> takeCourseList = takeCourseService.list(param);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(takeCourseList)) {
            totalCount = takeCourseList.get(0).getTotalCount();
        }
        String queryString = param.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, param.getPageSize(), param.getPageIndex(), queryString);

        model.addAttribute("list", takeCourseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        List<CourseDto> courseAllList = courseService.listAll();
        model.addAttribute("courseAllList", courseAllList);

        return "admin/take-course/list";
    }

    @PostMapping("/admin/take-course/status.do")
    public String status(Model model, TakeCourseParam param) {

        param.init();

        ServiceResult result = takeCourseService.updateStatus(param.getId(), param.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/admin/take-course/list.do";
    }

}
