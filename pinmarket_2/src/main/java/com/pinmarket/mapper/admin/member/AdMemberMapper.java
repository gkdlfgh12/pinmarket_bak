package com.pinmarket.mapper.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

public interface AdMemberMapper {
	//관리자 로그인
	public MemberVO login(MemberVO memberVO);
	
	//멤버 총 수 가져오기
	public int memberTotal(String str_id);

	//멤버 리스트 출력
	public List<MemberVO> getList(Map<String, Object> map);
	
	//멤버 정보 가져오기
	public MemberVO detailInfo(int id);
	
	//멤버 삭제
	int memberDelete(int[] id);
	
	//멤버 수정
	int updateMember(MemberVO memberVO);
	
	//멤버 프로필 사진 변경
	void changeImage(AttachmentVO attach);
	
	//멤버 추가시 프로필 추가
	void insertImage(AttachmentVO attach);
	
	//새 멤버 생성
	int writeMember(MemberVO memberVO);
	
}
