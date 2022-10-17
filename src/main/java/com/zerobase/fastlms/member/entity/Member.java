package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member implements MemberCode{
    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private boolean emailAuthYn;        //이메일 인증 성공 여부
    private LocalDateTime emailAuthDt;  //이메일 인증 시간
    private String emailAuthKey;        //이메일 인증 키 저장

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private boolean adminYn;

    private String userStatus;      //이용가능상태, 정지상태

    private String zipcode;
    private String address;
    private String addressDetail;

    private LocalDateTime lastLoginDt;
}
