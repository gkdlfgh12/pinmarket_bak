package com.pinmarket.service.mypage;

import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

public interface MypageService {

	//내 정보 추출
	MemberVO getMyInfo(int id);

	
	//////////rest
	
	//비밀번호 변경시 기존 비밀번호 체크
	int chkPwd(String password, int id);

	//변경된 비밀번호 저장
	int changePwd(String newPassword, int id);

	//정보 변경
	int changeInfo(MemberVO memberVO);

	//프로필 정보 변경
	int changeProfile(MultipartFile profileImg, AttachmentVO attachmentVO);

	//프로필 없을 시 추가
	void insertProfile(MultipartFile profileImg, AttachmentVO attachmentVO);

}
