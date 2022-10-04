package com.zerobase.fastlms.course.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long categoryId;

    String imagePath;
    String keyword;
    String subject;

    @Column(length = 1000)
    String summary;

    @Lob //텍스트 양이 많아서 따로 지정해줌
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;

    LocalDateTime regDt;
    LocalDateTime udtDt;
}
