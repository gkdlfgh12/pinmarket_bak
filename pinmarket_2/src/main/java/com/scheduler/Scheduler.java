package com.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pinmarket.service.auction.AuctionService;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class Scheduler {

	/*@Scheduled(cron = "* * * * * *")
	public void test() {
		log.info("로그 입니다..~~~ ");
		System.out.println("로그 입니다..~~~ ");
	}*/
	
	@Autowired
	AuctionService service;
	
	//매달 매일 0시 5분에 실행
					// 초 뷴     시  날  달 요일
	@Scheduled(cron = "0 0/5 0 * * *")
	public void auctionStatusChange() {
		log.info("옥션의 날짜 값에 따라 상태 값 변경");
		Date toDay = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String toDate = simpleDateFormat.format(toDay);
		int result = service.updateStatus(toDate);
		log.info("result : "+result);
	}
}
