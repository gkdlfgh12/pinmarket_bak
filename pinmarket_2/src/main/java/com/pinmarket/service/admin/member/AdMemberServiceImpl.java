package com.pinmarket.service.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.member.AdMemberMapper;
import com.pinmarket.vo.MemberVO;

@Service
public class AdMemberServiceImpl implements AdMemberService{

	//여기에 메퍼 만들기ㅣ
	@Autowired
	AdMemberMapper mapper;
	
	public MemberVO login(MemberVO memberVO) {
		return mapper.login(memberVO);
	}
	
}
