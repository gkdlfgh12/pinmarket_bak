package com.pinmarket.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.DailyRollingFileAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.notice.NoticeService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.SearchVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice")
@Log4j
public class NoticeController {
	
	@Autowired
	NoticeService service;
	
	@GetMapping("/freeFaqList")
	public String freeFaqList(HttpServletRequest request, Model model, PageVO pageVO) throws Exception {
		log.info("자유 질문 게시판!! ");
		
		//자유 질문에 맞게 페이징 정보 수정bb
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("freeFaqList");
		}
		
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO",memberVO);
		
		//게시글 총 개수 겟
		int totalCnt = service.getFreeFaqTotal();
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		log.info("pageObject : ~ "+pc);
		log.info("pageVO : ~ "+pageVO);
		
		//게시글 겟
		List<BoardVO> list = service.getFreeFaqList(pc);
		log.info("getFreeFaqList : "+list);
		model.addAttribute("list",list);
		
		return "notice.freeFaqList";
	}

	@PostMapping("/wrtieFreeFaq")
	public String wrtieFreeFaq(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			BoardVO boardVO) {
		
		log.info("wrtieFreeFaq : ~");
		//세션정보로 id 값 얻기
		MemberVO memberVO = SessionCreater.getSession(request);
		boardVO.setBoard_type("freeFaq");
		boardVO.setMember_id(memberVO.getId());
		
		int result = service.wrtieFreeFaq(boardVO);
		log.info("result : ~"+result);
		
		ra.addAttribute("page",pageVO.getPage());
		ra.addAttribute("countPerPage",pageVO.getCountPerPage());
		
		return "redirect:/notice/freeFaqList";
	}
	
	@GetMapping("/freeView")
	public String freeView(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			@RequestParam String id) {
		
		log.info("freeView : ~");
		log.info("id : ~"+id);
		log.info("pageVO : ~"+pageVO);
		
		//세션정보로 id 값 얻기
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO", memberVO);
		log.info("memberVO"+memberVO);
		
		//자유 질문 입력
		BoardVO boardVO = service.getFaq(id);
		model.addAttribute("boardVO",boardVO);
		log.info("boardVO"+boardVO);
		
		//페이징 정보 전달
		model.addAttribute("pageVO",pageVO);
		
		return "notice.freeFaqView";
	}
	
	
	@GetMapping("/freeModifyForm")
	public String freeModifyForm(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			@RequestParam String board_id) {
		
		log.info("freeModifyForm : ~ "+board_id);
		
		//세션정보로 id 값 얻기
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO", memberVO);
		model.addAttribute("pageVO",pageVO);
		
		//자유 질문 입력
		BoardVO boardVO = service.getFaq(board_id);
		model.addAttribute("boardVO",boardVO);
		log.info("boardVO"+boardVO);
		
		return "notice.freeFaqModify";
	}
	
	@PostMapping("/freeModify")
	public String freeModify(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			BoardVO boardVO) {
		
		log.info("freeModifyForm : ~ "+boardVO);
		//세션정보로 id 값 얻기
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO", memberVO);
		model.addAttribute("pageVO",pageVO);
		
		//여기에 수정한거 저장하는 로직 구현
		int result = service.modifyFaq(boardVO);
		log.info("result : "+result);
		ra.addAttribute("page",pageVO.getPage());
		ra.addAttribute("countPerPage",pageVO.getCountPerPage());
		ra.addAttribute("id", boardVO.getId());
		
		return "redirect:/notice/freeView";
	}
	
	@GetMapping("/freeDel")
	public String freeDel(HttpServletRequest request, RedirectAttributes ra, Model model, PageVO pageVO,
			BoardVO boardVO) {
		//여기에 수정한거 저장하는 로직 구현
		int result = service.deleteFaq(boardVO);
		
		ra.addAttribute("page",pageVO.getPage());
		ra.addAttribute("countPerPage",pageVO.getCountPerPage());
		
		return "redirect:/notice/freeFaqList";
	}
	
	//best faq
	@GetMapping("/bestFaqList")
	public String bestFaqList(HttpServletRequest request, Model model, PageVO pageVO) throws Exception {
		log.info("자주묻는 질문 게시판!! ");
		
		MemberVO memberVO = SessionCreater.getSession(request);
		model.addAttribute("loginVO",memberVO);
		
		List<BoardVO> list = service.getBestFaqList();
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<list.size();i++) {
			list.get(i).setContent(list.get(i).getContent().replace("\n", "</br>"));
		}
		model.addAttribute("list", list);
		log.info("listlist : ~ "+list);
		
		return "notice.bestFaqList";
	}
	
}
