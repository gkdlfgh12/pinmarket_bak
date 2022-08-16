package com.pinmarket.controller.admin.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pinmarket.service.admin.product.AdProductService;
import com.pinmarket.service.admin.product.AdProductServiceImpl;
import com.pinmarket.util.FileUtil;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.ProductVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/product")
@Log4j
public class AdProductController {
	
	@Autowired
	AdProductService service;

	//상품 목록 가져오기
	@GetMapping("/list")
	public String list(Model model) {
		
		List<ProductVO> list = service.list();
		//개행문자 -> html태그로 변경 (줄바꿈)
		for(int i=0;i<list.size();i++) {
			list.get(i).setDescript(list.get(i).getDescript().replace("\r\n", "</br>"));
		}
		model.addAttribute("list",list);
		
		return "admin.product.list";
	}
	
	//상품 수정
	@PostMapping("/updateProduct")
	public String updateProduct(HttpServletRequest request, Model model, ProductVO productVO) throws Exception {
		
		service.updateProduct(productVO);
		
		if(productVO.getProduct_file().getOriginalFilename() != "") {
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(productVO.getId());
			attach.setFile_type("product");
			attach.setReal_name(productVO.getProduct_file().getOriginalFilename());
			attach.setFile_path("/upload/productImage/");
			attach.setFile_size(productVO.getProduct_file().getSize());
			attach.setFile_ext(productVO.getProduct_file().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/productImage", productVO.getProduct_file(), request).split("/")[3]);
			//attach.setThumbnail_name(FileUtil.thumbnailUpload("/upload/thumbProductImage", productVO.getProduct_file(), request));
			attach.setThumbnail_name("");
			service.changeImage(attach);
		}
		
		return "redirect:/admin/product/list";
	}
	
	//상품 입력
	@PostMapping("/insertProduct")
	public String insertProduct(HttpServletRequest request, Model model, ProductVO productVO) throws Exception {
		
		service.insertProduct(productVO);
		
		if(productVO.getProduct_file() != null) {
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(productVO.getId());
			attach.setFile_type("product");
			attach.setReal_name(productVO.getProduct_file().getOriginalFilename());
			attach.setFile_path("/upload/productImage/");
			attach.setFile_size(productVO.getProduct_file().getSize());
			attach.setFile_ext(productVO.getProduct_file().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/productImage", productVO.getProduct_file(), request).split("/")[3]);
			attach.setThumbnail_name("");
			service.insertImage(attach);
		}
		
		return "redirect:/admin/product/list";
	}
}
