package com.pinmarket.vo;

import lombok.Data;

@Data
public class AuctionStatusVO {
	private int id;
	private int auction_id;
	private int ranking_id;
	private String status;
	private String regDate;
}
