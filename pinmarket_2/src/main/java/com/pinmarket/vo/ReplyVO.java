package com.pinmarket.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int id;
	private int board_id;
	private int member_id;
	private String content;
	private String status;
	private String regDate;
	private String updateDate;
}
