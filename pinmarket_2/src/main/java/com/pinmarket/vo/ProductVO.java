package com.pinmarket.vo;

import lombok.Data;

@Data
public class ProductVO {
	private int id;
	private String product_name;
	private int product_price;
	private String descript;
	private String status;
	
	private AttachmentVO attachmentVO;
}
