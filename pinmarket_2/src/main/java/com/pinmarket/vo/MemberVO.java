package com.pinmarket.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {
	private int id;
	private String str_id;
	private String pw;
	private String name;
	private String birth;
	private String gender;
	private String address1;
	private String address2;
	private String zipcode;
	private String tel;
	private String email;
	private String login_type;
	private String status;
	private int member_level;
	private MultipartFile profileImage;
	private String sns_id;
	private int item_cnt;
	
	private AttachmentVO attachmentVO;
	//private int member_id;
}
