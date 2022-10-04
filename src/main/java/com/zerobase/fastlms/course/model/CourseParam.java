package com.zerobase.fastlms.course.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CourseParam extends CommonParam {
    long id;
    long categoryId;
}
