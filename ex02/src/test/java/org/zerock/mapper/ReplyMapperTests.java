package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {214L, 215L, 216L, 217L, 218L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 214L);
		
		assertNotEquals(0, list.size());
	}
	
	@Test // 책 383쪽
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			log.info(i + "," + (i % 5));
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer " + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testCreate2() {
		ReplyVO vo = new ReplyVO();
		
//		vo.setRno(1L); 시퀀스로 만들어서 자동으로 만들어짐 넣을필요 X
		vo.setBno(218L);
		vo.setReply("댓글 테스트");
		vo.setReplyer("newbie");
		
		mapper.insert(vo);
		
		try {
			vo.setBno(213L); // tb1.board 테이블에 없는 값
			
			mapper.insert(vo);
			fail();
		} catch (Exception e) {
			
		}
		
	}
	
	@Test // 책 385쪽
	public void testRead() {
		
		Long rno = 6L;
		
		ReplyVO vo = mapper.read(rno);
		
		log.info(vo);
	}
	
	@Test 
	public void testDelete() {
		Long rno = 1L;
		
		ReplyVO vo = mapper.read(rno);
		
		mapper.delete(rno);
		
		
		
		try {
			vo = mapper.read(rno);
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void testUpdate() {
		/*
		 * Long rno = 6L;
		 * 
		 * ReplyVO vo = mapper.read(rno);
		 * 
		 * vo.setReply("댓글 수정 테스트");
		 * 
		 * int count = mapper.update(vo);
		 * 
		 * log.info("update count: " + count );
		 */
		
		ReplyVO vo = new ReplyVO();
		vo.setRno(6L);
		vo.setReply("댓글 수정 테스트");
		mapper.update(vo);
		
		vo = mapper.read(6L);
		
		assertEquals("댓글 수정 테스트", vo.getReply());
	}
	
}



































