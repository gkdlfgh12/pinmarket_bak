package com.pinmarket.mapper.admin.auction;

import java.util.List;
import java.util.Map;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

public interface adAuctionMapper {
	
	//옥션의 개수
	int getTotal(String title);
		
	//옥션 리스트 겟
	List<AuctionVO> getList(Map<String, Object> map);
	
	//옥션 삭제
	int deleteAuction(Integer[] delChk);
	
	//옥션 자세히 보기
	AuctionVO getDetail(Integer id);

	//옥션에 대응되는 이미지 파일 가져오기
	List<AttachmentVO> getImageFile(Integer id);
	
	//api
	//옥션에 대응되는 랭크 정보 가져오기
	List<RankingVO> getRankList(SearchVO searchVO);
}
