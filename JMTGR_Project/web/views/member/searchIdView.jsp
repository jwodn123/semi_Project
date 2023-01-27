<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>


div#SearchPwdForm{
  width: 500px;
  height: 500px;
}

div#SearchPwdForm input[type=button]{
    text-align:center;
    position: relative;
    top: 250px;
    left: 850px;
    color: rgba(0,0,0,0.5);
    width: 100px;
    height: 30px;
    font-size: 15px;
    font-weight: bold;
    background:white;
    border: none;
}

div#SearchPwdForm input[type=button]:hover{
  text-decoration: none;
  color: black;
  position: relative;
  left : 850px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  border: none;

}
div#SearchPwdForm td{
position: relative;
  top: 150px;
  left: 800px;
  width: 500px
  size: 30px;
  font-size: 25px;
  heigtht: 300px;
  color: rgba(119, 32, 187, 0.5);
  font-weight: bold;
}

</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1 align="center">조회된 아이디</h1>

  <div id="SearchPwdForm">
<table id="SearchPwdForm">
<tr>
	<td><%= member.getUserName() %>님의 아이디는 <%= member.getUserId() %>입니다.</td>
</tr>
<br>
<input type="button" align="center" value="로그인하기" onclick="location.href='/jmtgr/views/member/loginPage.jsp'">
<input type="button" align="center" value="뒤로가기" onclick="history.go(-1);">
</table>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
