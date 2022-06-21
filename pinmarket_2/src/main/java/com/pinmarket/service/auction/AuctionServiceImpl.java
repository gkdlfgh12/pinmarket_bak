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
@Log4j
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	AuctionMapper mapper;
	
	@Override
	public int write(AuctionVO auction) {
		return mapper.write(auction);
	}

	@Override
	public int profileSave(AttachmentVO vo) {
		return mapper.profileSave(vo);
	}

	@Override
	public List<AuctionVO> list(SearchVO searchVO) {
		log.info("들어옴? list");
		return mapper.list(searchVO);
	}

	@Override
	public List<AttachmentVO> getImageFile(int [] arrNum) {
		return mapper.getImageFile(arrNum);
	}

	@Override
	public Map<String, Object> view(String id) {
		int tmpNum = Integer.parseInt(id);
		int []arrNum = {tmpNum};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("auction",mapper.view(id));
		map.put("attach",mapper.getImageFile(arrNum));
		return map;
	}

	@Override
	public int rankInsert(RankingVO rank) {
		return mapper.rankInsert(rank);
	}

	@Override
	public List<RankingVO> getRankList(SearchVO searchVO) {
		return mapper.getRankList(searchVO);
	}

	@Override
	public int memberCheck(int member_id, int auction_id) {
		Map<String, Integer> checkVal = new HashMap<String, Integer>();
		checkVal.put("member_id", member_id);
		checkVal.put("auction_id", auction_id);
		return mapper.memberCheck(checkVal);
	}

	@Override
	public int aucStatusInsert(RankingVO rank) {
		return mapper.aucStatusInsert(rank);
	}

	@Transactional
	@Override
	public int rankComp(String auction_id, String rank_id, String guest_id,String host_id) {
		Map<String, String> compMap = new HashMap<String, String>();
		compMap.put("auction_id", auction_id);
		compMap.put("rank_id", rank_id);
		log.info("auction_id : "+compMap.get("auction_id"));
		log.info("rank_id : "+compMap.get("rank_id"));
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

	@Override
	public MemberVO getAucStatus(int auction_id) {
		return mapper.getAucStatus(auction_id);
	}

	@Override
	public RankingVO rankCompList(String auction_id) {
		return mapper.rankCompList(auction_id);
	}

	@Override
	public List<DistrictVO> getDistrictList() {
		return mapper.getDistrictList();
	}

	@Override
	public List<DistrictVO> getSiGuGun(String doCode) {
		return mapper.getSiGuGun(doCode);
	}

	@Override
	public int updateStatus(String toDate) {
		return mapper.updateStatus(toDate);
	}
}
