<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
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

<title>게시글 읽기</title>
</head>
<body>
<u:navbar></u:navbar>
	
	<%-- <h1>${criteria.pageNum } : ${criteria.amount }</h1> controller에서 @ModelAttribute가 없을때 
	는 앞글자를 소문자로한 타입명을 따라옴
	--%> 
	<%-- <h1>${cri.pageNum } : ${cri.amount }</h1>  @ModelAttribute("cri")로 어노테이션을 사용했을때는 cri로 꺼내
													올수 있다. --%>
	
<div class="container-sm">
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
				<h1>게시물 보기</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
	
		
		<!-- 같은경로 라서 action="" 를 생략할수있음  -->
			<div class="form-group">
				<label for="input3">#번호</label>
				<input readonly value="${board.bno }" type= "text" class="form-control" id="input3"/>
			</div>
			<div class="form-group">
    			<label for="input1">제목</label>
    			<input readonly value='<c:out value= "${board.title }"/>'  type="text" class="form-control" id="input1" placeholder="제목을 입력하세요.">
  			</div>
  			
  			<div class="form-group">
    			<label for="textarea1">내용</label>
    			<textarea readonly class="form-control" id="textarea1" rows="3"><c:out value= "${board.content }"/></textarea>
  			</div>
  			
  			<div class="form-group">
    			<label for="input2">작성자</label>
    			<input readonly value="${board.writer }" type="text" class="form-control" id="input2">
  			</div> 			
			
				<!-- 
				"수정 -> 수정, 삭제
				책 : 254page 위쪽 코드
				수정 button 요소를 만들고
				button을 눌렀을 때 이동하는 js 작성  -->
				
				<!-- 
				우리 : 
				a요소로 만듬
				 -->
				 <c:url value="/board/modify" var="modifyLink">
				 	<c:param name="bno" value="${board.bno }" />
				 	<c:param name="pageNum" value="${cri.pageNum }"/>
				 	<c:param name="amount" value="${cri.amount }"/>
				 	<c:param name="type" value="${cri.type }"></c:param>
				 	<c:param name="keyword" value="${cri.keyword }"></c:param>
				 </c:url>
				 <a href="${modifyLink }" class="btn btn-secondary">수정</a>
		
			</div>
		</div>
	</div>

</body>
</html>