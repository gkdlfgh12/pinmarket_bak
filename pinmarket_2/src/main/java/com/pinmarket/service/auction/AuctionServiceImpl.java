package com.pinmarket.service.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinmarket.mapper.auction.AuctionMapper;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.DistrictVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@Service
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	AuctionMapper mapper;
	
	//경매 등록
	@Override
	public int write(AuctionVO auction) {
		return mapper.write(auction);
	}

	//프로필 이미지 저장
	@Override
	public int profileSave(AttachmentVO vo) {
		return mapper.profileSave(vo);
	}

	//경매 리스트
	@Override
	public List<AuctionVO> list(SearchVO searchVO) {
		return mapper.list(searchVO);
	}

	//이미지 파일 가져오기
	@Override
	public List<AttachmentVO> getImageFile(int [] arrNum) {
		return mapper.getImageFile(arrNum);
	}

	//경매 정보 가져오기
	@Override
	public Map<String, Object> view(String id) {
		int tmpNum = Integer.parseInt(id);
		int []arrNum = {tmpNum};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("auction",mapper.view(id));
		map.put("attach",mapper.getImageFile(arrNum));
		return map;
	}

	//rank 등록
	@Override
	public int rankInsert(RankingVO rank) {
		return mapper.rankInsert(rank);
	}

	//rank 리스트 가져오기
	@Override
	public List<RankingVO> getRankList(SearchVO searchVO) {
		return mapper.getRankList(searchVO);
	}

	//랭크에 이미 등록된 계정이 있는지 체크
	@Override
	public int memberCheck(int member_id, int auction_id) {
		Map<String, Integer> checkVal = new HashMap<String, Integer>();
		checkVal.put("member_id", member_id);
		checkVal.put("auction_id", auction_id);
		return mapper.memberCheck(checkVal);
	}

	//auc_status에 값 저장
	@Override
	public int aucStatusInsert(RankingVO rank) {
		return mapper.aucStatusInsert(rank);
	}

	//경매 완료 처리로 status_tb의 status값을 comp로 변경한다.
	@Transactional
	@Override
	public int rankComp(String auction_id, String rank_id, String guest_id,String host_id) {
		Map<String, String> compMap = new HashMap<String, String>();
		compMap.put("auction_id", auction_id);
		compMap.put("rank_id", rank_id);
		//옥션의 상태 값 변경
		mapper.auctionComp(auction_id);
		//랭크 상태 값 변경
		int result = mapper.rankComp(compMap);
		if(result == 1) {
			Map<String, String> chatAddMap = new HashMap<String, String>();
			chatAddMap.put("guest_id", guest_id);
			chatAddMap.put("host_id", host_id);
			//경매 status가 comp가 되면 채팅방 생성
			mapper.addChatRoom(chatAddMap);
		}
		return result;
	}

	//옥션 글의 상태 값 조회
	@Override
	public MemberVO getAucStatus(int auction_id) {
		return mapper.getAucStatus(auction_id);
	}

	//완료된 RANKVO 추출
	@Override
	public RankingVO rankCompList(String auction_id) {
		return mapper.rankCompList(auction_id);
	}

	//지역 정보 추출
	@Override
	public List<DistrictVO> getDistrictList() {
		return mapper.getDistrictList();
	}

	//도를 해당되는 시구군을 추출
	@Override
	public List<DistrictVO> getSiGuGun(String doCode) {
		return mapper.getSiGuGun(doCode);
	}

	//날짜 값에 맞추어서 매일 0시 5분에 상태값 업데이트
	@Override
	public int updateStatus(String toDate) {
		return mapper.updateStatus(toDate);
	}
}
