package com.zerobase.fastlms.admin.common.model;

import lombok.*;

@Getter
@Setter
public class ResponseResultHeader {
    boolean result;
    String message;

    public ResponseResultHeader(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseResultHeader(boolean result) {
        this.result = result;
    }
}
