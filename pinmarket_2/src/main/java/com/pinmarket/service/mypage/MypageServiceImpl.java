package com.pinmarket.service.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.mapper.mypage.MypageMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.RankingVO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageMapper mapper;
	
	//내 정보 추출
	@Override
	public MemberVO getMyInfo(int id) {
		return mapper.getMyInfo(id);
	}

	//내 결제 정보 추출
	@Override
	public List<OrderVO> getPaymentInfo(int id) {
		return mapper.getPaymentInfo(id);
	}

	//내가 올린 옥션 리스트
	@Override
	public List<AuctionVO> getMyAutionList(int id, PageCreator pc) {
		//경매 리스트 가져오기
		HashMap<String, Object> mapVO = new HashMap<String, Object>();
		mapVO.put("id", id);
		mapVO.put("pc", pc);
		List<AuctionVO> auctionVO = mapper.getMyAutionList(mapVO);
		
		
		//여기서 각각의 auction id로 랭크 검색해서 hash에 넣어서 표출
		if(auctionVO != null) {
			for(Integer i=0;i<auctionVO.size();i++) {
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				List<RankingVO> rankVO = null;
				
				//만약 경매가 성사된 거라면 성사된 경매의 개수와 랭크 id GET
				RankingVO compRankVO = mapper.getAuctionRankStatus(auctionVO.get(i).getId());
				
				//경매가 성사된 것 과 안된것을 분기처리하여 저장
				if(compRankVO == null) {
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", 0);
					rankVO = mapper.getAuctionRankList(map);
				}else {
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", compRankVO.getId());
					rankVO = mapper.getAuctionRankList(map);
				}
				map.clear();
				auctionVO.get(i).setRankingVO(rankVO);
				
				/*List<Object> dataSet = new ArrayList<Object>();
				dataSet.add(auctionVO.get(i));
				dataSet.add(rankVO);
				mapVO.put("auction"+i, dataSet);*/
				//mapVO.put(i, dataSet);
			}
		}
		
		return auctionVO;
	}
	
	//비밀번호 변경시 기존 비밀번호 체크
	@Override
	public int chkPwd(String password, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("id", id);
		return mapper.chkPwd(map);
	}

	//변경된 비밀번호 저장
	@Override
	public int changePwd(String newPassword, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newPassword", newPassword);
		map.put("id", id);
		return mapper.changePwd(map);
	}

	//정보 변경
	@Override
	public int changeInfo(MemberVO memberVO) {
		return mapper.changeInfo(memberVO);
	}

	//프로필 정보 변경
	@Override
	public int changeProfile(MultipartFile profileImg, AttachmentVO attachmentVO) {
		attachmentVO.setFile_type("member");
		attachmentVO.setReal_name(profileImg.getOriginalFilename());
		attachmentVO.setFile_path("/upload/memberImage/");
		attachmentVO.setFile_size(profileImg.getSize());
		attachmentVO.setFile_ext(profileImg.getContentType().split("/")[1]);
		return mapper.changeProfile(attachmentVO);
	}

	//프로필 없을 시 추가
	@Override
	public void insertProfile(MultipartFile profileImg, AttachmentVO attachmentVO) {
		attachmentVO.setFile_type("member");
		attachmentVO.setReal_name(profileImg.getOriginalFilename());
		attachmentVO.setFile_path("/upload/memberImage/");
		attachmentVO.setFile_size(profileImg.getSize());
		attachmentVO.setFile_ext(profileImg.getContentType().split("/")[1]);
		mapper.insertProfile(attachmentVO);
	}

	//내가 올린 경매 총 게시글 수
	@Override
	public int getMyAutionTotal(int member_id) {
		return mapper.getMyAutionTotal(member_id);
	}

	//내가 올린 랭크의 수
	@Override
	public int getMyRankTotal(int member_id) {
		return mapper.getMyRankTotal(member_id);
	}

	//내가 올린 랭크와 옥션 정보 겟
	@Override
	public List<RankingVO> getMyRankList(int member_id, PageCreator pc) {
		
		//자 여기 보세요!!~  일단 랭크 리스트 가져와서 해당 랭크 리스트들의 옥션 아이디를 하나하나 비교해서 가져온 후 랭크vo에 옥션 객체 자체를 저장
		
		//랭크 리스트 가져오기
		HashMap<String, Object> mapVO = new HashMap<String, Object>();
		mapVO.put("member_id", member_id);
		mapVO.put("pc", pc);
		List<RankingVO> rankVO = mapper.getMyRankList(mapVO);
		
		if(rankVO != null) {
			for(Integer i=0;i<rankVO.size();i++) {
				
				AuctionVO tmpAuctionVO = mapper.getAuctionInfo(rankVO.get(i).getAuction_id());
				
				int result = mapper.checkAucRankStatus(rankVO.get(i));
				rankVO.get(i).setAuctionVO(tmpAuctionVO);
				rankVO.get(i).setAucResult(result);
				
			}
		}
		
		return rankVO;
	}



}
