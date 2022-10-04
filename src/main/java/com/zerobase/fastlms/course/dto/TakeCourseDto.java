package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.TakeCourse;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseDto {

    long id;
    long courseId;
    String userId;

    long payPrice;
    String status; //상태(수강신청, 결제완료, 수강취소)

    LocalDateTime regDt;

    //join
    String userName;
    String phone;
    String subject;

    long totalCount;
    long seq;

    public static TakeCourseDto of(TakeCourse takeCourse) {
        return TakeCourseDto.builder()
                .id(takeCourse.getId())
                .courseId(takeCourse.getCourseId())
                .userId(takeCourse.getUserId())
                .payPrice(takeCourse.getPayPrice())
                .status(takeCourse.getStatus())
                .regDt(takeCourse.getRegDt())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
