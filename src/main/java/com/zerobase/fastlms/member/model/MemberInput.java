package com.zerobase.fastlms.member.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class MemberInput {
    private String userId;
    private String userName;
    private String password;
    private String phone;

    private String newPassword;

    private String zipcode;
    private String address;
    private String addressDetail;
}
