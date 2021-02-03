package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;
import org.zerock.domain.Rest2;

import lombok.extern.log4j.Log4j;

@RestController	// @RestController = @Controller + @ResponseBody 인 기능을 함	view로 포워딩 되는것이 X
@RequestMapping("/rest2")	// data를 그 자체로 보여줌
@Log4j
public class RestControllerEx2 {
	
	@RequestMapping("/ex1")
	public String method1() {
		return "hello";
	}
	
	@RequestMapping("/ex2")
	public Rest1 method2() {
		
		//	전송, 수신 방법이 http(HyperText Transper Protocol)
		log.info("method2");
		
		Rest1 rest1 = new Rest1();
		rest1.setName("장민주");
		rest1.setAge(26);
		
		
		return rest1;
	}
	
	@RequestMapping("/ex3")
	public String method3() {
		
		//	전송, 수신 방법이 http(HyperText Transper Protocol)
		log.info("method3");
		
		Rest1 rest1 = new Rest1();
		rest1.setName("MinJu");
		rest1.setAge(26);
		
		// method2처럼하면 오류가뜸 / return을 string으로 풀어서 보내줘야 결과가 나옴
	//	String res = "이름: " + rest1.getName() + ", " + "나이: " + rest1.getAge();
		String res = "{\"name\":\"" + rest1.getName() + "\", \"age\":" + rest1.getAge() + "}";
		// {"name":"MinJu", "age":26} 이렇게  이름에는 "" , string도 "", int는  "" 없이 표시
		/*
		a string						""
		a number						숫자만 넘어옴
		an object (JSON object)			{}
		an array						[]
		a boolean						true / false
		null							null
		*/		
		return res;
	}
	
	@RequestMapping("/ex4")
	public Rest1 method4() {
		
		//	전송, 수신 방법이 http(HyperText Transper Protocol)
		log.info("method4");
		
		Rest1 rest1 = new Rest1();
		rest1.setName("MinJu");
		rest1.setAge(26);
		
		return rest1;
	}
	
	@RequestMapping("/ex5")
	public Rest2 method5() {
		Rest2 r2 = new Rest2();
		r2.setAddress("seoul");
		
		Rest1 r1 = new Rest1();
		r1.setName("jeju");
		r1.setAge(200);
		r1.setVote(true);
		
		r2.setRest1(r1);
		
		return r2;
	}
	
	@RequestMapping("/ex6")
	public String[] method6() {
		String[] arr = {"java", "json", "xml"};
		
		return arr;
	}
	
	@RequestMapping("/ex7")
	public List<String> method7() {
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		list.add("spring");
		
		return list;
	}
	
	@RequestMapping("/ex8")
	public Map<String, String> method8() {
		Map<String, String> map = new HashMap<>();
		map.put("A", "AA");
		map.put("B", "BB");
		map.put("C", "CC");
		
		return map;
	}
	
	@RequestMapping("/ex9")
	public List<Rest1> method9() {
		List<Rest1> list = new ArrayList<>();
		
		Rest1 r1 = new Rest1();
		r1.setName("donald");
		r1.setAge(73);
		r1.setVote(true);
		
		list.add(r1);
		
		Rest1 r2 = new Rest1();
		r2.setName("trump");
		r2.setAge(55);
		r2.setVote(false);
		
		list.add(r2);
		
		return list;
	}
	
	//	특정 status code로 응답할 때
	@RequestMapping("/ex10")
	public ResponseEntity<String> method10() {
		// ResponseEntity<T> 의 리턴 타입은 ResponseEntity<>의 제네릭타입이다. 
		// 즉 여기서는 String 타입으로 리턴함
		// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
		
		return ResponseEntity.status(404).body("hello");
	}
	
	@RequestMapping("/ex11")
	public ResponseEntity<String> method11(int num) {
		
		if (num > 0) {
			return ResponseEntity.status(200).body("spring");
		} else {
			return ResponseEntity.status(404).body("");
		}
	}
}



















