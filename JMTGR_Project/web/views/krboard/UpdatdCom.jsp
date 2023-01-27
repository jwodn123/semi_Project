<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="korea_recipe_board.model.vo.KRBoard"%>
<%@ page import="java.util.ArrayList, korea_recipe_board.model.vo.Comment" %>
<%
	KRBoard krb = (KRBoard)request.getAttribute("kb");
	ArrayList<Comment> com = (ArrayList<Comment>)request.getAttribute("comment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style>
	table {
		text-align : center;
	}
</style>
<script type="text/javascript" >
	
</script>
</head>

<body>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<hr>
<h1 align="center"><%= krb.getKrBoardTitle() %></h1>

<table align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="red">
<tr><th>글 번호</th><td colspan="3"><%= krb.getKrBoardNo() %></td> </tr>
<tr><th>작성자</th><td colspan="3"><%= loginMember.getUserId() %></td> </tr>
<tr><th>이름</th><td colspan="3"><%= krb.getKrBoardTitle() %></td> </tr>
<tr><th>요리방법</th><td colspan="3"><%= krb.getCookName() %></td> </tr>

<tr>
<th colspan="4">댓글</th></tr>

<% for(Comment co : com) {%>
<tr>
<td>작성자</td><td><%= co.getUserId() %></td><td>작성날짜</td><td><%= co.getCommDate() %></td>
</tr>
<% if(co.getUserId().equals(loginMember.getUserId())) {%>
<tr>
<td id="reply">댓글 내용</td><td colspan="2"><%= co.getCommContent() %></td>
<td><button onclick="javascript:location.href='/jmtgr/comdelete?cono=<%= co.getCommNo() %>&krno=<%= co.getKrBoardNo() %>'">댓글 삭제</button> <br>
<td><button onclick="javascript:location.href='views/krboard/UpdateCom.jsp?krno=<%= co.getKrBoardNo() %>&cono=<%= co.getCommNo() %>'"></button></td>
</td>
</tr>
<% } else { %>
<tr>
<td>댓글 내용</td><td colspan="3"><%= co.getCommContent() %></td>
</tr>
<%}}%>

<tr>
<th colspan="4">
<button onclick="javascript:location.href='/jmtgr/krupview?krno=<%= krb.getKrBoardNo() %>'"> 글 수정</button>
<button onclick="javascript:location.href='/jmtgr/krdelete?krno=<%= krb.getKrBoardNo() %>'">글 삭제</button>
<button onclick="javascript:location.href='/jmtgr/krlist'"> 목록으로 </button>
</th> </tr>
</table>
<br>
<form action="/jmtgr/cominsert.en?krno=<%= krb.getKrBoardNo() %>">
<input type="hidden" name="krno" value="<%= krb.getKrBoardNo() %>">
<input type="hidden" name="userid" value="<%= loginMember.getUserId() %>">
<table align="center" width="1000" border="1" cellspacing="0" cellpadding="5" bordercolor="hotpink">
<tr><th>댓글 달기 </th><td><textarea rows="1" cols="50" name="comcontent"></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="댓글 등록" style="width:200px;"></th></tr>
</table>
</form>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
