package com.zerobase.fastlms.course.mapper;

import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {
    long selectListCount(TakeCourseParam param);
    List<TakeCourseDto> selectList(TakeCourseParam param);
    List<TakeCourseDto> selectListMyCourse(TakeCourseParam param);
}
