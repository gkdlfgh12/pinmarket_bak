package com.pinmarket.service.admin.member;

import com.pinmarket.vo.MemberVO;

public interface AdMemberService {
	
	//관리자 로그인
	public MemberVO login(MemberVO memberVO);
}
