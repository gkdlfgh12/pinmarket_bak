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
@Log4j
public class AdNoticeController {
	
	@Autowired
	AdNoticeService service;

	@GetMapping("/freeList")
	public String list(HttpServletRequest request, Model model, PageVO pageVO) {
		
		log.info("freeList : ");
		
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("freeFaqList");
		}
		
		MemberVO memberVO = SessionCreater.getSession(request);
		log.info("memberVO : `~~~ "+memberVO);
		model.addAttribute("loginVO",memberVO);
		
		//게시글 총 개수 겟
		int totalCnt = service.freeTotal();
		log.info("totalCnt : "+totalCnt);
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		log.info("pageObject : ~ "+pc);
		log.info("pageVO : ~ "+pageVO);
		
		List<BoardVO> list = service.freeList(pc);
		log.info("list : ~ "+list);
		model.addAttribute("list",list);
		
		return "admin.notice.freeList";
	}
	
	@GetMapping("/freeView")
	public String freeView(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			@RequestParam String id) {
		
		MemberVO memberVO = SessionCreater.getSession(request);
		log.info("memberVO : `~~~ "+memberVO);
		model.addAttribute("loginVO",memberVO);
		
		BoardVO boardVO = service.freeView(id);
		model.addAttribute("boardVO",boardVO);
		model.addAttribute("page",pageVO.getPage());
		model.addAttribute("countPerPage",pageVO.getCountPerPage());
		log.info("boardVO : ~ "+boardVO);
		
		return "admin.notice.freeView";
	}
	
	
	
	@PostMapping("/freeSelDel")
	public String freeDel(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			int[] delChk) {
		
		log.info("gggg: ::: ~~ "+delChk);
		int result = service.selectFreeDel(delChk);
		log.info("result : ~~ "+result);
		
		return "redirect:/admin/notice/freeList?page="+pageVO.getPage()+"&countPerPage="+pageVO.getCountPerPage();
	}
	
	//best faq
	@GetMapping("/bestList")
	public String bestFaqList(HttpServletRequest request, Model model, PageVO pageVO) throws Exception {
		log.info("자주묻는 질문 게시판!! ");
		
		List<BoardVO> list = service.bestList();
		model.addAttribute("list", list);
		log.info("listlist : ~ "+list);
		
		return "admin.notice.bestList";
	}
	
	@PostMapping("/bestFaqWrite")
	public String bestFaqWrite(HttpServletRequest request, Model model, BoardVO boardVO) {
		
		log.info("bestFaqWrite "+boardVO);
		MemberVO memberVO = SessionCreater.getSession(request);
		boardVO.setBoard_type("bestFaq");
		boardVO.setMember_id(memberVO.getId());
		log.info("memberVO : `~~~ "+memberVO);
		
		int reulst = service.writeBestFaq(boardVO);
		log.info("reulst : `~~ "+reulst);
		
		return "redirect:/admin/notice/bestList";
	}
}
