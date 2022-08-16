package com.pinmarket.mapper.auction;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionStatusVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.DistrictVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

public interface AuctionMapper {
	//경매 등록
	public int write(AuctionVO auction);
	
	//프로필 이미지 저장
	public int profileSave(AttachmentVO vo);
	
	//경매 리스트
	public List<AuctionVO> list(SearchVO searchVO);
	
	//이미지 파일 가져오기
	public List<AttachmentVO> getImageFile(int [] arrNum);
	
	//경매 정보 가져오기
	public AuctionVO view(String id);
	
	//rank 등록
	public int rankInsert(RankingVO rank);

	//rank 리스트 가져오기
	public List<RankingVO> getRankList(SearchVO searchVO);
	
	//랭크에 이미 등록된 계정이 있는지 체크
	public int memberCheck(Map<String, Integer> checkVal);
	
	//auc_status에 값 저장
	public int aucStatusInsert(RankingVO rank);
	
	//옥션의 상태 값을 comp로 변경
	public int auctionComp(String auction_id);

	//경매 완료 처리로 status_tb의 status값을 comp로 변경한다.
	public int rankComp(Map<String, String> compMap);
	
	//auction status가 comp가 되면 채팅방 생성
	public int addChatRoom(Map<String, String> chatAddMap);
	
	//옥션 글의 상태 값 조회
	public MemberVO getAucStatus(int auction_id);
	
	//완료된 RANKVO 추출
	public RankingVO rankCompList(String auction_id);

	//지역 정보 추출
	public List<DistrictVO> getDistrictList();
	
	//도를 해당되는 시구군을 추출
	public List<DistrictVO> getSiGuGun(String doCode);
	
	//날짜 값에 맞추어서 매일 0시 5분에 상태값 업데이트
	public int updateStatus(String toDate);
}
