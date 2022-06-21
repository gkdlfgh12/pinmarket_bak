package com.pinmarket.mapper.chatting;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.MsgVO;
import com.pinmarket.vo.RoomVO;

public interface ChattingMapper {
	
	//room 정보 get
	public List<RoomVO> getRoomList(Map<String, Object> roomInfoMap);
	
	//메시지 받기
	public List<MsgVO> getMsgList(Integer room_id);
	
	//메시지 저장
	public int insertMsg(Map<String, Object> msgMap);
	
	//채팅 방 나가기
	public int roomDelete(Integer room_id);

}
