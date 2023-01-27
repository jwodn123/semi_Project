<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, korea_recipe_board.model.vo.KRBoard" %>
<%
ArrayList<KRBoard> krblist = (ArrayList<KRBoard>)request.getAttribute("kblist");
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
<script src="https://kit.fontawesome.com/262e649fa4.js" crossorigin="anonymous"></script>

<style>
.listtitle {
	font-family: 'Jua', sans-serif;
	font-size: 50px;
	text-align: center;
	}

a{
 text-decoration : none;
}

.page_wrap {
	text-align: center;
	font-size: 0;
	margin-bottom : 50px;
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

table {
    border-collapse: collapse;
    text-align: center;
    line-height: 1.5;

}
table thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
table tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
table td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
	
#toolbox {
	position : absolute;
	left : 60%;
	top : 20%;
	border : none;
	width : 350px;
	height : 120px;
	margin : 10px;
	background : #ffffff;
	padding : 5px;
	
}

#searchboxT {
	align : center;
	width:400px;
	height:20px;
	padding : 0;
	margin : 0;
	border : 0;
	background : #ffffff;
}


#toolbox input{
	outline : none;
	flote : left;
	heigth:20px;
}

#toolbox a {
	color : black;
	text-decoration : none;
}

#write{
	position : absolute;
	left : 70%;
	top : 170%;
	border : 1px solid black;
}


#daumSearch .box_searchbar {float:left;background:url(http://i1.daumcdn.net/svc/image/U03/common_icon/5265025001055B0001) no-repeat;background-position:0 0}
#daumSearch .search_on {position:relative;background-position:0 -50px}
#daumSearch .tf_keyword {display:inline;float:left;width:370px;height:18px;margin:11px 10px 0;padding:0;border:0 none;font-weight:bold;font-size:16px;line-height:18px;font-family:'돋움',dotum,sans-serif;background-color:transparent;outline:none}
#daumSearch .btn_search {float:left;overflow:hidden;width:66px;height:39px;border:0 none;background-position:0 -300px;cursor:pointer}
#daumSearch .btn_search:hover  {background-position:-70px -300px}
/* search bar 내부검색창 */
#daumSearch .fld_inside {position:relative;width:100%;height:31px}
#daumSearch .fld_inside .box_searchbar {background-position: 0 -200px}
#daumSearch .fld_inside .tf_keyword {width:242px;height:18px;margin:7px 9px 0;font-weight:normal;font-size:12px;line-height:1.5}
#daumSearch .fld_inside .btn_search {width:50px;height:31px;background-position:100% -200px}
#daumSearch .fld_inside .btn_search:hover {background-position:100% -300px}
#daumSearch .fld_inside .bg_on {background:url(http://i1.daumcdn.net/svc/image/U03/common_icon/5265025001055B0001) no-repeat 2px -422px}
#daumSearch .fld_inside .search_on {background-position:0 -250px}
#daumSearch .fld_inside .search_on .btn_search {background-position:100% -250px}
#daumSearch .fld_inside .search_on .btn_search:hover {background-position:100% -300px}
/* search bar 옵션이 있을경우 */
#daumSearch .opt_on .tf_keyword {width:158px;margin:7px 5px 0}
#daumSearch .opt_search {float:left;width:92px;height:31px;line-height:14px}
#daumSearch .opt_search .search_option {height:31px}
#daumSearch .opt_search .tf_opt {width:82px;height:18px;margin:7px 0 0 10px;border:0 none;background-color:transparent;outline:none;cursor:pointer}
#daumSearch .opt_search .btn_opt {position:absolute;top:0;left:1px;width:91px;height:31px;;background-image:url(data:image/gif;base64,R0lGODlhAQABAIAAAP///////yH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==)}
#daumSearch .opt_search .ico_arrow {float:right;overflow:hidden;width:10px;height:15px;margin-top:8px;border-right:1px solid #d9d9d9;background-position:0 -350px;text-indent:-9999px}
#daumSearch .opt_search .layer_opt {display:none;overflow:hidden;position:absolute;top:29px;left:0;width:90px;border:1px solid #a5a6ac;border-top:0 none;background-color:#fff}
#daumSearch .opt_search .list_opt {float:left;width:100%;margin-top:1px;padding:2px 0 3px;border-top:1px solid #e8e8ea}
#daumSearch .opt_search .lab_opt {display:block;position:relative;z-index:1;width:100%;padding:5px 0 3px;background-color:#fff;text-indent:9px;cursor:pointer}
#daumSearch .opt_search .inp_opt {position:absolute;width:100%;height:22px;opacity:0;filter: alpha(opacity=0)}
#daumSearch .opt_open .btn_opt {top:0;left:0;width:91px;height:29px;border-top:1px solid #a5a6ac;border-left:1px solid #a5a6ac}
#daumSearch .opt_open .ico_arrow {margin:0;height:29px;border-color:#a5a6ac;background-position:0 -375px}
#daumSearch .opt_open .layer_opt {display:block}
#daumSearch .list_opt li {float:left;width:100%}
#daumSearch .on .lab_opt {font-weight:bold;background-color:#f0f0f0;letter-spacing:-1px}
</style>
<script type="text/javascript" src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
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
	
	function login(){
		alert('로그인 후 이용해 주세요.');
	}
	
	function movePage(keyword) {
		location.href="/jmtgr/orderbylist?orderby="+keyword;
	}
