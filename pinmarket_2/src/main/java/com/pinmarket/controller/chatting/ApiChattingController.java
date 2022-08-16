package com.pinmarket.controller.chatting;

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

@RestController
@RequestMapping("/api")
public class ApiChattingController {
	
	@Autowired
	@Qualifier("chattingServiceImpl")
	ChattingService service;
	
	//채팅방 리스트 가져오기
	@GetMapping("/chatting/getRoomList")
	public ResponseEntity<List<RoomVO>> getRoomList(@RequestParam Integer host_id, @RequestParam String auction_title){
		
		List<RoomVO> roomList = service.getRoomList(host_id,auction_title);
		
		return new ResponseEntity<List<RoomVO>>(roomList,HttpStatus.OK);
	}

	//채팅 메시지 가져오기
	@GetMapping("/chatting/getMsgList")
	public ResponseEntity<List<MsgVO>> getMsgList(@RequestParam Integer room_id){
		
		List<MsgVO> msgList = service.getMsgList(room_id);
		
		return new ResponseEntity<List<MsgVO>>(msgList,HttpStatus.OK);
	}
	
	//채팅방 삭제
	@GetMapping("/chatting/roomDelete")
	public ResponseEntity<String> roomDelete(@RequestParam Integer room_id){
		
		int result = service.roomDelete(room_id);
		String str_result = "";
		if(result == 1) str_result = "success";
		else str_result = "faile";
		
		return new ResponseEntity<String>(str_result,HttpStatus.OK);
	}
}
