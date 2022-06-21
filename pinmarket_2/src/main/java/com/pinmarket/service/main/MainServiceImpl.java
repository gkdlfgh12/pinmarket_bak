package com.pinmarket.service.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinmarket.mapper.main.MainMapper;
import com.pinmarket.vo.AuctionVO;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	MainMapper mapper;
	
	@Override
	public List<AuctionVO> getTopAuction(int startRow, int endRow) {
		Map<String, Integer> row = new HashMap<String, Integer>();
		row.put("startRow", startRow);
		row.put("endRow", endRow);
		return mapper.getTopAuction(row);
	}

}
