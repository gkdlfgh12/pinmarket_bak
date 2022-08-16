package com.pinmarket.service.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.member.MemberMapper;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService{
	
	@Autowired MemberMapper mapper;
	
	//회원가입
	@Override
	public int join(MemberVO vo, AttachmentVO attachmentVO) {
		log.info("service의 vo "+vo);
		int result = mapper.join(vo);
		if(vo.getProfileImage() != null) {
			//파일 테이블 저장용
			attachmentVO.setFk_id(vo.getId());
			attachmentVO.setFile_type("member");
			attachmentVO.setReal_name(vo.getProfileImage().getOriginalFilename());
			attachmentVO.setFile_path("/upload/image/");
			attachmentVO.setFile_size(vo.getProfileImage().getSize());
			attachmentVO.setFile_ext(vo.getProfileImage().getContentType().split("/")[1]);
			mapper.profileSave(attachmentVO);
		}
		
		return result;
	}

	//로그인
	@Override
	public MemberVO login(MemberVO vo) {
		return mapper.login(vo);
	}
	
	//네아로 callback 처리
	//id 중복 처리
	@Override
	public MemberVO joinCheck(String str_id, String sns_id) {
		log.info("service : ");
		log.info("str_id : "+str_id);
		log.info("sns_id : "+sns_id);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("str_id", str_id);
		map.put("sns_id", sns_id);
		
		return mapper.joinCheck(map);
	}
	
	//sns 회원가입
	@Override
	public int snsjoin(MemberVO profile) {
		
		return mapper.snsjoin(profile);
	}
	///////////////////api///////////////////////
	
	//id 중복체크
	@Override
	public int idDupleCheck(String str_id) {
		return mapper.idDupleCheck(str_id);
	}

	//상품 결제 후 item cnt 추가
	@Override
	public int addItemCnt(int member_id,int cnt) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("member_id", member_id);
		param.put("cnt", cnt);
		int result = mapper.addItemCnt(param);
		log.info(""+result);
		return result;
	}

	//랭크 등록 시 item 사용했으면 cnt - 1
	@Override
	public void minusItemCnt(int member_id) {
		mapper.minusItemCnt(member_id);
		
	}

	//아이템 개수 확인
	@Override
	public int getItemCnt(int member_id) {
		return mapper.getItemCnt(member_id);
	}




	
}
