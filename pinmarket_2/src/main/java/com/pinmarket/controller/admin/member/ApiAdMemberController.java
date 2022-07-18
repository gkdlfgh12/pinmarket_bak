package com.pinmarket.controller.admin.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.admin.member.AdMemberService;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/admin/member")
@Log4j
public class ApiAdMemberController {
	
	@Autowired
	AdMemberService service;
	
	@GetMapping("/detailInfo")
	public ResponseEntity<MemberVO> detailInfo(HttpServletRequest request, @RequestParam int id) {
		
		log.info("멤버 디테일 정보 가져오기");
		log.info("memberVO의 id : ~ "+id);
		MemberVO memberVO = service.detailInfo(id);
		log.info(" : ~ "+memberVO);
		
		return new ResponseEntity<MemberVO>(memberVO, HttpStatus.OK);
	}
}
