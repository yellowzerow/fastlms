package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput input);

    boolean emailAuth(String uuid); //uuid에 해당하는 계정을 활성화

    //입력한 이메일로 비밀번호 초기화 정보를 저장
    boolean sendResetPassword(ResetPasswordInput input);

    //입력받은 uuid에 대해서 password 초기화
    boolean resetPassword(String id, String password);

    //입력받은 uuid가 유효한지 확인
    boolean checkResetPassword(String uuid);

    List<MemberDto> list(MemberParam param);    //회원의 목록 리턴(관리자계정만 사용가능)

    MemberDto detail(String userId);    //회원 상세 정보

    boolean updateStatus(String userId, String userStatus);     //회원 상태 변경

    boolean updatePassword(String userId, String password);     //회원 비밀번호 초기화

    ServiceResult updateMember(MemberInput input);              //회원 정보 수정
    ServiceResult updateMemberPassword(MemberInput input);      //회원 정보 페이지 내 비밀번호 변경

    ServiceResult withdraw(String userId, String password);      //회원 탈퇴
}
