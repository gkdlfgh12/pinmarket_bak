package com.pinmarket.vo;

import lombok.Data;

@Data
public class PageVO {
	private int page;
	private int countPerPage;
	
	PageVO() {
		this.page = 1;
		this.countPerPage = 10;
	}
}
