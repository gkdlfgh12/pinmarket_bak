package com.pinmarket.vo;

import lombok.Data;

@Data
public class OrderVO {
	private int id;
	private int member_id;
	private int product_id;
	private String order_id;
	private String card_name;
	private String card_num;
	private String product_name;
	private String method_type;
	private String pg_type;
	private String receipt_id;
	private String status;
	
	private String price;
	
	private String requested_at;
	private String purchased_at;
}
