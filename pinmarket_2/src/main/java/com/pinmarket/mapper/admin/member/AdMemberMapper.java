package com.pinmarket.mapper.admin.member;

import com.pinmarket.vo.MemberVO;

public interface AdMemberMapper {
	//관리자 로그인
	public MemberVO login(MemberVO memberVO);
}
