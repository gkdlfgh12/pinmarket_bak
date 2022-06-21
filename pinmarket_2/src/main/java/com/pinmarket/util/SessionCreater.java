package com.pinmarket.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class SessionCreater {

	public static MemberVO getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO memberVO= (MemberVO)session.getAttribute("loginVO");
		log.info("memberVO : ~ "+memberVO);
		//model.addAttribute("loginVO",memberVO);
		
		return memberVO;
	}
}
