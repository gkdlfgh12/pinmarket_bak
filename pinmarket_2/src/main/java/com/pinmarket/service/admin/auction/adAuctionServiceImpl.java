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
	
	@Override
	public int getTotal(String title) {
		return mapper.getTotal(title);
	}
	
	@Override
	public List<AuctionVO> getList(PageCreator pc, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("title", title);
		return mapper.getList(map);
	}

	@Override
	public int deleteAuction(Integer[] delChk) {
		return mapper.deleteAuction(delChk);
	}

	@Override
	public Map<String, Object> getDetail(Integer id) {
		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("auction",mapper.getDetail(id));
		mapInfo.put("attach",mapper.getImageFile(id));
		
		return mapInfo;
	}

	@Override
	public List<RankingVO> getRankList(SearchVO searchVO) {
		return mapper.getRankList(searchVO);
	}
}
