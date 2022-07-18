package com.pinmarket.mapper.member;

import java.util.Map;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

public interface MemberMapper {
	
	//회원가입
	public int join(MemberVO vo);
	
	//로그인
	public MemberVO login(MemberVO vo);
	
	//프로필 이미지 저장
	public int profileSave(AttachmentVO vo);
	
	//네아로 callback 처리
	//id 중복 처리
	public MemberVO joinCheck(Map<String, String> map);
	
	//sns 회원가입
	public int snsjoin(MemberVO profile);
///////////////////api///////////////////////
	
	//id 중복체크
	public int idDupleCheck(String str_id);
	
	//상품 결제 후 item cnt 추가
	public int addItemCnt(Map<String, Integer> param);
	
	//랭크 등록 시 item 사용했으면 cnt - 1
	public void minusItemCnt(int member_id);
	
	//아이템 개수 확인
	public int getItemCnt(int member_id);
}
