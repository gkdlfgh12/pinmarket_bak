package com.pinmarket.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RankingVO {
	private int id;
	private int member_id;
	private String str_id;
	private int auction_id;
	private String title;
	private String content;
	private int payment_status;
	private String regDate;
	private MultipartFile profile;
	
	private AttachmentVO attachmentVO;
}
