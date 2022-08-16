package com.pinmarket.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.pinmarket.service.chatting.ChattingService;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

public class ChatEchoHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessionList = new ArrayList<>();
	private Map<Integer, WebSocketSession> userSessions = new HashMap<>();
	
	@Autowired
	ChattingService service;
	
	 /**
     * websocket 연결 성공 시
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("연결 성공 시 after");
    	//WebSocketSession session는 서버와 연결된 클라이언트 하나의 세션값이다
    	//이값을 클라이언트가 들어올때마다 sessionList에 저장하는 역할을 한다.
    	sessionList.add(session);
    	int senderId = getId(session);
    	userSessions.put(senderId,session);
    }
 
    /**
     * websocket 연결 종료 시
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	int senderId = getId(session);
    	userSessions.remove(senderId);
    }
    
    /**
     * websocket 메세지 수신 및 송신
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	//세션에 연결된 모든 사용자들한테 다 보내기
    	/*for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(senderId + ":" + message.getPayload()));
		}*/
    	String msg = message.getPayload();
    	System.out.println("msg : "+msg);
    	//room_id, 댓글 작성자 id, 상대방 id , 메시지
    	if (!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			if (strs != null && strs.length == 4) {
				String room_id = strs[0];
				int member_id = Integer.parseInt(strs[1]);
				int opponent_id = Integer.parseInt(strs[2]);
				String real_msg = strs[3]; 
				if(true) {
					//상대방한테 전달
					WebSocketSession opponentSession = userSessions.get(opponent_id);
					if (opponentSession != null) {
						TextMessage tmpMsg = new TextMessage("from you|&,&| "+real_msg);
						opponentSession.sendMessage(tmpMsg);
					}
					//자신에게 전달
					WebSocketSession chatWriterSession = userSessions.get(member_id);
					if (chatWriterSession != null) {
						TextMessage tmpMsg = new TextMessage("from me|&,&| "+real_msg);
						chatWriterSession.sendMessage(tmpMsg);
					}
					service.insertMsg(room_id,real_msg,member_id);
				}
			}
		}
    }

	private int getId(WebSocketSession session) {
		Map<String,Object> httpSession = session.getAttributes(); 
		MemberVO vo = (MemberVO) httpSession.get("loginVO");
		if(vo == null) {
			return 0;//session.getId();
		}
		else {
			return vo.getId();
		}
	}
}