package com.pinmarket.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.admin.product.AdProductService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/admin/product")
public class ApiAdProductController {
	
	@Autowired
	AdProductService service;
	
	//상품 삭제
	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam(defaultValue = "0") Integer id) {
		
		//상품 삭제
		service.deleteProduct(id);
		
		//상품 이미지 삭제
		service.deleteAttachment(id,"product");
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
}
