package com.pinmarket.service.product;

import java.util.List;

import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

public interface ProductService {

	//상품 목록 get
	List<ProductVO> getProductList();

	//결제 전 주문 컬럼 생성
	int createOrder(OrderVO orderVO);

	//결제 후, 결제 검증 전 주문 컬럼 업데이트
	int updateStatus(OrderVO orderVO);

	//주문 정보 가져오기
	OrderVO getOrder(String receipt_id);

	//검증 후 상태 값 변경
	int OrderStatusUpdate(OrderVO vo);

}
