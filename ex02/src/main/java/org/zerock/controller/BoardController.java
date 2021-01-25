package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardService service;
	
	
	/*
	public BoardController(BoardService service) { @AllArgsConstructor로 처리할수있음.
		super();
		this.service = service;
	}
	*/
	/*
//	@RequestMapping(value = "/list", method = RequestMethod.GET )
	@GetMapping("/list") // 주석처리한 윗줄과 같은역할
	public void list(Model model) { 
		// handler 메소드의 return type 이 void인 경우
		// 		요청결로가 view(jsp)의 이름이 됨.
		//		이 메소드는 (/board/list) 에서 일함 - > /board/list.jsp 가 일하게됨.
	    log.info("********************************** list **********************************");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
	}
	*/
	
	@GetMapping("/list")
	public void list(@ModelAttribute("cri") Criteria cri, Model model) {
		List<BoardVO> list = service.getList(cri);
		
		int total = service.getTotal(cri);
		
		PageDTO dto = new PageDTO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
		
	}
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {
		// 아무일도 안하는것같지만 스프링이 안에서 일을해줌. (파라미터에따라)
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) { // RedirectAttributes rttr 한번 사용하고 버림.
		
		/* 
		파라미터에 명시해 주는 것만으로도 주석처리한 일을 spring 이 알아서 처리해줌.
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		*/
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());	// 객체로 붙게됨.
		rttr.addFlashAttribute("message", board.getBno() + "번 글이 등록되었습니다.");
		
		
			
		// rttr.addAttribute(attributeValue) 쿼리스트링으로 붙음
		//return "board/list"; forward 는 하나의 request에서 완료가 됨
		return "redirect:/board/list"; // reidrect는 두개의 request에서 완료가됨.
	}
	// 표: /board/read(x) , 코드: /board/get (이걸로하기)
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno,
			@ModelAttribute("cri") Criteria cri, Model model) { // 파라미터에 Model 을 지정해주면 디스패처서블릿이 model를 쓸수있게 해줌
		/** 예전 코드 ( 스프링 사용 전 )
		String boardNum = request.getParamter("num"); 스프링이 알아서 받아와줌. (파라미터값과 일치한다면)
		int num = Integer.parseInt(boardNum);
		
		BoardVO vo = service.get((long) num );
		
		request.setAttribute("board", vo);
		
		request.getRequestDispatcher(".jsp").forward();
		*/
		
		log.info("get method - bno: " + bno);
		log.info(cri);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
//		model.addAttribute("cri", cri);
	}
	/*
	@GetMapping("/modify")
	public void modify(Long bno, Model model) {
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	*/
//	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@PostMapping("/modify")
	public String modify(BoardVO board,Criteria cri ,RedirectAttributes rttr) {
		/** 스프링 사용전.
		BoardVO board = new BoardVO();
		board.setBno(request.getParameter("bno"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParamter("content"));
		*/
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success"); // 책에서 modal 창이있는데 그걸 위함이다.
			rttr.addFlashAttribute("message", board.getBno() + "번 글이 수정되었습니다.");
		}
		log.info(cri);
		rttr.addAttribute("pageNum", cri.getPageNum()); // request가 아니라 redirect해줬기때문에
		rttr.addAttribute("amount", cri.getAmount());	// RedirectAttributes에 넣어줘야함
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	
//	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,Criteria cri ,RedirectAttributes rttr) { 
		// @RequestParam("bno") 은 파라미터값과 이름이 같다면 생략가능.
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result" , "success");
			rttr.addFlashAttribute("message", bno + "번 글이 삭제되었습니다.");
		}
		rttr.addAttribute("pageNum", cri.getPageNum()); // request가 아니라 redirect해줬기때문에
		rttr.addAttribute("amount", cri.getAmount());	// RedirectAttributes에 넣어줘야함
		rttr.addAttribute("type", cri.getType());		// RedirectAttributes.addAttribute를 하면 
		rttr.addAttribute("keyword", cri.getKeyword());	// redirect할때 쿼리스트링으로 붙여줌.
		
		return "redirect:/board/list";
		
	}
	
}

// servlet/jsp
// ControllerUsingURI(Servlet) .... properties를 읽어서 일함
// ex ) /list.do -> ListHandler