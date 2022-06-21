package com.pinmarket.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.notice.NoticeMapper;
import com.pinmarket.util.PageCreator;
import com.pinmarket.vo.BoardVO;
import com.pinmarket.vo.PageVO;
import com.pinmarket.vo.ReplyVO;
import com.pinmarket.vo.SearchVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper mapper;
	
	@Override
	public int getFreeFaqTotal() {
		return mapper.getFreeFaqTotal();
	}

	@Override
	public List<BoardVO> getFreeFaqList(PageCreator pc) {
		return mapper.getFreeFaqList(pc);
	}

	@Override
	public int wrtieFreeFaq(BoardVO boardVO) {
		return mapper.wrtieFreeFaq(boardVO);
	}

	@Override
	public BoardVO getFaq(String id) {
		return mapper.getFaq(id);
	}

	@Override
	public int modifyFaq(BoardVO boardVO) {
		return mapper.modifyFaq(boardVO);
	}

	@Override
	public int deleteFaq(BoardVO boardVO) {
		return mapper.deleteFaq(boardVO);
	}

	@Override
	public List<ReplyVO> getFreeReplyList(SearchVO searchVO) {
		return mapper.getFreeReplyList(searchVO);
	}
	
	@Override
	public int freeReplyWrite(ReplyVO replyVO) {
		return mapper.freeReplyWrite(replyVO);
	}

	@Override
	public int freeReplyDel(Integer reply_id) {
		return mapper.freeReplyDel(reply_id);
	}

	@Override
	public List<BoardVO> getBestFaqList() {
		return mapper.getBestFaqList();
	}

}
