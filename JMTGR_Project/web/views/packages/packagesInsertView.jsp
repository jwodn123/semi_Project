<%@page import="jdk.nashorn.internal.runtime.arrays.ArrayIndex"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, packages.model.vo.Packages"%>


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
</style>
</head>
<body>
<%@ include file="../common/header.jsp"%>


	<form method="post" id="listaddform"
		action="/jmtgr/pinsert">
		<table id="outer" align="center" width="500" cellspacing="5"
			cellpadding="0">
			<tr>
				<th colspan="2">레시피 재료를 추가해주세요.</th>

			</tr>

			<tr>
				<th width="120">사용자 아이디</th>
				<td><input type="text" name="USER_ID"
					 required ></td>
			</tr>
			<tr>
				<th width="120">*인분</th>
				<td><input type="text" name="PEOPLE" 
					required></td>
			</tr>
			<tr>
				<th width="120">한식 글 번호</th>
				<td><input type="text" name="KR_BOARD_NO"
				></td>
			</tr>
			
			<tr>
				<th colspan="2"><input type="submit" value="추가하기">
			</tr>
			
		</table>

	</form>
	<div id = footer><%@ include file="../common/footer.jsp"%></div>
</body>
</html>