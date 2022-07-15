package com.pinmarket.service.main;

import java.util.List;

import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.ProductVO;

public interface MainService {

	//메인 화면 TOP10 게시글 출력
	List<AuctionVO> getTopAuction(int startRow, int endRow);

	//인기 상품 출력
	ProductVO getTopProduct();

}
