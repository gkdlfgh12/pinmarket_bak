package com.pinmarket.service.mypage;

import java.util.ArrayList;
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
				log.info("결과 값 0- 1 "+compRankVO);
				
				//경매가 성사된 것 과 안된것을 분기처리하여 저장
				if(compRankVO == null) {
					log.info("결과 값 0 "+auctionVO.get(i));
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", 0);
					rankVO = mapper.getAuctionRankList(map);
				}else {
					log.info("결과 값 1 이상, 경매가 완료가 된 옥션  : "+compRankVO.getId());
					log.info("결과 값 1 이상, 경매가 완료가 된 옥션  : "+auctionVO.get(i).getId());
					map.put("id", auctionVO.get(i).getId());
					map.put("rankId", compRankVO.getId());
					rankVO = mapper.getAuctionRankList(map);
					log.info("rankVOrankVOrankVO : :: "+rankVO);
				}
				map.clear();
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

	@Override
	public int getMyAutionTotal(int member_id) {
		return mapper.getMyAutionTotal(member_id);
	}

	@Override
	public int getMyRankTotal(int member_id) {
		return mapper.getMyRankTotal(member_id);
	}

	@Override
	public List<RankingVO> getMyRankList(int member_id, PageCreator pc) {
		
		//자 여기 보세요!!~  일단 랭크 리스트 가져와서 해당 랭크 리스트들의 옥션 아이디를 하나하나 비교해서 가져온 후 랭크vo에 옥션 객체 자체를 저장
		
		//랭크 리스트 가져오기
		HashMap<String, Object> mapVO = new HashMap<String, Object>();
		mapVO.put("member_id", member_id);
		mapVO.put("pc", pc);
		log.info("rankVO : rankVO ~~ rankVO : rankVO ~~  ");
		List<RankingVO> rankVO = mapper.getMyRankList(mapVO);
		
		if(rankVO != null) {
			for(Integer i=0;i<rankVO.size();i++) {
				
				AuctionVO tmpAuctionVO = mapper.getAuctionInfo(rankVO.get(i).getAuction_id());
				
				//log.info("tmpAuctionVO : ~ tmpAuctionVO : "+tmpAuctionVO);
				
				int result = mapper.checkAucRankStatus(rankVO.get(i));
				log.info("result : `  ~~ "+rankVO.get(i));
				log.info("result : `  ~~ "+result);
				rankVO.get(i).setAuctionVO(tmpAuctionVO);
				rankVO.get(i).setAucResult(result);
				log.info("rankVO : rankVOrankVOrankVO : ~~ "+rankVO.get(i));
				
			}
		}
		
		return rankVO;
	}



}
