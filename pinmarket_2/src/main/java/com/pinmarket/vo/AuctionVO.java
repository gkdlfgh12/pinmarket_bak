package com.pinmarket.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AuctionVO {
	private int id;
	private int member_id;
	private String title;
	private String content;
	private String address1;
	private String address2;
	private String status;
	private String regDate;
	private String startDate;
	private String endDate;
	private String latitude;
	private String longitude;
	private MultipartFile[] profile_file;

	//임시 옥션의 사용자 STR_ID를 가져온다
	private String str_id;
	//각 옥션에 붙은 랭크의 개수
	private int rt_cnt;
	private AttachmentVO attachmentVO;
	
	private List<RankingVO> rankingVO;
}
