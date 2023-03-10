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
	<th>??????</th>
	<td><%= square.getMs_board_title() %></td>
</tr>
<tr>
	<th>?????????</th><td><%= square.getUser_id() %></td>
</tr>
<tr>
	<th>????????????</th><td><%= square.getMs_board_date() %></td>
</tr>
<tr>
	<th>????????? ??????</th><td><%= square.getAddress() %>
	<div id="map" style="width:100%;height:350px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5342f07f03dac4df0ffedfffd23fa3ac&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // ????????? ????????? div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // ????????? ????????????
        level: 3 // ????????? ?????? ??????
    };  

// ????????? ???????????????    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// ??????-?????? ?????? ????????? ???????????????
var geocoder = new kakao.maps.services.Geocoder();

// ????????? ????????? ???????????????
geocoder.addressSearch('<%= square.getAddress() %>', function(result, status) {

    // ??????????????? ????????? ??????????????? 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // ??????????????? ?????? ????????? ????????? ???????????????
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // ?????????????????? ????????? ?????? ????????? ???????????????
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:2px 0;"> <%= square.getAddress() %> </div>'
        });
        infowindow.open(map, marker);

        // ????????? ????????? ??????????????? ?????? ????????? ??????????????????
        map.setCenter(coords);
    } 
});    
</script>
	</td>
</tr>
<tr>
	<th>??????</th><td><%= square.getMs_board_content() %></td>
</tr>
<tr><th colspan="2">
<% if(loginMember.getUserId().equals(square.getUser_id())){ //??? ????????? ????????????%> 
	<button onclick="javascript:location.href='/jmtgr/spmove?squareno=<%= square.getMs_board_no() %>'">?????????????????? ??????</button>&nbsp;
	<button onclick="javascript:location.href='/jmtgr/sdel?squareno=<%= square.getMs_board_no() %>'">????????????</button>&nbsp;
	<button onclick="javascript:location.href='/jmtgr/slist?page=<%= currentPage %>'">??????</button>
<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">??????</button>
<% } else { %>
	<%-- <button onclick="javascript:location.href=/jmtgr/slist?page=<%= currentPage %>">??????</button> --%>
<%} %>
<% }else if(loginMember.getUserId() == "admin") { //????????? ???????????? %>
	<button onclick="javascript:location.href='/jmtgr/sdel?squareno=<%= square.getMs_board_no() %>';">????????????</button>&nbsp;
	<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">??????</button>
<% } else { %>
	<button onclick="javascript:location.href=/jmtgr/slist?page=<%= currentPage %>">??????</button>
<%} %>
<% }else{ //???????????? ???????????? %>
	<% if(keyword != null && searchKeyword != null) {%>
	<button onclick="javascript:location.href='/jmtgr/squaresearch?keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'">??????</button>
<% } else { %>
	<button class="bu1" onclick="javascript:location.href='/jmtgr/slist?page=<%= currentPage %>'">??????</button>
<%}} %>
</th></tr>
</table>

<!-- ?????? -->
<table class="type04">
<% for(SquareComment sco : com) {%>
<tr>
	<td>?????????</td>
	<td><%= sco.getUser_id() %></td>
	<td>????????????</td>
	<td><%= sco.getComm_date() %></td>
</tr>
<%if(sco.getUser_id().equals(loginMember.getUserId()) && sco.getComm_no() == comno){%>
<%-- <% if(sco.getUser_id() != null && sco.getComm_no() == comno){ %> --%>
<tr>
<form action="/jmtgr/comupdate">
<input type="hidden" name="squareno" value="<%= sco.getMs_board_no() %>">
<input type="hidden" name="comno" value="<%= comno %>">
<td id="reply">?????? ??????</td><td colspan="2"><textarea rows="3
" cols="34" name="comcontent"><%= sco.getComm_content() %></textarea></td>
<td><input type="submit" value="??????">
<input type="button" onclick="javascript:history.go(-1)" value="??????"></td>
</form>
</tr>
<%}else if(sco.getUser_id().equals(loginMember.getUserId())) {%>
<%-- <% }else if(sco.getUser_id() != null) {%>  --%>
<tr>
	<td id="reply">?????? ??????</td><td colspan="2"><%= sco.getComm_content() %></td>
	<td width="50"><button class="bu" onclick="javascript:location.href='/jmtgr/sdetail?squareno=<%= sco.getMs_board_no() %>&comno=<%= sco.getComm_no() %>'">??????</button>
	<button class="bu" onclick="javascript:location.href='/jmtgr/comdelete?comno=<%= sco.getComm_no() %>&squareno=<%= sco.getMs_board_no() %>'">??????</button> </td>
</tr>
<% }else if(loginMember.getUserId().equals("admin")){ %>
<tr>
	<td id="reply">?????? ??????</td><td colspan="2"><%= sco.getComm_content() %></td>	
	<button class="bu" onclick="javascript:location.href='/jmtgr/comdelete?comno=<%= sco.getComm_no() %>&squareno=<%= sco.getMs_board_no() %>'">??????</button> </td>
</tr>
<% } else { %>
<tr>
	<td width="100">?????? ??????</td><td colspan="2"><%= sco.getComm_content() %></td>
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
	<th>??????</th>
	<td><textarea rows="3" cols="60" name="comcontent"></textarea></td>
	<td><input type="submit" value="?????? ??????" style="width:70px;height:60px;"></td>
</tr>
</table>
</form>
<div style= "padding-top : 100px;"></div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>