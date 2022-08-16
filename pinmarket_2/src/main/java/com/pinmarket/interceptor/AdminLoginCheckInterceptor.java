package com.pinmarket.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

public class AdminLoginCheckInterceptor implements HandlerInterceptor{
	//컨트롤러를 찾은 후 실행
	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}*/
	
	//컨트롤러 작업 전 실행
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean result = true;
        
        HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
        
        if(memberVO == null) {
        	response.setCharacterEncoding("UTF-8");
        	response.setContentType("text/html; charset=UTF-8");
        	PrintWriter printwriter = response.getWriter();
        	printwriter.print("<script>alert('로그인 회원만 접근할 수 있습니다.');</script>");
        	printwriter.print("<script>location.href='/admin/member/loginForm';</script>");
        	printwriter.flush();
        	printwriter.close();
        }else if(memberVO != null && memberVO.getMember_level() != 0) {
        	response.setCharacterEncoding("UTF-8");
        	response.setContentType("text/html; charset=UTF-8");
        	PrintWriter printwriter = response.getWriter();
        	printwriter.print("<script>alert('회원의 권한이 맞지 않습니다.');</script>");
        	printwriter.print("<script>location.href='/admin/member/loginForm';</script>");
        	printwriter.flush();
        	printwriter.close();
        }
        
        return result;
    }
	
	//무조건 실행되는 메서드로 예외 발생시 받아서 예외정보를 저장하거나 이메일로 받아서 처리할 수 있다.
	/*
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
	}
	*/
}
