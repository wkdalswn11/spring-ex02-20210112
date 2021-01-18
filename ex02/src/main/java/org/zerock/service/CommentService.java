package org.zerock.service;

import java.util.List;

import org.zerock.domain.CommentVO;

public interface CommentService {
	
	public void register(CommentVO comment);
	
	public List<CommentVO> getList();
	
	public boolean update(CommentVO comment);
	
	public boolean delete(Long cno);
	
	public CommentVO get(Long cno);
}
