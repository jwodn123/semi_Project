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
<style>
div#deleteForm{
  position: relative;

  width: 500px;
  height : 500px;
  background : white;
  color : black;
  margin: auto;
  font-weight : bold;
}
div#deleteForm input[type=submit]{
  text-align: center;
	color: rgba(0,0,0,0.5);
	position: relative;
  top: 240px;
      left: -40px;
	width: 70px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	background: white;
	border: none;

}

div#deleteForm input[type=submit]:hover{
  text-decoration: none;
	width: 70px;
	height: 30px;
	text-align: center;
	color: black;
	font-weight: bold;

}

div#deleteForm input[type=button]{
    text-align:center;
    position: relative;
    top: 240px;
    left: -40px;
    color: rgba(0,0,0,0.5);
    width: 100px;
    height: 30px;
    font-size: 15px;
    font-weight: bold;
    background:white;
    border: none;
}

div#deleteForm input[type=button]:hover{
  text-decoration: none;
  color: black;
  position: relative;
  left : -40px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  border: none;

}

div#deleteForm input[type=password]{
  width:200px;
  height: 25;
  position: relative;
    left: 150px;
    top: 180px;
  font-size: 16px;

}

</style>
 <script type="text/javascript">
 function validate(userpass){
	var deleteCheck = confirm("정말로 탈퇴하시겠습니까?");

	if(deleteCheck ){
		if(loginMember.getUserPwd() == userpass){
		alert('탈퇴 완료되었습니다.');

		return true;
		}
	}else{
		alert("취소하였습니다.")
	}return false;
 }
</script>
</head>
<%@ include file="../common/header.jsp" %>
<body>
<h1 align="center">회원탈퇴</h1>
<div id="deleteForm">


<% if (loginMember !=null){ %>
<form name="deleteform" action="/jmtgr/mdel.cp?userid=<%= loginMember.getUserId() %>&userpwd<%=loginMember.getUserPwd() %>" method="post" onsubmit="return validate(userpass);">


<input type="password" id="userpass" name="userpwd" placeholder="비밀번호" >

	<input type="submit" value="탈퇴">
	<input type="button" align="center" value="돌아가기" onclick="history.go(-1);">
</table>
</form>
<%} %>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
