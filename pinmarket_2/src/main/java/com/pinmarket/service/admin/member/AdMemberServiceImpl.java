package com.pinmarket.service.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.member.AdMemberMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
public class AdMemberServiceImpl implements AdMemberService{

	@Autowired
	AdMemberMapper mapper;
	
	//관리자 로그인
	@Override
	public MemberVO login(MemberVO memberVO) {
		return mapper.login(memberVO);
	}

	//멤버 총 수 가져오기
	@Override
	public int memberTotal(String str_id) {
		return mapper.memberTotal(str_id);
	}
	
	//멤버 리스트 출력
	@Override
	public List<MemberVO> getList(PageCreator pc, String str_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("str_id", str_id);
		return mapper.getList(map);
	}

	//멤버 정보 가져오기
	@Override
	public MemberVO detailInfo(int id) {
		return mapper.detailInfo(id);
	}

	//멤버 삭제
	@Override
	public int memberDelete(int[] id) {
		return mapper.memberDelete(id);
	}
	
	//멤버 수정
	@Override
	public int updateMember(MemberVO memberVO) {
		return mapper.updateMember(memberVO);
	}

	//멤버 프로필 사진 변경
	@Override
	public void changeImage(AttachmentVO attach) {
		mapper.changeImage(attach);
	}

	//멤버 추가시 프로필 추가
	@Override
	public void insertImage(AttachmentVO attach) {
		mapper.insertImage(attach);
	}

	//새 멤버 생성
	@Override
	public int writeMember(MemberVO memberVO) {
		return mapper.writeMember(memberVO);
	}
	
}
