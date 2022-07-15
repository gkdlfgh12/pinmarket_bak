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

	@GetMapping("/list")
	public String list(Model model) {
		
		log.info("product : ");
		
		List<ProductVO> list = service.list();
		log.info("list : ~ "+list);
		model.addAttribute("list",list);
		
		return "admin.product.list";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(HttpServletRequest request, Model model, ProductVO productVO) throws Exception {
		
		log.info("list : ~~ "+productVO);
		int result = service.updateProduct(productVO);
		log.info("result : "+result);
		
		if(productVO.getProduct_file() != null) {
			log.info("상품 !! ");
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
			log.info("attach : ~`~ "+attach);
			service.changeImage(attach);
		}
		
		return "redirect:/admin/product/list";
	}
	
	@PostMapping("/insertProduct")
	public String insertProduct(HttpServletRequest request, Model model, ProductVO productVO) throws Exception {
		
		log.info("insertProduct : ~~ "+productVO);
		int result = service.insertProduct(productVO);
		log.info("insertProduct id : ~~ "+productVO.getId());
		log.info("result : "+result);
		
		if(productVO.getProduct_file() != null) {
			log.info("상품 !! ");
			AttachmentVO attach = new AttachmentVO();
			attach.setFk_id(productVO.getId());
			attach.setFile_type("product");
			attach.setReal_name(productVO.getProduct_file().getOriginalFilename());
			attach.setFile_path("/upload/productImage/");
			attach.setFile_size(productVO.getProduct_file().getSize());
			attach.setFile_ext(productVO.getProduct_file().getContentType().split("/")[1]);
			attach.setSave_name(FileUtil.upload("/upload/productImage", productVO.getProduct_file(), request).split("/")[3]);
			attach.setThumbnail_name("");
			log.info("attach : ~`~ "+attach);
			service.insertImage(attach);
		}
		
		return "redirect:/admin/product/list";
	}
}
