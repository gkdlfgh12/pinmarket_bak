package com.pinmarket.util;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

//@ControllerAdvice : 해당 클래스를 예외발생시 호출되게 ioc에 저장해놈
//@ControllerAdvice는 예외를 처리하는 존재임을 명시
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// @ExceptionHandler : 발생한 특정 예외를 잡아서 하나의 메소드에서 공통 처리해 줄 수 있게 해준다. 
	//여기선 NoHandlerFoundException 발생시 들어오게 설정해놈
	//배열로 여러개 처리도 가능
	@ExceptionHandler(NoHandlerFoundException.class)
	//요청에 대한 응답 코드 값을 넘겨준다. 만약 200을 넘겨주면 클라이언트는 404가 아닌 200코드 값을 리턴 받게 된다.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		log.info("요청하신 페이지가 존재하지 않습니다.");
		return "err/404";
	}

	//최상위 Exception 클래스를 기입하면서 대부분의 클래스는 해당 메서드로 들어오게 된다.
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {		
		log.info("오류!!");
		log.info("ex.getMessage() : "+ex.getMessage());
		model.addAttribute("exception",ex);
		return "err/exception";
	}
}