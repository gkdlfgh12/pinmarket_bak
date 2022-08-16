package com.pinmarket.service.chatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.chatting.ChattingMapper;
import com.pinmarket.vo.MsgVO;
import com.pinmarket.vo.RoomVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ChattingServiceImpl implements ChattingService{
	
	@Autowired
	@Qualifier("chattingMapper")
	ChattingMapper mapper;

	//room 정보 get
	@Override
	public List<RoomVO> getRoomList(Integer host_id,String auction_title) {
		log.info("getRoomService 진입 : ~ "+host_id);
		Map<String, Object> roomInfoMap = new HashMap<String, Object>();
		roomInfoMap.put("host_id", host_id);
		roomInfoMap.put("auction_title", auction_title);
		return mapper.getRoomList(roomInfoMap);
	}

	//메시지 받기
	@Override
	public List<MsgVO> getMsgList(Integer room_id) {
		return mapper.getMsgList(room_id);
	}

	//메시지 저장
	@Override
	public int insertMsg(String room_id, String real_msg, Integer member_id) {
		log.info("insertMsg : ");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("room_id", room_id);
		msgMap.put("real_msg", real_msg);
		msgMap.put("member_id", member_id);
		return mapper.insertMsg(msgMap);
	}

	//채팅 방 나가기
	@Override
	public int roomDelete(Integer room_id) {
		return mapper.roomDelete(room_id);
	}
	
}
