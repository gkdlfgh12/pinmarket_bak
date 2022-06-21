package com.pinmarket.mapper.main;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AuctionVO;

public interface MainMapper {
	//메인 화면 TOP10 게시글 출력
	List<AuctionVO> getTopAuction(Map<String, Integer> row);
}
