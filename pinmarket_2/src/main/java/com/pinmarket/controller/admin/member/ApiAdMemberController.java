package com.pinmarket.controller.admin.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.admin.member.AdMemberService;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/admin/member")
public class ApiAdMemberController {
	
	@Autowired
	AdMemberService service;
	
	//내 정보 가져오기
	@GetMapping("/detailInfo")
	public ResponseEntity<MemberVO> detailInfo(HttpServletRequest request, @RequestParam int id) {
		
		MemberVO memberVO = service.detailInfo(id);
		
		return new ResponseEntity<MemberVO>(memberVO, HttpStatus.OK);
	}
}
