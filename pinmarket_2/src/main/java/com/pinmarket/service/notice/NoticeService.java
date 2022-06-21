package com.pinmarket.service.notice;

import java.util.List;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

public interface NoticeService {
	//자유 질문 게시판 총 개수 겟
	int getFreeFaqTotal();

	//자유 질문 게시판 리스트 추출
	List<BoardVO> getFreeFaqList(PageCreator pc);

	//자유 질문 입력
	int wrtieFreeFaq(BoardVO boardVO);

	//faq 자세히 보기
	BoardVO getFaq(String id);

	//faq 수정
	int modifyFaq(BoardVO boardVO);
	
	//faq 삭제
	int deleteFaq(BoardVO boardVO);
	
	/////////////////////////////////////답변
	
	//faq 답변 리스트 get
	List<ReplyVO> getFreeReplyList(SearchVO searchVO);

	//faq에 답변 달기
	int freeReplyWrite(ReplyVO replyVO);

	//faq 답변 삭제
	int freeReplyDel(Integer reply_id);

	//best faq 리스트 겟
	List<BoardVO> getBestFaqList();



}
