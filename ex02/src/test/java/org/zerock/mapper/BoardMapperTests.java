package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		
	//	assertEquals(list.size(), 5);
		assertNotEquals(list.size(), 0);
		log.info(list.size());
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 제목");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		int before = mapper.getList().size();
		
		mapper.insert(board);
		
		int after = mapper.getList().size();
		
		log.info(board);
		
		assertEquals(before + 1 , after);
		
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 제목");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(board);
		
		int after = mapper.getList().size();
		
		log.info(board);
		
		assertEquals(before + 1 , after);
		assertNotEquals(board.getBno(), new Long(0L));
	}
	@Test
	public void testRead() {
		// 존재하는 게시물 번호로 테스트
		
		BoardVO board = mapper.read(13L);
		
		log.info(board);
	}
	@Test
	public void testDelete() {
//		BoardVO board = new BoardVO();
//		mapper.delete(20L);
		
		log.info("DELETE COUNT: " + mapper.delete(11L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		// 실행전 존재하는 번호인지 확인할 것.
		board.setBno(14L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("장민주");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
		log.info(board);
	}
	
}








