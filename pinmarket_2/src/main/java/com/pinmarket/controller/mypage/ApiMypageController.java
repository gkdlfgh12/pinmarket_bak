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
@Log4j
public class ApiMypageController {
	
	@Autowired
	MypageService service;

	@GetMapping("/mypage/changePwd")
	public ResponseEntity<String> changePwd(HttpServletRequest request, Model model, String password, String newPassword) {
		
		MemberVO memberVO = SessionCreater.getSession(request);
		
		log.info("/savePwd 들어옴 : ~~ "+newPassword+" : "+password);
		password = DigestUtils.sha256Hex(password);
		
		//기존 비밀번호가 맞는지 체크
		int pwaResult = service.chkPwd(password, memberVO.getId());
		log.info("pwaResult : ~ "+pwaResult);
		if(pwaResult != 1) {
			return new ResponseEntity<String>("pwdFail",HttpStatus.OK);
		}
		
		//비밀번호 save
		int saveResult = service.changePwd(DigestUtils.sha256Hex(newPassword), memberVO.getId());
		log.info("saveResult : ~ "+saveResult);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PostMapping("/mypage/changeInfo")
	public ResponseEntity<String> changeInfo(HttpServletRequest request, Model model, @RequestBody MemberVO memberVO){
		
		memberVO.setId(SessionCreater.getSession(request).getId());
		log.info("changeInfo 들어옴 : ~ ");
		log.info("memberVO : ~ "+memberVO);
		
		int result = service.changeInfo(memberVO);
		if(result == 1) return new ResponseEntity<String>("success",HttpStatus.OK);
		else return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@PostMapping("/mypage/profileUpload")
	public ResponseEntity<String> profileUpload(
			HttpServletRequest request,
			@RequestParam(value = "profileImg") MultipartFile profileImg,
			@RequestParam(value = "member_id") Integer member_id) throws Exception{
		
		log.info("log info () ~ ~ ");
		log.info("file : ~ "+profileImg);
		log.info("member_id : ~ "+member_id);
		//파일 업로드
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFk_id(member_id);
		attachmentVO.setSave_name(FileUtil.upload("/upload/memberImage", profileImg, request).split("/")[3]);
		attachmentVO.setThumbnail_name(FileUtil.thumbnailUpload("/upload/memberImage/thumbMemberImage", profileImg, request));
		log.info("member");
		log.info("save_name : "+profileImg.getOriginalFilename());
		log.info("real_name : "+profileImg.getOriginalFilename());
		log.info("file_path : "+"/upload/memberImage/"+profileImg.getOriginalFilename());
		log.info("file_size : "+profileImg.getSize());
		log.info("file_ext : "+profileImg.getContentType());
		int result = service.changeProfile(profileImg,attachmentVO);
		if(result == 0) {
			service.insertProfile(profileImg,attachmentVO);
		}
		log.info("result : ~ "+result);
		return null;
	}
}
