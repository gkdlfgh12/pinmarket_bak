package com.pinmarket.controller.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class adMainController {
	
	@GetMapping("/admin/dashboard")
	public String dashboard() {
		
		log.info("dashboard : ");
		
		return "admin.dashboard.dashboard";
	}
}
