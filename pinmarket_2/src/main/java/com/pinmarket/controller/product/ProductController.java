package com.pinmarket.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pinmarket.service.product.ProductService;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	//상품 목록 출력
	@GetMapping("/list")
	public String list(Model model) {
		
		List<ProductVO> list = service.getProductList();
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<list.size();i++) {
			list.get(i).setDescript(list.get(i).getDescript().replace("\r\n", "</br>"));
		}
		model.addAttribute("list",list);
		
		return "product.list";
	}
	
}
