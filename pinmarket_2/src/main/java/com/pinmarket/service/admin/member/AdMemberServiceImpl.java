package com.pinmarket.service.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.member.AdMemberMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdMemberServiceImpl implements AdMemberService{

	//여기에 메퍼 만들기ㅣ
	@Autowired
	AdMemberMapper mapper;
	
	@Override
	public MemberVO login(MemberVO memberVO) {
		return mapper.login(memberVO);
	}

	@Override
	public int memberTotal(String str_id) {
		return mapper.memberTotal(str_id);
	}
	
	@Override
	public List<MemberVO> getList(PageCreator pc, String str_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("str_id", str_id);
		return mapper.getList(map);
	}

	@Override
	public MemberVO detailInfo(int id) {
		return mapper.detailInfo(id);
	}

	@Override
	public int memberDelete(int[] id) {
		return mapper.memberDelete(id);
	}
	
	@Override
	public int updateMember(MemberVO memberVO) {
		return mapper.updateMember(memberVO);
	}

	@Override
	public void changeImage(AttachmentVO attach) {
		mapper.changeImage(attach);
	}

	@Override
	public void insertImage(AttachmentVO attach) {
		mapper.insertImage(attach);
	}

	@Override
	public int writeMember(MemberVO memberVO) {
		return mapper.writeMember(memberVO);
	}
	
}
