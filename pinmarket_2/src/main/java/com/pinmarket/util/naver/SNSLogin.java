package com.pinmarket.util.naver;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class SNSLogin {

	OAuth20Service oauthService;
	private String profileUrl;
	
	public SNSLogin(SnsValue sns) {
		this.oauthService = new ServiceBuilder(sns.getNaverClientID())
				.apiSecret(sns.getNaverClientSecret())
				.callback(sns.getNaverRedirectUrl())
				.scope("profile")
				.build(sns.getApi20Instance()); //build���� DefaultApi20�� �����ؼ� �־��ش�.
		
		profileUrl = sns.getProfileUrl();
	}

	//���̹��� �α��� ��� ��ũ
	public Object getNaverAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public MemberVO getUserProfile(String code) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code); // accesstoken�� authorization code���� �����Ͽ� ���� ����
		OAuthRequest request = new OAuthRequest(Verb.GET, profileUrl); // request ��ü ����
		oauthService.signRequest(accessToken, request); //������ ������ �������� ���� �ʿ��� ������ ��ü�� ����
		
		Response response = oauthService.execute(request); // accessToken�� request��ü �� �̿��Ͽ� ������ ���� get�Ͽ� response�� ����
		
		return parseJson(response.getBody());
	}

	private MemberVO parseJson(String body) throws Exception {
		MemberVO user = new MemberVO();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		System.out.println("rootNode : ~ "+rootNode);
		/*String id = rootNode.get("id").asText();
		user.setUname(rootNode.get("displayName").asText());
		JsonNode nameNode = rootNode.path("name");
		*/
		if(rootNode.get("resultcode").asText().equals("00")) {
			JsonNode responseNode = rootNode.path("response");
			user.setStr_id(responseNode.get("email").asText());
			user.setSns_id(responseNode.get("id").asText());
			//responseNode.get("nickname").asText();
			//responseNode.get("profile_image").asText();
			if(responseNode.get("gender").asText().equals("M")) {
				user.setGender("man");
			}else {
				user.setGender("woman");
			}
			user.setEmail(responseNode.get("email").asText());
			user.setTel(responseNode.get("mobile").asText());
			user.setName(responseNode.get("name").asText());
			
			//����
			String year = responseNode.get("birthyear").asText();
			String day = responseNode.get("birthday").asText();
			log.info("year : "+year+", day : "+day);
			//SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			//Date date = sdformat.parse(year+"-"+day);
			//log.info("date.getYear() : "+date.getYear()+" - "+date.getDate()+"");
			user.setBirth(year+"-"+day);
			user.setLogin_type("naver");
		}
		
		return user;
	}
}
/*
 {"resultcode":"00","message":"success",
 "response":{"id":"A5vu-2LNtttE_K2kZu4nJYdgRvSApVZAluVqyVbFimI",
 			"nickname":"\ud558\uc77c\ud638",
 			"profile_image":"https:\/\/ssl.pstatic.net\/static\/pwe\/address\/img_profile.png",
 			"gender":"M",
 			"email":"gkdlfgh12@naver.com",
 			"mobile":"010-3878-8230",
 			"mobile_e164":"+821038788230",
 			"name":"\ud558\uc77c\ud638",
 			"birthday":"06-24",
 			"birthyear":"1996"}}
*/