package com.pinmarket.controller.mypage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.service.mypage.MypageService;
import com.pinmarket.util.FileUtil;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
public class ApiMypageController {
	
	@Autowired
	MypageService service;

	//내 정보에서 비밀번호 변경
	@GetMapping("/mypage/changePwd")
	public ResponseEntity<String> changePwd(HttpServletRequest request, Model model, String password, String newPassword) {
		
		MemberVO memberVO = SessionCreater.getSession(request);
		password = DigestUtils.sha256Hex(password);
		
		//기존 비밀번호가 맞는지 체크
		int pwaResult = service.chkPwd(password, memberVO.getId());
		if(pwaResult != 1) {
			return new ResponseEntity<String>("pwdFail",HttpStatus.OK);
		}
		
		//비밀번호 save
		int saveResult = service.changePwd(DigestUtils.sha256Hex(newPassword), memberVO.getId());
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	//내 정보 변경
	@PostMapping("/mypage/changeInfo")
	public ResponseEntity<String> changeInfo(HttpServletRequest request, Model model, @RequestBody MemberVO memberVO){
		
		memberVO.setId(SessionCreater.getSession(request).getId());
		
		int result = service.changeInfo(memberVO);
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	//프로필 이미지 변경
	@PostMapping("/mypage/profileUpload")
	public ResponseEntity<String> profileUpload(
			HttpServletRequest request,
			@RequestParam(value = "profileImg") MultipartFile profileImg,
			@RequestParam(value = "member_id") Integer member_id) throws Exception{
		
		//파일 업로드
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFk_id(member_id);
		attachmentVO.setSave_name(FileUtil.upload("/upload/memberImage", profileImg, request).split("/")[3]);
		attachmentVO.setThumbnail_name(FileUtil.thumbnailUpload("/upload/memberImage/thumbMemberImage", profileImg, request));
		int result = service.changeProfile(profileImg,attachmentVO);
		if(result == 0) {
			service.insertProfile(profileImg,attachmentVO);
		}
		return null;
	}
}
