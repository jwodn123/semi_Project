
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ page import="java.lang.*"%>
<%
	Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style>
div#myinfo{
  position:relative;
  left: 850px;
	width: 440px;
	height: 500px;
	text-align: center;
}
div#myinfo th{
  position: relative;
  padding:10px;
  left:-50px;


}


a {
	text-decoration : none;
}

}
div#sub{

}
div#sub a{
	text-align: center;
	color: rgba(0,0,0,0.5);
  position: relative;
  left: -125px;
  top:15px;
  padding-left: 10px;
	width: 150px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	background: white;
	border: none;
}

div#sub a:hover{
	text-decoration: none;
  position: relative;
	width: 150px;
	height: 30px;
	text-align: center;
	color: black;
	font-weight: bold;
}

</style>
<script type="text/javascript">
	function moveUpdateView(){
		//요청한 회원의 정보 수정페이지를 내보내는 서블릿을 요청함
		//다시 요청한 회원의 아이디를 전송해서 처리함
		location.href = "/jmtgr/mupview?userid=<%=member.getUserId()%>
	";
	}
</script>
</head>
<%@ include file="../common/header.jsp"%>
<body>
	<h1 align="center">개인 정보</h1>
	<br>
	<div id="myinfo">
		<table>
			<tr>
				<th>아이디</th>
				<td><%=member.getUserId()%></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><%=member.getUserPwd()%></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=member.getUserName()%></td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td><%=member.getUserNo()%></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><%=member.getPhone()%></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=member.getGender()%></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=member.getAddress1()%></td>
			</tr>
			<tr>
				<th>우편</th>
				<td><%=member.getAddress2()%></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><%=member.getAddress3()%></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=member.getEmail()%></td>
			</tr>

		</table>
    <div id="sub">
      <a onclick="javascript:location.href='/jmtgr/mupview?userid=<%=member.getUserId()%>'">정보수정</a>
      <a href="/jmtgr/userboard?userid=<%= loginMember.getUserId() %>">내가 쓴 글</a>
      <a onclick="location.href='views/member/deleteForm.jsp'">탈퇴</a>
      <a onclick="history.go(-1);">뒤로가기</a>

    </div>
	</div>
	<hr>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
