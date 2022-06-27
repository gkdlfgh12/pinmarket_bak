package com.pinmarket.controller.auction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pinmarket.service.auction.AuctionService;
import com.pinmarket.service.member.MemberService;
import com.pinmarket.util.FileUtil;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.DistrictVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.RankingVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/auction")
@Log4j
public class AuctionController {
	
	@Autowired
	@Qualifier("auctionServiceImpl")
	AuctionService service;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model, SearchVO searchVO) {
		log.info("searchVO.getTitle() : "+searchVO.getTitle());
		
		//지역 도 정보 가져오기
		List<DistrictVO> districtList = service.getDistrictList();
		log.info("districtList : "+districtList);
		model.addAttribute("districtList",districtList);
		
		//로그인 세션 정보 get
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		log.info("memberVO : "+memberVO);
		model.addAttribute("loginVO",memberVO);
		
		//게시글 정보 뽑아오기
		/*List<AuctionVO> list = service.list(searchVO);
		log.info("list ~ ");
		int [] arrNum = new int[list.size()];
		for(int i=0;i<list.size(); i++){
			arrNum[i] = list.get(i).getId();
		}
		//이미지 파일 뽑아오기
		//list로 foreach 사용해서 id를 비교 한 후 일치되는 값들만 가져와서 hash로 묶어서 view에 표출
		List<AttachmentVO> tmpAttachVO = service.getImageFile(arrNum); 
		log.info("attachVO : "+tmpAttachVO);
		int index = 0;
		
		model.addAttribute("list",list);
		model.addAttribute("tmpAttachVO",tmpAttachVO);
		model.addAttribute("searchVO",searchVO);*/
		return "auction.list";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		
		log.info("writeForm : ~~");
		
		return "auction.writeForm";
	}
	
	@PostMapping("/write")
	public String write(HttpServletRequest request, Model model, AuctionVO auction) throws Exception {
		
		log.info("write : ~~");
		log.info("auction : "+auction);
		
		//옥션 저장 시 현재 날짜와 경매 시작 날자를 비교하여 상태 값 open 또는 wait로 변경
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdformat.format(c1.getTime());
		
        Date startDate = sdformat.parse(auction.getStartDate());
        Date toDay = sdformat.parse(strToday);
        if (startDate.compareTo(toDay) > 0) {
        	auction.setStatus("wait");
		}
		
		//옥션 저장 시 세션으로 해당 id 저장
		HttpSession session = request.getSession();
		MemberVO member= (MemberVO)session.getAttribute("loginVO");
		auction.setMember_id(member.getId());
		int result = service.write(auction);
		log.info("result : "+result);
		
		//auction 정보 저장
		for(int i=0;i < auction.getProfile_file().length;i++) {
			log.info("여긴 ~1");
			if(!auction.getProfile_file()[i].isEmpty()) {
				log.info("여긴 ~2");
				AttachmentVO attachmentVO = new AttachmentVO();
				attachmentVO.setFk_id(auction.getId());
				attachmentVO.setFile_type("auction");
				attachmentVO.setReal_name(auction.getProfile_file()[i].getOriginalFilename());
				attachmentVO.setFile_path("/upload/auctionImage/");
				attachmentVO.setFile_size(auction.getProfile_file()[i].getSize());
				attachmentVO.setFile_ext(auction.getProfile_file()[i].getContentType().split("/")[1]);
				attachmentVO.setSave_name(FileUtil.upload("/upload/auctionImage", auction.getProfile_file()[i], request).split("/")[3]);
				//이쪽을 i가 0일때 무조건 들어오지 않으니 에러
				if(i == 0){
					attachmentVO.setThumbnail_name(FileUtil.thumbnailUpload("/upload/thumbAuctionImage", auction.getProfile_file()[0], request));
				}else {
					attachmentVO.setThumbnail_name("");
					//이미지 리사이징 - 포기
					//FileUtil.thumbnailUpload("/upload/auctionImage", auction.getProfile_file()[i], request);
				}
				log.info("attachmentVO : "+attachmentVO);
				service.profileSave(attachmentVO);
			}
		}
		return "redirect:/auction/list";
	}
	
	@GetMapping("/detailForm")
	public String detailForm(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") String id) {
		
		log.info("detailForm ~~ 데이터 가져오기");
		if(id != "0") {
			Map<String, Object> auctionInfo = service.view(id);
			model.addAttribute("auctionInfo",auctionInfo);
			log.info("auctionInfo : "+auctionInfo);
			//옥션 id를 이용해서 status_tb 조회 후 guest_id를 구해 온다.
			AuctionVO vo = (AuctionVO)auctionInfo.get("auction");
			log.info("auctionInfo vo : "+vo);
			//경매 완료시 여기서 guest_id 구함
			if(vo.getStatus().equals("comp")) {
				MemberVO statusVO = service.getAucStatus(vo.getId());
				model.addAttribute("guest_id",statusVO.getStr_id());
				log.info("여긴 오나??? ~ 22");
			}
		}
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		log.info("memberVO : "+memberVO);
		model.addAttribute("loginVO",memberVO);
		log.info("222");
		//랭킹 목록 가져오기
		/*List<RankingVO> rank = service.getRankList(id);
		log.info("ranking : "+rank);
		model.addAttribute("rank",rank);
		*/
		return "auction.detailForm";
	}

	//여기에 트랜잭션 필요
	@Transactional
	@PostMapping("/rankInsert")
	public String rankInsert(HttpServletRequest request, Model model, RankingVO rank) throws Exception {
		
		log.info("rankingInsert ~~ 데이터 가져오기");
		log.info("rank : "+rank);
		if(rank.getPayment_status() == 0) rank.setPayment_status(-1);
		service.rankInsert(rank);
		AttachmentVO attach = new AttachmentVO();
		attach.setFk_id(rank.getId());
		attach.setFile_type("rank");
		attach.setReal_name(rank.getProfile().getOriginalFilename());
		attach.setFile_path("/upload/auctionImage/");
		attach.setFile_size(rank.getProfile().getSize());
		attach.setFile_ext(rank.getProfile().getContentType().split("/")[1]);
		attach.setSave_name(FileUtil.upload("/upload/auctionImage", rank.getProfile(), request).split("/")[3]);
		attach.setThumbnail_name(FileUtil.thumbnailUpload("/upload/thumbAuctionImage", rank.getProfile(), request));
		log.info("여기 들어오나? !! ~ "+rank);
		
		//랭크 이미지 저장
		service.profileSave(attach);
		
		// AUC_STATUS_TB에 값 저장 
		//auction_id, rank_id를 저장 하고 입력 후 바로 니까 status값은 wait로 표기
		int result = service.aucStatusInsert(rank);
		
		//멤버의 item_cnt - 1 하기
		memberService.minusItemCnt(rank.getMember_id());
		
		return "redirect:/auction/detailForm?id="+rank.getAuction_id();
	}
	
	@GetMapping("/api/chatting/getMsgListTest")
	public String test() {
		
		log.info("/api/chatting/getMsgList22 진입~~~!!");
		
		return "auction.list";
	}
}
