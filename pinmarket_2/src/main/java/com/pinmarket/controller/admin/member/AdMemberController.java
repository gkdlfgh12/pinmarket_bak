package com.pinmarket.controller.admin.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.member.AdMemberServiceImpl;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/member")
@Log4j
public class AdMemberController {
	
	@Autowired
	AdMemberServiceImpl service;
	
	//로그인 폼
	@GetMapping("/loginForm")
	public String loginForm() {
		
		log.info("관리자 로그인 폼");
		
		return "admin.member.loginForm";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes ra, MemberVO memberVO) {
		
		log.info("관리자 로그인");
		log.info("memberVO : ~ "+memberVO);
		memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		
		MemberVO saveVO = service.login(memberVO);
		log.info("saveVO : ~ "+saveVO);
		
		if(saveVO == null) {
			//실패 처리
			ra.addFlashAttribute("msg", "해당 계정은 존재 하지 않습니다.");
			return "redirect:/admin/member/loginForm";
		}else if(saveVO.getMember_level() != 0){
			//권한 부족
			ra.addFlashAttribute("msg", "해당 계정은 권한이 부족합니다.");
			return "redirect:/admin/member/loginForm";
		}else {
			//세션 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", saveVO);
			return "redirect:/admin/auction/list";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		//세션 생성
		HttpSession session = request.getSession();
		session.removeAttribute("loginVO");
		
		return "redirect:/admin/member/loginForm";
	}
}
