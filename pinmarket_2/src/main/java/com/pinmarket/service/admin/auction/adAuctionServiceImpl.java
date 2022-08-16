package com.pinmarket.service.admin.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.auction.adAuctionMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

@Service
public class adAuctionServiceImpl implements adAuctionService{

	@Autowired
	adAuctionMapper mapper;
	
	//옥션의 개수
	@Override
	public int getTotal(String title) {
		return mapper.getTotal(title);
	}
	
	//옥션 리스트 겟
	@Override
	public List<AuctionVO> getList(PageCreator pc, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("title", title);
		return mapper.getList(map);
	}

	//옥션 삭제
	@Override
	public int deleteAuction(Integer[] delChk) {
		return mapper.deleteAuction(delChk);
	}

	//옥션 정보, 이미지 파일, 가져오기
	@Override
	public Map<String, Object> getDetail(Integer id) {
		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("auction",mapper.getDetail(id));
		mapInfo.put("attach",mapper.getImageFile(id));
		
		return mapInfo;
	}

	//옥션에 대응되는 랭크 정보 가져오기
	@Override
	public List<RankingVO> getRankList(SearchVO searchVO) {
		return mapper.getRankList(searchVO);
	}
}
