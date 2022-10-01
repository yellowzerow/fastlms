package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();
    boolean add(String categoryName);   //카테고리 신규 추가

    boolean update(CategoryInput input);    //카테고리 수정

    boolean delete(long id);            //카테고리 삭제
}
