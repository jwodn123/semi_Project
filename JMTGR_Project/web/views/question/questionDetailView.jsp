<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="question.model.vo.Question"%>
<%@ page import="question.model.vo.Reply "%>
<%
   Question question = (Question)request.getAttribute("question");
   int currentPage =((Integer)request.getAttribute("currentPage")).intValue();
   Reply reply = null;
   if(request.getAttribute("reply") != null) {
	   reply = (Reply)request.getAttribute("reply");
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
	
<script src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
<script>
	function deleteRe(){
		$.ajax({
			url : "/jmtgr/delreply",
			type : "post",
			data : {
				qno : <%= question.getQusNo() %>
			},
		success :function(data){
			alert('삭제 되었습니다.');
			location.href="/jmtgr/qdetail?qnum="+<%= question.getQusNo() %>;
		},
		 error:function(request,status,error){
				alert("삭제 실패");
			    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	function updateAns(){
		
	}
</script>
<style type="text/css">
body {
	font-family: 'Jua', sans-serif;
}

h2.qdwrite {
	position: absolute;
	left: 300px;
	font-size: 30px;
}

 table, tr, th, td {
	/* border-collapse: collapse;
 	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none; */
	font-size : 15px;
	text-align : center;
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
	top: 100px;
	font-size: 60px;
}

hr.qhr {
	width: 220px;
	border: 2px solid red;
	position: absolute;;
	left: 40px;
	top: 240px;
}

.bu {
    position: relative;
	left: 1200px;
	top: 150px; 
}

.bu a:link {
	color: black;
	text-decoration: none;
	position: relative;
	font-size: 15px;
}

.bu a:visited {
	color: black;
	text-decoration: none;
}

.bu a:hover {
	color: black;
	text-decoration: none;
}

button {
	border: none;
	background: none;
	font-family: 'Jua', sans-serif;
	font-size: 15px;
}

hr.qhr {
	width: 220px;
	border: 2px solid black;
	position: relative;
	left: -62px;
	top: 70px;
}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<div style="padding-top: 20px;"></div>
	<h2 class="qdwrite"><%= question.getQusNo() %>
		번 문의 상세보기
	</h2>
	<hr class="qhr">
	<div style="padding-top: 210px;"></div>
	<table align="center" width="500" border="1" cellspacing="0"
		cellpadding="5">
		<tr align="left">
			<th>제 목 :</th>
			<td><%= question.getQusTitle() %></td>
		</tr>
		<tr align="left">
			<th>작성자 :</th>
			<td><%= question.getUserId() %></td>
		</tr>
		<tr align="left">
			<th>등록 날짜 :</th>
			<td><%= question.getQusBoardDate() %></td>
		</tr>
		<% if(question.getQusPwd() != null){ %>
		<tr align="left">
			<th>비밀 번호 :</th>
			<td><%= question.getQusPwd() %></td>
		</tr>
		<% } %>
		<tr align="left">
			<th>첨부 파일 :</th>
			<td>
				<% if(question.getQusOriginalFileName() != null ) { %> <a
				href="/jmtgr/qfdown?ofile=<%= question.getQusOriginalFileName()%>
                &rfile=<%= question.getQusRenameFileName()%>"><%= question.getQusOriginalFileName() %></a>
				<% } else { %> &nbsp; <% } %>
			</td>
		</tr>
		<tr align="left">
			<th>내 용 :</th>
			<td><%= question.getQusContent() %></td>
		</tr>
	</table>
	
		<table align="center" width="500" border="1" cellspacing="0" text-align="center" id="anstable">
		<tr><th width="400">답변</th><th width="100">답변 날짜</th></tr>
		<%if(reply != null) { %>
			<tr><td><%= reply.getQusReplyContent() %></td><td><%= reply.getQusReplyDate() %></td></tr>
			<%if(loginMember.getUserId().equals("admin")) { %>
			<tr><td colspan="2"><button onclick="deleteRe()">답변 삭제</button></td></tr>
			<%} %>
		<% } else { %>
		<%if(loginMember.getUserId().equals("admin")) { %>
		<form action="/jmtgr/qreply.en" method="post">
		<input type="hidden" name="replyqno" value=<%= question.getQusNo() %>>
		<tr><td colspan="2"><textarea rows="5" cols="50" name="content" id="content"></textarea></td></tr>
		<tr><td colspan="2"><input type="submit" value="답변 등록"></td></tr>	
		</form>	
		<%} else { %>
		<tr><td colspan="2">답변 미 등록</td></tr>
		<% }} %>
	</table>
	
	
	<div class="bu">
	<% if(loginMember != null && loginMember.getUserId().equals(question.getUserId()) || loginMember.getUserId().equals("admin")) {%>
		<a href="/jmtgr/qupview?qnum=<%= question.getQusNo() %>&page=<%= currentPage %>">수정 하기</a> &nbsp;
		<a href="/jmtgr/qdel?qnum=<%= question.getQusNo() %>&rfile=<%= question.getQusRenameFileName() %>">삭제 하기</a> &nbsp;
		<button onclick="javascript:location.href='/jmtgr/qlist?page=<%= currentPage %>'">목록 보기</button>
		<%} else { %>
		<button onclick="javascript:location.href='/jmtgr/qlist?page=<%= currentPage %>'">목록 보기</button>
		<%} %>
	</div>
	<div style="padding-top: 200px;"></div>
		<%@ include file="../common/footer.jsp"%>
</body>
</html>