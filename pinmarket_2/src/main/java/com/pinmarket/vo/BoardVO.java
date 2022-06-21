package com.pinmarket.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int id;
	private int member_id;
	private String str_id;
	private String content;
	private String title;
	private String board_type;
	private int hit;
	private String status;
	private String regDate;
	
	private String rnum;
}
