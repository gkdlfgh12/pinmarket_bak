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
	
	@Override
	public int freeTotal(String title) {
		return mapper.freeTotal(title);
	}
	
	@Override
	public List<BoardVO> freeList(PageCreator pc, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pc", pc);
		map.put("title", title);
		return mapper.freeList(map);
	}

	@Override
	public BoardVO freeView(String id) {
		return mapper.freeView(id);
	}

	@Override
	public int selectFreeDel(int[] delChk) {
		return mapper.selectFreeDel(delChk);
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
	public List<BoardVO> bestList() {
		return mapper.bestList();
	}

	@Override
	public int writeBestFaq(BoardVO boardVO) {
		return mapper.writeBestFaq(boardVO);
	}

	@Override
	public int bestDel(Integer id) {
		return mapper.bestDel(id);
	}

	@Override
	public int updateBestFaq(BoardVO boardVO) {
		return mapper.updateBestFaq(boardVO);
	}
}
