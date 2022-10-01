package com.zerobase.fastlms.admin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInput {

    long id;
    String categoryName;
    int sortValue;
    boolean usingYn;

}
