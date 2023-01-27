<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<script type="text/javascript">

function addRow(){
	var content = document.getElementById('krcontent');
	var row = content.insertRow(krcontent.rows.length);
	var row2 = content.insertRow(krcontent.rows.length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell5 = row.insertCell(2);
	var i = (krcontent.rows.length -4) /2;
	row2.innerHTML = '<th>첨부 파일</th>'+'<td><input type="file" name="upfile'+i+'"></td><td><input type="text" name="count" value="'+i+'" readonly></td>';
	row.innerHTML ='<th>내용</th>'+'<td colspan="2"><textarea rows="5" cols="50" name="content'+i+'"></textarea></td>';
}
 

function deletecon(){
	
	var table = document.getElementById('krcontent');
	if(table.rows.length < 9) return;
	table.deleteRow(table.rows.length-1)
	table.deleteRow(table.rows.length-1)

}

function rowCount(){
	var table = document.getElementById('krcontent');
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
<h1 align="center">게시글 작성</h1>

<form action="/jmtgr/insertkr.en" method="post" enctype="multipart/form-data">

<table id="krcontent" align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="red">
<tr>
<th>글 제목</th><td colspan="2"><input type="text" name="btitle"></td></tr>
<tr><th>요리 분류</th><td colspan="2">
	밥 <input type="radio" name="cname" value="밥" >
	국물 <input type="radio" name="cname" value="국물">
	찜 <input type="radio" name="cname" value="찜"><br>
	면 <input type="radio" name="cname" value="면">
	기타 <input type="radio" name="cname" value="기타" checked>
	디저트 <input type="radio" name="cname" value="후식">
 </tr>
<tr><th>작성자</th><td colspan="2"><input type="text" name="writer" readonly value="<%= loginMember.getUserId() %>"></td></tr>
<tr><th>대표 사진</th><td colspan="2"><input type="file" name="thumbnail"></td></tr>
<tr><th>글 내용</th><td colspan="2"><textarea rows="5" cols="50" name="content1"></textarea></td> </tr>
<tr><th>첨부 파일</th><td><input type="file" name="upfile1"></td><td><input type="text" name="count" value="1"></td> </tr>
</table>
<input type="submit" value="글 저장">
<input type="reset" value="취소">
<input type="button" onclick="javascript:history.go(-1)" value="뒤로가기">
</form>
<button id="new" onclick="addRow()"> 내용 추가 </button>
<button id="delete" onclick="deletecon()">삭제</button>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>


        


