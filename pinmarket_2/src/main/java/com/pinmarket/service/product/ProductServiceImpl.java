package com.pinmarket.service.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinmarket.mapper.product.ProductMapper;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper mapper;
	
	//상품 목록 get
	@Override
	public List<ProductVO> getProductList() {
		return mapper.getProductList();
	}

	//결제 전 주문 컬럼 생성
	@Transactional
	@Override
	public int createOrder(OrderVO orderVO) {
		int result = mapper.createOrder(orderVO);
		
		//주문 컬럼 생성 후 해당 id 값으로 order_id 만들어서 추가
		Date time = new Date();
		
		orderVO.setOrder_id("p_"+time.getTime()+"_"+orderVO.getId());
		result =+ mapper.addOrder_id(orderVO);
		
		return result;
	}

	//결제 후, 결제 검증 전 주문 컬럼 업데이트
	@Override
	public int updateStatus(OrderVO orderVO) {
		return mapper.updateStatus(orderVO);
	}

	//주문 정보 가져오기
	@Override
	public OrderVO getOrder(String receipt_id) {
		return mapper.getOrder(receipt_id);
	}

	//검증 후 상태 값 변경
	@Override
	public int OrderStatusUpdate(OrderVO vo) {
		return mapper.OrderStatusUpdate(vo);
	}
}
