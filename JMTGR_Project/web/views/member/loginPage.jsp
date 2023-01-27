<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<script type="text/javascript" src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>

<style>
* { box-sizing: border-box; }
@import url('https://fonts.googleapis.com/css?family=Rubik:400,500&display=swap');


body {
  font-family: 'Rubik', sans-serif;
}

.container {
  display: flex;
  height: 100vh;
}

.left {
  overflow: hidden;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  animation-name: left;
  animation-duration: 1s;
  animation-fill-mode: both;
  animation-delay: 1s;
}

.right {
  flex: 1;
  background-color: black;
  transition: 1s;
  background-image: url('resources/images/01.png');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}



.header > h2 {
  margin: 0;
  color: #F7BB97;
}

.header > h4 {
  margin-top: 10px;
  font-weight: normal;
  font-size: 15px;
  color: rgba(0,0,0,.4);
}

.form {
  max-width: 80%;
  display: flex;
  flex-direction: column;
}

.form > #p2 {
  text-align: right;
  margin-left: 90px;
  margin-top: -37px;
  margin-right : 40px;
}

.form > #p1 {
  text-align : left;
  margin-left: 40px;
  width : 150px;
}

.form > #p3 {
  text-align : left;
  margin-left: 40%;
}

.form > p > a {
  color: #000;
  font-size: 14px;
  text-decoration : none;
}

.form-field {
  height: 46px;
  padding: 0 16px;
  border: 2px solid #ddd;
  border-radius: 4px;
  font-family: 'Rubik', sans-serif;
  outline: 0;
  transition: .2s;
  margin-top: 20px;
}

.form-field:focus {
  border-color: #F7BB97;
}

.form > button {
  padding: 12px 10px;
  border: 0;
  background: linear-gradient(to right, #DD5E89 0%,#F7BB97 100%);
  border-radius: 3px;
  margin-top: 10px;
  color: #fff;
  letter-spacing: 1px;
  font-family: 'Rubik', sans-serif;
}

.animation {
  animation-name: move;
  animation-duration: .4s;
  animation-fill-mode: both;
  animation-delay: 2s;
}

.a1 {
  animation-delay: 2s;
}

.a2 {
  animation-delay: 2.1s;
}

.a3 {
  animation-delay: 2.2s;
}

.a4 {
  animation-delay: 2.3s;
}

.a5 {
  animation-delay: 2.4s;
}

.a6 {
  animation-delay: 2.5s;
}

@keyframes move {
  0% {
    opacity: 0;
    visibility: hidden;
    transform: translateY(-40px);
  }

  100% {
    opacity: 1;
    visibility: visible;
    transform: translateY(0);
  }
}

@keyframes left {
  0% {
    opacity: 0;
    width: 0;
  }

  100% {
    opacity: 1;
    padding: 20px 40px;
    width: 440px;
  }
  
}

a {
	text-decoration : none;
	color : #F7BB97;
	}
</style>
<script>
	function loginPage(){
		var userid = document.getElementById('userid').value;
		var userpwd = document.getElementById('userpwd').value;
		location.href="/jmtgr/login.cp?userid="+userid+"&userpwd="+userpwd;		
	}
</script>
</head>
<body>
  <div class="container">
    <div class="left">
      <div class="header">
        <h2 class="animation a1"><a href="/jmtgr/index.jsp">오늘 뭐 먹지 ?</a></h2>
        <h4 class="animation a2">로그인 하세요</h4>
      </div>
      <div class="form">
        <input type="text" class="form-field animation a3" class="pos" name="userid" id="userid" placeholder="아이디">
        <input type="password" class="form-field animation a4" name="userpwd" id="userpwd" placeholder="비밀번호">        
        <p id="p1" class="animation a5"><a href="/jmtgr/views/member/memberSearchId.jsp">아이디 찾기</a></p>
        <p id="p2" class="animation a5"><a href="/jmtgr/views/member/memberSearchPwd.jsp">비밀번호 찾기</a></p>
        <p id="p3" class="animation a5"><a href="/jmtgr/views/member/cryptoEnrollPage.jsp">회원가입</a></p>
        <button class="animation a6" onclick="loginPage()">로그인</button>
      </div>
    </div>
    <div class="right"></div>
  </div>

</body>
</html>
