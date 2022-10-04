package com.zerobase.fastlms.course.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TakeCourseInput {
    long courseId;
    String userId;

    long takeCourseId;
}
