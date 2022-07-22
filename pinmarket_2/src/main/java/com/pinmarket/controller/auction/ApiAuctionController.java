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
@Log4j
public class ApiAuctionController {
	
	@Autowired
	@Qualifier("auctionServiceImpl")
	AuctionService service;
	
	@Autowired
	MemberService memberService;

	@PostMapping("/auction/list")
	public ResponseEntity<List<AuctionVO>> list(Model model, @RequestBody SearchVO vo) throws JsonProcessingException {
		log.info("vo.StartIndex()"+vo);
		//게시글 정보 뽑아오기
		List<AuctionVO> list = service.list(vo);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<list.size();i++) {
			list.get(i).setContent(list.get(i).getContent().replace("\r\n", "</br>"));
		}
		log.info("list 결과지 ~ "+list);
		model.addAttribute("searchVO",vo);
		/*log.info("list : ~ "+list);
		int [] arrNum = new int[list.size()];
		for(int i=0;i<list.size(); i++){
			arrNum[i] = list.get(i).getId();
		}*/
		//이미지 파일 뽑아오기
		//list로 foreach 사용해서 id를 비교 한 후 일치되는 값들만 가져와서 hash로 묶어서 view에 표출
		//List<AttachmentVO> tmpAttachVO = service.getImageFile(arrNum); 
		//ObjectMapper mapper = new ObjectMapper();
		//String str_list = mapper.writeValueAsString(list);
		//String str_tmpAttachVO = mapper.writeValueAsString(tmpAttachVO);
		/*log.info("api의 attachVO 문자값 : "+str_tmpAttachVO);
		log.info("api의 str_list 문자값 : "+str_list);
		test.put("list", list);
		test.put("tmpAttachVO", tmpAttachVO);*/
		
		return new ResponseEntity<List<AuctionVO>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/rank/list")
	public ResponseEntity<List<RankingVO>> rankList(HttpServletRequest request, Model model, @RequestBody SearchVO searchVO){
		
		List<RankingVO> rankList = service.getRankList(searchVO);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<rankList.size();i++) {
			rankList.get(i).setContent(rankList.get(i).getContent().replace("\r\n", "</br>"));
		}
		log.info("rankList : "+rankList);
		
		return new ResponseEntity<List<RankingVO>>(rankList,HttpStatus.OK);
	}
	
	@GetMapping("/rank/check")
	public ResponseEntity<String> memberCheck(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String id,
			@RequestParam(defaultValue = "none") String auctionId){
		int member_id = Integer.parseInt(id);
		int auction_id = Integer.parseInt(auctionId);
		String result = "";
		log.info("member_id : "+member_id);
		if(service.memberCheck(member_id,auction_id) > 0) {
			log.info("deny");
			result = "deny";
		}else {
			log.info("permit");
			result = "permit";
		}
		log.info("result : "+result);
		
		int itemCnt = memberService.getItemCnt(member_id);
		result = result+"_"+itemCnt;
		log.info("itemCnt : ~ "+result);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/rank/rankComp")
	public ResponseEntity<String> rankComp(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String auction_id, @RequestParam(defaultValue = "none") String rank_id
			,@RequestParam(defaultValue = "none") String guest_id, @RequestParam(defaultValue = "none") String host_id){
		
		log.info("guest_id : "+guest_id);
		log.info("host_id : "+host_id);
		
		//해당 경매 완료처리 후 채팅방tb에 데이터 생성 및 해당 옥션의 상태 값도 변경
		int compResult = service.rankComp(auction_id,rank_id,guest_id,host_id);
		log.info("compResult : "+compResult);
		String result = "comp";
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/rank/rankCompList")
	public ResponseEntity<RankingVO> rankCompList(HttpServletRequest request, Model model, 
			@RequestParam(defaultValue = "none") String auction_id){
		log.info("auction_id : ~ "+auction_id);
		
		//해당 경매 완료처리 후 채팅방tb에 데이터 생성
		RankingVO rankVO = service.rankCompList(auction_id);
		String result = "comp";
		return new ResponseEntity<RankingVO>(rankVO,HttpStatus.OK);
	}
	
	@GetMapping("/auction/getSiGuGun")
	public ResponseEntity<List<DistrictVO>> getSiGuGun(@RequestParam String doCode){
		log.info("getSiGuGun 진입 : ");
		List<DistrictVO> test = service.getSiGuGun(doCode);
		log.info("test : "+test);
		
		return new ResponseEntity<List<DistrictVO>>(test,HttpStatus.OK);
	}
}
