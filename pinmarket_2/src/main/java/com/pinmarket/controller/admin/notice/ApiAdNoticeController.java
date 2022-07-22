package com.pinmarket.controller.admin.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.notice.AdNoticeService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.ProductVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/api/admin/notice")
@Log4j
public class ApiAdNoticeController {
	
	@Autowired
	AdNoticeService service;
	
	//faq 답변 리스트 가져오기
	@PostMapping("/freeReplyList")
	public ResponseEntity<List<ReplyVO>> freeReplyList(HttpServletRequest request, Model model,
		@RequestBody SearchVO searchVO){
		
		log.info("searchVO : ~ "+searchVO);
		
		List<ReplyVO> replyList = service.getFreeReplyList(searchVO);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<replyList.size();i++) {
			log.info("들어오나?? ");
			replyList.get(i).setContent(replyList.get(i).getContent().replace("\n", "</br>"));
		}
		
		log.info("replyList : ~ "+replyList);
		
		return new ResponseEntity<List<ReplyVO>>(replyList, HttpStatus.OK);
	}

	//freeFaq의 답변 (or 답변에 답변)
	@PostMapping("/freeReplyWrite")
	public ResponseEntity<String> freeReplyWrite(HttpServletRequest request, Model model, 
			@RequestBody ReplyVO replyVO){
		log.info("질문에 답변 달기 기능");
		
		//세션정보로 id 값 얻기
		HttpSession session = request.getSession();
		MemberVO memberVO= (MemberVO)session.getAttribute("loginVO");
		replyVO.setMember_id(memberVO.getId());
		
		log.info("board_id : ~ "+replyVO.getBoard_id());
		log.info("content : ~ "+replyVO.getContent());
		log.info("member_id : ~ "+replyVO.getMember_id());
		
		int result = service.freeReplyWrite(replyVO);
		
		log.info("result : ~ "+result);
		
		if(result == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/freeReplyDel")
	public ResponseEntity<String> freeReplyDel(HttpServletRequest request, Model model, @RequestParam Integer reply_id){
		
		log.info("삭제 : ~ "+reply_id);
		int result = service.freeReplyDel(reply_id);
		
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.BAD_GATEWAY);
	}
	
	@GetMapping("/bestDel")
	public ResponseEntity<String> bestDel(HttpServletRequest request, Model model, @RequestParam Integer id){
		
		log.info("삭제 : ~ "+id);
		int result = service.bestDel(id);
		log.info("result : ~  "+result);
		
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.BAD_GATEWAY);
	}
	
	@PostMapping("/updateBest")
	public ResponseEntity<String> bestUpdate(HttpServletRequest request, Model model, 
			@RequestBody BoardVO boardVO){
		log.info("자주묻는 질문 수정");
		
		//세션정보로 id 값 얻기
		HttpSession session = request.getSession();
		MemberVO memberVO= (MemberVO)session.getAttribute("loginVO");
		boardVO.setMember_id(memberVO.getId());
		
		log.info("board_id : ~ "+boardVO);
		
		int result = service.updateBestFaq(boardVO);
		
		log.info("result : ~ "+result);
		
		if(result == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
