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
	$("#btn-1").click(function() {
		$.ajax({
			method: "POST",
			url: "/replies/new",
			data: '{"bno":218, "reply":"new reply(ajax)", "replyer":"user96"}',
			contentType: "application/json"
		});
	});
	$("#btn-2").click(function() {
		$.ajax({
			method: "GET",
			url: "/replies/pages/218/1",
		});
	});
	$("#btn-3").click(function() {
		$.ajax({
			method: "GET", /* method를 type으로 사용해도됨  */
			url: "/replies/26"
		});
	});
	$("#btn-4").click(function() {
		$.ajax({
			method: "DELETE",
			url: "/replies/28"
		});
	});
	$("#btn-5").click(function() {
		$.ajax({
			method: "PUT",
			url: "/replies/26",
			data: '{"reply":"ajax 수정테스트"}',
			contentType: "application/json"
		});
	});
});
</script>
</head>
<body>
<h1>AJAX ex1</h1>
<div>
<button id="btn-1">새 댓글</button>
</div>
<div>
<button id="btn-2">댓글 목록</button>
</div>
<div>
<button id="btn-3">댓글 보기</button>
</div>
<div>
<button id="btn-4">댓글 삭제</button>
</div>
<div>
<button id="btn-5">댓글 수정</button>
</div>
</body>
</html>