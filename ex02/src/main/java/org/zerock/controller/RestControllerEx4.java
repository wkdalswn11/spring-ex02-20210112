package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest4")
@Log4j
public class RestControllerEx4 {
	
	@RequestMapping(value = "/ex1", produces = MediaType.TEXT_PLAIN_VALUE )
	public String method1() {
		log.info("method1");
		
		return "hello world";
	}
	
	@RequestMapping(value = "/ex2", produces = MediaType.APPLICATION_JSON_VALUE )
	public String method2() {
		log.info("method2");
		
		return "{}";
	}
	
	@RequestMapping(value = "/ex3", produces = { MediaType.APPLICATION_XML_VALUE,
												 MediaType.APPLICATION_JSON_VALUE })
	public Rest1 method3() {
		log.info("method3");
		
		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(77);
		r.setVote(true);
		r.setObj(null);
		
		return r;
	}
	
	@RequestMapping(value="/ex4", produces = MediaType.TEXT_PLAIN_VALUE )
	public String method4() {	// produces 는 Accept와 관련이있다
		log.info("method4");
		
		return "hello world";
	}
	
	@RequestMapping(value="/ex5", produces = "text/plain;charset=UTF-8" ) // 한글이 깨질때는 ;charset=UTF-8 을 붙여준다.
																		  // text/plain;charset=UTF-8 이렇게써줘야함.
														// MediaType.TEXT_PLAIN_VALUE 이렇게 쓰면 ;charset=UTF-8 붙일수 x
	public String method5() {	// produces 는 Accept와 관련이있다 * 사진 참고 *
		log.info("method5");
		
		return "헬로우 월드";
	}
}










