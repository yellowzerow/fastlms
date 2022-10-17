package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {

    boolean add(BannerInput input);             //배너 등록하기

    boolean modify(BannerInput input);          //배너 수정하기

    List<BannerDto> list(BannerParam param);    //관리자가 볼 배너 목록

    BannerDto getById(long id);                 //배너 상세 정보

    boolean delete(String idList);              //배너 삭제하기

    List<BannerDto> frontViewList();            //프론트에 보여줄 배너 목록
}
