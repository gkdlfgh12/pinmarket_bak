package com.pinmarket.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pinmarket.service.mypage.MypageService;
import com.pinmarket.util.PageCreator;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.RankingVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MypageService service;

	//내 정보 출력
	@GetMapping("/myInfo")
	public String myInfo(HttpServletRequest request, Model model) {
		
		MemberVO loginVO = SessionCreater.getSession(request);
		
		MemberVO memberVO = service.getMyInfo(loginVO.getId());
		model.addAttribute("memberVO",memberVO);
		
		return "mypage.myInfo";
	}
	
	//내 결제 정보 출력
	@GetMapping("/paymentInfo")
	public String paymentInfo(HttpServletRequest request, Model model) {
		
		MemberVO loginVO = SessionCreater.getSession(request);
		List<OrderVO> orderVO = service.getPaymentInfo(loginVO.getId());
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<orderVO.size();i++) {
			orderVO.get(i).getProductVO().setDescript((orderVO.get(i).getProductVO().getDescript().replace("\r\n", "</br>")));
		}
		model.addAttribute("orderVO",orderVO);
		return "mypage.paymentInfo";
	}
	
	//내가 올린 경매 정보
	@GetMapping("/myAutionList")
	public String myAutionList(HttpServletRequest request, Model model, PageVO pageVO) {
		//페이징 버튼을 클릭하지 않았다면 페이징 정보 내가 올린 게시글에 맞게 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("myAutionList");
		}
		
		MemberVO loginVO = SessionCreater.getSession(request);
		//---------------------------
		//게시글 총 개수 겟
		int totalCnt = service.getMyAutionTotal(loginVO.getId());
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		//---------------------------
		
		List<AuctionVO> listSet = service.getMyAutionList(loginVO.getId(),pc);
		
		model.addAttribute("listSet",listSet);
		
		return "mypage.myAutionInfo";
	}
	
	//내가 올린 랭크 목록
	@GetMapping("/myRankList")
	public String myRankList(HttpServletRequest request, Model model, PageVO pageVO) {
		//페이징 버튼을 클릭하지 않았다면 페이징 정보 내가 올린 게시글에 맞게 수정
		if(pageVO.getPage() == 1) {
			pageVO.setPaging("myRankList");
		}
		
		MemberVO loginVO = SessionCreater.getSession(request);
		
		//아직 소스 완성 안됨!!
		//랭크의 토탈 구하기
		int totalCnt = service.getMyRankTotal(loginVO.getId());
		model.addAttribute("totalCnt",totalCnt);
		PageCreator pc = new PageCreator();
		pc.setPaging(pageVO);
		pc.setArticleTotalCount(totalCnt);
		model.addAttribute("pc",pc);
		
		//랭크리스트와 그에 대응 되는 옥션을 같이 가져오는 코드
		List<RankingVO> listSet = service.getMyRankList(loginVO.getId(),pc);
		model.addAttribute("listSet",listSet);
		return "mypage.myRankList";
	}
}
