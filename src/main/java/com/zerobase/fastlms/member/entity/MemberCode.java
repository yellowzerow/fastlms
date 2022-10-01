package com.zerobase.fastlms.member.entity;

public interface MemberCode {

    //인증 요청중인 상태
    String MEMBER_STATUS_REQ = "REQ";

    //현재 이용중인 상태
    String MEMBER_STATUS_ING = "ING";

    //현재 정지된 상태
    String MEMBER_STATUS_STOP = "STOP";
}
