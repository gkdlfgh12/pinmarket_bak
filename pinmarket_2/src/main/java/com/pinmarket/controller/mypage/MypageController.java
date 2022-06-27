package com.pinmarket.controller.mypage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pinmarket.service.mypage.MypageService;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage")
@Log4j
public class MypageController {
	
	@Autowired
	MypageService service;

	@GetMapping("/myInfo")
	public String myInfo(HttpServletRequest request, Model model) {
		log.info("/myInfo 들어옴");
		
		MemberVO loginVO = SessionCreater.getSession(request);
		
		log.info("loginVO : ~~ "+loginVO);
		MemberVO memberVO = service.getMyInfo(loginVO.getId());
		log.info("memberVO : ~~ "+memberVO);
		model.addAttribute("memberVO",memberVO);
		
		return "mypage.myInfo";
	}
	
	@GetMapping("/paymentInfo")
	public String paymentInfo(HttpServletRequest request, Model model) {
		
		log.info("/paymentInfo 들어옴");
		
		MemberVO loginVO = SessionCreater.getSession(request);
		log.info("loginVO : ~~ "+loginVO);
		List<OrderVO> orderVO = service.getPaymentInfo(loginVO.getId());
		model.addAttribute("orderVO",orderVO);
		log.info("orderVO : ~ "+orderVO);
		return "mypage.paymentInfo";
	}
	
	@GetMapping("/myAutionList")
	public String myAutionList(HttpServletRequest request, Model model) {
		
		log.info("myAutionList : ~ ");
		MemberVO loginVO = SessionCreater.getSession(request);
		List<AuctionVO> listSet = service.getMyAutionList(loginVO.getId());
		log.info("listSet.size() : ~ "+listSet.size());
		model.addAttribute("listSet",listSet);
		log.info("myAutionList list : ~ "+listSet);
		
		return "mypage.myAutionInfo";
	}
}
