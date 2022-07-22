package com.pinmarket.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	private int id;
	private String product_name;
	private int product_price;
	private String descript;
	private String status;
	private MultipartFile product_file;
	private int item_cnt;
	
	private AttachmentVO attachmentVO;
}
