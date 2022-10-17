package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {
    
    private final BannerService bannerService;
    
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {

        parameter.init();
        List<BannerDto> bannerDtoList = bannerService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(bannerDtoList)) {
            totalCount = bannerDtoList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", bannerDtoList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(
            Model model,
            HttpServletRequest request,
            BannerInput input
    ) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = input.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/banner/add";
    }

    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(
            Model model,
            HttpServletRequest request,
            MultipartFile file,
            BannerInput input
    ) {
        FileUtil fileUtil = new FileUtil();

        String saveFilename = "";
        String urlFilename = "";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            String baseLocalPath = "D:/zerobaseProject/SpringBootProject/fastlms/files";
            String baseUrlPath = "/files";

            String[] arrFilename = fileUtil.getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(newFile.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        input.setFilename(saveFilename);
        input.setUrlFilename(urlFilename);

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = input.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = bannerService.modify(input);

        } else {
            boolean result = bannerService.add(input);
        }

        return "redirect:/admin/banner/list.do";
    }
    
    @PostMapping("/admin/banner/delete.do")
    public String delete(BannerInput input) {

        boolean result = bannerService.delete(input.getIdList());
        
        return "redirect:/admin/banner/list.do";
    }

}
