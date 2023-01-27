<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
%>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
     <title>오늘 뭐 먹지?</title>
    <style type="text/css">
    @font-face {
font-family: 'S-CoreDream-4Regular';
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
font-weight: normal;
font-style: normal;
}

@font-face {
font-family: 'Bazzi';
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff');
font-weight: normal;
font-style: normal;
}


    body {
      margin : 0;
      padding: 0;
    }

    header {
      font-family: 'S-CoreDream-4Regular';
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      padding: 0;
      z-index: 10000;
      -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
      -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
      box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
      transition: all 0.2s ease-in-out;
      height: 105px;
      background-color: #ffffff;
    }
    header h1#logo {
      font-size: 35px;
      font-family: 'Bazzi';
      position: absolute;
      top : 5px;
      margin-left : 80px;
      color : black;
    }
    header ul#menubar1 {
      list-style: none;
      margin-left: 320px;
      position:absolute;
      font-size: 14px;
      top : -5px;
    }
    header ul#menubar1 li {
      float: left;
      height: 30px;
      margin-right: 5px;
      padding: 0;
    }
    header ul#menubar1 li a {
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      margin: 0;
      padding-top: 5px;
    }
    header ul#menubar1 li a:hover{
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      text-shadow: 1px 1px 2px #FFC19E;
      margin: 0;
      padding-top: 5px;
    }
    header ul#menubar2 {
      list-style: none;
      margin-left: 1300px;
      position: absolute;
      font-size: 14px;
      top : -5px;
    }
    header ul#menubar2 li {
      float: left;
      height: 30px;
      margin-right: 5px;
      padding: 0;
    }
    header ul#menubar2 li a {
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      margin: 0;
      padding-top: 5px;
    }
    header ul#menubar2 li a:hover{
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      margin: 0;
      text-shadow: 1px 1px 2px #FFC19E;
      padding-top: 5px;
    }
    
    
    header ul#menubar3 {
      list-style: none;
      margin-left: 1200px;
      position: absolute;
      font-size: 14px;
      top : -5px;
    }
    header ul#menubar3 li {
      float: left;
      height: 30px;
      margin-right: 5px;
      padding: 0;
    }
    header ul#menubar3 li a {
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      margin: 0;
      padding-top: 5px;
    }
    header ul#menubar3 li a:hover{
      text-decoration: none;
      width: 120px;
      height: 30px;
      display: block;
      text-align: center;
      color: black;
      font-weight: bold;
      margin: 0;
      text-shadow: 1px 1px 2px #FFC19E;
      padding-top: 5px;
    }

    #submenu {
    position: absolute;
    list-style: none;
    opacity: 0;
    visibility: hidden;
    transition: all 0.15s ease-in;
    margin-top: 17px;
    width: 1200px;
    height: 150px;
    font-size: 14px;
  }

  #submenu > li {
    padding: 16px 28px;
  }

  #submenu > li >  a {
    color: rgba(255,255,255,0.6);
    text-decoration: none;
  }

  #menubar1 > li:hover #submenu {
  opacity: 1;
  visibility: visible;
}

#submenu > li >  a:hover {
 text-decoration: underline;
}

    hr {
       border: solid 1px black;
       width: 79%;
       margin-left : 360px;
       position: absolute;
       top : 40px;
}

    </style>
    </head>
    <body>
    <header>
    <a href="/jmtgr/index.jsp"><h1 id="logo">오늘 뭐 먹지 ?</h1></a>
    <hr align="center">
    <ul id="menubar1">
    <li><a href="/jmtgr/krlist">한식</a>
       <ul id="submenu">
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=밥"> 밥 </a></li>
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=국물"> 국 </a></li>
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=면"> 면 </a></li>
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=찜"> 찜 </a></li>
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=기타"> 기타 </a></li>
         <li><a href="/jmtgr/krsearchT.en?searchKeyword=cook&keyword=후식"> 디저트 </a></li>
       </ul>
    </li>
    <% if(loginMember != null) {%>
    <li><a href="/jmtgr/mrlist.ss">나만의 레시피</a></li>
    <li><a href="/jmtgr/slist">맛남의 광장</a></li>
    <% }else { %>
    <li><a onclick="alert('로그인 후 이용 가능합니다.')">나만의 레시피</a></li>
    <li><a onclick="alert('로그인 후 이용 가능합니다.')">맛남의 광장</a></li>
    <%} %>
    </ul>
    <% if(loginMember != null && loginMember.getUserId().equals("admin")) { %>
    <ul id="menubar2">
    <li><a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId()%>">내정보 보기</a></li>
    <li><a href="/jmtgr/mlist">회원 관리</a></li>
    <li><a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId() %>"><%= loginMember.getUserName() %>님 </a></li>
    <li><a href="/jmtgr/logout">로그아웃</a></li>
    </ul>
    <%  %>
    <% } else if(loginMember != null) {%>    
    <ul id="menubar3">
    <li><a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId()%>">내정보 보기</a></li>
    <li><a href="/jmtgr/userboard?userid=<%= loginMember.getUserId() %>">내가 쓴 글</a></li>
    <li><a href="/jmtgr/dlist">신고하기</a></li>
    <li><a href="/jmtgr/qlist">문의하기</a></li>
    <li><a href="/jmtgr/logout">로그아웃</a></li>
    </ul>
    <%} else { %>
    <ul id="menubar2">
    <li><a href="/jmtgr/views/member/cryptoEnrollPage.jsp">회원가입</a></li>
    <li><a href="/jmtgr/views/member/loginPage.jsp">로그인</a></li>
    <li><a href="/jmtgr/views/member/memberSearchId.jsp">아이디 찾기</a></li>
    <li><a href="/jmtgr/views/member/memberSearchPwd.jsp">비밀 번호 찾기</a></li>
    </ul>
    <% } %>
    </header>
    <div style= "padding-top : 150px;"></div>
    </body>
    </html>
