package com.zerobase.fastlms.member.mapper;

import com.zerobase.fastlms.member.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam param);
    List<MemberDto> selectList(MemberParam param);

}
