package com.pinmarket.service.admin.auction;

import java.util.List;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AuctionVO;

public interface adAuctionService {
	
	//옥션의 개수
	int getTotal(String title);

	//옥션 리스트 겟
	List<AuctionVO> getList(PageCreator pc, String title);

	//옥션 삭제
	int deleteAuction(Integer[] delChk);


}
