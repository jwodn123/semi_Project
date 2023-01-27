<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="msquare.model.vo.Square, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Square> list = (ArrayList<Square>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	String keyword = (String)request.getAttribute("keyword");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
$(document).ready(function() {
	alert("시작");
}
</script>
<style type="text/css">
table.tb {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
}

table.tb thead th {
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: gray;
	border-bottom: 4px solid black;
}

table.tb tbody th {
	width: 170px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid gray;
	background: #f3f6f7;
}

table.tb td {
	width: 170px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid gray;
}

button {
	position: relative;
	right: 150px;
}

.page_wrap {
	text-align: center;
	font-size: 0;
}

.page_nation {
	display: inline-block;
}

.page_nation .none {
	display: none;
}

.page_nation a {
	display: block;
	margin: 0 3px;
	float: left;
	border: 1px solid #e6e6e6;
	width: 28px;
	height: 28px;
	line-height: 28px;
	text-align: center;
	background-color: #fff;
	font-size: 13px;
	color: #999999;
	text-decoration: none;
}

.page_nation .arrow {
	border: 1px solid #ccc;
}

.page_nation .pprev {
	background: #f8f8f8 url('resources/images/page_pprev.png') no-repeat
		center center;
	margin-left: 0;
}

.page_nation .prev {
	background: #f8f8f8 url('resources/images/page_prev.png') no-repeat
		center center;
	margin-right: 7px;
}

.page_nation .next {
	background: #f8f8f8 url('resources/images/page_next.png') no-repeat
		center center;
	margin-left: 7px;
}

.page_nation .nnext {
	background: #f8f8f8 url('resources/images/page_nnext.png') no-repeat
		center center;
	margin-right: 0;
}

.page_nation a.active {
	background-color: #42454c;
	color: #fff;
	border: 1px solid #42454c;
}
.search {
	position:relative;
	left: 890px;
}
.search1 {
	position:relative;
	left: 890px;
}

</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<h1 class="put" align="center" class="s2">만남의 광장</h1><br>

<form action="/jmtgr/ssearch" method="post"> 
	<select class="search1" name="searchKeyword">      
	    <option align="right" value="title" > 제목 </option>
	    <option align="right" value="name" > 아이디 </option>
	</select>
	<div class="search">
		<input type="search" name="keyword" placeholder="검색어 입력">
		<input type="submit" value="검색">
	</div>
</form>

<form>
<table class="tb" align="center">
	<thead>
	<tr>
		<th>No.</th>
		<th>제목</th>
		<th align="center">작성자</th>
		<th align="center">작성날짜</th>
		<th align="center">조회수</th>
	</tr>
	</thead>
<% for(Square s : list){%>
<tr>
	<td align="left"><%= s.getMs_board_no() %></td>
	<td><a href="/jmtgr/sdetail?squareno=<%= s.getMs_board_no() %>&keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>&page=<%= currentPage %>"><%= s.getMs_board_title() %></a></td>
	<td align="center"><%= s.getUser_id() %></td>
	<td align="center"><%= s.getMs_board_date() %></td>
	<td align="center"><%= s.getRead_count() %></td>
</tr>
<% } %>
</table>
</form>
<br>
<div style="align:center;text-align:right;">
<button onclick="javascript:location.href='/jmtgr/views/square/squareWriteForm.jsp'">글쓰기</button>
</div>
<br>

<!-- 페이징 처리 -->
<form>
<div class="page_wrap">
	<div class="page_nation">
		<!-- 맨처음으로 이동처리 -->
		<% if(currentPage <= 1) { %>
			<a class="arrow pprev" href="#"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/slist?page=1"></a>
		<% } %>
		<!-- 이전으로 이동 처리 -->
		<% if((currentPage - 1) >= startPage){ %>
			<a class="arrow prev" href="/jmtgr/slist?page=<%= currentPage - 1 %>"></a>
		<% }else{ %>
			<a class="arrow prev" href="#"></a>
			&nbsp;
		<% } %>
		<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage) { %>
			<a class="active" href="#=<%= p %>"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/slist?page=<%= p %>"><%= p %></a>
		<% }} %>
		<!-- 다음으로 이동 처리 -->
		<% if((currentPage + 1) <= endPage && (currentPage + 1) <= maxPage ){ %>
			<a class="arrow next" href="/jmtgr/slist?page=<%= currentPage + 1 %>"></a>
		<% }else{ %>
			<a class="arrow next" href="#"></a>			
			&nbsp;
		<% } %>
		
		<!-- 맨끝 페이지로 이동 처리 -->
		<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" href="#"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow nnext" href="/jmtgr/slist?page=<%= maxPage %>"></a>
		<% } %>	
	</div>	
</div>
</form>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/footer.jsp" %> 
</body>
</html>






































