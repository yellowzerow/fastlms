package com.zerobase.fastlms.admin.service.impl;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import com.zerobase.fastlms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput input) {
        Banner banner = Banner.builder()
                .filename(input.getFilename())
                .urlFilename(input.getUrlFilename())
                .alterText(input.getAlterText())
                .linkedUrl(input.getLinkedUrl())
                .sortValue(input.getSortValue())
                .usingYn(input.isUsingYn())
                .howOpen(input.getHowOpen())
                .regDt(LocalDateTime.now())
                .build();
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean modify(BannerInput input) {

        Optional<Banner> optionalBanner = bannerRepository.findById(input.getId());
        if (optionalBanner.isEmpty()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setFilename(input.getFilename());
        banner.setUrlFilename(input.getUrlFilename());
        banner.setAlterText(input.getAlterText());
        banner.setLinkedUrl(input.getLinkedUrl());
        banner.setSortValue(input.getSortValue());
        banner.setUsingYn(input.isUsingYn());
        banner.setHowOpen(input.getHowOpen());
        banner.setRegDt(LocalDateTime.now());
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam param) {

        long totalCount = bannerMapper.selectListCount(param);

        List<BannerDto> list = bannerMapper.selectList(param);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - param.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return BannerDto.of(bannerRepository.getById(id));
    }

    @Override
    public boolean delete(String idList) {

        if (idList != null && idList.length() > 0) {

            String[] ids = idList.split(",");

            for (String x : ids) {

                long id = 0L;

                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }

            }

        }

        return true;
    }

    @Override
    public List<BannerDto> frontViewList() {
        List<BannerDto> bannerList = null;

        Optional<List<Banner>> optionalBanners = bannerRepository.findByUsingYnOrderBySortValue(true);

        if (optionalBanners.isPresent()) {
            List<Banner> banners = optionalBanners.get();
            bannerList = BannerDto.of(banners);
        }

        return bannerList;
    }
}
