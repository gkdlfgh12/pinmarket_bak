package com.pinmarket.controller.product;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinmarket.service.member.MemberService;
import com.pinmarket.service.product.ProductService;
import com.pinmarket.util.SessionCreater;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;

import bootpay.javaApache.model.request.Cancel;
import bootpay.javaApache2.Bootpay;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
@Log4j
public class ApiProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/product/createOrder")
	public ResponseEntity<OrderVO> createOrder(HttpServletRequest request, Model model, 
			@RequestParam Integer product_id) {
		OrderVO orderVO = new OrderVO();
		
		MemberVO memberVO = SessionCreater.getSession(request);
		orderVO.setMember_id(memberVO.getId());
		orderVO.setProduct_id(product_id);
		log.info("orderCreater : ~ "+product_id);
		
		//결제 전 주문 컬럼 생성
		int result = service.createOrder(orderVO);
		log.info("result : ~~ "+result);
		log.info("orderVO.getId() : ~ "+orderVO.getOrder_id());
		
		return new ResponseEntity<OrderVO>(orderVO,HttpStatus.OK);
	}
	
	@PostMapping("/product/updateOrder")
	public ResponseEntity<String> updateOrder(@RequestBody OrderVO orderVO){
		
		//여기서 결제 후 주문 정보 업데이트 하면 됨
		log.info("orderVO : ~ "+orderVO);
		int result = service.updateStatus(orderVO);
		log.info("result : ~ "+result);
		if(result == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
		else return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	//결제 검증
	@GetMapping("/product/veri")
	public ResponseEntity<String> veri(String receipt_id, String member_id) throws Exception {
		log.info("member_id : ~ "+member_id);
		String rest_application_id = "626657eb270180001ef696c5";
		String private_key = "pBugUGWHXaPenuVEy1ddijiWENITqeUkZ+oCK0C4lW8=";
		String str = "";
		int result;
		Bootpay api = new Bootpay(
				rest_application_id,
		        private_key
		);
		api.getAccessToken(); // accessToken 값 가져와서 api 참조변수에 저장
		log.info("api.token 값 확인 : "+api.token); // accessToken 값 표출
		
		//부트페이 서버에서 결제 정보들을 db에 저장된 값과 비교하기 위해 가져옴
		try {
		    HttpResponse res = api.verify(receipt_id);
		    str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//여기서 서버에서 recipt_id와 token값을 보내서 헤더 status값  과 db에 저장된 가격을 비교하여 검증을 하고
		//오류가 있으면 결제 취소 처리를 하면됨
		//그리고 헤더값가 함께 body값을 세팅해서 알맞게 리턴하면 됨... 끝....
		
		OrderVO orderView = service.getOrder(receipt_id); 
		log.info("orderViewVO : "+orderView);
		OrderVO vo = new OrderVO();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(str);
		log.info("rootNode.get(\"status\").asInt() : "+rootNode.get("status").asInt());
		log.info("rootNode.path(\"data\").get(\"price\").asInt() : "+rootNode.path("data").get("price").asInt());
		log.info("rootNode.get(\"status\").asInt() : "+rootNode.get("status").asInt());
		if(rootNode.get("status").asInt() == 200) {
			log.info("1차 !!!!  "+rootNode.get("status").asInt());
			if(rootNode.path("data").get("price").asInt() == Integer.parseInt(orderView.getPrice()) && rootNode.path("data").get("status").asInt() == 1) {
				log.info("2차 !!!!");
				//주문 상태 값 comp로 변경
				vo.setReceipt_id(receipt_id);
				vo.setStatus("comp");
				result = service.OrderStatusUpdate(vo);
				
				//결제 완료 후 해당 유저의 member_tb에서 item count 추가
				log.info("getMember_id() : "+orderView.getMember_id());
				//이건 상품 별로 아이템 개수 분기 처리 강제로 함
				int cnt= orderView.getItem_cnt();
				/*if(orderView.getProduct_id() == 1) cnt = 5;
				else if(orderView.getProduct_id() == 2) cnt = 10;
				else if(orderView.getProduct_id() == 3) cnt = 15;*/
				//멤버에 item 등록
				log.info("addItemCnt : "+memberService.addItemCnt(orderView.getMember_id(), cnt));
				
				return ResponseEntity.status(HttpStatus.OK).body("success");
			}
		}
		
		//여기서 부턴 취소 프로세스
		Cancel cancel = new Cancel();
		cancel.receiptId = receipt_id;
		cancel.name = "관리자 하일호";
		cancel.reason = "테스트용 취소";

		try {
		    HttpResponse res = api.receiptCancel(cancel);
		    String str1 = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
		    vo.setReceipt_id(receipt_id);
		    vo.setStatus("cancel");
		    service.OrderStatusUpdate(vo);
		    System.out.println(str1);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
	}
}
