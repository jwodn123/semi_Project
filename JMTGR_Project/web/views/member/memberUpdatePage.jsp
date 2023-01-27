<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style>
div#updatePage{
	position: relative;
  width:440px;
  height: 500px;
  margin: auto;
  top: -45px;
}

div#updatePage th{


}

div#updatePage input[type=submit]{
	text-align: center;
	color: rgba(0,0,0,0.5);
	position: relative;
	top: 450px;
	left : 135px;
	width: 70px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	background: white;
	border: none;

}

div#updatePage input[type=submit]:hover{
	text-decoration: none;
	width: 70px;
	height: 30px;
	text-align: center;
	color: black;
	font-weight: bold;

}

div#updatePage input[type=text]{

	width:200px;
	height: 25px;
	font-size: 16px;
}

div#updatePage input[type=password]{

	width:200px;
	height: 25px;
	font-size: 16px;
}

div#updatePage input[type=tel]{
	width:200px;
	height: 25px;
	font-size: 16px;
}

div#updatePage input[type=email]{
	width:200px;
	height: 25px;
	font-size: 16px;
}
div#updatePage input[type=button]{
	position: relative;
	left:-30px;
	margin:0px;
}

div#updatePage input[type=button].a{
    text-align:center;
    position: relative;
    top: 450px;
    left: 135px;
    color: rgba(0,0,0,0.5);
    width: 100px;
    height: 30px;
    font-size: 15px;
    font-weight: bold;
    background:white;
    border: none;
}

div#updatePage input[type=button]:hover.a{
  text-decoration: none;
  color: black;
  position: relative;
  left : 135px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  border: none;

}


</style>
<script>
	function validate(){
		var pwdValue = document.getElementById("userpwd").value;
		var pwdValue2 = document.getElementById("userpwd2").value;

		if(pwdValue !== pwdValue2){
			alert("비밀번호가 다릅니다. 다시 입력해주세요.");
			document.getElementById("userpwd").select();
			return false;
		}
		return true;


	}

</script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<h1 align="center">회원 정보 수정</h1>
	<div id="updatePage">
	<br>
	<form method="post" action="/jmtgr/mupdate.cp"
		onsubmit="return validate();">
		<table id="outer" align="center" width="500" cellspacing="5"
			cellpadding="0">
			<tr>
				<th width="120">아이디</th>
				<td><input type="text" name="userid" value="<%= member.getUserId() %>" readonly></td>
			</tr>
			<tr>
				<th width="120">비밀번호</th>
				<td><input type="password" name="userpwd" id="userpwd" required placeholder="비밀번호를 입력하시오"></td>
			</tr>
			<tr>
			<th>비밀번호 재입력</th>
			<td><input type="password" id="userpwd2" placeholder="비밀번호 재입력" required>
			</tr>
			<tr>
				<th width="120">이름</th>
				<td><input type="text" name="username" value="<%= member.getUserName() %>" readonly></td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td><input type="text" name="userno"
					value="<%=member.getUserNo()%>" readonly></td>
			</tr>
			<tr>
				<th>성 별</th>
				<td>
					<%
						if (member.getGender().equals("M")) {
					%> <input type="radio"
					name="gender" value="M" checked>남자 &nbsp; <input
					type="radio" name="gender" value="F">여자
				</td>
				<%
					} else {
				%>
				<input type="radio" name="gender" value="M">남자 &nbsp;
				<input type="radio" name="gender" value="F" checked>여자
				</td>
				<%
					}
				%>
			</tr>
			<tr>
	<th>전화번호</th>
	<td><input type="tel" name="phone" value="<%= member.getPhone() %>"></td>
</tr>
<tr>
	<th>주소</th>
	<td><input  type="text"  name="address1" id="sample4_roadAddress"  placeholder="주소"></td>
</tr>
<tr>
	<th>우편번호</th>
	<td><input type="text" name="address2" id="sample4_postcode"  placeholder="우편번호"></td>
	<td><input type="button" name="address" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></td>

</tr>
<tr>
	<th>상세주소</th>
	<td><input type="text" name="address3" id="sample4_detailAddress"  placeholder="상세주소"></td>
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
															//		 document.getElementById("sample4_roadAddress").value = data.jibunAddress;
                              }

								        }).open();
      }
  </script>
</tr>
<tr>
	<th>이메일</th>
	<% if(member.getEmail() != null) { %>
	<td><input type="email" name="email" value="<%= member.getEmail() %>"></td>
	<% }else{ %>
		<td><input type="email" name="email"></td>

	<% } %>
</tr>


		<input type="submit" value="수정완료"> &nbsp;
		<input type="button" class="a" value="뒤로가기" onclick="history.go(-1);">
		</table>
	</form>
</div>
<hr>
	  <%@ include file="../common/footer.jsp" %>
</body>
</html>
