package com.pinmarket.controller.chatting;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinmarket.service.chatting.ChattingService;
import com.pinmarket.vo.MsgVO;
import com.pinmarket.vo.RoomVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
@Log4j
public class ApiChattingController {
	
	@Autowired
	@Qualifier("chattingServiceImpl")
	ChattingService service;
	
	@GetMapping("/chatting/getRoomList")
	public ResponseEntity<List<RoomVO>> getRoomList(@RequestParam Integer host_id, @RequestParam String auction_title){
		
		log.info("getRoomList() 진입 : ~");
		log.info("roomvo 정보 : "+host_id);
		log.info("auction_title 정보 : "+auction_title);
		
		List<RoomVO> roomList = service.getRoomList(host_id,auction_title);
		log.info("roomList : ~ "+roomList);
		
		return new ResponseEntity<List<RoomVO>>(roomList,HttpStatus.OK);
	}

	@GetMapping("/chatting/getMsgList")
	public ResponseEntity<List<MsgVO>> getMsgList(@RequestParam Integer room_id){
		
		log.info("getMsgList() 진입 : ~");
		log.info("room_id 정보 : "+room_id);
		
		List<MsgVO> msgList = service.getMsgList(room_id);
		log.info("MsgVO : ~ "+msgList);
		
		return new ResponseEntity<List<MsgVO>>(msgList,HttpStatus.OK);
	}
	
	@GetMapping("/chatting/roomDelete")
	public ResponseEntity<String> roomDelete(@RequestParam Integer room_id){
		
		int result = service.roomDelete(room_id);
		String str_result = "";
		if(result == 1) str_result = "success";
		else str_result = "faile";
		
		return new ResponseEntity<String>(str_result,HttpStatus.OK);
	}
}
