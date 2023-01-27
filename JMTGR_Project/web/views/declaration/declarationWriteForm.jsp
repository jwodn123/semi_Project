<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
 <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style type="text/css">
    body {
     font-family: 'Jua', sans-serif;
    }

    h1.dwrite {
    font-family: 'Jua', sans-serif;
    position : absolute;
    left : 300px;
    color : red;
    }
 
    table,tr,th,td {
     border-collapse : collapse;
     border-left : none;
     border-right : none;
     border-bottom : none;
     border-top : none;
   }
   
   
   td{
     border-bottom : none;
   }
   
   input {
     border : none;
     background : none;
     font-family: 'Jua', sans-serif;
   }
   textarea {
     border : 2px solid black;
     font-family: 'Jua', sans-serif;
   }
   .button {
     position:relative;
     left : 300px;
	 top: 150px;
     font-size : 60px;
   }
   
   hr.qhr {
   width : 220px;
   border : 2px solid red;
   position: relative;
   left : -62px;
   top : 70px;
}
   
</style>
</head>
<body>
<%@ include file = "../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

     <div style= "padding-top : 20px;"></div>
<h1 class="dwrite"> 신고 작성</h1>
<hr class="qhr">
 <div style= "padding-top : 100px;"></div>
<form action="/jmtgr/dinsert" method="post" enctype="multipart/form-data" >
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr align="left"><th>제 목 :</th><td><input type="text" name="title" size="50"></td></tr>
<tr align="left"><th>파일 선택 : </th>
   <td><input type="file"name="ofile">
   </td></tr>
  <tr align="left"><th>비밀번호 :</th><td><input type="text" name="pwd" size="10"></td></tr>
<tr align="left"><th>내 용 : </th><td><textarea rows="5" cols="50" name="content"></textarea></td></tr>
<tr><th colspan="2">
<div class="button">
<input type="submit" value="등록하기">
<input type="reset" value="작성취소"> 
<input type="button" value="목록보기" onclick="javascript:history.go(-1); return false;">
</div>
</th></tr>
</table>
</form>
	<div style="padding-top: 200px;"></div>
<%@ include file = "../common/footer.jsp" %>
</body>
</html>
