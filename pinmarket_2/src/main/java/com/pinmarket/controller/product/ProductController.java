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
@Log4j
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		log.info("product list : ~ ");
		
		List<ProductVO> list = service.getProductList();
		model.addAttribute("list",list);
		log.info("list :~~ "+list);
		
		return "product.list";
	}
	
}
