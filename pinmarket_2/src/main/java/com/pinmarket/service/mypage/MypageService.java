package com.pinmarket.service.mypage;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

public interface MypageService {

	//내 정보 추출
	MemberVO getMyInfo(int id);

	//내 결제 정보 추출
	List<OrderVO> getPaymentInfo(int id);
	
	//내가 올린 옥션 리스트
	List<AuctionVO> getMyAutionList(int id);
	
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
