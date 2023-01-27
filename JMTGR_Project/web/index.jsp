<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>오늘 뭐 먹지 ?</title>
<link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
  <header>  
  <nav class="food">
  <% if(loginMember != null) { %>
    <a href="/jmtgr/krlist">한식 레시피</a>
    <a href="/jmtgr/mrlist.ss">나만의 레시피</a>
    <a href="/jmtgr/slist">맛남의 광장</a>
    <%} else { %>
    <a href="/jmtgr/krlist">한식 레시피</a>
    <a onclick="alert('로그인 후 이용해주세요.')">나만의 레시피</a>
    <a onclick="alert('로그인 후 이용해주세요.')">맛남의 광장</a>
    <%} %>    
    <div id="indicator1"></div>
  </nav>
  <% if(loginMember != null && loginMember.getUserId().equals("admin")) { %>
	  <nav class="login">
	    <a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId() %>">내 정보 보기</a>
	    <a href="/jmtgr/mlist"> 회원 관리</a>
	    <a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId() %>"><%= loginMember.getUserName() %>님 &nbsp;</a>	
	    <a href="/jmtgr/logout">로그 아웃</a>
	    <div id="indicator"></div>
	  </nav>	  
 <% } else if( loginMember != null) { %>
	   <nav class="login">
	    <a href="/jmtgr/userboard?userid=<%= loginMember.getUserId() %>">내가 쓴 글</a>
	    <a href="/jmtgr/pselect?userid=<%= loginMember.getUserId() %>">장바구니</a>
	    <a href="/jmtgr/myinfo?userid=<%= loginMember.getUserId() %>"><%= loginMember.getUserName() %>님 &nbsp;</a>	
	    <a href="/jmtgr/logout">로그 아웃</a>
	    <div id="indicator"></div>
	  </nav>
	  
  <%} else{ %>
	  <nav class="login">
	    <a href="/jmtgr/views/member/cryptoEnrollPage.jsp">회원 가입</a>
	    <a href="/jmtgr/views/member/loginPage.jsp">로그인</a>
	    <a href="/jmtgr/views/member/memberSearchId.jsp">아이디 찾기</a>
	    <a href="/jmtgr/views/member/memberSearchPwd.jsp">비밀번호 찾기</a>
	    <div id="indicator"></div>
	  </nav>
  <% } %>
     <div style= "padding-top : 300px;"></div>
       <div class="main" align="center">
         <img class="mySlides" src="resources/images/korea.jpg">
         <img class="mySlides" src="resources/images/갈비찜 메인.jpg">
         <img class="mySlides" src="resources/images/한식3.jpg">
         <div class="logo">오늘 뭐 먹지 ?<br>
              <div class="locon">배는 고픈데 매일 먹는 음식은 식상하고<br>
              혼자 먹기엔 외로운 사람들은 위해
             </div>
         </div>
       </div>
       <script>
       var myIndex = 0;
       carousel();

       function carousel() {
           var i;
           var x = document.getElementsByClassName("mySlides");
           for (i = 0; i < x.length; i++) {
              x[i].style.display = "none";
           }
           myIndex++;
           if (myIndex > x.length) {myIndex = 1}
           x[myIndex-1].style.display = "block";
           setTimeout(carousel, 3000);
       }
       </script>
</header>
       <div style= "padding-top : 100px;"></div>
<h3 align="center">Menu Info</h3>
  <div class="wrapper">
    <div class="one">
        <h1 class="title">
                  밥 <br>
          <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=밥&searchKeyword=cook'"> VIEW </button>
          </h1>
    </div>
    <div class="two">
        <h1 class="title"> 국 <br>
            <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=국&searchKeyword=cook'"> VIEW </button>
        </h1>
    </div>
    <div class="three">
        <h1 class="title"> 면 <br>
           <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=면&searchKeyword=cook'"> VIEW </button>
        </h1>
    </div>
    
    <div class="four">
        <h1 class="title"> 볶음 / 튀김 <br>
           <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=기타&searchKeyword=cook'"> VIEW </button>
        </h1>
    </div>
   
    <div class="five">
       <h1 class="title"> 찜 <br>
       <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=찜&searchKeyword=cook'"> VIEW </button>
       </h1>
    </div>
 		<div class="six">
       <h1 class="title"> 디저트 <br>
       <button class="view" onclick="javascript:location.href='/jmtgr/krsearchT.en?keyword=후식&searchKeyword=cook'"> VIEW </button>
       </h1>
    </div>
  </div>
  <div style= "padding-top : 300px;"></div>
   <div class="wrapper2">
      <div class="myrecipe"></div>
        <div class="content">나만의 레시피
         <hr class="col">
          <div class="cont1">
             본인 만 알고 있는 레시피를 자랑하거나<br>
             평범한 레시피 보다는 새로운 레시피를 알고 싶다면 ...<br>
             <a href="/jmtgr/mrlist.ss">나만의 레시피 가기</a>
          </div>
        </div>
        <div class="content2">맛남의 광장
         <hr class="col2">
          <div class="cont2">
              요즘 외롭고 쓸쓸하다<br>
              새로운 만남을 원한다면 ...<br>
              <a href="/jmtgr/slist">만남의 광장 가기</a>
          </div>
        </div>
      <div class="mysquare"></div>
   </div>
     <div style= "padding-top : 300px;"></div>
     <%@ include file="views/common/footer.jsp" %>
</body>
</html>
