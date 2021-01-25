package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Component
@Service // @Service 에 @Component가 포함되어있음 그러므로 component scan 에 의해 bean이 됨.
@AllArgsConstructor // 필드를 갖는 생성자를 만들어줌.
@Log4j
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	
	/*
	//@AutoWired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	*/
	// mapper.read(33);
	
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}
	
	/*
	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
	}
	*/
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(Long bno) {
		
		return mapper.read(bno);
	}
	
	@Override
	public boolean modify(BoardVO board) {
		
		return mapper.update(board) == 1;
	}
	
	@Override
	public boolean remove(Long bno) {
		
		return mapper.delete(bno) == 1; // 1 은 지워지는 행의 갯수임
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
}
