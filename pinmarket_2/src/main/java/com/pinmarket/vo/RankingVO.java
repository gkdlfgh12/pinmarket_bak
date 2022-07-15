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
	private int aucResult;
	//관리자 페이지 에서 옥션의 상태값 체크를 위한 옥션 id값 가져오기
	private int comp_status;
	
	private AttachmentVO attachmentVO;
	private AuctionVO auctionVO;
	
	private int rank_cnt;
}
