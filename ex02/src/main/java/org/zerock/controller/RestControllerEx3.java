package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest3")
@Log4j
public class RestControllerEx3 {
	
	@RequestMapping("/ex1")
	public String method1(String name) {
		log.info("name: " + name);
		
		return "amugerna";
	}
	
	@RequestMapping("/ex2/{val}") // path variable
	public String method2(@PathVariable("val") String value) { //  @PathVariable 를 써서 경로에있는 /{val} 을 받아올수있음
		log.info("method2");
		log.info(value);
		
		return "method2";
	}
	
	@RequestMapping("/ex3/{val}")
	public String method3(@PathVariable("val") String val) {
		log.info("method3");
		
		return val;
	}
	
	@RequestMapping("/ex4/{val}/other/{age}")
	public String method4(@PathVariable String val, @PathVariable int age) {
		
		return val + age;
	}
	
	
	
	@RequestMapping("/ex5")
	public String method5(@RequestBody String b) {
		log.info(b);
		
		return "method5";
		
	}
	
	@RequestMapping("/ex6")
	public String method6(@RequestBody Rest1 body) {
		log.info(body);
		
		
		return "method6";
	}
	
	@RequestMapping(value = "/ex7", consumes = "text/plain")	// * consumes = "text/plain" 는 request Header와
	public String method7(@RequestBody String body) {			// 관련이 있음 *
		log.info(body);											// request Header (Content-Type)과 연관 있다는 말
		
		return "mehtod7";
	}
	
	@RequestMapping(value = "/ex8", consumes = MediaType.APPLICATION_JSON_VALUE) // 오타가 날수있으니 이렇게 쓰는 것이 안전. 
	public String method8(@RequestBody String body) {			  
		log.info(body);
		
		return "mehtod8";
	}
	
	@RequestMapping(value = "/ex9", consumes = { MediaType.APPLICATION_JSON_VALUE,
												 MediaType.TEXT_PLAIN_VALUE }) // 오타가 날수있으니 이렇게 쓰는 것이 안전. 
	public String method99(@RequestBody String body) {			  
		log.info(body);
		
		return "mehtod9";
	}
}
