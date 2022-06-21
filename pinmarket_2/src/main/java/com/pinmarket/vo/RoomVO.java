package com.pinmarket.vo;

import lombok.Data;

@Data
public class RoomVO {
	private int room_id;
	private int host_id;
	private int guest_id;
	private String room_status;
	private int auc_status_id;
	private String regDate;
	
	//다른 vo 정보
	private int auction_id;
	private String auction_title;
	private String host_str;
	private String guest_str;
	
}
