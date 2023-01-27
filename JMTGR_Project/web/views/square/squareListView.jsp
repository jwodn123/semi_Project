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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>

<script>
function endPage(){
	alert('마지막 페이지입니다.');
}

function startPage(){
	alert('처음 페이지 입니다.');
}
function nowPage(){
	alert('현재 페이지 입니다.');
}

function orderbyPage(keyword){
	$.ajax({
		url : "/jmtgr/orderby",
		type : "post",
		data : {orderby : keyword},
		success : function(data){
			return true;
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('정렬 실패');
		}
	})
}

function movePage(keyword) {
	if(keyword != "none"){
	location.href="/jmtgr/orderby?orderby="+keyword;
	}
	else{
		alert('정렬 실패');
	}
}
</script>
<style type="text/css">

img {
	width: 100%;
	height: 400px;
	margin-top: -124px;
}
h1.s2{
	position: absolute;
	top: 230px;
	left: 750px;
	font-size: 70px;
	color:  #ffffff;
	font-family: 'MaplestoryOTFBold';
    text-shadow: 3px 3px 7px black;
}
@font-face {
    font-family: 'MaplestoryOTFBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}


.search__input {
        width: 180px;
        padding: 12px 24px;
        background-color: transparent;
        transition: transform 250ms ease-in-out;
        font-size: 14px;
        line-height: 18px;
        color: #575756;
        background-color: transparent;
        background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24'%3E%3Cpath d='M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z'/%3E%3Cpath d='M0 0h24v24H0z' fill='none'/%3E%3C/svg%3E");
        background-repeat: no-repeat;
        background-size: 18px 18px;
        background-position: 95% center;
        border-radius: 50px;
        border: 1px solid #575756;
        transition: all 250ms ease-in-out;
        backface-visibility: hidden;
        transform-style: preserve-3d;
        position: absolute;
        left: 1600px;
        top : 250px;
    }

.search__input::placeholder {
            color: rgba(87, 87, 86, 0.8);
            text-transform: uppercase;
            letter-spacing: 1.5px;
        }

.search__input:hover,
        .search__input:focus {
            padding: 12px 0;
            outline: 0;
            border: 1px solid transparent;
            border-bottom: 1px solid #575756;
            border-radius: 0;
            background-position: 100% center;
        }



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
	left: 63%;
}
.search1 {
	position:relative;
	left: 59%;
	top: 25px;
	height: 22px;
}


</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>


<div>
	<img src="resources/images/squarem1.jpg">
</div>
<div style= "padding-top : 100px;"></div>
<h1 class="s2">만남의 광장</h1><br>


<form action="/jmtgr/squaresearch" method="post">
	<select class="search1" name="searchKeyword">
	    <option align="right" value="title" > 제목 </option>
	    <option align="right" value="name" > 아이디 </option>
	</select>
	<div class="search">
		<input type="search" name="keyword" placeholder="검색어 입력">
		<input type="submit" value="검색">
	</div>
</form>

<div style= "padding-top : 50px;"></div>


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
	<td><a href="/jmtgr/sdetail?squareno=<%= s.getMs_board_no() %>&page=<%= currentPage %>"><%= s.getMs_board_title() %></a></td>
	<td align="center"><%= s.getUser_id() %></td>
	<td align="center"><%= s.getMs_board_date() %></td>
	<td align="center"><%= s.getRead_count() %></td>
</tr>
<% } %>
</table>
</form>
<br>
<div style="align:center;text-align:right; margin-right:330px;">
<button onclick="javascript:location.href='/jmtgr/views/square/squareWriteForm.jsp'">글쓰기</button>
</div>
<br>

<!-- 페이징 처리 -->
<form>
<div class="page_wrap">
	<div class="page_nation">
		<% if(currentPage <= 1) { %>
			<a class="arrow pprev" onclick="startPage()"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/slist?page=1"></a>
		<% } %>
		<!-- 이전 그룹으로 이동 처리 -->
		<% if((currentPage - 10) < startPage && startPage != 1){ %>
			<a class="arrow prev" href="/jmtgr/slist?page=<%= currentPage = startPage-10 %>"></a>
		<% }else { %>
			<a class="arrow prev" onclick="startPage()"></a>
			&nbsp;
		<% }%>

		<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage) { %>
			<a class="active" onclick="nowPage()"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/slist?page=<%= p %>"><%= p %></a>
		<% }} %>
		<!-- 다음 그룹으로 이동 처리 -->
		<% if(endPage != maxPage ){ %>
			<a class="arrow next" href="/jmtgr/slist?page=<%= currentPage = startPage + 10 %>"></a>
		<% }else{ %>
			<a class="arrow next" onclick="endPage()"></a>
			&nbsp;
		<% } %>

		<!-- 맨끝 페이지로 이동 처리 -->
		<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" onclick="endPage()"></a>
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
