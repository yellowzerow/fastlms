package com.zerobase.fastlms.course.service.impl;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.repository.CourseRepository;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TakeCourseRepository takeCourseRepository;

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(CourseInput input) {

        LocalDate saleEndDt = getLocalDate(input.getSaleEndDtText());

        Course course = Course.builder()
                .categoryId(input.getCategoryId())
                .subject(input.getSubject())
                .keyword(input.getKeyword())
                .summary(input.getSummary())
                .contents(input.getContents())
                .price(input.getPrice())
                .salePrice(input.getSalePrice())
                .saleEndDt(saleEndDt)
                .regDt(LocalDateTime.now())
                .filename(input.getFilename())
                .urlFilename(input.getUrlFilename())
                .build();
        courseRepository.save(course);

        return true;
    }

    @Override
    public boolean set(CourseInput input) {

        LocalDate saleEndDt = getLocalDate(input.getSaleEndDtText());

        Optional<Course> optionalCourse = courseRepository.findById(input.getId());
        if (optionalCourse.isEmpty()) {
            //수정할 데이터 없음
            return false;
        }

        Course course = optionalCourse.get();
        course.setCategoryId(input.getCategoryId());
        course.setSubject(input.getSubject());
        course.setKeyword(input.getKeyword());
        course.setSummary(input.getSummary());
        course.setContents(input.getContents());
        course.setPrice(input.getPrice());
        course.setSalePrice(input.getSalePrice());
        course.setSaleEndDt(saleEndDt);
        course.setUdtDt(LocalDateTime.now());
        course.setFilename(input.getFilename());
        course.setUrlFilename(input.getUrlFilename());
        courseRepository.save(course);

        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam param) {

        long totalCount = courseMapper.selectListCount(param);

        List<CourseDto> list = courseMapper.selectList(param);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (CourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - param.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public CourseDto getById(long id) {
        return courseRepository.findById(id).map(CourseDto::of)
                .orElse(null);
    }

    @Override
    public boolean delete(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (id > 0) {
                    courseRepository.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public List<CourseDto> frontList(CourseParam param) {

        if (param.getCategoryId() < 1) {
            List<Course> courseList = courseRepository.findAll();
            return CourseDto.of(courseList);
        }

        return courseRepository.findByCategoryId(param.getCategoryId())
                .map(CourseDto::of).orElse(null);

    }

    @Override
    public CourseDto frontDetail(long id) {
        return courseRepository.findById(id)
                .map(CourseDto::of).orElse(null);
    }

    @Override
    public ServiceResult req(TakeCourseInput input) {

            ServiceResult result = new ServiceResult();

            Optional<Course> optionalCourse = courseRepository.findById(input.getCourseId());
            if (optionalCourse.isEmpty()) {
                result.setResult(false);
                result.setMessage("강좌 정보가 존재하지 않습니다.");
                return result;
            }

            Course course = optionalCourse.get();

            //이미 신청정보가 있는지 확인
            String[] statusList = {TakeCourse.STATUS_REQ, TakeCourse.STATUS_COMPLETE};
            long count = takeCourseRepository.countByCourseIdAndUserIdAndStatusIn(
                    course.getId(), input.getUserId(), Arrays.asList(statusList));

            if (count > 0) {
                result.setResult(false);
                result.setMessage("이미 신청한 강좌입니다.");
                return result;
            }

            TakeCourse takeCourse = TakeCourse.builder()
                    .courseId(course.getId())
                    .userId(input.getUserId())
                    .payPrice(course.getSalePrice())
                    .regDt(LocalDateTime.now())
                    .status(TakeCourse.STATUS_REQ)
                    .build();
            takeCourseRepository.save(takeCourse);

            result.setResult(true);
            result.setMessage("");
            return result;
    }

    @Override
    public List<CourseDto> listAll() {
        List<Course> courseList = courseRepository.findAll();

        return CourseDto.of(courseList);
    }
}