package com.pinmarket.mapper.main;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

public interface MainMapper {
	//메인 화면 TOP10 게시글 출력
	List<AuctionVO> getTopAuction(Map<String, Integer> row);
	
	//상품을 구매한 개수 출력
	int getTopProductCnt();

	//인기 상품 출력
	ProductVO getTopProduct(int product_id);

	//인기상품의 주문 개수와 id정보 가져오기
	List<OrderVO> getTopProductList();

}
