<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member, java.util.ArrayList" %>
 <%
 	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지 ?</title>
<style type="text/css">
form.sform{
	display : none;
	background : lightgray;
}
div#adminView{
  height: 161px;
}

</style>
<script type="text/javascript" src="/jmtgr/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//작성된 이벤트 처리 코드는 실행 대기 상태가 됨
	//jQuery("선택자").실행시킬메소드(전달값...);
	$("input[name=item]").on("change", function(){
		//이벤트가 발생된 radio 와 연결된 폼만 보이게 하고,
		//나머지 폼은 안보이게 처리함
		$("input[name=item]").each(function(index){
			//radio 하나씩 checked 인지 확인하고
			if($(this).is(":checked")){ //해당 인덱스 순번의 radio가 체크상태이나?
				$("form.sform").eq(index).css("display", "block");
			}else{
				$("form.sform").eq(index).css("display", "none");
			}
		});
	});
});

function changeLogin(element){
	//선택한 radio의 name 속성값에서 userid 분리 추출함
	var userid = element.name.substring(8);
	console.log(userid);
	if(element.checked == true && element.value == "false"){
		console.log("로그인제한 체크함");
		location.href="/jmtgr/loginOk?userid=" + userid + "&ok=false";
	}else{
		console.log("로그인제한 해제함");
		location.href="/jmtgr/loginOk?userid=" + userid + "&ok=true";
	}
}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1 align="center">전체 회원 관리 페이지</h1>
<h2 align="center">현재 회원수 : <%= list.size() %> 명</h2>
<center>
	<button onclick="javascript:location.href='/jmtgr/mlist'">전체 보기</button>
<div id="adminView">


<br>
<!-- 항목별 검색 기능 추가 -->
<fieldset id="ss">
<legend>검색할 항목을 선택하세요.</legend>
<input type="radio" name="item" id="uid"> 회원 아이디 &nbsp;
<input type="radio" name="item" id="ulogok"> 로그인 제한 &nbsp;
</fieldset>
<!-- 검생기능 영역 끝  -->

<br>
<!-- 회원 아이디로 검색 폼  -->
<form action="/jmtgr/asearch" method="post" id="idform" class="sform">
<input type="hidden" name="action" value="id">
<fieldset>
<legend>검색할 아이디를 입력하세요.</legend>
<input type="search" name="keyword"> &nbsp;
<input type="submit" value="검색">
</fieldset>
<fieldset>
<legend>검색할 로그인 제한/기능을 선택하세요.</legend>
<input type="radio" name="keyword" value="Y" > 로그인 기능 &nbsp;
<input type="radio" name="keyword" value="N" > 로그인 제한 &nbsp;
<input type="submit" value="검색">
</fieldset>
</form>
</center>
<br>
<table align="center" border="1" cellspacing="0" cellpadding="3">
<tr>
<th>아이디</th><th>이름</th><th>주민번호</th><th>전화번호</th><th>성별</th><th>주소</th><th>우편번호</th><th>상세주소</th>
<th>이메일</th><th>로그인 제한</th>
</tr>
<% for(Member m : list){ %>
<tr>
<td><%= m.getUserId() %></td>
<td><%= m.getUserName() %></td>
<td><%= m.getUserNo() %></td>
<td><%= m.getPhone() %></td>
<td><%= (m.getGender().equals("M"))? "남자" : "여자" %></td>
<td><%= m.getAddress1() %></td>
<td><%= m.getAddress2() %></td>
<td><%= m.getAddress3() %></td>
<td><%= m.getEmail()%></td>
<td>
<% if(m.getLogin_Ok().equals("Y")){ %>
<input type="radio" name="loginok_<%= m.getUserId() %>" onchange="changeLogin(this);" value="true" checked> 가능 &nbsp;
<input type="radio" name="loginok_<%= m.getUserId() %>" onchange="changeLogin(this);" value="false"> 제한
<% }else{ %>
<input type="radio" name="loginok_<%= m.getUserId() %>" onchange="changeLogin(this);" value="true" > 가능 &nbsp;
<input type="radio" name="loginok_<%= m.getUserId() %>" onchange="changeLogin(this);" value="false" checked> 제한
<% } %>
</td>
</tr>
<% } %>
</table>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
