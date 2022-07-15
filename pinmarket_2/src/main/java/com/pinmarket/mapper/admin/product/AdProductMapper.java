package com.pinmarket.mapper.admin.product;

import java.util.List;
import java.util.Map;

import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.ProductVO;

public interface AdProductMapper {

	//어드민 상품 목록 가져오기
	public List<ProductVO> list();
	
	//상품 입력
	public int updateProduct(ProductVO productVO);
	
	//상품 이미지 변경
	public void changeImage(AttachmentVO attach);
	
	//상품 등록
	public int insertProduct(ProductVO productVO);
	
	//상품 등록에 따른 이미지 저장
	public void insertImage(AttachmentVO attach);
	
	//상품 삭제
	public int deleteProduct(Integer id);
	
	//상품 이미지 삭제
	public int deleteAttachment(Map<String, Object> map);
}
