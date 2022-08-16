package com.pinmarket.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinmarket.mapper.notice.NoticeMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper mapper;
	
	//자유 질문 게시판 총 개수 겟
	@Override
	public int getFreeFaqTotal() {
		return mapper.getFreeFaqTotal();
	}

	//자유 질문 게시판 리스트 추출
	@Override
	public List<BoardVO> getFreeFaqList(PageCreator pc) {
		return mapper.getFreeFaqList(pc);
	}

	//자유 질문 입력
	@Override
	public int wrtieFreeFaq(BoardVO boardVO) {
		return mapper.wrtieFreeFaq(boardVO);
	}

	//faq 자세히 보기
	@Transactional
	@Override
	public BoardVO getFaq(String id) {
		mapper.updateHit(id);
		return mapper.getFaq(id);
	}

	//faq 수정
	@Override
	public int modifyFaq(BoardVO boardVO) {
		return mapper.modifyFaq(boardVO);
	}

	//faq 삭제
	@Override
	public int deleteFaq(BoardVO boardVO) {
		return mapper.deleteFaq(boardVO);
	}

	//faq 답변 리스트 get
	@Override
	public List<ReplyVO> getFreeReplyList(SearchVO searchVO) {
		return mapper.getFreeReplyList(searchVO);
	}
	
	//faq에 답변 달기
	@Override
	public int freeReplyWrite(ReplyVO replyVO) {
		return mapper.freeReplyWrite(replyVO);
	}

	//faq 답변 삭제
	@Override
	public int freeReplyDel(Integer reply_id) {
		return mapper.freeReplyDel(reply_id);
	}

	//best faq 리스트 겟
	@Override
	public List<BoardVO> getBestFaqList() {
		return mapper.getBestFaqList();
	}

}
