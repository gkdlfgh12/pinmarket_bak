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
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/admin")
@Log4j
public class ApiAdAuctionController {
	
	@Autowired
	adAuctionService service;
	
	@PostMapping("/rank/list")
	public ResponseEntity<List<RankingVO>> rankList(HttpServletRequest request, Model model, @RequestBody SearchVO searchVO){
		
		log.info("/rank/list");
		//랭크 리스트 가져오기
		List<RankingVO> rankList = service.getRankList(searchVO);
		log.info("rankList : "+rankList);
		log.info("searchVO : "+searchVO);
		
		//
		
		return new ResponseEntity<List<RankingVO>>(rankList,HttpStatus.OK);
	}
}
