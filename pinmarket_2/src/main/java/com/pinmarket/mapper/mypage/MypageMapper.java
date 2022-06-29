package com.pinmarket.mapper.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.RankingVO;

public interface MypageMapper {
	//내 정보 추출
	MemberVO getMyInfo(int id);
	
	//내 결제 정보 추출
	List<OrderVO> getPaymentInfo(int id);
	
	//내가 올린 옥션 리스트
	List<AuctionVO> getMyAutionList(HashMap<String, Object> mapVO);
	
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
	
	//내가 올린 옥션의 랭크 리스트 출력
	List<RankingVO> getAuctionRankList(HashMap<String, Object> map);
	
	//경매 랭크 상태값 확인
	RankingVO getAuctionRankStatus(int id);

	//경매 랭크 상태값 확인 - 내가 올린 랭크글 상태확인용
	AuctionVO getAuctionRankStatus2(int auction_id);
	
	//내가 올린 경매 총 게시글 수
	int getMyAutionTotal(int member_id);

	//내가 올린 랭크 수
	int getMyRankTotal(int member_id);
	
	//내가 올린 랭크와 옥션 정보 겟
	List<RankingVO> getMyRankList(HashMap<String, Object> map);

	AuctionVO getAuctionInfo(int auction_id);

}
