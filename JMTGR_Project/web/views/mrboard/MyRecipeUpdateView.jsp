<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="my_recipe_board.model.vo.* , java.util.*"%>
<%
	MyRecipe mr = (MyRecipe)request.getAttribute("mr");
	ArrayList<MrContent> mrclist = (ArrayList<MrContent>)request.getAttribute("mrclist");
	int i = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<script type="text/javascript"
	src="/jmtgr/resources/js/jquery-3.5.1.min.js"> </script>
<script type="text/javascript">
function addRow(){
	var content = document.getElementById('mrcontent');
	var row = content.insertRow(mrcontent.rows.length);
	var row2 = content.insertRow(mrcontent.rows.length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell5 = row.insertCell(2);
	
	var i = (mrcontent.rows.length-4) / 2;
	
	row2.innerHTML = '<th>첨부 파일</th>'+'<td><input type="file" name="upfile'+i+'"></td><td><input type="text" name="countB" value="'+i+'" readonly></td>';
	row.innerHTML ='<th>글 내용</th>'+'<td colspan="2"><textarea rows="5" cols="50" name="content'+i+'"></textarea></td>';
}
 

function deletecon(){
	
	var table = document.getElementById('mrcontent');
	if(table.rows.length < 9) return;
	table.deleteRow(table.rows.length-1)
	table.deleteRow(table.rows.length-1)

}

function readCount() {
	var table = getElementById('mrcontent');
	var i = krcontent.rows.length;
	var count = krco
	
}
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/sidebar.jsp" %>
	
	<h1>게시글 수정</h1>
<form action="/jmtgr/mrupdate.en" method="post" enctype="multipart/form-data">
<input type="hidden" name="mrno" value="<%= mr.getMrBoardNo() %>">
<input type="hidden" name="ofile" value="<%= mr.getMrboardoriginFile() %>">
<input type="hidden" name="rfile" value="<%= mr.getMrboardrenameFile() %>">
<input type="hidden" name="originRow" value="<%= mrclist.size() %>">
<table align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="red" id="mrcontent">

<tr><th>글 번호</th><td colspan="2"><input type="text" name="mrno" value=<%= mr.getMrBoardNo() %> readonly></td> </tr>
<tr><th>글 제목</th><td colspan="2"><input type="text" name="title" value=<%= mr.getMrBoardTitle() %>></td></tr>

<tr><th>작성자</th><td colspan="2"><input type="text" name="userid" readonly value="<%= mr.getUserId() %>"></td></tr>
 <% if(mr.getMrboardoriginFile() != null) {%>
<tr><th>대표 사진</th><td colspan="2"><input type="file" name="thumbnail"><%= mr.getMrboardoriginFile() %> <br> <input type="checkbox" name="deleteFlag<%=i %>" value="yes"> 파일삭제</td></tr> 
<% } else { %>
<tr><th>대표 사진</th><td colspan="2">이미지 없음 <input type="file" name="thumbnail"></td></tr>

<%} %> 
<% for(MrContent mrc : mrclist) {i++;  %>
<input type="hidden" name="ofile<%= i %>" value="<%= mrc.getMrOriginFile() %>">
<input type="hidden" name="rfile<%= i %>" value="<%= mrc.getMrRenamefile() %>">
<tr><th>글 내용</th><td colspan="2"><textarea rows="5" cols="50" name="content<%=i%>"><%= mrc.getMrBoardContent() %></textarea></td> </tr>
<% if(mrc.getMrOriginFile() != null) {%>
<tr><th>첨부 파일</th><td><input type="file" name="upfile<%=i%>"><%= mrc.getMrOriginFile() %> <br> <input type="checkbox" name="deleteFlag<%=i %>" value="yes"> 파일삭제</td><td class="count"><input type="text" name="countA" value="<%= mrc.getMrCount() %>" id="count" readonly></td></tr>
<% } else { %>
<tr><th>첨부 파일</th><td><input type="file" name="upfile<%=i%>"> 파일 없음 </td><td class="count"><input type="text" name="countA" value="<%= mrc.getMrCount() %>" id="count" readonly></td></tr>
<% }} %>

</table>
<input type="submit" value="글 수정">
<input type="reset" value="취소">
<input type="button" onclick="javascript:history.go(-1);" value="뒤로가기">
</form>
<button id="new" onclick="addRow(<%= mrclist.size() %>)"> 내용 추가 </button>
<button id="delete" onclick="deletecon()">삭제</button>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>