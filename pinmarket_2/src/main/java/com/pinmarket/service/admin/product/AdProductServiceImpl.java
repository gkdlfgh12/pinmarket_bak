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
@Log4j
public class AdProductServiceImpl implements AdProductService{
	
	@Autowired
	AdProductMapper mapper;
	
	@Override
	public List<ProductVO> list() {
		return mapper.list();
	}

	@Override
	public int updateProduct(ProductVO productVO) {
		return mapper.updateProduct(productVO);
	}

	@Override
	public void changeImage(AttachmentVO attach) {
		mapper.changeImage(attach);
	}

	@Override
	public int insertProduct(ProductVO productVO) {
		return mapper.insertProduct(productVO);
	}

	@Override
	public void insertImage(AttachmentVO attach) {
		mapper.insertImage(attach);
	}

	@Override
	public int deleteProduct(Integer id) {
		return mapper.deleteProduct(id);
	}

	@Override
	public int deleteAttachment(Integer id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return mapper.deleteAttachment(map);
	}
}
