package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userId;
    String clientIp;
    String userAgent;
    LocalDateTime loginDt;
}
