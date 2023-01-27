<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<script>
window.onload = function(){
	confirm('오류페이지 입니다. 이전페이지로 돌아가시겠습니까?')	
};
function goback(window.onload){
	if(window.onload === true) {
		location.href="/jmtgr/index.jsp";
	}
};

</script>
</head>
<body>
<h1 align="center">에러페이지</h1>
<h3> 발생 : <%= request.getAttribute("message") %></h3>
<h3><button onclick="javascript:history.go(-1)">뒤로 가기</button></h3>
</body>
</html>