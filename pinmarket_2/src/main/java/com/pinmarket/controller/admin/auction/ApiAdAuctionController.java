package com.pinmarket.controller.admin.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.admin.auction.adAuctionService;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.PageVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/admin/auction")
@Log4j
public class ApiAdAuctionController {
	
	@Autowired
	adAuctionService service;
	
	@PostMapping("/del")
	public ResponseEntity<List<AuctionVO>> list(Model model, PageVO pageVO) {
		
		log.info("auction del : ");
		
		return null;
	}
}
