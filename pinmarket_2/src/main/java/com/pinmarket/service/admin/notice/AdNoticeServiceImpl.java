package com.pinmarket.service.admin.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.admin.notice.AdNoticeMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

@Service
public class AdNoticeServiceImpl implements AdNoticeService{

	@Autowired
	AdNoticeMapper mapper;
	
	//자유 질문 게시글 개수
	@Override
	public int freeTotal(String title) {
		return mapper.freeTotal(title);
	}
	
	//자유 질문 게시판
	@Override
	public List<BoardVO> freeList(PageCreator pc, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("title", title);
		return mapper.freeList(map);
	}

	//자유질문 게시판 자세히 보기
	@Override
	public BoardVO freeView(String id) {
		return mapper.freeView(id);
	}

	//자유질문 게시판 선택 삭제
	@Override
	public int selectFreeDel(int[] delChk) {
		return mapper.selectFreeDel(delChk);
	}

	//답글 출력
	@Override
	public List<ReplyVO> getFreeReplyList(SearchVO searchVO) {
		return mapper.getFreeReplyList(searchVO);
	}

	//답글 작성
	@Override
	public int freeReplyWrite(ReplyVO replyVO) {
		return mapper.freeReplyWrite(replyVO);
	}

	//답글 삭제
	@Override
	public int freeReplyDel(Integer reply_id) {
		return mapper.freeReplyDel(reply_id);
	}

	//자주묻는 질문 리스트
	@Override
	public List<BoardVO> bestList() {
		return mapper.bestList();
	}

	//자주묻는 질문 작성
	@Override
	public int writeBestFaq(BoardVO boardVO) {
		return mapper.writeBestFaq(boardVO);
	}

	//자주묻는 질문 삭제
	@Override
	public int bestDel(Integer id) {
		return mapper.bestDel(id);
	}

	//자주묻는 질문 수정
	@Override
	public int updateBestFaq(BoardVO boardVO) {
		return mapper.updateBestFaq(boardVO);
	}
}
