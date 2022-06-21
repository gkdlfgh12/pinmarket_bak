package com.pinmarket.service.chatting;

import java.util.List;

import com.pinmarket.vo.MsgVO;
import com.pinmarket.vo.RoomVO;

public interface ChattingService {

	//room 정보 get
	public List<RoomVO> getRoomList(Integer host_id,String auction_title);

	//메시지 받기
	public List<MsgVO> getMsgList(Integer room_id);

	//메시지 저장
	public int insertMsg(String room_id, String real_msg, Integer member_id);

	//채팅 방 나가기
	public int roomDelete(Integer room_id);

}
