<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="msquare.model.vo.Square"%>
<%@ page import="java.util.ArrayList, msquare.model.vo.SquareComment" %>
<%
	Square square = (Square)request.getAttribute("square");
	ArrayList<SquareComment> com = (ArrayList<SquareComment>)request.getAttribute("comment");
	int comno = 0;
	if(request.getAttribute("comno") != null){
		comno = ((Integer)(request.getAttribute("comno"))).intValue();
	}
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
	String keyword = (String)request.getAttribute("keyword");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi1</title>
<style type="text/css">
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
    margin : 20px 10px;
    position:relative;
    left:30%;
}
table.type03 th {
    width: 147px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;

}
table.type03 td {
    width: 500px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
table.type04 {
    border-collapse: collapse;
    text-align: left;
    line-height: 0.8;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
    margin : 20px 10px;
    position:relative;
    left:30%;
}
table.type04 th {
    width: 114px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
table.type04 td {
    width: 151px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
button {
	position:relative;
	left:190px;
}
.bu {
	position:relative;
	left:25px;
}
.bu1 {
	position:relative;
	left:330px;
}

table.type05 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
    margin : 20px 10px;
    position:relative;
    left:32%;
}
table.type05 th {
    width: 33px;
    padding: 15px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;

}
table.type05 td {
    width: 70px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<br>
<table class="type03"> 
<tr>
	<th>제목</th>
	<td><%= square.getMs_board_title() %></td>
</tr>
<tr>
	<th>작성자</th><td><%= square.getUser_id() %></td>
</tr>
<tr>
	<th>등록날짜</th><td><%= square.getMs_board_date() %></td>
</tr>
<tr>
	<th>만남의 장소</th><td><%= square.getAddress() %>
	<div id="map" style="width:100%;height:350px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5342f07f03dac4df0ffedfffd23fa3ac&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('<%= square.getAddress() %>', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:2px 0;"> <%= square.getAddress() %> </div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>
	</td>
</tr>
<tr>
	<th>내용</th><td><%= square.getMs_board_content() %></td>
</tr>
<tr><th colspan="2">
<% if(loginMember.getUserId().equals(square.getUser_id())){ //글 작성자 상세보기%> 
	<button onclick="javascript:location.href='/jmtgr/spmove?squareno=<%= square.getMs_board_no() %>'">수정페이지로 이동</button>&nbsp;
	<button onclick="javascript:location.href='/jmtgr/sdel?squareno=<%= square.getMs_board_no() %>'">삭제하기</button>&nbsp;
	<button onclick="javascript:location.href='/jmtgr/slist?page=<%= currentPage %>'">목록</button>
<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">목록</button>
<% } else { %>
	<%-- <button onclick="javascript:location.href=/jmtgr/slist?page=<%= currentPage %>">목록</button> --%>
<%} %>
<% }else if(loginMember.getUserId() == "admin") { //관리자 상세보기 %>
	<button onclick="javascript:location.href='/jmtgr/sdel?squareno=<%= square.getMs_board_no() %>';">삭제하기</button>&nbsp;
	<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">목록</button>
<% } else { %>
	<button onclick="javascript:location.href=/jmtgr/slist?page=<%= currentPage %>">목록</button>
<%} %>
<% }else{ //비작성자 상세보기 %>
	<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">목록</button>
<% } else { %>
	<button class="bu1" onclick="javascript:location.href='/jmtgr/slist?page=<%= currentPage %>'">목록</button>
<%}} %>
</th></tr>
</table>

<!-- 댓글 -->
<table class="type04">
<% for(SquareComment sco : com) {%>
<tr>
	<td>작성자</td>
	<td><%= sco.getUser_id() %></td>
	<td>작성날짜</td>
	<td><%= sco.getComm_date() %></td>
</tr>
<%if(sco.getUser_id().equals(loginMember.getUserId()) && sco.getComm_no() == comno){%>
<%-- <% if(sco.getUser_id() != null && sco.getComm_no() == comno){ %> --%>
<tr>
<form action="/jmtgr/comupdate">
<input type="hidden" name="squareno" value="<%= sco.getMs_board_no() %>">
<input type="hidden" name="comno" value="<%= comno %>">
<td id="reply">댓글 내용</td><td colspan="2"><textarea rows="3
" cols="34" name="comcontent"><%= sco.getComm_content() %></textarea></td>
<td><input type="submit" value="등록">
<input type="button" onclick="javascript:history.go(-1)" value="취소"></td>
</form>
</tr>
<%}else if(sco.getUser_id().equals(loginMember.getUserId())) {%>
<%-- <% }else if(sco.getUser_id() != null) {%>  --%>
<tr>
	<td id="reply">댓글 내용</td><td colspan="2"><%= sco.getComm_content() %></td>
	<td width="50"><button class="bu" onclick="javascript:location.href='/jmtgr/sdetail?squareno=<%= sco.getMs_board_no() %>&comno=<%= sco.getComm_no() %>'">수정</button>
	<button class="bu" onclick="javascript:location.href='/jmtgr/comdelete?comno=<%= sco.getComm_no() %>&squareno=<%= sco.getMs_board_no() %>'">삭제</button> </td>
</tr>
<% }else if(loginMember.getUserId().equals("admin")){ %>
<tr>
	<td id="reply">댓글 내용</td><td colspan="2"><%= sco.getComm_content() %></td>	
	<button class="bu" onclick="javascript:location.href='/jmtgr/comdelete?comno=<%= sco.getComm_no() %>&squareno=<%= sco.getMs_board_no() %>'">삭제</button> </td>
</tr>
<% } else { %>
<tr>
	<td width="100">댓글 내용</td><td colspan="2"><%= sco.getComm_content() %></td>
	<td> &nbsp; </td>
</tr>
<%}}%>
</table>
<br><br>

<form action="/jmtgr/cominsert?squareno=<%= square.getMs_board_no() %>">
<input type="hidden" name="squareno" value="<%= square.getMs_board_no() %>">
<input type="hidden" name="userid" value="<%= loginMember.getUserId() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table class="type05"> 
<tr>
	<th>댓글</th>
	<td><textarea rows="3" cols="60" name="comcontent"></textarea></td>
	<td><input type="submit" value="댓글 등록" style="width:70px;height:60px;"></td>
</tr>
</table>
</form>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>