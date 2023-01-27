<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
    

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>오늘 뭐 먹지 ?</title>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <style type="text/css">
     footer{
     	text-align : center;
        background-color: #C5796D;
       	height:150px;
        font-family: 'Jua', sans-serif;
     }
     #box {
     	position : absolute;
     	left : 350px;
     }

     .logo h1{
        position: relative;
        font-size : 20px;
        width: 150px;
        left : 50px;
        top : 70px;
        color : white;
     }
     footer a {
       text-decoration: none;
       color : white;
     }
     footer ul li {
       list-style-type: circle;
       color : white;
     }
     .home {
       float: left;
       margin-left: 300px;
       margin-top: -8px;
     }

     .board {
       float: left;
       margin-left: 100px;
       margin-top: -20px;
     }
     .board li {
       margin-top: 10px;
     }

     .qna {
       float: left;
       margin-left: 100px;
       margin-top: -20px;
     }

     .qna li {
       margin-top: 10px;
     }

      </style>
  </head>
    <footer>
    <div class="logo">
	<h1>오늘 뭐 먹지?</h1>
      </div>
    <div id="box">    
      <ul class="home">
        <li><a href="/jmtgr/index.jsp"> Home </a></li>
      </ul>
      <% if(loginMember != null) { %>
      <ul class="board">
        <li><a href="/jmtgr/krlist"> 한식 </a></li>
        <li><a href="/jmtgr/mrlist.ss"> 나만의 레시피 </a></li>
        <li><a href="/jmtgr/slist"> 맛남의 광장 </a></li>
      </ul>
      <ul class="qna">
        <li><a href="/jmtgr/qlist"> 문의하기 </a></li>
        <li><a href="/jmtgr/dlist"> 신고하기 </a></li>
      </ul>
      <% } else { %>
      <ul class="board">
        <li><a onclick="javascripc:alert('로그인 후 이용해주세요.')"> 한식 </a></li>
        <li><a onclick="javascripc:alert('로그인 후 이용해주세요.')"> 나만의 레시피 </a></li>
        <li><a onclick="javascripc:alert('로그인 후 이용해주세요.')"> 맛남의 광장 </a></li>
      </ul>
      <ul class="qna">
        <li><a onclick="javascripc:alert('로그인 후 이용해주세요.')"> 문의하기 </a></li>
        <li><a onclick="javascripc:alert('로그인 후 이용해주세요.')"> 신고하기 </a></li>
      </ul>
      <%} %>
      </div>
    </footer>
</html>
