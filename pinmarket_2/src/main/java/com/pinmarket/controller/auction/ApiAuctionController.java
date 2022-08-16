package com.pinmarket.controller.auction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pinmarket.service.auction.AuctionService;
import com.pinmarket.service.member.MemberService;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.DistrictVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
public class ApiAuctionController {
	
	@Autowired
	@Qualifier("auctionServiceImpl")
	AuctionService service;
	
	@Autowired
	MemberService memberService;

	//경매 리스트 출력
	@PostMapping("/auction/list")
	public ResponseEntity<List<AuctionVO>> list(Model model, @RequestBody SearchVO vo) throws JsonProcessingException {
		//게시글 정보 뽑아오기
		List<AuctionVO> list = service.list(vo);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<list.size();i++) {
			list.get(i).setContent(list.get(i).getContent().replace("\r\n", "</br>"));
		}
		model.addAttribute("searchVO",vo);
		
		return new ResponseEntity<List<AuctionVO>>(list,HttpStatus.OK);
	}
	
	//랭크 리스트 출력
	@PostMapping("/rank/list")
	public ResponseEntity<List<RankingVO>> rankList(HttpServletRequest request, Model model, @RequestBody SearchVO searchVO){
		
		List<RankingVO> rankList = service.getRankList(searchVO);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<rankList.size();i++) {
			rankList.get(i).setContent(rankList.get(i).getContent().replace("\r\n", "</br>"));
		}
		
		return new ResponseEntity<List<RankingVO>>(rankList,HttpStatus.OK);
	}
	
	//랭크 참여 여부 체크
	@GetMapping("/rank/check")
	public ResponseEntity<String> memberCheck(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String id,
			@RequestParam(defaultValue = "none") String auctionId){
		int member_id = Integer.parseInt(id);
		int auction_id = Integer.parseInt(auctionId);
		String result = "";
		if(service.memberCheck(member_id,auction_id) > 0) {
			result = "deny";
		}else {
			result = "permit";
		}
		
		int itemCnt = memberService.getItemCnt(member_id);
		result = result+"_"+itemCnt;
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	//경매 : 랭크 관계 성립
	@GetMapping("/rank/rankComp")
	public ResponseEntity<String> rankComp(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String auction_id, @RequestParam(defaultValue = "none") String rank_id
			,@RequestParam(defaultValue = "none") String guest_id, @RequestParam(defaultValue = "none") String host_id){
		
		//해당 경매 완료처리 후 채팅방tb에 데이터 생성 및 해당 옥션의 상태 값도 변경
		service.rankComp(auction_id,rank_id,guest_id,host_id);
		String result = "comp";
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	//완료처리된 랭크 정보 가져오기
	@GetMapping("/rank/rankCompList")
	public ResponseEntity<RankingVO> rankCompList(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String auction_id){
		//해당 경매 완료처리 후 채팅방tb에 데이터 생성
		RankingVO rankVO = service.rankCompList(auction_id);
		return new ResponseEntity<RankingVO>(rankVO,HttpStatus.OK);
	}
	
	//경매 검색용 위치(시군구) 정보 가져오기
	@GetMapping("/auction/getSiGuGun")
	public ResponseEntity<List<DistrictVO>> getSiGuGun(@RequestParam String doCode){
		List<DistrictVO> test = service.getSiGuGun(doCode);
		
		return new ResponseEntity<List<DistrictVO>>(test,HttpStatus.OK);
	}
}
