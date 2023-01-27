<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="my_recipe_board.model.vo.MyRecipe, java.util.ArrayList"%>
<%
	
ArrayList<MyRecipe> mrlist = (ArrayList<MyRecipe>)request.getAttribute("mrlist");
	String[] name = new String[]{"one", "two", "three", "four", "five", "six"};
	
	int i = 0;
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
<style>
.listtitle {
	font-family: 'Jua', sans-serif;
	font-size: 50px;
	text-align: center;
}

.listmain {
	display: grid;
	grid-template-columns: 450px 450px 450px;
	grid-template-rows: 450px 450px 450px;
	margin-left: 14%;
	font-family: 'Jua', sans-serif;
}

.one {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: cover;
}

h1.title {
	color: black;
	padding-top: 10px;
	margin-left: 40px;;
	margin-top: 380px;
	text-align: center;
	font-size: 30px;
	width: 380px;
	height: 85px;
	background-color: white;
	border: 1px solid gray;
}

.two {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: contain;
	margin-top: -45px;
	margin-left: 15px;
}

.three {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: contain;
	margin-left: 30px;
}

.four {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: contain;
	margin-top: 80px;
}

.five {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: cover;
	margin-top: 25px;
	margin-left: 15px;
}

.six {
	width: 100%;
	height: 100%;
	background-repeat: no-repeat;
	background-size: contain;
	margin-top: 80px;
	margin-left: 30px;
}

button.view {
	background: none;
	border: none;
	font-family: 'Jua', sans-serif;
	font-size: 14px;
}

button.add {
	background: none;
	border: none;
	font-family: 'Jua', sans-serif;
}

button.write {
	background: none;
	border: none;
	font-family: 'Jua', sans-serif;
	font-size : 20px;
	position: relative;
	left: 1450px;
	top: -1400px;

}

.arrayWay {
	font-family: 'Jua', sans-serif;
	position: relative;
	left: 255px;
	top: -1460px;
	}

#searchbox  {
	font-family: 'Jua', sans-serif;
	position: relative;
	left: 255px;
	top: 112px;
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
	
	<hr>
	<script>
function clearText(field){ 
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;}
</script>
	<div id="searchbox" class="search">
		<form action="/jmtgr/mrsearch.en" method="post">
			<input type="search" name="keyword" value="제목으로 검색" style="width: 200px;" onfocus="clearText(this)">
			 <input type="submit" value="검색">
		</form>
	</div>

	<div class="listtitle">나만의 레시피</div>
	<br>
	<div style="padding-top: 50px;"></div>
	<div class="listmain">
		<% for(MyRecipe mrb : mrlist) { %>
		<div class="<%= name[i] %>" style="background-image:url(resources/thumbnail/<%= mrb.getMrboardoriginFile() %>)">
			
			<h1 class="title">
				<%= mrb.getMrBoardTitle() %>
				<br>
				
				<button class="view" onclick="javascript:location.href='/jmtgr/mrdetail.ss?mrno=<%= mrb.getMrBoardNo() %>'">	VIEW</button>
				<button class="add"	onclick="javascript:location.href='/jmtgr/mrdetail.ss?mrno=<%= mrb.getMrBoardNo() %>'">재료 추가</button>
				
			</h1>
		</div>
		<% i++; } %>
	</div>
	<% if(loginMember != null) {%>
	<button class="write" onclick="javascript:location.href='views/mrboard/MyRecipeWriteForm.jsp'">글 작성하기</button>
	<% } %>

	<br>
	<div class="arrayWay">
	<a href="/jmtgr/orderbyS?orderby=title">[ 제목 정렬 ]</a>
	<a href="/jmtgr/orderbyS?orderby=tdesc">[ 제목 역순 정렬 ]</a>
	</div>
	
	<div class="page_wrap">
	<div class="page_nation">
		<% if(currentPage <= 1) { %>
			<a class="arrow pprev" href="#"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/mrlist.ss?page=1"></a>
		<% } %>
		<!-- 이전 그룹으로 이동 처리 -->
		<% if((currentPage - 1) >= startPage){ %>
			<a class="arrow prev" href="/jmtgr/mrlist.ss?page=<%= currentPage - 1 %>"></a>
		<% }else{ %>
			<a class="arrow prev" href="#"></a>
			&nbsp;
		<% } %>
		<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage) { %>
			<a class="active" href="#=<%= p %>"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/mrlist.ss?page=<%= p %>"><%= p %></a>
		<% }} %>
		<!-- 다음 그룹으로 이동 처리 -->
		<% if((currentPage + 1) <= endPage && (currentPage + 1) <= maxPage ){ %>
			<a class="arrow next" href="/jmtgr/mrlist.ss?page=<%= currentPage + 1 %>"></a>
		<% }else{ %>
			<a class="arrow next" href="#"></a>			
			&nbsp;
		<% } %>
		
		<!-- 맨끝 페이지로 이동 처리 -->
		<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" href="#"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow nnext" href="/jmtgr/mrlist.ss?page=<%= maxPage %>"></a>
		<% } %>	
	</div>	
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>