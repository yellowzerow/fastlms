package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput input);     //강좌 등록
    boolean set(CourseInput input);     //강좌 정보 수정
    List<CourseDto> list(CourseParam param);  //강좌 목록
    CourseDto getById(long id);         //강좌 상세 정보
    boolean delete(String idList);      //강좌 삭제

    //일반 사용자
    List<CourseDto> frontList(CourseParam param);     //프론트 강좌 목록

    CourseDto frontDetail(long id);     //프론트 강좌 상세 정보

    ServiceResult req(TakeCourseInput input);     //수강 신청
}
