package com.pinmarket.service.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.main.MainMapper;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MainServiceImpl implements MainService{

	@Autowired
	MainMapper mapper;
	
	@Override
	public List<AuctionVO> getTopAuction(int startRow, int endRow) {
		Map<String, Integer> row = new HashMap<String, Integer>();
		row.put("startRow", startRow);
		row.put("endRow", endRow);
		return mapper.getTopAuction(row);
	}

	@Override
	public ProductVO getTopProduct() {
		//상품을 구매한 개수 출력
		Integer topCnt = mapper.getTopProductCnt();
		List<OrderVO> topProductList = mapper.getTopProductList();
		log.info("topCnt : ~ "+topCnt);
		int product_id = 0;
		//여기서 리스트랑 주문 개수 비교해서 인기 상품 가져오기
		if(topProductList.size() != 0) {
			log.info(1);
			for(int i=0;i<topProductList.size();i++) {
				log.info(2);
				if(topProductList.get(i).getOrder_cnt() == topCnt) {
					log.info(topProductList.get(i).getOrder_cnt());
					log.info(topCnt);
					product_id = topProductList.get(i).getProduct_id();
				}
			}
		}else {
			log.info(4);
		}
		log.info("resultId : ~ "+product_id);
		ProductVO productVO = mapper.getTopProduct(product_id);
		log.info("productVOproductVO : ~ "+productVO);
		
		return productVO;
	}

}
