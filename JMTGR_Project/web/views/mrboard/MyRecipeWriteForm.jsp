<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>

<script type="text/javascript">

function addRow() {
	var content = document.getElementById('mrcontent');
	var row = content.insertRow(mrcontent.rows.length);
	var row2 = content.insertRow(mrcontent.rows.length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var i = (mrcontent.rows.length-3)/2;
	row.innerHTML = '<th>사진</th>'+'<td><input type="file" name="upfile'+i+'"></td><td><input type="text" name="count" value="'+i+'" readonly></td>';
	row2.innerHTML ='<th>내용</th>'+'<td colspan="2"><textarea rows="5" cols="50" name="content'+i+'"></textarea></td>';
}

function deletecon() {
	var table = document.getElementById('mrcontent');
	if (table.rows.length < 9 ) return;
	table.deleteRow(table.rows.length-1)
	table.deleteRow(table.rows.length-1)	
}
	
function rowCount() {
	var table = document.getElementById('mrcontent');
	var count = table.rows.length;
	consol.log(count);
	var result = document.getElementById('rowcount');
	result.innerHTML = count;	
}

</script>

</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<hr>
<h1 align="center">나만의 레시피 작성 </h1>
<div align="center">
<form action ="/jmtgr/mrinsert.en" method="post" enctype="multipart/form-data">
<table id = "mrcontent" align="center" width ="1000" border="1" cellspacing ="0" cellpadding ="5" bordercolor = gray>
<tr><th>제목</th>		<td colspan="2"> <input type="text" name="title" > </td></tr>
<tr><th>작성자</th>	<td colspan="2"> <input type="text" name="userid" readonly value="<%= loginMember.getUserId() %>"></td></tr>
<tr><th>대표 사진</th>	<td colspan="2"> <input type="file" name="thumbnail"></td></tr>
<tr><th>내용</th> 	<td colspan="2"> <textarea rows="5" cols="50" name="content1"></textarea></td></tr>
<tr><th>사진  </th> <td><input type="file" name="upfile1"></td><td><input type="text" name="count" value="1"></td> </tr>
</table>
<hr>

<input type="submit" value="글 저장"> &nbsp;
<input type="button" value="저장 취소" onclick="javascript:history.go(-1); return false;">  &nbsp;
</form>
<button id = "new" onclick="addRow()">내용 추가</button> &nbsp; 
<button id = "delete" onclick="deletecon"> 내용삭제</button>&nbsp;&nbsp;
</div>
<hr>
</body>
</html>












