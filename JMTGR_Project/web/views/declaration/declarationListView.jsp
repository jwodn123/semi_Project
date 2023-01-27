<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="declaration.model.vo.Declaration, java.util.ArrayList, java.sql.Date"%>
<%
  ArrayList<Declaration> list = (ArrayList<Declaration>)request.getAttribute("list");
  int listCount = ((Integer)request.getAttribute("listCount")).intValue();
  int startPage = ((Integer)request.getAttribute("startPage")).intValue();
  int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
  int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
  int endPage = ((Integer)request.getAttribute("endPage")).intValue(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<script type="text/javascript">
function showWriteForm(){
	location.href = "/jmtgr/views/declaration/declarationWriteForm.jsp";
}

</script>
<style type="text/css">
h1.declaration {
	font-family: 'Jua', sans-serif;
	position: absolute;
	left: 300px;
	color: red;
}

table.tb {
	font-family: 'Jua', sans-serif;
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
}

th.cols {
	text-align: center;
}

table.tb thead th {
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: gray;
	border-bottom: 3px solid red;
}

table.tb tbody th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid gray;
	background: #f3f6f7;
}

table.tb td {
	width: 150px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid gray;
}

button.dwrite {
	font-family: 'Jua', sans-serif;
	font-size: 25px;
	border: none;
	background: none;
	position: relative;
	left: 1620px;
	top: 60px;
}

button.dwrite:hover {
	color: white;
	text-shadow: 2px 2px 2px red;
}

hr.dhr {
	width: 220px;
	border: 2px solid red;
	position: relative;
	left: -62.5px;
	top: 70px;
}

a {
	text-decoration: none;
}

.det a:visited {
	color: black;
	text-decoration: none;
}

.det a:hover {
	color: red;
	text-shadow: 1px 1px 1px black;
	text-decoration: none;
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


</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<div style="padding-top: 20px;"></div>
	<h1 class="declaration">신고하기</h1>
	<hr class="dhr">
	<div style="padding-top: 100px;"></div>
	<table class="tb" align="center">
		<thead>
			<tr>
				<th class="cols">글 번호</th>
				<th class="cols">제목</th>
				<th class="cols">작성자</th>
				<th class="cols">작성 날짜</th>
				<th class="cols">답 변</th>
			</tr>
		</thead>
		<% for(Declaration d : list) { %>
		<tr>
			<td align="center"><%= d.getDeNo() %></td>
			<td class="det">
			<% if(loginMember != null && loginMember.getUserId().equals("admin")){ %>
			<a href="/jmtgr/ddetail?dnum=<%= d.getDeNo() %>&page=<%= currentPage  %>"><%= d.getDeTitle() %></a>			
			<% } else { %>
			<a href="/jmtgr/views/declaration/Pwd.jsp?dnum=<%= d.getDeNo() %>&page=<%= currentPage  %>"><%= d.getDeTitle() %></a>
			<%} %>
			</td>
			<td align="center"><%= d.getUserId() %></td>
			<td align="center"><%= d.getDeBoardDate() %></td>
			<td align="center"></td>
			<% } %> 
<%-- <% if(reply.getQusReplyContent() != null) { %>
#
<% } else { %>
&nbsp;
<% } %> --%>
			</tr>
	</table>

	<button class="dwrite" onclick="showWriteForm();">신고 작성</button>
	<div style="padding-top: 100px;"></div>
	<!-- 페이징 처리 -->
	<div class="page_wrap">
		<div class="page_nation">
			<% if(currentPage <= 1) { %>
			<a class="arrow pprev" href="#"></a> &nbsp;
			<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/dlist?page=1"></a>
			<% } %>
			<!-- 이전 그룹으로 이동 처리 -->
			<% if((currentPage - 1) >= startPage){ %>
			<a class="arrow prev" href="/jmtgr/dlist?page=<%= currentPage - 1 %>"></a>
			<% }else{ %>
			<a class="arrow prev" href="#"></a> &nbsp;
			<% } %>
			<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
			<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage) { %>
			<a class="active" href="#=<%= p %>"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/dlist?page=<%= p %>"><%= p %></a>
			<% }} %>
			<!-- 다음 그룹으로 이동 처리 -->
			<% if((currentPage + 1) <= endPage && (currentPage + 1) <= maxPage ){ %>
			<a class="arrow next" href="/jmtgr/dlist?page=<%= currentPage + 1 %>"></a>
			<% }else{ %>
			<a class="arrow next" href="#"></a> &nbsp;
			<% } %>

			<!-- 맨끝 페이지로 이동 처리 -->
			<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" href="#"></a> &nbsp;
			<% }else{ %>
			<a class="arrow nnext" href="/jmtgr/dlist?page=<%= maxPage %>"></a>
			<% } %>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
