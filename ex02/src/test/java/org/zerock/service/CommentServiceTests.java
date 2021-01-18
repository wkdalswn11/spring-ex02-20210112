package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CommentVO;
import org.zerock.mapper.CommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentServiceTests {
	
	// @Setter(onMethod_ = {@Autowired, @Qualifier("commentServiceImpl"))
	private CommentService service;
	
	@Autowired
	@Qualifier("commentServiceImpl")  // CommentService에 관련된 bean이 여러개일때 어떤 bean을 사용할지 명시하는 방법.
	// @Qualifier("commentServiceImple2") commentServiceImple2를 이용하고싶으면 위를 지우고 이렇게써주면됨.
	public void setService(CommentService service) {
		this.service = service;
	}
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;

	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("새로 작성하는 내용");
		comment.setWriter("newbie");
		
		int before = mapper.getList().size();
		
		service.register(comment);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testGetList() {
		List<CommentVO> list = service.getList();
		
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testGet() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("새로 작성하는 내용");
		comment.setWriter("newbie");
		
		service.register(comment);
		
		CommentVO vo = service.get(comment.getCno());
		
		assertNotNull(vo);
		assertEquals(vo.getCno(), comment.getCno());
	}
	
	@Test
	public void testDelete() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("새로 작성하는 내용");
		comment.setWriter("newbie");
		
		service.register(comment);
		
		int before = service.getList().size();
		
		assertTrue(service.delete(comment.getCno()));
		
		int after = service.getList().size();
		
		assertEquals(before - 1 , after);
	}
	
	@Test
	public void testUpdate() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("새로 작성하는 내용");
		comment.setWriter("newbie");
		
		service.register(comment);
		
		CommentVO up = new CommentVO();
		up.setCno(comment.getCno());
		up.setContent("수정된 내용");
		
//		board.setTitle("수정된 제목");		up을 사용안하고 바로 board에 수정하는 내용을 
//		board.setContent("수정된 내용");	넣어도 됨
//		board.setWriter("장민주");
		assertTrue(service.update(up));
		
		CommentVO up2 = service.get(comment.getCno());
		
		
		assertEquals("수정된 내용", up2.getContent());
//		assertEquals("수정된 제목", board.getTitle());	주석 된것처럼 했을때.
//		assertEquals("수정된 내용", board.getContent());
		
	}
	
}
