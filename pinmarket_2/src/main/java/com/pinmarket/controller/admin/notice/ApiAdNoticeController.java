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
public class ApiAdNoticeController {
	
	@Autowired
	AdNoticeService service;
	
	//free faq 답변 리스트 가져오기
	@PostMapping("/freeReplyList")
	public ResponseEntity<List<ReplyVO>> freeReplyList(HttpServletRequest request, Model model,
		@RequestBody SearchVO searchVO){
		
		List<ReplyVO> replyList = service.getFreeReplyList(searchVO);
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<replyList.size();i++) {
			replyList.get(i).setContent(replyList.get(i).getContent().replace("\n", "</br>"));
		}
		
		
		return new ResponseEntity<List<ReplyVO>>(replyList, HttpStatus.OK);
	}

	//free Faq 답변
	@PostMapping("/freeReplyWrite")
	public ResponseEntity<String> freeReplyWrite(HttpServletRequest request, Model model, 
			@RequestBody ReplyVO replyVO){
		
		//세션정보로 id 값 얻기
		HttpSession session = request.getSession();
		MemberVO memberVO= (MemberVO)session.getAttribute("loginVO");
		replyVO.setMember_id(memberVO.getId());
		
		int result = service.freeReplyWrite(replyVO);
		
		if(result == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	//free faq 삭제
	@GetMapping("/freeReplyDel")
	public ResponseEntity<String> freeReplyDel(HttpServletRequest request, Model model, @RequestParam Integer reply_id){
		
		int result = service.freeReplyDel(reply_id);
		
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.BAD_GATEWAY);
	}
	
	//best faq 삭제
	@GetMapping("/bestDel")
	public ResponseEntity<String> bestDel(HttpServletRequest request, Model model, @RequestParam Integer id){
		
		int result = service.bestDel(id);
		
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.BAD_GATEWAY);
	}
	
	//best faq 수정
	@PostMapping("/updateBest")
	public ResponseEntity<String> bestUpdate(HttpServletRequest request, Model model, 
			@RequestBody BoardVO boardVO){
		
		//세션정보로 id 값 얻기
		HttpSession session = request.getSession();
		MemberVO memberVO= (MemberVO)session.getAttribute("loginVO");
		boardVO.setMember_id(memberVO.getId());
		
		int result = service.updateBestFaq(boardVO);
		
		if(result == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
