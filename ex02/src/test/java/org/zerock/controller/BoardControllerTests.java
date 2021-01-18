package org.zerock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration // dispatcherservlet이 일하게함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; // 서버를 실행시키지 않고도 dispatcherservlet에게 요청을 날릴 수 있게함
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		// 서버를 실행시키지않고 요청을 날릴수있는 가짜 mockMvc를 만드는 것.
		// 다른 test 되는 메소드보다 가장 먼저 실행되어야 하므로 @Before 사용.
	}
	
	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mockMvc);
	}
	
	@Test
	public void testList() throws Exception {
//		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"));
//		// perform메소드는 dispatcherservlet 요청을 보내는 메소드.
//		MvcResult rs = result.andReturn();
//		ModelAndView mv = rs.getModelAndView();
//		log.info(mv.getView());
//		log.info(mv.getModel().get("list"));
		
		Object o = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
		.andReturn()
		.getModelAndView()
		.getModel()
		.get("list");
		
		assertNotNull(o);
		assertTrue(o instanceof List);
		assertNotEquals(((List) o).size(), 0);
	}
	
	@Test
	public void testRegister() throws Exception {
		int before = mapper.getList().size();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register") // perform 으로 요청을 보내고
				.param("title", "테스트 새글 제목")					   			 // .param 메소드로 컬럼에 값을 넣어서 
				.param("content", "테스트 새글 내용")				   			 // 테스트 해볼수 있음.
				.param("writer", "user01"))							   			 // .param("파라미터명", "넣어볼 내용")
		.andReturn();						  
		
		ModelAndView mv = result.getModelAndView();
		FlashMap map = result.getFlashMap();
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
		assertEquals("redirect:/board/list", mv.getViewName());
		assertNotNull(map.get("result"));
		// BoardController 에서 result 라는 이름으로  board.getBno() 를 담았으니
		// map.get("result") 는 board.getBno() 를 가리킴.
		log.info(map.get("result") + "*********************************************");
	}
	@Test
	public void testGet() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/get") 
				.param("bno", "1"))					   			  
		.andReturn();
		
		String viewName = result.getModelAndView().getViewName();
		Map<String, Object> modelMap = result.getModelAndView().getModelMap();
		
		assertEquals("board/get", viewName);
		assertNotNull(modelMap.get("board"));
		assertEquals(new Long(1), ((BoardVO) modelMap.get("board")).getBno());
	}
	
	@Test
	public void testModify() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새 제목");
		board.setContent("새 게시물");
		board.setWriter("user26");
		
		mapper.insertSelectKey(board);
		
		Long key = board.getBno();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify") 
				.param("bno", key + "")
				.param("title", "수정된 게시물 111")
				.param("content", "수정된 내용 111")
				.param("writer", "user26"))
		.andReturn();
		
		FlashMap map = result.getFlashMap();
		String viewName = result.getModelAndView().getViewName();
		BoardVO mod = mapper.read(key);
		
		assertEquals("수정된 게시물 111", mod.getTitle());
		assertEquals("수정된 내용 111", mod.getContent());
		assertEquals("success", map.get("result")); // 성공적으로 service.modify 가 실행됐어야 값이들어오는것.
		assertEquals("redirect:/board/list", viewName);
	}
	
	@Test
	public void testRemove() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새 제목");
		board.setContent("새 게시물");
		board.setWriter("user26");
		
		mapper.insertSelectKey(board);
		
		int before = mapper.getList().size();
		
		Long key = board.getBno();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove") 
				.param("bno", key + ""))
		.andReturn();
		
		int after = mapper.getList().size();
		
		assertEquals(before - 1, after);
		
		String viewName = result.getModelAndView().getViewName();
		
		assertEquals("redirect:/board/list", viewName);
		
		assertEquals("success" , result.getFlashMap().get("result"));
	}
}














