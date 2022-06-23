package com.pinmarket.service.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.mapper.mypage.MypageMapper;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageMapper mapper;
	
	@Override
	public MemberVO getMyInfo(int id) {
		return mapper.getMyInfo(id);
	}

	@Override
	public List<OrderVO> getPaymentInfo(int id) {
		return mapper.getPaymentInfo(id);
	}
	
	@Override
	public int chkPwd(String password, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("id", id);
		return mapper.chkPwd(map);
	}

	@Override
	public int changePwd(String newPassword, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newPassword", newPassword);
		map.put("id", id);
		return mapper.changePwd(map);
	}

	@Override
	public int changeInfo(MemberVO memberVO) {
		return mapper.changeInfo(memberVO);
	}

	@Override
	public int changeProfile(MultipartFile profileImg, AttachmentVO attachmentVO) {
		attachmentVO.setFile_type("member");
		attachmentVO.setReal_name(profileImg.getOriginalFilename());
		attachmentVO.setFile_path("/upload/memberImage/");
		attachmentVO.setFile_size(profileImg.getSize());
		attachmentVO.setFile_ext(profileImg.getContentType().split("/")[1]);
		return mapper.changeProfile(attachmentVO);
	}

	@Override
	public void insertProfile(MultipartFile profileImg, AttachmentVO attachmentVO) {
		attachmentVO.setFile_type("member");
		attachmentVO.setReal_name(profileImg.getOriginalFilename());
		attachmentVO.setFile_path("/upload/memberImage/");
		attachmentVO.setFile_size(profileImg.getSize());
		attachmentVO.setFile_ext(profileImg.getContentType().split("/")[1]);
		mapper.insertProfile(attachmentVO);
	}


}
