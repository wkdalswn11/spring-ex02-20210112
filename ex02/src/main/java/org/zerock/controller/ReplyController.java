package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
@AllArgsConstructor	// 필드에있는것으로 생성자를 만들어주는 어노테이션
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping(value = "/new",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("vo: " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("count: " + insertCount);
		
		if (insertCount == 1) {
			return new ResponseEntity<> ("success999", HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ReplyVO>> getList( // ResponseEntity 를 사용하는 이유는 상태코드나 해더를 조정하기 위해서
			@PathVariable("page")int page,
			@PathVariable("bno") Long bno) { 
		
		Criteria cri = new Criteria(page, 10);
		
		List<ReplyVO> list = service.getList(cri, bno);
		
		log.info(list);
		
		return new ResponseEntity<List<ReplyVO>> (list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		ReplyVO vo = service.get(rno);
		
		log.info(vo);
		
		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping(
			// method = RequestMethod.DELETE 어노테이션을 @requestMapping 으로쓸경우 위랑 경로가 겹치니 method = 로 정해준것.
			value = "/{rno}",	// method =  RequestMethod.DELETE 를 안쓸려면 어노테이션을  @DeleteMapping 이렇게해준다.
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		int removeCount = service.remove(rno);
		
		log.info(removeCount);
		
		if (removeCount == 1) {
			return new ResponseEntity<String> ("success999", HttpStatus.OK);
		} else {
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping (
			method = { RequestMethod.PUT, RequestMethod.PATCH },
			value = "/{rno}",
			consumes = MediaType.APPLICATION_JSON_VALUE , // 우리가 값을 넣어주는 mediatype
			produces = MediaType.TEXT_PLAIN_VALUE) // 응답하는 mediatype
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo , @PathVariable("rno") Long rno) {
		
		vo.setRno(rno);
		
		log.info("rno : " + rno);
			
		int modifyCount = service.modify(vo);
		
		log.info("modifyCount: " + modifyCount);
		
		if (modifyCount == 1) {
			return new ResponseEntity<String> ("success999", HttpStatus.OK);
		} else {
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
