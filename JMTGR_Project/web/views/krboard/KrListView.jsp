<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="korea_recipe_board.model.vo.KRBoard, java.util.ArrayList"%>
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

.listmain {
	display: grid;
	grid-template-columns: 450px 450px 450px;
	grid-template-rows: 450px 450px ;
	margin-left: 13.5%;
	font-family: 'Jua', sans-serif;
	margin-bottom : 200px;
	
}

.one {
	width: 100%;
	height: 100%;
	background-repeat : no-repeat;
	background-size : cover;
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
	background-repeat : no-repeat;
	background-size : contain;
	margin-top: -45px;
	margin-left: 15px;
}

.three {
	width: 100%;
	height: 100%;
	background-repeat : no-repeat;
	background-size : contain;
	margin-left: 30px;
}

.four {
	width: 100%;
	height: 100%;
	background-repeat : no-repeat;
	background-size : contain;
	margin-top: 80px;
}

.five {
	width: 100%;
	height: 100%;
	background-repeat : no-repeat;
	background-size : cover;
	margin-top: 25px;
	margin-left: 15px;
}

.six {
	width: 100%;
	height: 100%;
	background-repeat : no-repeat;
	background-size : contain;
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
	left: 1500px;
	
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

#toolbox {
	position : absolute;
	left : 68%;
	top : 23%;
	border : none;
	width : 350px;
	height : 150px;
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

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>

	<div class="listtitle" style="margin-top:30px;">한식</div>
	<div id="toolbox">
	<div id="searchboxT" class="search">
		<form id="daumSearch" name="daumSearch" action="/jmtgr/krsearchT.en">
    <fieldset class="fld_inside opt_on" style="border:0 solid black;"><!--  내부검색 fld_inside 추가 --><!--  내부검색 옵션이 있을경우 opt_on 추가 -->
        <div class="box_searchbar"><!-- 입력창 선택시 search_on 추가  -->
            <!-- 옵션창  -->        
            <span style="float:left;">    
                    <select name="searchKeyword" style="height:30px;">
                    <option value="none">분 류</option>
				<option value="title">이름</option>
				<option value="cook">요리 방법</option>
			</select>
               </span>
            <!--// 옵션창  -->
            <input type="text" class="tf_keyword" id="qTop" name="keyword" title="검색어 입력" autocomplete="off" spellcheck="false" />
            <button id="daumBtnSearch" class="ico_search btn_search" type="submit"><span class="ir_wa">검색</span></button>
        </div>
    </fieldset>
</form>
	</div>	<br>
	
	<div id="orderby" style="float:right; margin-right:33px; margin-top:5px;">
	<select id="orderbyValue" name="orderbyKeyword" onchange="movePage(this.value)" style="height:30px; margin-left:15px;">
	<option value="none">정렬 기준 선택</option>
	<option value="title"> 제목 순 정렬 </option>
	<option value="tdesc"> 제목 역순 정렬 </option>		
	<option value="readcount"> 조회수 정렬 </option>
	</select>
	</div> 	<br>
	<div style="float:right; margin-top:5px; margin-right:33px; margin-left:50px;">
	<span style="margin-left:15px;"><a href="/jmtgr/listboard"><i class="fas fa-list"></i>&nbsp; 리스트 형식으로 보기</a></span>
	</div>
</div> 
	
	<br>
	<div style="padding-top: 160px;"></div>
	<div class="listmain">
		<% for(KRBoard kb : krblist) { %>
		<div class="<%= name[i] %>" style="background-image:url(resources/thumbnail/<%= kb.getRenameFile() %>); background-size : cover;">
			
			<h1 class="title">
				<%= kb.getKrBoardTitle() %>
				<br>
				<% if(loginMember != null && loginMember.getUserId().equals("admin")) {%>
				<button class="view" onclick="javascript:location.href='/jmtgr/krdetail.ss?krno=<%= kb.getKrBoardNo() %>&page=<%= currentPage %>'">	VIEW</button>
				<button class="add"	onclick="javascript:location.href='/jmtgr/krbno?krno=<%= kb.getKrBoardNo() %>'">재료 추가</button>
				<% } else if(loginMember != null){ %>				
				<button class="view" onclick="javascript:location.href='/jmtgr/krdetail.ss?krno=<%= kb.getKrBoardNo() %>&page=<%= currentPage %>'"> VIEW</button>
				<%} else {%>
				<button class="view" onclick="alert('로그인 후 이용해 주세요')"> VIEW</button>
				<%} %>
			</h1>
		</div>
		<% i++; } %>		
	</div>
<% if(loginMember != null && loginMember.getUserId().equals("admin")) {%>
	<button class="write" onclick="javascript:location.href='views/krboard/KrInsertView.jsp'">글 작성하기</button>
	<% } %>
	
	<div class="page_wrap">
	<div class="page_nation">
		<% if(currentPage <= 1) { %>
			<a class="arrow pprev" onclick="startPage()"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow pprev" href="/jmtgr/krlist?page=1"></a>
		<% } %>
		<!-- 이전 그룹으로 이동 처리 -->
		<% if((currentPage - 10) < startPage && startPage != 1){ %>
			<a class="arrow prev" href="/jmtgr/krlist?page=<%= currentPage = startPage-10 %>"></a>
		<% }else { %>
			<a class="arrow prev" onclick="startPage()"></a>
			&nbsp;
		<% }%>
		
		<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage) { %>
			<a class="active" onclick="nowPage()"><%= p %></a>
			<% }else{ %>
			<a href="/jmtgr/krlist?page=<%= p %>"><%= p %></a>
		<% }} %>
		<!-- 다음 그룹으로 이동 처리 -->
		<% if(endPage != maxPage ){ %>
			<a class="arrow next" href="/jmtgr/krlist?page=<%= currentPage = startPage + 10 %>"></a>
		<% }else{ %>
			<a class="arrow next" onclick="endPage()"></a>			
			&nbsp;
		<% } %>
		
		<!-- 맨끝 페이지로 이동 처리 -->
		<% if(currentPage >= maxPage){ %>
			<a class="arrow nnext" onclick="endPage()"></a>
			&nbsp;
		<% }else{ %>
			<a class="arrow nnext" href="/jmtgr/krlist?page=<%= maxPage %>"></a>
		<% } %>	
	</div>	
</div>

<%@ include file="../common/footer.jsp" %>
</body>
</html>