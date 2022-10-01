package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;

    long totalCount;
    long seq;

    public static MemberDto of(Member member) {
       return MemberDto.builder()
               .userId(member.getUserId())
               .userName(member.getUserName())
               .phone(member.getPhone())
               .regDt(member.getRegDt())
               .emailAuthYn(member.isEmailAuthYn())
               .emailAuthKey(member.getEmailAuthKey())
               .emailAuthDt(member.getEmailAuthDt())
               .resetPasswordKey(member.getResetPasswordKey())
               .resetPasswordLimitDt(member.getResetPasswordLimitDt())
               .adminYn(member.isAdminYn())
               .userStatus(member.getUserStatus())
               .build();
    }
}
