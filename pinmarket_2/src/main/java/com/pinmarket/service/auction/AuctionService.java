package com.pinmarket.service.auction;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionStatusVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.DistrictVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

public interface AuctionService {
	//경매 등록
	public int write(AuctionVO auction);
	
	//프로필 이미지 저장
	public int profileSave(AttachmentVO vo);

	//경매 리스트
	public List<AuctionVO> list(SearchVO searchVO);

	//이미지 파일 가져오기
	public List<AttachmentVO> getImageFile(int [] arrNum);

	//auction 자세히 보기
	public Map<String, Object> view(String id);

	//rank 등록
	public int rankInsert(RankingVO rank);

	//랭킹 목록 겟
	public List<RankingVO> getRankList(SearchVO searchVO);

	//해당 멤버가 랭크를 달았는지 확인
	public int memberCheck(int member_id, int auction_id);

	//auc_status에 값 저장
	public int aucStatusInsert(RankingVO rank);

	//경매 완료 처리로 status_tb의 status값을 comp로 변경한다.
	public int rankComp(String auction_id, String rank_id,String guest_id,String host_id);

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
