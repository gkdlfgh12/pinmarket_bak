package com.pinmarket.vo;

import lombok.Data;

@Data
public class AttachmentVO {
	private String id;
	private int fk_id;
	private String file_type;
	private String save_name;
	private String real_name;
	private String file_path;
	private long file_size;
	private String thumbnail_name;
	private String file_ext;
}
