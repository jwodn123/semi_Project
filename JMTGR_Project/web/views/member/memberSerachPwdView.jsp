	<%@page import="member.controller.MemberSearchPwdServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	String userid = (String)request.getAttribute("userid");
	String password = (String)request.getAttribute("password");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>


div#passwordsave{
  width: 500px;
	height: 500px
}

div#passwordsave button{
    text-align:center;
    position: relative;
    top: 250px;
    left: 850px;
    color: rgba(0,0,0,0.5);
    width: 200px;
    height: 30px;
    font-size: 15px;
    font-weight: bold;
    background:white;
    border: none;
}

div#passwordsave button:hover{
  text-decoration: none;
  color: black;
  position: relative;
  left : 850px;
  width: 200px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  border: none;

}
div#passwordsave td{
position: relative;
  top: 150px;
  left: 750px;
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
<h1 align="center">임시 비밀번호</h1>

	<div id="passwordsave">
<table id="passwordsave">
<tr>
	<td>회원님의 임시비밀번호는 <%= password %>입니다</td>
</tr>
<br>
<button onclick="javascript:location.href='/jmtgr/passwordsave.cp?userid=<%= userid %>&userpwd=<%= password %>'">임시비밀번호 저장</button>
</table>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
