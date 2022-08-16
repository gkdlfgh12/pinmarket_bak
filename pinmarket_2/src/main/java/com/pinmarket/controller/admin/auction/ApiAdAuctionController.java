package com.pinmarket.controller.admin.auction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.admin.auction.adAuctionService;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

@RestController
@RequestMapping("/api/admin")
public class ApiAdAuctionController {
	
	@Autowired
	adAuctionService service;
	
	//랭크 리스트 가져오기
	@PostMapping("/rank/list")
	public ResponseEntity<List<RankingVO>> rankList(HttpServletRequest request, Model model, @RequestBody SearchVO searchVO){
		
		//랭크 리스트 가져오기
		List<RankingVO> rankList = service.getRankList(searchVO);
		
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<rankList.size();i++) {
			rankList.get(i).setContent(rankList.get(i).getContent().replace("\r\n", "</br>"));
		}
		
		return new ResponseEntity<List<RankingVO>>(rankList,HttpStatus.OK);
	}
}
