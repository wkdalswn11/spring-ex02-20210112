package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/rest1")
@Log4j
public class RestControllerEx1 {
	
	@RequestMapping("/ex1")
	@ResponseBody			// @ResponseBody 이걸 사용할경우 return 해주는 값이 view의 이름이아닌 data 그자체를 리턴
	public String method1() {
		log.info("method1");
		return "hello";
	}
}
