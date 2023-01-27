<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<script src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
<script>

$(document).ready(function(){
	$('#qpwd').hide();
})

function setDisplay(){
	if($('input[name="pwdok"]:checked').val() == 00) {
		$('#qpwd').show();
	}else {
		$('#qpwd').hide();
	}

}

</script>
<style type="text/css">
body {
	font-family: 'Jua', sans-serif;
}

h1.qwrite {
	position: absolute;
	left: 300px;
}

table, tr, th, td {
	border-collapse: collapse;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

td {
	border-bottom: none;
}

input {
	border: none;
	background: none;
	font-family: 'Jua', sans-serif;
}

textarea {
	border: 2px solid black;
	font-family: 'Jua', sans-serif;
}

.button {
	position: relative;
	left: 300px;
	top: 150px;
	font-size: 60px;
}

hr.qhr {
	width: 220px;
	border: 2px solid black;
	position: absolute;;
	left: -60px;
	top: 240px;
}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<h1 class="qwrite">문의 작성</h1>
	<hr class="qhr">
	<div style="padding-top: 210px;"></div>
	<form action="/jmtgr/qinsert" method="post"
		enctype="multipart/form-data">
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="5">
			<tr align="left">
				<th>제 목 :</th>
				<td><input type="text" name="title" size="50"></td>
			</tr>
			<tr>
			<th align="left">파일 선택 :</th>
			<td><input type="file" name="ofile"></td>
			</tr>
			<tr align="left">
				<th>비밀번호 :</th>
				<td><input type="radio" id="pwdok" name="pwdok" value="00" onchange="setDisplay()"> 비밀번호 설정
				    <input type="radio" id="pwdno" name="pwdok" value="10" onchange="setDisplay()" checked> 비밀번호 미설정
					<div id="qpwd">
						<input type="password" name="qpassword" size="10">
					</div></td>
			</tr>
			<tr align="left">
				<th>내 용 :</th>
				<td><textarea rows="5" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<div class="button">
						<input type="submit" value="등록하기">
						<input type="reset" value="작성취소">
						<input type="button" value="목록보기" onclick="javascript:history.go(-1); return false;">
					</div>
				</th>
			</tr>
		</table>
	</form>
		<div style="padding-top: 200px;"></div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>