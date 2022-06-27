package com.pinmarket.service.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pinmarket.mapper.mypage.MypageMapper;
import com.pinmarket.vo.AttachmentVO;
import com.pinmarket.vo.AuctionVO;
import com.pinmarket.vo.MemberVO;
import com.pinmarket.vo.OrderVO;
import com.pinmarket.vo.ProductVO;
import com.pinmarket.vo.RankingVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
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
	public List<AuctionVO> getMyAutionList(int id) {
		List<AuctionVO> auctionVO = mapper.getMyAutionList(id);
		
		HashMap<String, Object> mapVO = new HashMap<String, Object>();
		
		//여기서 각각의 auction id로 랭크 검색해서 hash에 넣어서 표출
		if(auctionVO != null) {
			for(Integer i=0;i<auctionVO.size();i++) {
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				List<RankingVO> rankVO = null;
				
				//만약 경매가 성사된 거라면 성사된 경매의 개수와 랭크 id GET
				RankingVO compRankVO = mapper.getAuctionRankStatus(auctionVO.get(i).getId());
				log.info("결과 값 0 - 1 "+compRankVO);
				
				//경매가 성사된 것 과 안된것을 분기처리하여 저장
				if(compRankVO == null) {
					log.info("결과 값 0 "+auctionVO.get(i));
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", 0);
					rankVO = mapper.getAuctionRankList(map);
				}else {
					log.info("결과 값 1 이상 ");
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", compRankVO.getId());
					rankVO = mapper.getAuctionRankList(map);
				}
				
				auctionVO.get(i).setRankingVO(rankVO);
				
				log.info("auctionVO : ~  ~~ "+auctionVO.get(i));
				
				/*List<Object> dataSet = new ArrayList<Object>();
				dataSet.add(auctionVO.get(i));
				dataSet.add(rankVO);
				mapVO.put("auction"+i, dataSet);*/
				//mapVO.put(i, dataSet);
				
				//log.info("rankVO : ~ "+mapVO.get(auctionVO.get(i).getId()));
			}
		}
		
		log.info("auctionVO : ~ "+auctionVO);
		return auctionVO;
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
