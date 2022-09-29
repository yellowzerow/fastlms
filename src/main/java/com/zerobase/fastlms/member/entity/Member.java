package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private LocalDateTime regDt;

    private boolean emailAuthYn;        //이메일 인증 성공 여부
    private LocalDateTime emailAuthDt;  //이메일 인증 시간
    private String emailAuthKey;        //이메일 인증 키 저장
}
