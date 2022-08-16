package com.pinmarket.controller.admin.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.notice.AdNoticeService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.PageVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/notice")
public class AdNoticeController {
	
	@Autowired
	AdNoticeService service;

	//free faq 목록 가져오기
	@GetMapping("/freeList")
	public String list(HttpServletRequest request, Model model, PageVO pageVO, 
			@RequestParam(required = false, defaultValue = "") String title) {
		
		
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("freeFaqList");
		}
		
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO",memberVO);
		
		//게시글 총 개수 겟
		int totalCnt = service.freeTotal(title);
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		
		List<BoardVO> list = service.freeList(pc, title);
		model.addAttribute("list",list);
		
		return "admin.notice.freeList";
	}
	
	//free faq 자세히 보기
	@GetMapping("/freeView")
	public String freeView(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			@RequestParam String id) {
		
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO",memberVO);
		
		BoardVO boardVO = service.freeView(id);
		model.addAttribute("boardVO",boardVO);
		model.addAttribute("page",pageVO.getPage());
		model.addAttribute("countPerPage",pageVO.getCountPerPage());
		
		return "admin.notice.freeView";
	}
	
	//free faq 선택삭제
	@PostMapping("/freeSelDel")
	public String freeDel(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			int[] delChk) {
		
		int result = service.selectFreeDel(delChk);
		
		return "redirect:/admin/notice/freeList?page="+pageVO.getPage()+"&countPerPage="+pageVO.getCountPerPage();
	}
	
	//best faq 목록 가져오기
	@GetMapping("/bestList")
	public String bestFaqList(HttpServletRequest request, Model model, PageVO pageVO) throws Exception {
		
		List<BoardVO> list = service.bestList();
		model.addAttribute("list", list);
		
		return "admin.notice.bestList";
	}
	
	//best faq 작성
	@PostMapping("/bestFaqWrite")
	public String bestFaqWrite(HttpServletRequest request, Model model, BoardVO boardVO) {
		
		MemberVO memberVO = SessionCreater.getSession(request);
		boardVO.setBoard_type("bestFaq");
		boardVO.setMember_id(memberVO.getId());
		
		int reulst = service.writeBestFaq(boardVO);
		
		return "redirect:/admin/notice/bestList";
	}
}
