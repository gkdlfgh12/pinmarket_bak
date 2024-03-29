package com.pinmarket.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginOkInterceptor implements HandlerInterceptor{
	//컨트롤러를 찾은 후 실행
		/*@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

			//modelAndView()로 모델에 저장된 데이터와 사용사에 리턴할 뷰 이름은 출력할 수 있다.
			log.info("[ postHandle ]");
			log.info("getModel() : "+modelAndView.getModel());
			log.info("getViewName() : "+modelAndView.getViewName());
		}*/
		
		//컨트롤러 작업 전 실행
		@Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	        boolean result = true;
	        
	        HttpSession session = request.getSession();
	        if(session.getAttribute("loginVO") == null) {
	        	response.setCharacterEncoding("UTF-8");
	        	response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter printwriter = response.getWriter();
	        	printwriter.print("<script>alert('로그인 회원만 접근할 수 있습니다.');</script>");
	        	printwriter.print("<script>location.href='/member/loginForm';</script>");
	        	printwriter.flush();
	        	printwriter.close();
	        }
	        
	        //여기서 세션이나 로그인 정보를 얻어내서 유무값 확인후 리다이렉트 할지 정함
	        return result;
	    }
		
		//무조건 실행되는 메서드로 예외 발생시 받아서 예외정보를 저장하거나 이메일로 받아서 처리할 수 있다.
		/*
		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
			
		}
		*/
}
