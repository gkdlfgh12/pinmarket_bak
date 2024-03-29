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
	
	//네아로 콜백 주소
	@RequestMapping(value = "/auth/naver/callback", method = {RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(Model model, @RequestParam String code, HttpServletRequest requset) throws Exception {
		
		// 1.code를 이용해서 access_token 받기
		SNSLogin snsLogin = new SNSLogin(naverSns);
		// 2.access_token을 이용해서 사용자 profile 정보 가져오기
		MemberVO profile = snsLogin.getUserProfile(code);
		// 3.DB 해당 유저가 존재하는지 체크(access_token값 비교 - 그럴려면 db에 naver_id컬럼 하나 추가해야함)
		if(profile != null) {
			//db에 naver에서 가져온 값이 저장되어 있는지 체크
			MemberVO vo = service.joinCheck(profile.getStr_id(), profile.getSns_id());
			HttpSession session = requset.getSession();
			if(vo != null) {
				//세션에 저장
				session.setAttribute("loginVO", vo);
			}else{
				//회원 없을때는 회원가입 시키기
				service.snsjoin(profile);
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
	
	//로그인 폼
	@GetMapping("/loginForm")
	public String loginForm(Model model) throws ParseException {
		
		SNSLogin snsLogin = new SNSLogin(naverSns);
		//로그인 버튼 링크 전달
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		
		return "member.loginForm";
	}
	
	//로그인 처리
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes ra, MemberVO vo){
		
		vo.setPw(DigestUtils.sha256Hex(vo.getPw()));
		MemberVO dbvo = service.login(vo);
		
		
		if(dbvo != null) {
			//세션 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", dbvo);
			return "redirect:/main"; 
		}else {
			//실패 처리
			ra.addFlashAttribute("msg", "해당 계정은 존재 하지 않습니다.");
			return "redirect:/member/loginForm";
		}
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes ra, MemberVO vo) {
		
		//세션 생성
		HttpSession session = request.getSession();
		session.removeAttribute("loginVO");
		
		return "redirect:/member/loginForm";
	}
	
	//회원가입 폼
	@GetMapping("joinForm")
	public String joinForm() {
		
		return "member.joinForm";
	}
	
	//회원가입 처리
	@PostMapping("/join")
	public String join(HttpServletRequest request, MemberVO vo, Model model) throws Exception {
		
		//파일 업로드
		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setSave_name(FileUtil.upload("/upload/memberImage", vo.getProfileImage(), request).split("/")[3]);
		attachmentVO.setThumbnail_name(FileUtil.thumbnailUpload("/upload/memberImage/thumbMemberImage", vo.getProfileImage(), request));
		vo.setPw(DigestUtils.sha256Hex(vo.getPw()));
		int result = service.join(vo, attachmentVO);
		
		if(result == 1) return "redirect:/member/loginForm";
		else return "redirect:/member/joinForm";
	}
}
