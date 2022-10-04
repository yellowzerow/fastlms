package com.zerobase.fastlms.admin.common.model;

import lombok.*;

@Getter
@Setter
public class ResponseResult {
    ResponseResultHeader header;
    Object body;

    public ResponseResult(boolean result, String message) {
        header = new ResponseResultHeader(result, message);
    }

    public ResponseResult(boolean result) {
        header = new ResponseResultHeader(result);
    }
}
