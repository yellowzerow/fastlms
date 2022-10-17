package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    
    Long id;

    String filename;
    String urlFilename;
    String alterText;
    String linkedUrl;
    int sortValue;
    boolean usingYn;
    String howOpen;
    LocalDateTime regDt;

    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .alterText(banner.getAlterText())
                .linkedUrl(banner.getLinkedUrl())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .howOpen(banner.getHowOpen())
                .regDt(banner.getRegDt())
                .build();
    }

    public static List<BannerDto> of(List<Banner> list) {
        if (list == null) {
            return null;
        }

        List<BannerDto> bannerList = new ArrayList<>();
        for (Banner banner : list) {
            bannerList.add(BannerDto.of(banner));
        }

        return bannerList;
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
