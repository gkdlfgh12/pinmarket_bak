package com.pinmarket.mapper.admin.notice;

import java.util.List;

import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

public interface AdNoticeMapper {
	
	//자유 질문 게시글 개수
	public int freeTotal();
	
	//자유 질문 게시판
	public List<BoardVO> freeList(PageCreator pc);
	
	//자유질문 게시판 자세히 보기
	public BoardVO freeView(String id);
	
	//자유질문 게시판 선택 삭제
	public int selectFreeDel(int[] delChk);
	
	//답글 출력
	public List<ReplyVO> getFreeReplyList(SearchVO searchVO);
	
	//답글 작성
	public int freeReplyWrite(ReplyVO replyVO);
	
	//답글 삭제
	public int freeReplyDel(Integer reply_id);
	
	//베스트 리스트
	public List<BoardVO> bestList();
	
	//자주묻는 질문 작성
	public int writeBestFaq(BoardVO boardVO);
	
	//자주묻는 질문 삭제
	public int bestDel(Integer id);
	
	//자주묻는 질문 수정
	public int updateBestFaq(BoardVO boardVO);
}
