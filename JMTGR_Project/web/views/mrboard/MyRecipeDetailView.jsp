<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="my_recipe_board.model.vo.* , java.util.ArrayList, recipe_matelial_list.model.vo.MaterialList"%>
<%
	MyRecipe myrecipe = (MyRecipe)request.getAttribute("myrecipe");
	ArrayList<MrContent> mrclist = (ArrayList<MrContent>)request.getAttribute("mrclist");
	ArrayList<Comment> com = (ArrayList<Comment>) request.getAttribute("comment");
	ArrayList<LH> lhlist = (ArrayList<LH>) request.getAttribute("lhlist");
	ArrayList<MaterialList> mvlist = (ArrayList<MaterialList>)request.getAttribute("mvlist");
	int[] result = ((int[]) request.getAttribute("lh"));
	
	int cono = 0;
	if (request.getAttribute("cono") != null) {
		cono = ((Integer) (request.getAttribute("cono"))).intValue();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style>
	table {
		text-align: center;
	}

	div.LHbutton {
		display: inline-block;
		postition: absolute;
		right: 800px;
	}
</style>

<script type="text/javascript">	
</script>

</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<hr>
<h1 align="center"><%= myrecipe.getMrBoardTitle() %> </h1>

<table align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="gray">
<tr> <th>글번호</th> <td colspan="3"><%=myrecipe.getMrBoardNo()%></td> </tr>
<tr> <th>작성자</th> <td colspan="3"><%=myrecipe.getUserId()%> </td> </tr>
<tr> <th>등록 날짜 </th> <td colspan="3"><%=myrecipe.getMrBoardDate()%></td> </tr>
</table>
<table align="center" width="1000" border="2" cellspacing="0" cellpadding="5" bordercolor="white">>
<% for(MrContent mrc : mrclist) {%>
<% if(mrc.getMrBoardContent() != null) { %>
<tr> <th></th> <td colspan="3"><%= mrc.getMrBoardContent()%></td> </tr>
<% } else { %>
<tr> <th></th> <td colspan="3"> </td> </tr>
<%} %>
<% if(mrc.getMrRenamefile() != null && !mrc.getMrRenamefile().equals("null") ) { %>
<tr> <th></th> <td colspan="3"><img src="resources/mrthumbnail/<%=mrc.getMrRenamefile()%>"></td> </tr>
<% } else { %>
<tr> <th></th> <td colspan="3"> </td> </tr>
<% }} %>
<tr><td colspan="4">
<% if(loginMember.getUserId().equals(myrecipe.getUserId())) { %>

<button onclick="javascript:location.href='/jmtgr/mrupview.en?mrno=<%=myrecipe.getMrBoardNo()%>'">글 수정</button>
<button onclick="javascript:location.href='/jmtgr/mrdel?mrno=<%=myrecipe.getMrBoardNo()%>'">글 삭제</button>
<button onclick="javascript:location.href='/jmtgr/mrlist.ss'"> 목록으로 </button>
	<% } else { %>
<button onclick="javascript:location.href='/jmtgr/mrlist.ss'"> 목록으로</button>
	<% } %>
</td> </tr>
</table>	
<br>

<table align="center" width= "1000" border= "2" cellspacing= "0" cellpadding = "5" bordercolor = "gray">
<tr> <th colspan="3">댓글</th> </tr>
	<% for (Comment co : com) { %>
		<tr> 
			<td>작성자</td>  <td><%=co.getUserId()%></td> 
			<td>작성날짜</td> <td><%=co.getCommDate()%></td>
		</tr>
<tr>
	<% if (co.getUserId().equals(loginMember.getUserId()) && co.getCommNo() == cono) { %>
		<form action="/jmtgr/upcom.en">
			<input type="hidden" name="mrno" value="<%= co.getMrBoardNo() %>">
			<input type="hidden" name="cono" value="<%= cono %>">
			<th id="reply">댓글 내용</th><td colspan="2"><textarea rows="1" cols="50" name="comcontent"><%= co.getCommContent() %></textarea></td>
			<td>
				<input type="submit" value="저장">
			<br>
				<input type="button" onclick="javascript:history.go(-1)" value="작성 취소">
			</td>
		</form>
	<% } else if(co.getUserId().equals(loginMember.getUserId())) {%>
			<tr>	
				<td id="reply">댓글 내용</td> <td colspan="2"><%=co.getCommContent()%></td> 
				<td><button onclick="javascript:location.href='/jmtgr/delcom?cono=<%=co.getCommNo()%>&mrno=<%=co.getMrBoardNo()%>'"> 댓글 삭제</button> <br>
					<button onclick="javascript:location.href='/jmtgr/mrdetail.ss?mrno=<%=co.getMrBoardNo()%>&cono=<%=co.getCommNo()%>'">댓글 수정</button>
			</td>
	<% } else {%>
		<td>댓글 내용</td> <td colspan="3"><%=co.getCommContent()%></td>
	<% }} %>
		</tr>
		</table>
		<br>
<form action="/jmtgr/inCom.en?mrno=<%=myrecipe.getMrBoardNo()%>">
<input type="hidden" name="mrno" value="<%=myrecipe.getMrBoardNo()%>">
<input type="hidden" name="userid" value="<%=loginMember.getUserId()%>">
<table align="center" width="1000" border="1" cellspacing="0" cellpadding="5" bordercolor="gray">
<tr><th>댓글 등록</th> <td><textarea rows="1" cols="50" name="comcontent"></textarea></td></tr>
<tr> <th colspan="2"><input type="submit" value="댓글 등록" style="width: 200px;"></th> </tr>
</table>

<h1 align = "center">
<div id="like" class="LHbutton"> 
<button type="button">
<img src="/jmtgr/resources/mrfile/like.png"  width = "40px" height = "40px "
	 onclick="javascript:location.href='/jmtgr/lh?lh=like&userid=<%=loginMember.getUserId()%>&mrno=<%=myrecipe.getMrBoardNo()%>'">좋아요<%=result[0]%>개
</button>
<div id="hate" class="LHbutton" >
<button type="button"> 
<img src="/jmtgr/resources/mrfile/hate.png"  width = "40px" height = "40px"
	onclick="javascript:location.href='/jmtgr/lh?lh=hate&userid=<%=loginMember.getUserId()%>&mrno=<%=myrecipe.getMrBoardNo()%>'">싫어요<%=result[1]%>개
</button>
</div> </div>
</h1>

</form>

<form method="post" id="addform"
		action="/jmtgr/pmrinsert?USER_ID=<%= loginMember.getUserId() %>&MR_BOARD_NO=<%= myrecipe.getMrBoardNo() %>">
		<table id="outer" align="center" width="500" cellspacing="5"
			cellpadding="0">
			<tr>
				<th colspan="2">구매용 재료를 추가해주세요.</th>

			</tr>
			
			<tr>
					<th>재료이름</th>
					<th>그램(g)</th>
					
			</tr>
			<%
					for (MaterialList m : mvlist) {
				%>
				<tr>
					<td><%=m.getMaName()%></td>
					<td><%=m.getGram()%></td>
					
					<%
						}
					%>
				</tr>
			
		
			<tr>
				<th width="120">*인분</th>
				<td><input type="number" name="PEOPLE" 
					required></td>
			</tr>
			
			
			<tr>
				<th colspan="2"><input type="submit" value="추가하기">
			</tr>
			
		</table>
		
	</form>


<hr>
<%@ include file="../common/footer.jsp" %>


</body>
</html>



































