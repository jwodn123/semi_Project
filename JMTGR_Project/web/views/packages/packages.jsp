<%@page import="jdk.nashorn.internal.runtime.arrays.ArrayIndex"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, packages.model.vo.Packages, packages.model.vo.PackagesResult, packages.model.vo.SubPackage"%>

<%
	ArrayList<PackagesResult> rlist = (ArrayList<PackagesResult>) request.getAttribute("list");
	ArrayList<PackagesResult> rlist2 = (ArrayList<PackagesResult>) request.getAttribute("list2");
	ArrayList<SubPackage> mlist = new ArrayList<SubPackage>();
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  var win=null;
  function printIt(printThis)  {
    win = window.open();
    self.focus();
    win.document.open();
    win.document.write('<'+'html'+'><'+'head'+'><'+'style'+'>');
    win.document.write('body, td { font-family: Verdana; font-size: 10pt;}');
    win.document.write('<'+'/'+'style'+'><'+'/'+'head'+'><'+'body'+'>');
    win.document.write(printThis);
    win.document.write('<'+'/'+'body'+'><'+'/'+'html'+'>');
    win.document.close();
    win.print();
    win.close();
  }
</script>
<meta charset="UTF-8">
<title>오늘 뭐 먹지</title>
<style type="text/css">

#footer{
 width:100%;
   height:100px;
   position:absolute;
   bottom:0;

  text-align: center;
  color: white;
}

#printme button{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;


}

#printme {
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	margin: auto;
	width: 800px;
	border: 1px solid skyblue;
	text-align: center;


}

#printme table {
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;


}

#printme input non:not{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	width: 300px;

}

#printme input{
	border: 1px solid skyblue;
	background-color: rgba(0,0,0,0);
	color: skyblue;
	padding: 5px;
	

}


</style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
	<div id="printme">

		<h2 align="center">구매용 재료 리스트</h2>
		<br>


		<%
			for (PackagesResult p1 : rlist) {

				mlist = new ArrayList<SubPackage>(p1.getSubpagelist());
		%>



		<table align="center" border="1" cellspacing="0" cellpadding="6"
			width="800">


			<tr>
				<th>글 번호</th>
				<th>재료 이름</th>
				<th>그램(g)</th>
				<th>* 인분</th>
				<th>Total 그램(g)</th>
				<th>네이버 쇼핑 검색</th>
				


			</tr>

			<%
				for (SubPackage p2 : mlist) {
			%>




			
			<tr>

				<td><%=p1.getKrBoardNo()%> 번 관리자 재료</td>
				<td><%=p2.getMaName()%></td>
				<td><%=p2.getGram()%> (g)</td>
				<td><%=p2.getPeople()%></td>
				<td><%=p2.getGram() * p2.getPeople()%>(g)</td>
				<td>
					<button
						onclick="javascript:location.href='https://search.shopping.naver.com/search/all?query=<%=p2.getMaName()%>'">
						검색</button>
				</td>
					
			
			
				
			</tr>
			
			<tr>
					
			<%
				}
			%>
				<td colspan ="6">
					
					<button
						onclick="javascript:location.href='/jmtgr/ilistdelete?userid=<%=loginMember.getUserId()%>&krboardno=<%=p1.getKrBoardNo()%>'">
					<%=p1.getKrBoardNo()%>번 관리자용 재료삭제
					</button>


				</td>
			</tr>


		</table>
		<%
			}
		%>
		<br>
		
		
		<%
			for (PackagesResult p1 : rlist2) {

				mlist = new ArrayList<SubPackage>(p1.getSubpagelist());
		%>



		<table align="center" border="1" cellspacing="0" cellpadding="6"
			width="800">


			<tr>
				<th>글 번호</th>
				<th>재료 이름</th>
				<th>그램(g)</th>
				<th>* 인분</th>
				<th>Total 그램(g)</th>
				<th>네이버 쇼핑 검색</th>
				


			</tr>

			<%
				for (SubPackage p2 : mlist) {
			%>




			
			<tr>

				<td><%=p1.getMrBoardNo()%> 번 나만의 레시피 재료</td>
				<td><%=p2.getMaName()%></td>
				<td><%=p2.getGram()%> (g)</td>
				<td><%=p2.getPeople()%></td>
				<td><%=p2.getGram() * p2.getPeople()%>(g)</td>
				<td>
					<button
						onclick="javascript:location.href='https://search.shopping.naver.com/search/all?query=<%=p2.getMaName()%>'">
						검색</button>
				</td>
					
			
			
				
			</tr>
			
			<tr>
					
			<%
				}
			%>
				<td colspan ="6">
					
					<button
						onclick="javascript:location.href='/jmtgr/plistmrdelete?userid=<%=loginMember.getUserId()%>&mrboardno=<%=p1.getMrBoardNo()%>'">
					<%=p1.getMrBoardNo()%>번 나만의 레시피 재료삭제
					</button>


				</td>
			</tr>


		</table>
		<%
			}
		%>
		
		
		
		
		
		
		<div align="center">
			<button onclick="javascript:history.go(-1)">뒤로가기</button>
			<button
				onclick="javascript:printIt(document.getElementById('printme').innerHTML)">프린트
				하기</button>
			<p>
		</div>

	</div>






<div id = footer><%@ include file="../common/footer.jsp"%></div>
</body>
</html>