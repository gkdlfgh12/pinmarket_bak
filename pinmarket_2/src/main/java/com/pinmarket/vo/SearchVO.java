package com.pinmarket.vo;

import lombok.Data;

@Data
public class SearchVO {
	//auction의 id
	private int auction_id;
	private int board_id;
	private String title;
	private int startIndex;
	private int endIndex;
	private String district1;
	private String district2;
}
