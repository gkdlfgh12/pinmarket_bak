package com.pinmarket.controller.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.member.AdMemberServiceImpl;
import com.pinmarket.util.FileUtil;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.PageVO;

@Controller
@RequestMapping("/admin/member")
public class AdMemberController {
	
	@Autowired
	AdMemberServiceImpl service;
	
	//로그인 폼
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "admin.login.loginForm";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes ra, MemberVO memberVO) {
		
		memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		
		MemberVO saveVO = service.login(memberVO);
		
		if(saveVO == null) {
			//실패 처리
			ra.addFlashAttribute("msg", "해당 계정은 존재 하지 않습니다.");
			return "redirect:/admin/member/loginForm";
		}else if(saveVO.getMember_level() != 0){
			//권한 부족
			ra.addFlashAttribute("msg", "해당 계정은 권한이 부족합니다.");
			return "redirect:/admin/member/loginForm";
		}else {
			//세션 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", saveVO);
			return "redirect:/admin/member/list";
		}
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		//세션 생성
		HttpSession session = request.getSession();
		session.removeAttribute("loginVO");
		
		return "redirect:/admin/member/loginForm";
	}
	
	//계정 목록 가져오기
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model, PageVO pageVO, @RequestParam(required = false, defaultValue = "") String strId) {
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("adminMemberList");
		}
		//게시글 총 개수 겟
		int totalCnt = service.memberTotal(strId);
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		
		List<MemberVO> list = service.getList(pc, strId);
		model.addAttribute("list",list);
		
		return "admin.member.list";
	}
	
	//계정 삭제
	@PostMapping("/memberDelete")
	public String memberDelete(HttpServletRequest request, Model model, @RequestParam int []delChk) {
		
		service.memberDelete(delChk);
		
		return "redirect:/admin/member/list";
	}
	
	//계정 수정
	@PostMapping("/updateMember")
	public String updateMember(HttpServletRequest request, Model model, MemberVO memberVO) throws Exception {
		
		if(!memberVO.getPw().equals("")) {
			memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		}
		service.updateMember(memberVO);
		
		if(memberVO.getProfileImage() != null) {
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(memberVO.getId());
			attach.setFile_type("member");
			attach.setReal_name(memberVO.getProfileImage().getOriginalFilename());
			attach.setFile_path("/upload/memberImage/");
			attach.setFile_size(memberVO.getProfileImage().getSize());
			attach.setFile_ext(memberVO.getProfileImage().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/memberImage", memberVO.getProfileImage(), request).split("/")[3]);
			//attach.setThumbnail_name(FileUtil.thumbnailUpload("/upload/thumbMemberImage", memberVO.getProfileImage(), request));
			attach.setThumbnail_name("");
			service.changeImage(attach);
		}
		
		return "redirect:/admin/member/list";
	}
	
	//계정 추가
	@PostMapping("/writeMember")
	public String writeMember(HttpServletRequest request, Model model, MemberVO memberVO) throws Exception {
		
		if(!memberVO.getPw().equals("")) {
			memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		}
		int result = service.writeMember(memberVO);
		
		if(memberVO.getProfileImage() != null) {
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(memberVO.getId());
			attach.setFile_type("member");
			attach.setReal_name(memberVO.getProfileImage().getOriginalFilename());
			attach.setFile_path("/upload/memberImage/");
			attach.setFile_size(memberVO.getProfileImage().getSize());
			attach.setFile_ext(memberVO.getProfileImage().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/memberImage", memberVO.getProfileImage(), request).split("/")[3]);
			attach.setThumbnail_name("");
			service.insertImage(attach);
		}
		
		return "redirect:/admin/member/list";
	}
}


