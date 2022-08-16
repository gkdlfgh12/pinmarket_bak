package com.pinmarket.controller.admin.auction;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinmarket.service.admin.auction.adAuctionService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.PageVO;

@Controller
@RequestMapping("/admin/auction")
public class adAuctionController {
	
	@Autowired
	adAuctionService service;
	
	//경매 리스트 가져오기
	@GetMapping("/list")
	public String dashboard(Model model, PageVO pageVO, @RequestParam(defaultValue = "") String title) {
		
		model.addAttribute("page",pageVO.getPage());
		model.addAttribute("countPerPage",pageVO.getCountPerPage());
		
		//자유 질문에 맞게 페이징 정보 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("adminAuctionList");
		}
		model.addAttribute("title",title);
		int total = service.getTotal(title);
		
		model.addAttribute("totalCnt",total);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(total);
		model.addAttribute("pc",pc);
		List<AuctionVO> list = service.getList(pc, title);
		model.addAttribute("list",list);
		
		return "admin.auction.list";
	}
	
	//경매 선택삭제
	@PostMapping("/del")
	public String delete(Model model, RedirectAttributes ra, PageVO pageVO, @RequestParam Integer[] delChk) {
		
		/*String str_no="";
		for(int i=0;i<delChk.length;i++) {
			str_no += delChk[i];
			if(i != delChk.length-1) {
				str_no += ",";
			}
		}*/
		
		service.deleteAuction(delChk); 
		ra.addFlashAttribute("msg","완료");			
		
		
		return "redirect:/admin/auction/list?page="+pageVO.getPage()+"&countPerPage="+pageVO.getCountPerPage();
	}
	
	//경매 상세보기
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam Integer id) {
		
		//옥션 글 과 이미지 파일 가져오기
		Map<String, Object> mapInfo = service.getDetail(id);
		
		//개행문자 -> html태그로 변경 (줄바꿈)
		((AuctionVO) mapInfo.get("auction")).setContent(((AuctionVO) mapInfo.get("auction")).getContent().replace("\n", "</br>"));
		
		model.addAttribute("auctionVO",mapInfo.get("auction"));
		model.addAttribute("attachVO",mapInfo.get("attach"));
		
		return "admin.auction.detail";
	}
}
