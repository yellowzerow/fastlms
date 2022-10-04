package com.zerobase.fastlms.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult {
    boolean result;
    String message;

    public ServiceResult(boolean result) {
        this.result = result;
    }
}
