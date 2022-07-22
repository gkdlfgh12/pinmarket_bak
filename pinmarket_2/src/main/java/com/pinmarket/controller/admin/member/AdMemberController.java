package com.pinmarket.controller.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.member.AdMemberServiceImpl;
import com.pinmarket.util.FileUtil;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.PageVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/member")
@Log4j
public class AdMemberController {
	
	@Autowired
	AdMemberServiceImpl service;
	
	//로그인 폼
	@GetMapping("/loginForm")
	public String loginForm() {
		
		log.info("관리자 로그인 폼");
		
		return "admin.login.loginForm";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes ra, MemberVO memberVO) {
		
		log.info("관리자 로그인");
		log.info("memberVO : ~ "+memberVO);
		memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		
		MemberVO saveVO = service.login(memberVO);
		log.info("saveVO : ~ "+saveVO);
		
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
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		//세션 생성
		HttpSession session = request.getSession();
		session.removeAttribute("loginVO");
		
		return "redirect:/admin/member/loginForm";
	}
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model, PageVO pageVO, @RequestParam(required = false, defaultValue = "") String strId) {
		log.info("관리자 멤버 관리 : "+strId);
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("adminMemberList");
		}
		//게시글 총 개수 겟
		int totalCnt = service.memberTotal(strId);
		log.info("totalCnt : "+totalCnt);
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		log.info("pageObject : ~ "+pc);
		log.info("pageVO : ~ "+pageVO);
		
		List<MemberVO> list = service.getList(pc, strId);
		model.addAttribute("list",list);
		log.info("list : ~~~ "+list);
		
		return "admin.member.list";
	}
	
	@PostMapping("/memberDelete")
	public String memberDelete(HttpServletRequest request, Model model, @RequestParam int []delChk) {
		
		for(int i=0;i<delChk.length;i++) {
			log.info("멤버 삭제 : ~ "+delChk[i]);
		}
		int result = service.memberDelete(delChk);
		log.info("result : ~ "+result);
		
		return "redirect:/admin/member/list";
	}
	
	@PostMapping("/updateMember")
	public String updateMember(HttpServletRequest request, Model model, MemberVO memberVO) throws Exception {
		
		log.info("멤버 수정 : ~ "+memberVO);
		if(!memberVO.getPw().equals("")) {
			memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		}
		int result = service.updateMember(memberVO);
		log.info("result : ~ "+result);
		
		if(memberVO.getProfileImage() != null) {
			log.info("상품 !! ");
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
			log.info("attach : ~`~ "+attach);
			service.changeImage(attach);
		}
		
		return "redirect:/admin/member/list";
	}
	
	@PostMapping("/writeMember")
	public String writeMember(HttpServletRequest request, Model model, MemberVO memberVO) throws Exception {
		
		log.info("멤버 생성 : ~ "+memberVO);
		if(!memberVO.getPw().equals("")) {
			memberVO.setPw(DigestUtils.sha256Hex(memberVO.getPw()));
		}
		int result = service.writeMember(memberVO);
		log.info("result : ~ "+result);
		
		if(memberVO.getProfileImage() != null) {
			log.info("멤버 !! ");
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(memberVO.getId());
			attach.setFile_type("member");
			attach.setReal_name(memberVO.getProfileImage().getOriginalFilename());
			attach.setFile_path("/upload/memberImage/");
			attach.setFile_size(memberVO.getProfileImage().getSize());
			attach.setFile_ext(memberVO.getProfileImage().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/memberImage", memberVO.getProfileImage(), request).split("/")[3]);
			attach.setThumbnail_name("");
			log.info("attach : ~`~ "+attach);
			service.insertImage(attach);
		}
		
		return "redirect:/admin/member/list";
	}
}


