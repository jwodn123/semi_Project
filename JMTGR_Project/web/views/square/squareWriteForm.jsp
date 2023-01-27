<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="msquare.model.vo.Square" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style type="text/css">
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 2;
    border-top: 1px solid #ccc;
    border-left: 4px solid #369;
    margin : 70px 20px;
    position:relative;
    left:35%;
    width: 600px;
    height: 300px;
}
table.type03 th {
    width: 80px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;

}
table.type03 td {
    width: 349px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
input.put {
	position:relative;
	left:170px;
}
</style>
<script type="text/javascript">
	function searchAddress(){
		//창을 오픈하기 위한 함수
		popup = window.open("squareMapView.jsp", "mywin",
		"width=800,height=500,top=50,toolbar=yes,status=yes,left=230");
	}
</script>
</head>
<body>
<div style= "padding-top : 50px;"></div>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<br>
<h1 align="center"> 맛남의 광장 글 쓰기</h1>
<form action="/jmtgr/sinsert" method="post" name="jw">
<table class="type03">
<tr>
	<th>제목</th>
	<td><input type="text" name="title" size="50"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="writer" size="50" readonly value="<%= loginMember.getUserId() %>"></td> 
</tr>
<tr>
	<th>지도</th>
	<td>
		<input type="text" size="30" name="address">
		<input type="button" value="위치검색" onClick="searchAddress()">
	</td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="5" cols="50" name="content"></textarea></td>
</tr>
<tr><th colspan="2">
<input type="submit" value="등록하기" class="put">&nbsp;
<input type="reset" value="작성취소" class="put">&nbsp;
<input type="button" value="목록" onclick="javascript:history.go(-1); return false;" class="put">&nbsp;
</table>
</form>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/footer.jsp" %> 
</body>
</html>