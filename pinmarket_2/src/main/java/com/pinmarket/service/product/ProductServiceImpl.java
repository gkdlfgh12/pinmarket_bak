package com.pinmarket.service.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.product.ProductMapper;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper mapper;
	
	@Override
	public List<ProductVO> getProductList() {
		return mapper.getProductList();
	}

	@Override
	public int createOrder(OrderVO orderVO) {
		
		int result = mapper.createOrder(orderVO);
		
		//주문 컬럼 생성 후 해당 id 값으로 order_id 만들어서 추가
		Date time = new Date();
		
		orderVO.setOrder_id("p_"+time.getTime()+"_"+orderVO.getId());
		result =+ mapper.addOrder_id(orderVO);
		
		return result;
	}

	@Override
	public int updateStatus(OrderVO orderVO) {
		return mapper.updateStatus(orderVO);
	}

	@Override
	public OrderVO getOrder(String receipt_id) {
		return mapper.getOrder(receipt_id);
	}

	@Override
	public int OrderStatusUpdate(OrderVO vo) {
		return mapper.OrderStatusUpdate(vo);
	}
}
