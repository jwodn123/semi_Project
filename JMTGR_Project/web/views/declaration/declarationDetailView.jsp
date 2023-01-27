<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="declaration.model.vo.Declaration"%>
<%
   Declaration declaration = (Declaration)request.getAttribute("declaration");
   int currentPage =((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
</head>
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
	border-collapse: collapse;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

td {
	border-bottom: none;
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
	position: absolute;
	left: 1200px;
	top: 700px;
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
	border: 2px solid red;
	position: relative;
	left: -62px;
	top: 70px;
}
h2.ddtail {
	position: absolute;
	left: 300px;
	font-size: 30px;
}

</style>
<body>
<%@ include file = "../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

	<div style="padding-top: 20px;"></div>
	<h2 class="ddtail"><%= declaration.getDeNo() %> 번 신고 상세보기</h2>
		<hr class="qhr">
	<div style="padding-top: 210px;"></div>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr align="left"><th>제 목 :</th><td><%= declaration.getDeTitle() %></td></tr>
<tr align="left"><th>작성자 :</th><td><%= declaration.getUserId() %></td></tr>
<tr align="left"><th>등록 날짜 : </th><td><%= declaration.getDeBoardDate() %></td></tr>
<tr align="left">
   <th>첨부 파일 : </th>
   <td><% if(declaration.getDeOriginalFileName() != null ) { %>
        <a href="/jmtgr/dfdown?ofile=<%= declaration.getDeOriginalFileName()%>
                &rfile=<%= declaration.getDeRenameFileName()%>"><%= declaration.getDeOriginalFileName() %></a>
        <% } else { %> 
        &nbsp;
        <% } %> 
   </td>
  </tr>
<tr align="left"><th>내 용 :</th><td><%= declaration.getDeContent() %></td></tr>
</table>

<%-- <% if(loginMember != null)  {
         if(loginMember.getUserid().equals(declaration.getUserId())) { %>
         <a href="/jmtgr/dupview?dnum=<%= declaration.getDeNo() %>&page=<%= currentPage %>">수정 하기</a>
         &nbsp; &nbsp;
         <a href="/jmtgr/ddel?dnum=<%= declaration.getDeNo() %>&rfile=<%= declaration.getDeRenameFileName() %>">삭제 하기</a>
<% } else if(loginMember.getUserid().equals("admin")) { >
      <a href="/jmtgr/views/declaration/declarationReplyForm.jsp?dnum=<%=  declaration.getDeNo()  %>&page=<%= currentPage %>">답변 달기</a>
   <%  } %>
<%  } %> --%>
<div class="bu">
		<a href="/jmtgr/dupview?dnum=<%= declaration.getDeNo() %>&page=<%= currentPage %>">수정 하기</a> &nbsp;
		<a href="/jmtgr/ddel?dnum=<%= declaration.getDeNo() %>&rfile=<%= declaration.getDeRenameFileName() %>">삭제 하기</a> &nbsp;
		<button onclick="javascript:location.href='/jmtgr/dlist?page=<%= currentPage %>'">목록 보기</button>
	</div>
	 	<div style="padding-top: 200px;"></div>
		<%@ include file="../common/footer.jsp"%>
</body>
</html>