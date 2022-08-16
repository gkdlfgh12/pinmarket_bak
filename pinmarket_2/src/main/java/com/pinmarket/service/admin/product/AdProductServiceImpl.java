package com.pinmarket.service.admin.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.product.AdProductMapper;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
public class AdProductServiceImpl implements AdProductService{
	
	@Autowired
	AdProductMapper mapper;
	
	//어드민 상품 목록 가져오기
	@Override
	public List<ProductVO> list() {
		return mapper.list();
	}

	//상품 수정
	@Override
	public int updateProduct(ProductVO productVO) {
		return mapper.updateProduct(productVO);
	}

	//상품 이미지 변경
	@Override
	public void changeImage(AttachmentVO attach) {
		mapper.changeImage(attach);
	}

	//상품 등록
	@Override
	public int insertProduct(ProductVO productVO) {
		return mapper.insertProduct(productVO);
	}

	//상품 등록에 따른 이미지 저장
	@Override
	public void insertImage(AttachmentVO attach) {
		mapper.insertImage(attach);
	}

	//상품 삭제
	@Override
	public int deleteProduct(Integer id) {
		return mapper.deleteProduct(id);
	}

	//상품 이미지 삭제
	@Override
	public int deleteAttachment(Integer id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return mapper.deleteAttachment(map);
	}
}
