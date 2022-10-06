package com.zerobase.fastlms.course.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TakeCourseParam extends CommonParam {

    long id;
    String status;

    String userId;

    long searchCourseId;
}
