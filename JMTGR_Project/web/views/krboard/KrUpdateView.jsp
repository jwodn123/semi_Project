<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="korea_recipe_board.model.vo.KRBoard, korea_recipe_board.model.vo.KRContent, java.util.ArrayList"%>
<%
	KRBoard krb = (KRBoard)request.getAttribute("krb");
	ArrayList<KRContent> krclist = (ArrayList<KRContent>)request.getAttribute("krclist");
	int i = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<script type="text/javascript" src="/jmtgr/resources/js/jquery-3.5.1.min.js"> </script>
<script type="text/javascript">
function addRow(rowCount){
	var content = document.getElementById('krcontent');
	var row = content.insertRow(krcontent.rows.length);
	var row2 = content.insertRow(krcontent.rows.length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell5 = row.insertCell(2);
	
	var i = (krcontent.rows.length-5) / 2;
	row2.innerHTML = '<th>첨부 파일</th>'+'<td><input type="file" name="upfile'+i+'"></td><td><input type="text" name="countB" value="'+i+'" readonly></td>';
	row.innerHTML ='<th>내용</th>'+'<td colspan="2"><textarea rows="5" cols="50" name="content'+i+'"></textarea></td>';
}
 

function deletecon(){
	
	var table = document.getElementById('krcontent');
	if(table.rows.length < 9) return;
	table.deleteRow(table.rows.length-1)
	table.deleteRow(table.rows.length-1)

}

function readCount() {
	var table = getElementById('krcontent');
	var i = krcontent.rows.length;
	var count = krco
	
}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<h1>게시글 수정</h1>
<form action="/jmtgr/krupdate.en" method="post" enctype="multipart/form-data">
<input type="hidden" name="krno" value="<%=krb.getKrBoardNo() %>">
<input type="hidden" name="ofile" value="<%= krb.getThumbNailFile() %>">
<input type="hidden" name="rfile" value="<%= krb.getRenameFile() %>">
<input type="hidden" name="originRow" value="<%= krclist.size() %>">
<table align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="red" id="krcontent">
<tr><th>글 번호</th><td colspan="2"><input type="text" name="krno" value=<%= krb.getKrBoardNo() %> readonly></td> </tr>
<tr><th>글 제목</th><td colspan="2"><input type="text" name="btitle" value=<%= krb.getKrBoardTitle() %>></td></tr>
<tr><th>요리 분류</th><td colspan="2">
	밥 <input type="radio" name="cname" value="밥">
	국물 <input type="radio" name="cname" value="국물">
	찜 <input type="radio" name="cname" value="찜"><br>
	면 <input type="radio" name="cname" value="면">
	기타 <input type="radio" name="cname" value="기타">
	디저트 <input type="radio" name="cname" value="후식">
 </tr>
 <tr><th>작성자</th><td colspan="2"><input type="text" name="writer" readonly value="<%= krb.getAdminId() %>"></td></tr>
 <% if(krb.getThumbNailFile() != null && !krb.getRenameFile().equals("null")) {%>
<tr><th>대표 사진</th><td colspan="2"><input type="file" name="thumbnail"><%= krb.getThumbNailFile() %> <br> <input type="checkbox" name="deleteFlag" value="yes"> 파일삭제</td></tr> 
<% } else { %>
<tr><th>대표 사진</th><td colspan="2">이미지 없음 <input type="file" name="thumbnail"></td></tr>
<%} %> 

<% for(KRContent krc : krclist) {i++;  %>
<input type="hidden" name="ofile<%= i %>" value="<%= krc.getKrOriginFile() %>">
<input type="hidden" name="rfile<%= i %>" value="<%= krc.getKrRenameFile() %>">
<tr><th>글 내용</th><td colspan="2"><textarea rows="5" cols="50" name="content<%=i%>"><%= krc.getKrBoardContent() %></textarea></td> </tr>
<% if(krc.getKrOriginFile() != null) {%>
<tr><th>첨부 파일</th><td><input type="file" name="upfile<%=i%>"><%= krc.getKrOriginFile() %> <br> <input type="checkbox" name="deleteFlag<%=i %>" value="yes"> 파일삭제</td><td class="count"><input type="text" name="countA" value="<%= krc.getKrCount() %>" id="count" readonly></td></tr>
<% } else { %>
<tr><th>첨부 파일</th><td><input type="file" name="upfile<%=i%>"> 파일 없음 </td><td class="count"><input type="text" name="countA" value="<%= krc.getKrCount() %>" id="count" readonly></td></tr>
<% }} %>

</table>
<input type="submit" value="글 수정">
<input type="reset" value="취소">
<input type="button" onclick="javascript:history.go(-1);" value="뒤로가기">
</form>
<button id="new" onclick="addRow(<%= krclist.size() %>)"> 내용 추가 </button>
<button id="delete" onclick="deletecon()">삭제</button>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>