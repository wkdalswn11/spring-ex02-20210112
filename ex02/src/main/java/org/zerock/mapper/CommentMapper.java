package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CommentVO;

public interface CommentMapper {
	
	public List<CommentVO> getList();
	
	public void insert(CommentVO comment);
	
	public void insertSelectKey(CommentVO comment);
	
	public int update(CommentVO comment);
	
	public int delete(Long cno);
	
	public CommentVO read(Long cno); 
		
	
	
}
