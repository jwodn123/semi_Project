<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style type="text/css">


div#SearchPwdForm {
  position: relative;

  width: 500px;
	height : 500px;
	background : white;
	color : white;
  left: 850px;
	font-weight : bold;
}
div#SearchPwdForm input[type=text]{
  position: relative;
  left: 5px;
  top: 150px;
  margin-top:10px;
  maring-left:10px;
  font-size: 16px;
  width: 200px;
  height: 25px;
}


div#SearchPwdForm input[type=submit]{
  text-align: center;
  color: rgba(0,0,0,0.5);
  position: relative;
  top: 170px;
  left : 5px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  background: white;
  border: none;

}

div#SearchPwdForm input[type=submit]:hover{
  text-decoration: none;
  width: 100px;
  height: 30px;
  text-align: center;
  color: black;
  font-weight: bold;
}

div#SearchPwdForm input[type=button]{
  text-align: center;
  color: rgba(0,0,0,0.5);
  position: relative;
  top: 170px;
  left : 5px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  background: white;
  border: none;

}

div#SearchPwdForm input[type=button]:hover{
  text-decoration: none;
  width: 100px;
  height: 30px;
  text-align: center;
  color: black;
  font-weight: bold;
}

</style>
</head>
<body>
    <%@ include file="../common/header.jsp" %>
<h1 align="center">비밀번호 찾기</h1>
<div id="SearchPwdForm">
<form action="/jmtgr/searchpwd.en" method="post">
<input type="text" name="userid" id="uid" class="pos" placeholder="아이디" required> <br>
<input type ="text" name="username" id="uname" class="pos" placeholder="이름" required> <br>
<input type="text" name="userno" id="uno" class="pos" placeholder="주민번호" required> <br>
  <input type="submit" value="비밀번호 찾기"> &nbsp;
  <input type="button" value="돌아가기" onclick="javascript:history.go(-1)">
</form>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
