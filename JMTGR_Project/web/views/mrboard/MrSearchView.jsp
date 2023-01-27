<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="my_recipe_board.model.vo.MyRecipe, java.util.ArrayList"%>
<%
	ArrayList<MyRecipe> mrlist = (ArrayList<MyRecipe>) request.getAttribute("mrlist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>

<style>
a {
	text-decoration: none;
	font: white;
	float: left;
}


.list2 {
	font-family: 'Jua', sans-seri;
	background: white;
	border: 1px solid black;
	margein: 5px;
	padding: 0;
	float: left;
	width: 150px;
	height: 150px;

}

.limg {
	background: white;
	width: 130px;
	height: 100px;
	margin: 10px;
	text-align: left;
	text-align: bottom;
	vertical-align:bottom;
}

.but {
	text-align: center;
}

#searchbox {
	float: left;
}

.search {
	position: relative;
}
.listtitle {
	font-family: 'Jua', sans-seri;
	font-size: 50px;
	text-align: center;
}
</style>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<hr>
	<div class="listtitle">나만의 레시피 검색 결과</div>
	
		<div id="searchbox" class="search">
			<form action="/jmtgr/mrsearch.en" method="post">
				<input type="search" name="keyword" value="제목으로 검색"
					style="width: 200px;" onfocus="clearText(this)"> <input
					type="submit" value="검색"> &nbsp;
			</form>
		</div>
		<button class="but" onclick="javascript:location.href='/jmtgr/mrlist.ss'">글목록</button>
	<hr>

	

	<% if (mrlist.size() > 0) { %>
	<div id="total">
		<% for (MyRecipe mr : mrlist) { %>
		<div class="list2">
			<a href="/jmtgr/mrdetail.ss?mrno=<%=mr.getMrBoardNo()%>">
			&nbsp;<%=mr.getMrBoardTitle()%>
		<div class="limg" div id="inimg1"
			style="background-image:url(resources/mrthumbnail/<%=mr.getMrboardrenameFile()%>); background-size:cover;">
		</div>
		</a>
		</div>

		<% } %>
	</div>
	<% } else { %>
	<h1 align="center">결과를 찾지 못했습니다.</h1>
	<% } %>



















	<br>
</body>
</html>