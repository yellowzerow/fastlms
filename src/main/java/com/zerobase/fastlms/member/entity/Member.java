package com.zerobase.fastlms.member.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Member {
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private LocalDateTime regDt;
}
