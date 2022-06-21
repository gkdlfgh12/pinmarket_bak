package com.pinmarket.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MsgVO {
	private int room_id;
	private String message;
	private String regDate;
	private String updateDate;
	private int member_id;
	
	private String str_id;
}
