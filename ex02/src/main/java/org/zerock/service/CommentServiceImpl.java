package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CommentVO;
import org.zerock.mapper.CommentMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class CommentServiceImpl implements CommentService {
	
	private CommentMapper mapper;
	
	@Override
	public void register(CommentVO comment) {
		mapper.insert(comment);
	}
	
	@Override
	public List<CommentVO> getList() {
		return mapper.getList();
	}
	
	@Override
	public boolean update(CommentVO comment) {
		return mapper.update(comment) == 1;
	}
	
	@Override
	public boolean delete(Long cno) {
		return mapper.delete(cno) == 1;
	}
	
	@Override
	public CommentVO read(Long cno) {
		return mapper.read(cno);
	}
}
