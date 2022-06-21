package com.pinmarket.vo;

import lombok.Data;

@Data
public class DistrictVO {
	private int code;
	private int location_level;
	private int parent_code;
	private String name;
}
