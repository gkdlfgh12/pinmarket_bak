package com.pinmarket.apiController.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.member.MemberService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
@Log4j
public class apiMemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/idDupleCheck")
	public ResponseEntity<String> idDupleCheck(String str_id) {
		log.info("idDupleCheck ~~");
		int cnt = service.idDupleCheck(str_id);
		log.info("cnt : "+cnt);
		if(cnt == 0) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
}
