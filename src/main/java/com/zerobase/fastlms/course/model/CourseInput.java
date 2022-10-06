package com.zerobase.fastlms.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInput {
    long id;
    long categoryId;
    String subject;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    String idList;

    String filename;
    String urlFilename;
}
