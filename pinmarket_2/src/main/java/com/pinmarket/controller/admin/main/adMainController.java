package com.pinmarket.controller.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
public class adMainController {
	
	//메인
	@GetMapping("/admin/dashboard")
	public String dashboard() {
		
		return "admin.dashboard.dashboard";
	}
}
