
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="msquare.model.vo.Square"%>
<%
	Square square = (Square)request.getAttribute("square");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style type="text/css">
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
    margin : 20px 10px;
    position:relative;
    left:600px;
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
input.put {
	position:relative;
	left:200px;
}
</style>
<script type="text/javascript">
	function searchAddress(){
		//창을 오픈하기 위한 함수
		popup = window.open("views/square/squareMapView.jsp", "mywin",
		"width=800,height=500,top=50,toolbar=yes,status=yes,left=230");
	}
</script>
</head>
<body>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<br>
<form action="/jmtgr/supdate" method="post" name="jw">
<input type="hidden" name="no" value="<%= square.getMs_board_no() %>">
<table class="type03">
<tr>
	<th>제목</th>
	<td><input type="text" name="title" value="<%= square.getMs_board_title() %>"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="writer" readonly value="<%= square.getUser_id() %>"></td>
</tr>
<tr>
	<th>만남의 장소</th>
	<td>
		<input type="text" size="30" name="address" value="<%= square.getAddress() %>">
		<input type="button" value="위치 재검색" onClick="searchAddress()">
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
	<th>내용</th>
	<td><textarea rows="4" cols="70" name="content"><%= square.getMs_board_content() %></textarea></td>
</tr>
<tr class="la"><th colspan="2">
<input type="submit" value="수정하기" class="put">&nbsp;
<input type="reset" value="수정취소" class="put">&nbsp;
<input type="button" value="이전페이지" onclick="javascript:history.go(-1); return false;" class="put">&nbsp;
</th>
</tr>
</table>
</form>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/footer.jsp" %> 
</body>
</html>