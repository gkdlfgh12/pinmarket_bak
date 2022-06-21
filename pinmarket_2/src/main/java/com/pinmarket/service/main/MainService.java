package com.pinmarket.service.main;

import java.util.List;

import com.pinmarket.vo.AuctionVO;

public interface MainService {

	//메인 화면 TOP10 게시글 출력
	List<AuctionVO> getTopAuction(int startRow, int endRow);

}
