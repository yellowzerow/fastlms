package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam param) {

        param.init();

        List<CourseDto> courseList = courseService.list(param);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = param.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, param.getPageSize(), param.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";
    }

    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(
            Model model,
            HttpServletRequest request,
            CourseInput input
    ) {

        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (editMode) {
            long id = input.getId();

            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                //error 처리
                model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);
        return "admin/course/add";
    }

    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(
            Model model,
            HttpServletRequest request,
            CourseInput input
    ) {
        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = input.getId();

            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                //error 처리
                model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = courseService.set(input);
        } else {
            boolean result = courseService.add(input);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String delete(
            Model model,
            HttpServletRequest request,
            CourseInput input
    ) {
        boolean result = courseService.delete(input.getIdList());

        return "redirect:/admin/course/list.do";
    }
}
