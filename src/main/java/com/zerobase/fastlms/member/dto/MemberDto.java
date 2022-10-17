package com.zerobase.fastlms.member.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    LocalDateTime udtDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;

    private String zipcode;
    private String address;
    private String addressDetail;

    long totalCount;
    long seq;

    LocalDateTime lastLoginDt;
    List<LoginHistory> loginHistoryList;

    public static MemberDto of(Member member) {
       return MemberDto.builder()
               .userId(member.getUserId())
               .userName(member.getUserName())
               .phone(member.getPhone())
               .regDt(member.getRegDt())
               .udtDt(member.getUdtDt())
               .emailAuthYn(member.isEmailAuthYn())
               .emailAuthKey(member.getEmailAuthKey())
               .emailAuthDt(member.getEmailAuthDt())
               .resetPasswordKey(member.getResetPasswordKey())
               .resetPasswordLimitDt(member.getResetPasswordLimitDt())
               .adminYn(member.isAdminYn())
               .userStatus(member.getUserStatus())
               .zipcode(member.getZipcode())
               .address(member.getAddress())
               .addressDetail(member.getAddressDetail())
               .lastLoginDt(member.getLastLoginDt())
               .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }
    public String getUdtDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return udtDt != null ? udtDt.format(formatter) : "";
    }

    public String getLastLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return lastLoginDt != null ? lastLoginDt.format(formatter) : "";
    }
}
