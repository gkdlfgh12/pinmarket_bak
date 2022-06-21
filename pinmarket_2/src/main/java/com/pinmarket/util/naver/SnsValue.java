package com.pinmarket.util.naver;

import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class SnsValue implements SnsUrls{

	private String naverType;
	private String naverClientID;
	private String naverClientSecret;
	private String naverRedirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	
	public SnsValue(String naverType, String naverClientID, String naverClientSecret, String naverRedirectUrl) {
		super();
		this.naverType = naverType;
		this.naverClientID = naverClientID;
		this.naverClientSecret = naverClientSecret;
		this.naverRedirectUrl = naverRedirectUrl;
		this.api20Instance = NaverAPI20.getInstance();
		this.profileUrl = NAVER_PROFILE_URL;
	}
	
	
	
}
