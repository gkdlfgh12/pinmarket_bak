package com.pinmarket.mapper.mypage;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

public interface MypageMapper {
	//내 정보 추출
	MemberVO getMyInfo(int id);
	
	//비밀번호 변경시 기존 비밀번호 체크
	int chkPwd(Map<String, Object> map);
	
	//변경된 비밀번호 저장
	int changePwd(Map<String, Object> map);
	
	//정보 변경
	int changeInfo(MemberVO memberVO);
	
	//프로필 정보 변경
	int changeProfile(AttachmentVO attachmentVO);
	
	//프로필 없을 시 추가
	void insertProfile(AttachmentVO attachmentVO);
}
