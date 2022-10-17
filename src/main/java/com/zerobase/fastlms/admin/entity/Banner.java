package com.zerobase.fastlms.admin.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Banner implements OpenCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String filename;        //사진 파일 이름
    String urlFilename;     //사진 url 이름
    String alterText;       //대체 텍스트
    String linkedUrl;       //이동 url 정보
    int sortValue;          //정렬순서
    boolean usingYn;        //프론트 표시 여부
    String howOpen;         //새창 열림, 현재 페이지 열림
    LocalDateTime regDt;    //등록일
}
