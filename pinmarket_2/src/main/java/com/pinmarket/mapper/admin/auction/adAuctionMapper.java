package com.pinmarket.mapper.admin.auction;

import java.util.List;
import java.util.Map;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AuctionVO;

public interface adAuctionMapper {
	
	//옥션의 개수
	int getTotal(String title);
		
	//옥션 리스트 겟
	List<AuctionVO> getList(Map<String, Object> map);
	
	//옥션 삭제
	int deleteAuction(Integer[] delChk);
}
