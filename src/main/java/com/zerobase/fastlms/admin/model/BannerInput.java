package com.zerobase.fastlms.admin.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BannerInput {
    
    long id;

    String filename;
    String urlFilename;
    String alterText;
    String linkedUrl;
    int sortValue;
    boolean usingYn;
    String howOpen;
    LocalDateTime regDt;

    String idList;

}
