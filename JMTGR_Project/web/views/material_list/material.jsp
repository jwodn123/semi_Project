<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, korea_recipe_board.model.vo.KRBoard, recipe_matelial_list.model.vo.Material, recipe_matelial_list.model.vo.MaterialList"%>
<%
	ArrayList<Material> list = (ArrayList<Material>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지</title>
<style type="text/css">
#footer{
 width:100%;
   height:100px;
   position:absolute;
   bottom:0;

  text-align: center;
  color: white;
}


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
	position:relative;

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
	<h1 align="center">관리자 재료 추가용 화면</h1>
	<div align="center">
	<button onclick="javascript:location.href='/jmtgr/adilist'">재료
		전체 보기</button>
	</div>

	<table align="center" border="1" cellspacing="0" cellpadding="3"
		width="300">
		<tr>
			<th>재료 번호</th>
			<th>재료 이름</th>
			<th>삭제</th>

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
			<td><button
					onclick="javascript:location.href='/jmtgr/adidelete?mano=<%= m.getMaNo() %>'">
					삭제</button></td>
			<%
					}
					}
				%>


		</tr>
	</table>
	<form method="post" id="addform"
		action="/jmtgr/adinsertingre">
		<table id="outer" align="center" width="500" cellspacing="5"
			cellpadding="0">
			<tr>
				<th colspan="2">레시피 재료를 추가해주세요.</th>

			</tr>

			<tr>
				<th width="120">재료 번호</th>
				<td><input type="text" name="mano"></td>
			</tr>
			<tr>
				<th width="120">재료 이름</th>
				<td><input type="text" name="maname"></td>
			</tr>
			

			<tr>
				<th colspan="2"><input type="submit" value="추가하기">
			</tr>
		</table>

	</form>
	<div align = "center">
			<button onclick="javascript:history.go(-1)"> 뒤로가기 </button>	
		</div>
	
	
	</div>
	
</body>
</html>