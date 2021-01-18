package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CommentVO;

@Service
public class CommentServiceImple2 implements CommentService {

	@Override
	public void register(CommentVO comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommentVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(CommentVO comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long cno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentVO get(Long cno) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
