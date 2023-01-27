<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, korea_recipe_board.model.vo.KRBoard, recipe_matelial_list.model.vo.Material, recipe_matelial_list.model.vo.MaterialList"%>
<%
	KRBoard boardno = (KRBoard) session.getAttribute("boardno");
	ArrayList<Material> list = (ArrayList<Material>) request.getAttribute("list");
	ArrayList<MaterialList> mvlist = (ArrayList<MaterialList>) request.getAttribute("mvlist");
	String listaddform = (String)request.getAttribute("listaddform");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style type="text/css">

#main button{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;


}

#main {
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	margin: auto;
	width: 800px;
	border: 1px solid skyblue;
	text-align: center;


}

#main table {
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;


}

#main input non:not{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	width: 300px;

}

#main input{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	

}



</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div id = "main">
		<button onclick="javascript:location.href='/jmtgr/selectilist'">재료
			전체 보기</button>
		<button onclick="javascript:location.href='/jmtgr/views/material_list/material.jsp'">
			관리자용 재료 추가</button>	


		<br>
		<!-- 검색 기능 영역 끝 -->
		<br>
		<!--  재료이름으로 검색폼  버튼기능 삭제 했음-->
		<form action="/jmtgr/ingresearch?krno=<%=boardno.getKrBoardNo()%>" >
			<input type="hidden" name="listaddform">
			<fieldset>
				<legend> 검색할 재료명을 입력하세요.</legend>
				<input type="search" name="keyword"> &nbsp; 
				<input type="submit" value="검색" id="non">
			</fieldset>
		</form>

		<br>
		<table align="center" border="1" cellspacing="0" cellpadding="3"
			width="600">
			<tr>
				<th>재료 번호</th>
				<th>재료 이름</th>

			</tr>
			<%
				if (list != null) {
			%>
			<%
				for (Material m : list) {
			%>
			<tr>
				<td><%=m.getMaNo()%></td>
				<td><%=m.getMaName()%></td>
				<%
					}
					}
				%>


			</tr>



		</table>


		<br>

		<center>

			<br>
			<%
				if (list != null && listaddform != null) {
			%>
			<%
				for (Material m : list) {
			%>
			<form method="post"
				id = "listaddform"
				action="/jmtgr/insertingre?krno=<%=boardno.getKrBoardNo()%>"
				>
				<table id="outer" align="center" width="600" cellspacing="5"
					cellpadding="0">
					<tr>
						<th colspan="2">레시피 재료를 추가해주세요.</th>

					</tr>

					<tr>
						<th width="120">글 번호</th>
						<td><input type="text" name="r_Board_No"
							value="<%=boardno.getKrBoardNo()%>" required></td>
					</tr>
					<tr>
						<th width="120">재료 번호</th>
						<td><input type="text" name="ma_No" value="<%=m.getMaNo()%>"
							required></td>
					</tr>
					<tr>
						<th width="120">gram(g)</th>
						<td><input type="text" name="gram"></td>
					</tr>

					<tr >
						<th colspan="2" ><input type="submit" value="추가하기" id="non">
						 
					</tr>
				</table>

			</form>
			<%
				}
				}
			%>

			<%
					if (mvlist != null) {
				%>


			<table align="center" border="1" cellspacing="0" cellpadding="3"
				width="600">
				<tr>
					<th>재료이름</th>
					<th>그램(g)</th>
					<th>삭제</th>



				</tr>

				<%
					for (MaterialList m : mvlist) {
				%>
				<tr>
					<td><%=m.getMaName()%></td>
					<td><%=m.getGram()%></td>
					<td><button onclick="javascript:location.href='/jmtgr/idelete?mano=<%= m.getMaNo() %>&krno=<%= boardno.getKrBoardNo()%>'">삭제 </button></td>
					<%
						}
						
					%>


				</tr>
					


			</table>
			<%} %>
		
		<div align = "center">
			<button onclick="javascript:history.go(-1)"> 뒤로가기 </button>	
		</div>
	</div>
	
</body>
</html>