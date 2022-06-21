package com.pinmarket.controller.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.member.MemberService;
import com.pinmarket.util.FileUtil;
import com.pinmarket.util.naver.SNSLogin;
import com.pinmarket.util.naver.SnsValue;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {
	
	@Autowired
	@Qualifier("memberServiceImpl")
	private MemberService service;
	
	@Autowired
	@Qualifier("naverSns")
	SnsValue naverSns;
	
	@RequestMapping(value = "/auth/naver/callback", method = {RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(Model model, @RequestParam String code, HttpServletRequest requset) throws Exception {
		
		// 1.code를 이용해서 access_token 받기
		SNSLogin snsLogin = new SNSLogin(naverSns);
		// 2.access_token을 이용해서 사용자 profile 정보 가져오기
		MemberVO profile = snsLogin.getUserProfile(code);
		log.info("profile : "+profile);
		// 3.DB 해당 유저가 존재하는지 체크(access_token값 비교 - 그럴려면 db에 naver_id컬럼 하나 추가해야함)
		if(profile != null) {
			MemberVO vo = service.joinCheck(profile.getStr_id(), profile.getSns_id());
			HttpSession session = requset.getSession();
			if(vo != null) {
				//세션에 저장
				log.info("여긴 로그인 : ");
				session.setAttribute("loginVO", vo);
			}else{
				//log.info("profile 정보 : "+profile);
				//회원 없을때는 회원가입 시키기
				int joinResult = service.snsjoin(profile);
				log.info("여긴 회원가입 : ");
				//회원정보 꺼내서 세션 등록
				session.setAttribute("loginVO", service.joinCheck(profile.getStr_id(), profile.getSns_id()));
				return "redirect:/main";
			}
		}else {
			log.info("naver api 오류");
		}
		// 4.존재시 강제로그인, 미존재시 프로필 정보로 회원가입
		
		
		return "redirect:/main";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(Model model) throws ParseException {
		
		log.info("loginForm 1");
		SNSLogin snsLogin = new SNSLogin(naverSns);
		log.info("loginForm 2");
		//로그인 버튼 링크 전달
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		log.info("loginForm 3");
		
		return "member.loginForm";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes ra, MemberVO vo){
		
		log.info("로그인 실행 ~~");
		vo.setPw(DigestUtils.sha256Hex(vo.getPw()));
		MemberVO dbvo = service.login(vo);
		
		
		if(dbvo != null) {
			//세션 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", dbvo);
			return "redirect:/main"; // 메인으로
		}else {
			//실패 처리
			ra.addFlashAttribute("msg", "해당 계정은 존재 하지 않습니다.");
			return "redirect:/member/loginForm";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes ra, MemberVO vo) {
		
		log.info("로그아웃 실행 ~~");
		//세션 생성
		HttpSession session = request.getSession();
		session.removeAttribute("loginVO");
		
		return "redirect:/member/loginForm";
	}
	
	@GetMapping("joinForm")
	public String joinForm() {
		
		log.info("회원가입 폼 ~~");

		return "member.joinForm";
	}
	
	@PostMapping("/join")
	public String join(HttpServletRequest request, MemberVO vo, Model model) throws Exception {
		
		log.info("회원가입 실행 ~~");
		log.info("MemberVO vo : "+vo);
		
		//파일 업로드
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setSave_name(FileUtil.upload("/upload/memberImage", vo.getProfileImage(), request).split("/")[3]);
		attachmentVO.setThumbnail_name(FileUtil.thumbnailUpload("/upload/memberImage/thumbMemberImage", vo.getProfileImage(), request));
		vo.setPw(DigestUtils.sha256Hex(vo.getPw()));
		int result = service.join(vo, attachmentVO);
		log.info("member");
		log.info("vo.getId() : "+vo.getId());
		log.info("save_name : "+vo.getProfileImage().getOriginalFilename());
		log.info("real_name : "+vo.getProfileImage().getOriginalFilename());
		log.info("file_path : "+"/upload/image/"+vo.getProfileImage().getOriginalFilename());
		log.info("file_size : "+vo.getProfileImage().getSize());
		log.info("file_ext : "+vo.getProfileImage().getContentType());
		
		if(result == 1) return "redirect:/member/loginForm";
		else return "redirect:/member/joinForm";
	}
}
