package com.pinmarket.service.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.member.AdMemberMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

@Service
public class AdMemberServiceImpl implements AdMemberService{

	//여기에 메퍼 만들기ㅣ
	@Autowired
	AdMemberMapper mapper;
	
	@Override
	public MemberVO login(MemberVO memberVO) {
		return mapper.login(memberVO);
	}

	@Override
	public int memberTotal() {
		return mapper.memberTotal();
	}
	
	@Override
	public List<MemberVO> getList(PageCreator pc) {
		return mapper.getList(pc);
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
