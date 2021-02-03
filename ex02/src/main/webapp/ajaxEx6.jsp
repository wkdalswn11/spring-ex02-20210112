<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
<script>
$(document).ready(function() {
	$("#btn-1").click(function() { // 여기부터
		$.ajax("/replies/new", {
			type: "post",
			contentType: "application/json",
			data: '{"bno":218, "reply":"새 댓글입니다(ajax)", "replyer":"user96"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);	// controller에 "success"와는 관련없음
				console.log(jqXHR.responseText);
			}
				/* complete는 해야하는 일임. */
		});	// 여기 } 까지 setting
	});
	
	$("#btn-2").click(function() {
		$.ajax("/replies/new", {
			type: "post",
			contentType: "application/json",
			data: '{"reply":"새 댓글입니다(ajax)", "replyer":"user96"}', // 등록 실패를위해 bno값을 주지않음
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);	// controller에 "success"와는 관련없음
				//console.log(jqXHR.responseText);	// controller에 "success"와 관련이있음
			}
				/* complete는 해야하는 일임. */
		});
	});
	
	$("#btn-3").click(function() {
		$.ajax("/replies/new", {
			type: "post",
			contentType: "application/json",
			data: '{"bno":218,"reply":"새 댓글입니다(ajax)", "replyer":"user96"}' // 등록 실패를위해 bno값을 주지않음
		}).done(function(data, status, xhr) {	// done은 success와
			console.log("등록 성공");			// fail은 error와 같은일을함. 파라미터순서도 같음.
			//console.log(jqXHR.responseText); complite썻을때
			console.log(data);	// success 함수를 썻을때 위 주석처리한 것과 같은 것.
		}).fail(function() {
			console.log("등록 실패");
		});
	});
	
	$("#btn-4").click(function() {
		$.ajax("/replies/pages/218/1", {
			/* method: "get" type: "get" 둘다 같음*/
			type: "get"
		}).done(function(data, status, xhr) {
			// console.log(jqXHR.responseText);
			console.log(data);
			console.log(status);
			console.log("실행 성공");
	});
});
	$("#btn-5").click(function() {
		$.ajax("/replies/36", {
			type: "delete"
		}).done(function(data, status, xhr) { // 파라미터 data, status, xhr을 다쓸필요는 x 다만 순서는 다르면안됨
			console.log(data);				   // 순서는 data, status, xhr 의 순서가 맞아야함.
			console.log(status);
			console.log("삭제 성공")
		}).fail(function() {
				console.log(status);
				console.log("삭제 실패");
		});
	});
	$("#btn-6").click(function() {
		$.ajax("/replies/16", {
			type: "put",
			data: '{"reply":"수정한 댓글(ajax)"}',
			contentType: "application/json;charset=UTF-8" // 한글이깨질때 ;charset=UTF-8 추가
		}).done(function(data, status) {
			//console.log(jqXHR.responseText);
			console.log(data);
			console.log(status);
			console.log("수정 성공");
		}).fail(function(data, status, xhr) {
			console.log(status);
			console.log("수정 실패");
		});
	});
	
	$("#btn-7").click(function() {//get메소드일때 $.get을 사용할경우 $.ajax(url {type: "get"}) 이라고 한것과 같은 일을 함
		$.get("/replies/16")	
		.done(function(data) {
			console.log(data);
		});
	});
	
	$("#btn-8").click(function() {
		$.get("/replies/16", function(data) {	// 7번과 8번은 같은 역할을함.
			console.log(data);					// 7번의 .done(function(data){})를 8번에 $.get의 파라미터로 
		});										// 넣어준것임.
	});
	
	$("#btn-9").click(function() {
		$.get("/replies/16", function(data) {	// json 이라고 지정해서 data에 json형식으로 값이들어감
			console.log(data);	
			console.log(data.rno);
			console.log(data.bno);
			console.log(data.reply);
			console.log(data.replyer);
		}, "json");						// json형식이기때문에 rno, bno, reply, replyer 프로퍼티를 가져올수 O
	});
	
	$("#btn-10").click(function() {
		$.get("/replies/16", function(data) {	// text 이라고 지정해서 data에 text형식으로 값이들어감
			console.log(data);	
			console.log(data.rno);
			console.log(data.bno);
			console.log(data.reply);
			console.log(data.replyer);
		}, "text");						// text형식 즉 string이기 때문에 rno, bno, reply, replyer 프로퍼티를 가져올수 X				
	});
	
	$("#btn-11").click(function() {		//  11번과 9번은 같은 역할을함. 
		$.getJSON("/replies/16", function(data) {	//getJSON을 쓸경우 get메소드이면서 json형식이라는것
			console.log(data);	
			console.log(data.rno);
			console.log(data.bno);
			console.log(data.reply);
			console.log(data.replyer);
		});						
	});
});
	//	$.ajax([setting]) 만 쓸수있는게아니고 $.ajax(url, [setting]) 도가능 뒤에는 1.5버전부터.
	// complite 에서 status === "success" 일때와 success: function() 과 .done은 같은역할
	// complite 에서 status === "error" 일때와 error: function() 과 .fail은 같은역할
	// complite에서 파라미터 순서는 jqXHR, status
	// 순서는  jqXHR, status 의 순서가 맞아야함.
	// success error일때 파라미터 순서는 data status jqXHR
	// .done() .fail()일때 파라미터 순서 data status jqXHR
	// 파라미터 data, status, xhr을 다쓸필요는 x 다만 순서는 다르면안됨
	// 순서는 data, status, xhr 의 순서가 맞아야함.
	// complite , success , error는 ajax의 중괄호 안에
	// .done / .fail은 ajax의 중괄호 밖에
</script>
</head>
<body>
<h1>AJAX ex 6</h1>
<div>
<button id="btn-1">댓글 등록 성공</button>
</div>
<div>
<button id="btn-2">댓글 등록 실패</button>
</div>
<div>
<button id="btn-3">댓글 등록 성공 또는 실패</button>
</div>
<div>
<button id="btn-4">댓글 목록</button>
</div>
<div>
<button id="btn-5">댓글 삭제</button>
</div>
<div>
<button id="btn-6">댓글 수정</button>
</div>
<div>
<button id="btn-7">댓글 하나7</button>
</div>
<div>
<button id="btn-8">댓글 하나8</button>
</div>
<div>
<button id="btn-9">댓글 하나9</button>
</div>
<div>
<button id="btn-10">댓글 하나10</button>
</div>
<div>
<button id="btn-11">댓글 하나11</button>
</div>
</body>
</html>