</script>

</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<div class="listtitle" style="margin-top:20px;">한식</div>
	<div id="toolbox">
	
	<div id="orderby" style="float:right; margin-right:33px; margin-top:5px;">
	<select id="orderbyValue" name="orderbyKeyword" onchange="movePage(this.value)" style="height:30px; margin-left:15px;">
	<option value="none">정렬 기준 선택</option>
	<option value="title"> 제목 순 정렬 </option>
	<option value="tdesc"> 제목 역순 정렬 </option>		
	<option value="readcount"> 조회수 정렬 </option>
	</select>
	</div> 	<br>
	<div style="float:right; margin-top:5px; margin-right:33px; margin-left:50px;">
	<span style="margin-left:15px;"><a href="/jmtgr/krlist"><i class="fas fa-th-large"></i>&nbsp; 사진 형식으로 보기</a></span>
	</div>
</div> 


<table id="boardlist" align="center" width="1000" class="table" vlign="middle" style="margin-bottom: 100px; margin-top:70px;">
<tr><th>&nbsp;</th><th style="width:350px">글 제목</th><th>요리 분류</th><th>작성 날짜</th><th>조회수</th></tr>
<% for(KRBoard krb : krblist) {%>
<% if(loginMember != null) {%>
	<tr><th><%= krb.getKrBoardNo() %></th><td style="width:300px;"><a href="/jmtgr/krdetail.ss?page=<%= currentPage %>&krno=<%= krb.getKrBoardNo() %>"><%=krb.getKrBoardTitle() %></a></td> <td><%= krb.getCookName() %> </td> <td><%= krb.getKrBoardDate() %></td> <td><%= krb.getReadCount() %></td> </tr>
<% } else {%>
	<tr><th><%= krb.getKrBoardNo() %></th><td style="width:300px;"><a onclick="login()"><%=krb.getKrBoardTitle() %></a></td> <td><%= krb.getCookName() %> </td> <td><%= krb.getKrBoardDate() %></td> <td><%= krb.getReadCount() %></td> </tr>
<%}} %>

</table>


<div class="page_wrap">
	<div class="page_nation">
		<% if(currentPage <= 1) { %>
			<a class="arrow pprev" onclick="startPage()"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/listboard?page=1"></a>
		<% } %>
		<!-- 이전 그룹으로 이동 처리 -->
		<% if((currentPage - 10) < startPage && startPage != 1){ %>
			<a class="arrow prev" href="/jmtgr/listboard?page=<%= currentPage = startPage-10 %>"></a>
		<% }else { %>
			<a class="arrow prev" onclick="startPage()"></a>
			&nbsp;
		<% }%>
		
		<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage) { %>
			<a class="active" onclick="nowPage()"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/listboard?page=<%= p %>"><%= p %></a>
		<% }} %>
		<!-- 다음 그룹으로 이동 처리 -->
		<% if(endPage != maxPage ){ %>
			<a class="arrow next" href="/jmtgr/listboard?page=<%= currentPage = startPage + 10 %>"></a>
		<% }else{ %>
			<a class="arrow next" onclick="endPage()"></a>			
			&nbsp;
		<% } %>
		
		<!-- 맨끝 페이지로 이동 처리 -->
		<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" onclick="endPage()"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow nnext" href="/jmtgr/listboard?page=<%= maxPage %>"></a>
		<% } %>	
	</div>	
</div>
<hr>
<%@ include file="../common/footer.jsp" %> %>
</body>
</html>