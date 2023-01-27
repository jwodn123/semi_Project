<%@page import="jdk.nashorn.internal.runtime.arrays.ArrayIndex"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, packages.model.vo.Packages"%>

<%
	ArrayList<Packages> list = (ArrayList<Packages>)request.getAttribute("list");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/sidebar.jsp" %>

<h2 align="center">구매용 재료 리스트</h2>
	<br>
<table align="center" border="1" cellspacing="0" cellpadding="6"
		width="800">


		<tr>
			<th>글 번호</th>
			<th>게시글 이름</th>
		</tr>

	<%			
		for (Packages p : list) {

					%>
		
		

		<tr>
			<td><%=p.getKrBoardNo()%> 번 재료 </td>
			<a href="/jmtgr/pselect?krtitle=<%=p.getKrBoardTitle()%>"><%=p.getKrBoardTitle()%></a>
			
		</tr>
	<%}%>

		
	</table>
	
	<br>
	<div align = "center">
			<button onclick="javascript:history.go(-1)"> 뒤로가기 </button>	
	</div>
</body>
</html>