package com.pinmarket.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pinmarket.service.main.MainService;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Controller
public class MainController {
	
	@Autowired
	MainService service;
	
	//메인 페이지
	@GetMapping("/main")
	public String main123(Model model) {
		//top10 상품 5개씩 두번 추출
		List<AuctionVO> TOP10_1 = service.getTopAuction(1,5);
		model.addAttribute("TOP10_1",TOP10_1);
		
		//인기 상품 하나 출력
		ProductVO productVO = service.getTopProduct();
		model.addAttribute("productVO",productVO);
		
		return "main.main";
	}
	
}
