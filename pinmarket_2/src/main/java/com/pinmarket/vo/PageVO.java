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
	
	public void setPaging(String type) {
		if(type == "freeFaqList") {
			this.page = 1;
			this.countPerPage = 10;
		}else if(type == "myAutionList") {
			this.page = 1;
			this.countPerPage = 4;
		}else {
			this.page = 1;
			this.countPerPage = 10;
		}
	}
}
