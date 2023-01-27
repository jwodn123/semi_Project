<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="question.model.vo.Question"%>
<%
   Question question = (Question)request.getAttribute("question");
   int currentPage = ((Integer)request.getAttribute("page")).intValue();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap">
<style type="text/css">
body {
    font-family: 'Jua', sans-serif;
}
hr.qhr {
	width: 220px;
	border: 2px solid black;
	position: absolute;;
	left: -60px;
	top: 240px;
}

h1.qwrite {
	position: absolute;
	left: 300px;
}

.bu {
    border: none;
	background: none;
	font-family: 'Jua', sans-serif;
    position: relative;
	left: 1200px;
	top: 150px;  
	font-size: 15px;
}

</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
<h1 class="qwrite"><%= question.getQusNo() %>번  수정 하기</h1>
	<hr class="qhr">
		<div style="padding-top: 210px;"></div>
<form action="/jmtgr/qoriginup" method="post" enctype="multipart/form-data" >
<input type="hidden" name="qnum" value="<%= question.getQusNo() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<input type="hidden" name="ofile" value="<%= question.getQusOriginalFileName() %>">
<input type="hidden" name="rfile" vlaue="<%= question.getQusRenameFileName() %>">
<table align="center" width="500" border="0" cellspacing="0" cellpadding="5">
<tr align="left"><th width="120">제 목 :</th><td><input type="text" name="title" size="40" value="<%= question.getQusTitle()%>"></td></tr>
<tr align="left"><th>작성자 :</th><td><input type="text" name="writer" readonly value="<%= question.getUserId() %>"></td></tr>
<tr align="left">
   <th>파일 선택 : </th>
   <td>
   <% if(question.getQusOriginalFileName() != null) { %>
      <%= question.getQusOriginalFileName() %>&nbsp;
      <input type="checkbox" name="delflag" value="yes"> 파일삭제
      <br>
   <% } %>
   <input type="file"name="upfile">
   </td>
  </tr>
<tr align="left"><th>내 용 :</th><td><textarea rows="5" cols="50" name="content"><%= question.getQusContent() %></textarea></td></tr>
</table>
   <input type="submit" value="수정하기" class="bu"> &nbsp;
   <input type="reset" value="작성취소" class="bu"> &nbsp;
   <input type="button" value="이전페이지" class="bu" onclick="javascript:history.go(-1); return false;">
</form>
	<div style="padding-top: 200px;"></div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>