package com.pinmarket.controller.admin.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.auction.adAuctionService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.PageVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/auction")
@Log4j
public class adAuctionController {
	
	@Autowired
	adAuctionService service;
	
	@GetMapping("/list")
	public String dashboard(Model model, PageVO pageVO, @RequestParam(defaultValue = "") String title) {
		
		log.info("auction : ");
		model.addAttribute("page",pageVO.getPage());
		model.addAttribute("countPerPage",pageVO.getCountPerPage());
		
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("adminAuctionList");
		}
		model.addAttribute("title",title);
		int total = service.getTotal(title);
		log.info("auction : ~~ : "+total);
		model.addAttribute("totalCnt",total);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(total);
		model.addAttribute("pc",pc);
		List<AuctionVO> list = service.getList(pc, title);
		model.addAttribute("list",list);
		log.info("auction : ~~ : "+list);
		
		return "admin.auction.list";
	}
	
	@PostMapping("/del")
	public String delete(Model model, RedirectAttributes ra, PageVO pageVO, @RequestParam Integer[] delChk) {
		
		log.info("delChk[] : ~ "+delChk);
		String str_no="";
		for(int i=0;i<delChk.length;i++) {
			log.info("del : ~ "+delChk[i]);
			str_no += delChk[i];
			if(i != delChk.length-1) {
				str_no += ",";
			}
		}
		log.info("auction del : "+str_no);
		
		int result = service.deleteAuction(delChk); 
		ra.addFlashAttribute("msg","완료");			
		
		log.info("result : ~~ "+result);
		
		return "redirect:/admin/auction/list?page="+pageVO.getPage()+"&countPerPage="+pageVO.getCountPerPage();
	}
	
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam Integer id) {
		
		log.info("detail : "+id);
		//옥션 글 과 이미지 파일 가져오기
		Map<String, Object> mapInfo = service.getDetail(id);
		model.addAttribute("auctionVO",mapInfo.get("auction"));
		model.addAttribute("attachVO",mapInfo.get("attach"));
		log.info("attach : ~~ "+mapInfo.get("rankList"));
		
		return "admin.auction.detail";
	}
}
