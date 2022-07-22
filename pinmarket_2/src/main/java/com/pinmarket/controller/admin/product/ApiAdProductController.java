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
@Log4j
public class ApiAdProductController {
	
	@Autowired
	AdProductService service;
	
	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam(defaultValue = "0") Integer id) {
		
		log.info("1233  :  "+id);
		int result = service.deleteProduct(id);
		log.info("result : "+result);
		
		int result2 = service.deleteAttachment(id,"product");
		log.info("result2 : "+result2);
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
}
