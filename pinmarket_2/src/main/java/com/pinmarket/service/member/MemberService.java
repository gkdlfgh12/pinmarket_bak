package com.pinmarket.service.member;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

public interface MemberService {
	
	//회원가입
	public int join(MemberVO vo, AttachmentVO attachmentVO);
	
	//로그인
	public MemberVO login(MemberVO vo);
	
	//네아로 callback 처리
	//id 중복 처리
	public MemberVO joinCheck(String str_id, String sns_id);
	
	//sns 회원가입
	public int snsjoin(MemberVO profile);
	
	///////////////////api///////////////////////
	
	//id 중복체크
	public int idDupleCheck(String str_id);

	//상품 결제 후 item cnt 추가
	public int addItemCnt(int member_id,int cnt);

	//랭크 등록 시 item 사용했으면 cnt - 1
	public void minusItemCnt(int member_id);

}
