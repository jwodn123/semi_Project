<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// boardDerailView.jsp 가 보낸 파라미터 값 꺼내기
int QusNo = Integer.parseInt(request.getParameter("qnum"));
int currentPage = Integer.parseInt(request.getParameter("page"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
</head>
<body>
	<h1 align="center"><%=QusNo%>
		번 글 댓글 달기 페이지
	</h1>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<hr>
	<form action="/jmtgr/qreply" method="post">
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="5">
			<input type="hidden" name="qnum" value="<%=QusNo%>">
			<input type="hidden" name="page" value="<%=currentPage%>">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title" size="50"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" readonly value="관리자"></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="5" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기">
					&nbsp; <input type="reset" value="작성취소"> &nbsp; <input
					type="button" value="목록보기"
					onclick="javascript:location.href='/jmtgr/qlist?page=<%= currentPage %>'">목록
				</th>
			</tr>
		</table>
	</form>
</body>
</html>