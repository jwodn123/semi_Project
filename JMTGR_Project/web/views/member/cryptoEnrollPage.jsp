<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<script type="text/javascript" src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
<script>

function validate(){
  //유효성 검사 코드 작성함
  //서버 컨트롤러로 전송할 값들이 요구한 조건을 모두 만족하였는지 검사함

  //암호와 암호 확인이 일치하는지 확인함
  var pwdValue = document.getElementById("userpwd").value;
  var pwdValue2 = document.getElementById("userpwd2").value;

  if(pwdValue !== pwdValue2){
    alert("비밀번호가 다릅니다. 다시 입력해주세요.");
    document.getElementById("userpwd").select();
    return false; //전송 취소함
  }

  return true; //전송함
}

function usernoNo(){
  var userno = document.getElementById("userno").value;
  userno = userno.replace(/(?<=.{6})./gi, "*");

  if(userno != null){
    document.getElementById("userno").select();
    return true;
  }
  return false;
}



//아이디 중복 체크 확인을 위한 ajax 실행 처리용 홤수
function dupIdCheck(){
  $.ajax({
    url: "/jmtgr/idchk", type: "post",
    data: {userid: $("#userid").val()},
    success: function(data){
      if(data == "ok"){
        alert("사용 가능한 아이디입니다.");
        $("#userpwd").focus();

      }else{
        alert("이미 사용중인 아이디입니다.\n다시 입력하세요.");
        $("#userid").select();
      }
    },
    error: function(jqXHR, textstatus, errorthrown){
      console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
    }
  });

  return false; //클릭 이벤트가 전달되어서 submit 이 동작되지 않게 함
}

 </script>
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
  overflow: auto;
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
  max-width: 70%;
  display: flex;
  flex-direction: column;
  height: 80%;
}

.form > #p2 {
  text-align: right;
  margin-left: 50px;
  margin-top: -37px;
  width: 150px;
}

.form > #p1 {
  text-align : left;
  margin-left: 30px;
}

.form > p > a {
  color: #000;
  font-size: 14px;
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
  margin-top: 15px;
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
input[type=submit]{
  text-align: center;
  color: #fff;
    font-weight: bold;
      font-weight: bold;
      background: linear-gradient(to right, #DD5E89 0%,#F7BB97 100%);
      width: 150px;
      height: 40px;
      font-size: 15px;
      border: none;
      margin-top: 30px;
}
input[type=submit]:hover{
  text-decoration: none;
  width: 150px;
  height: 40px;
  text-align: center;
  color: black;
  font-weight: bold;
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
</head>
<body>
  <div class="container">
    <div class="right">
    </div>
    <div class="left">
      <div class="header">
      <h2 class="animation a1"><a href="/jmtgr/index.jsp">오늘 뭐 먹지 ?</a></h2>
          <h4 class="animation a2">회원가입</h4>
           <h4 class="animation a2">* 표시는 필수 입력란입니다.</h4>
      </div>
      <div class="form">
        <form action="/jmtgr/minsert.cp" method="post">

        <input type="text" class="form-field animation a3" class="pos" id="userid" name="userid" placeholder="* 아이디" required>
        <input type="button" class="form-field animation a3" onclick="return dupIdCheck();" value="중복체크">
        <input type="password" class="form-field animation a4" id="userpwd" name="userpwd" placeholder="* 비밀번호" required>
        <input type="password" class="form-field animation a4" id="userpwd2" onchange="validate()" placeholder="* 비밀번호 재확인" required>
        <input type="text" class="form-field animation a4" name="username" placeholder="* 이름" required>
        <input type="text" class="form-field animation a4" name="userno" id="userno" placeholder="* 주민 번호" required>
        <p id="p1" class="animation a4"><input type="radio" name="gender" value="M" checked required>남자</p>
        <p id="p2" class="animation a4"><input type="radio" name="gender" value="F">여자</p>
        <input type="tel" class="form-field animation a4" name="phone" placeholder="전화 번호">
        <input type="text" class="form-field animation a4" name="address1" id="sample4_roadAddress" placeholder="주소">
        <input type="text" class="form-field animation a4" name="address2" id="sample4_postcode" placeholder="우편 번호">
        <input type="button" class="form-field animation a4" name="address" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
        <input type="text" class="form-field animation a4" name="address3" id="sample4_detailAddress" placeholder="상세주소">
        <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
            //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
            function sample4_execDaumPostcode() {
                new daum.Postcode({
                    oncomplete: function(data) {

                        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        // document.getElementById('sample4_postcode').value = data.zonecode;

                        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                        var roadAddr = data.roadAddress; // 도로명 주소 변수

                                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                        if(data.userSelectedType=="J"){
                                          document.getElementById("sample4_roadAddress").value = data.jibunAddress;
                                        }else{
                                          document.getElementById("sample4_roadAddress").value =data.address;
                                        }
                                        document.getElementById('sample4_postcode').value = data.zonecode;
                                    //    document.getElementById("sample2_roadAddress").value = data.address;
                                    //       document.getElementById("sample4_roadAddress").value = data.jibunAddress;
                                    }

                              }).open();
            }
        </script>
        <input type="email" class="form-field animation a4" name="email" placeholder="이메일">

          <input type="submit" value="회원가입">
          </form>
      </div></div>
  </div>

</body>
</html>