<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="question.model.vo.Question, java.util.ArrayList, java.sql.Date, question.model.vo.Reply"%>
<%
  ArrayList<Question> list = (ArrayList<Question>)request.getAttribute("list");
  ArrayList<Reply> rlist = (ArrayList<Reply>)request.getAttribute("replylist");
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
    <meta charset="utf-8">
    <title>오늘 뭐 먹지 ?</title>
    <script src="https://kit.fontawesome.com/262e649fa4.js" crossorigin="anonymous"></script>
    <style type="text/css">
    @font-face {
    font-family: 'Bazzi';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }
    @font-face {
font-family: 'S-CoreDream-4Regular';
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
font-weight: normal;
font-style: normal;
}
         .qhead{
            background : #353535;
            width: 100%;
            height: 300px;
            margin-top: 50px;
         }
         .qtitle {
             font-family: 'Bazzi';
             color: white;
             position: absolute;
             left : 850px;
             top : 230px;
             font-size: 50px;
         }
         .top3{
           font-family: 'S-CoreDream-4Regular';
         }
         .top3title{
           font-family: 'Bazzi';
           font-size: 40px;
           margin-left: 80px;
         }
         .questionmain {
           font-family: 'S-CoreDream-4Regular';
         }
         button.qwrite {
         	font-size: 25px;
         	border: none;
         	background: none;
         	position: relative;
         	left: 1620px;
         	top: 60px;
         	outline: 0;
          font-family: 'Bazzi';
         }
         button.qwrite:hover {
         	color: white;
         	text-shadow: 2px 2px 2px black;
         }
         .questionlist {
           font-family: 'Bazzi';
           font-size: 40px;
           margin-left: 80px;
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
         
         table {
         	text-align : center;
         }
    </style>
    <script type="text/javascript">
    function movePage(){
      	location.href = "/jmtgr/views/question/questionWriteForm.jsp";
    }
    function checkpwd(){
    	if(loginMember.getQusPwd().equals(question.getQusPwd())) {
    	   location.href = "/jmtgr/views/question/questionDetailView.jsp";
    	} else {
    	   alert('비밀번호가 틀렸습니다');
    	   history.go(-1);
    	}
    }
    </script>
    <script type="text/javascript"
    	src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
    $(function(){

      $.ajax({
        url: "/jmtgr/qtop3",
        type: "get",
        dataType: "json",
        success: function(data){
           console.log("success : " + data);

           //object ==> string 으로 변환
           var jsonStr = JSON.stringify(data);
           //string ==> json 객체로 바꿈
           var json = JSON.parse(jsonStr);

           var values = "";
           for(var i in json.list){
              values += "<tr><td>" + json.list[i].qnum
                + "</td><td><a href='/jmtgr/qdetail?qnum="
                + json.list[i].qnum + "'>"
                + decodeURIComponent(json.list[i].qtitle).replace(/\+/gi, " ")
                + "</a></td><td>" + json.list[i].rcount + "</td></tr>";
           } //for in

           $("#toplist").html($("#toplist").html() + values);
        },
         error:  function(jqXHR, textstatus, errorthrown){
           console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
         }

     });

    }); //document.ready
    </script>
  </head>
  <body>
    	<%@ include file="../common/header.jsp"%>
    	<%@ include file="../common/sidebar.jsp" %>
    	
    <div class="qhead">
       <h1 class="qtitle">문의하기</h1>
    </div>
    <div style= "padding-top : 100px;"></div>
    <seciton>
  	<div class="top3">
  		<h1 class="top3title">자주 찾는 문의</h1>
  		<table id="toplist" width="800px" border="1" align="center">
  			<tr align="center">
  				<th>번호</th>
  				<th>제목</th>
  				<th>조회수</th>
  			</tr>
  		</table>
  	</div>
  	</section>
        <div style= "padding-top : 50px;"></div>
        <h1 class="questionlist"> 문의 목록 </h1>
    <div class="questionmain">
      <table class="tb" align="center" border="1" width="1500px">
        <thead>
          <tr>
            <th class="cols">비밀번호</th>
            <th class="cols">글 번호</th>
            <th class="cols">제목</th>
            <th class="cols">작성자</th>
            <th class="cols">작성 날짜</th>
            <th class="cols">내용</th>
          </tr>
        </thead>
        <% for(Question q : list) { i = 0;%>
    		<tr>
    			<td align="center" width="60">
    				<% if(q.getQusPwd() != null){ %>
    				 <i class="fas fa-lock"></i>    				
    				<% }else { %> <i class="fas fa-lock-open"></i> <% } %>
    			</td>
    			
    			<td align="center" width="60"><%= q.getQusNo() %></td>
    			<% if(loginMember.getUserId().equals("admin")){ %>
    			<td align="center" width="250"><a href="/jmtgr/qdetail?qnum=<%= q.getQusNo() %>&page=<%= currentPage  %>"><%= q.getQusTitle() %></a></td>    			
    			<%} else { %>
					 <% if(q.getQusPwd() != null) { %>
    					<td align="center" width="250"><a href="/jmtgr/views/question/Pwd.jsp?qnum=<%= q.getQusNo() %>&page=<%= currentPage  %>"><%= q.getQusTitle() %></a></td>
					<% } else { %>
						<td align="center" width="250"><a href="/jmtgr/qdetail?qnum=<%= q.getQusNo() %>&page=<%= currentPage  %>"><%= q.getQusTitle() %></a></td>
						<% }} %>
						
    			<td align="center" width="120"><%= q.getUserId() %></td>
    			<td align="center" width="140"><%= q.getQusBoardDate() %></td>
    			<td align="center" width="280"><%= q.getQusContent() %></td>
    				
    				
   					<%} %>
    		</tr>
    	</table>
    </div>
    <% if(loginMember == null) { %>
    <button class="qwrite" onclick="alert('로그인 후 이용해주세요')">문의 작성</button>    
    <%} else { %>
    <button class="qwrite" onclick="movePage()">문의 작성</button>
    <% } %>
    <div style="padding-top: 100px;"></div>
    <!-- 페이징 처리 -->
    <div class="page_wrap">
      <div class="page_nation">
        <% if(currentPage <= 1) { %>
        <a class="arrow pprev" href="#"></a> &nbsp;
        <% }else{ %>
        <a class="arrow pprev" href="/jmtgr/qlist?page=1"></a>
        <% } %>
        <!-- 이전 그룹으로 이동 처리 -->
        <% if((currentPage - 1) >= startPage){ %>
        <a class="arrow prev" href="/jmtgr/qlist?page=<%= currentPage - 1 %>"></a>
        <% }else{ %>
        <a class="arrow prev" href="#"></a> &nbsp;
        <% } %>
        <!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력 처리 -->
        <% for(int p = startPage; p <= endPage; p++){
        if(p == currentPage) { %>
        <a class="active" href="#=<%= p %>"><%= p %></a>
        <% }else{ %>
        <a href="/jmtgr/qlist?page=<%= p %>"><%= p %></a>
        <% }} %>
        <!-- 다음 그룹으로 이동 처리 -->
        <% if((currentPage + 1) <= endPage && (currentPage + 1) <= maxPage ){ %>
        <a class="arrow next" href="/jmtgr/qlist?page=<%= currentPage + 1 %>"></a>
        <% }else{ %>
        <a class="arrow next" href="#"></a> &nbsp;
        <% } %>

        <!-- 맨끝 페이지로 이동 처리 -->
        <% if(currentPage >= maxPage){ %>
        <a class="arrow nnext" href="#"></a> &nbsp;
        <% }else{ %>
        <a class="arrow nnext" href="/jmtgr/qlist?page=<%= maxPage %>"></a>
        <% } %>
      </div>
    </div>
    
    <%@ include file="../common/footer.jsp"%>
    	
  </body>
</html>
