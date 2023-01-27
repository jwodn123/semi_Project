<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="korea_recipe_board.model.vo.KRBoard"%>
<%@ page import="java.util.ArrayList, korea_recipe_board.model.vo.Comment, korea_recipe_board.model.vo.KRContent, recipe_matelial_list.model.vo.MaterialList" %>
<%
	KRBoard krb = (KRBoard)request.getAttribute("kb");
	ArrayList<MaterialList> mvlist = (ArrayList<MaterialList>)request.getAttribute("mvlist");
	ArrayList<KRContent> krclist = (ArrayList<KRContent>)request.getAttribute("krclist");
	ArrayList<Comment> com = (ArrayList<Comment>)request.getAttribute("comment");
	int cono = 0;
	if(request.getAttribute("cono") != null){
	cono = ((Integer)(request.getAttribute("cono"))).intValue();
	} 
	int currentPage = ((Integer)(request.getAttribute("page"))).intValue();
	String keyword = (String)request.getAttribute("keyword");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 뭐 먹지?</title>
<style>
	table {
		text-align : center;
	}
	img {
	  width : 600px;
	  hight : 600px;
	}
	
	
    
        @font-face {
    font-family: 'S-CoreDream-4Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }

    @font-face {
    font-family: 'Bazzi';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }

        body {
          margin : 0;
          padding: 0;
              font-family: 'S-CoreDream-4Regular';
        }

        header {
          font-family: 'S-CoreDream-4Regular';
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          padding: 0;
          z-index: 10000;
          -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
          -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
          box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
          transition: all 0.2s ease-in-out;
          height: 105px;
          background-color: white;
        }
        header h1#logo {
          font-size: 35px;
          font-family: 'Bazzi';
          position: absolute;
          top : 5px;
          margin-left : 80px;
          color : black;
        }
        header ul#menubar1 {
          list-style: none;
          margin-left: 320px;
          position:absolute;
          font-size: 14px;
          top : -5px;
        }
        header ul#menubar1 li {
          float: left;
          height: 30px;
          margin-right: 5px;
          padding: 0;
        }
        header ul#menubar1 li a {
          text-decoration: none;
          width: 120px;
          height: 30px;
          display: block;
          text-align: center;
          color: black;
          font-weight: bold;
          margin: 0;
          padding-top: 5px;
        }
        header ul#menubar1 li a:hover{
          text-decoration: none;
          width: 120px;
          height: 30px;
          display: block;
          text-align: center;
          color: black;
          font-weight: bold;
          text-shadow: 1px 1px 2px #FFC19E;
          margin: 0;
          padding-top: 5px;
        }
        header ul#menubar2 {
          list-style: none;
          margin-left: 1300px;
          position: absolute;
          font-size: 14px;
          top : -5px;
        }
        header ul#menubar2 li {
          float: left;
          height: 30px;
          margin-right: 5px;
          padding: 0;
        }
        header ul#menubar2 li a {
          text-decoration: none;
          width: 120px;
          height: 30px;
          display: block;
          text-align: center;
          color: black;
          font-weight: bold;
          margin: 0;
          padding-top: 5px;
        }
        header ul#menubar2 li a:hover{
          text-decoration: none;
          width: 120px;
          height: 30px;
          display: block;
          text-align: center;
          color: black;
          font-weight: bold;
          margin: 0;
          text-shadow: 1px 1px 2px #FFC19E;
          padding-top: 5px;
        }

        #submenu {
        position: absolute;
        list-style: none;
        opacity: 0;
        visibility: hidden;
        transition: all 0.15s ease-in;
        margin-top: 17px;
        width: 1200px;
        height: 150px;
        font-size: 14px;
      }

      #submenu > li {
        padding: 16px 28px;
      }

      #submenu > li >  a {
        color: rgba(255,255,255,0.6);
        text-decoration: none;
      }

      #menubar1 > li:hover #submenu {
      opacity: 1;
      visibility: visible;
    }

    #submenu > li >  a:hover {
     text-decoration: underline;
    }

        hr {
           border: solid 1px black;
           width: 79%;
           margin-left : 360px;
           position: absolute;
           top : 40px;
    }
    
    .krhead {
    	position : relative;
    	left : 23%;
    	width:1000px;
    }
    .krhead img{
      width: 800px;
      height: 500px;
    }
    .krhead {
      font-family: 'S-CoreDream-4Regular';
    }
    
    .krinfo{
    width : 900px;
    position : relative;
    left : 20%;
    font-family: 'S-CoreDream-4Regular';
    }
    
    .krt {
      height: 50px;
    }
    .krt2{
      height: 50px;
    }

    #addform{
      position: fixed;
      left : 75%;
      top : 150px;

    }
    table {
      text-align : center;
    }

    input {
      background: none;
      border: none;
      font-family: 'S-CoreDream-4Regular';
    }
    .conp{
      position: absolute;
      left : 475px;
      top : 1300px;
    }
    
    div#comment {
    	padding : 0;
    	margin : 0;
    	margin-top : 30px;
    	margin-bottom : 30px;
    	width : 800px;
    	position : relative;
    	left : 25%;
    }
    
    div#insertcom {
    	padding : 0;
    	margin : 0;
    	margin-top : 30px;
    	margin-bottom : 30px;
    	width : 800px;
    	position : relative;
    	left : 25%;
    }
    </style>

</head>

<body>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<hr>
<div style= "padding-top : 50px;"></div>
     <div class="krhead">
       <table align="center" width="1000" border="0" cellspacing="0" cellpadding="5">
       <%if(krb.getRenameFile() != null && !krb.getRenameFile().equals("null")) {%>
       <tr><td><img src="resources/thumbnail/<%= krb.getRenameFile() %>"></td></tr>
       <% } else { %>
       <tr><td>이미지 없음</td></tr>
       <%} %>
       <tr><th class="krt"><%= krb.getKrBoardTitle() %></th></tr>
       <tr><th class="krt2"><%= krb.getCookName() %></th></tr>
       <tr><th colspan="1"><%= krb.getAdminId() %></th><td>조회수 <%= krb.getReadCount()+1 %></td> </tr>
       </table>
     </div>
    <div style= "padding-top : 30px;"></div>
     <div class="krinfo">
         <table align="center" width="1000" border="0" cellspacing="0" cellpadding="5">
         <% for(KRContent krc : krclist) {%>
         <% if(krc.getKrBoardContent() != null) {%>
         <tr><th><%= krc.getKrBoardContent() %></th></tr>
         <%} else { %>
         <tr><th>글 내용</th></tr>
         <%} %>
         <% if(krc.getKrRenameFile() != null && !krc.getKrRenameFile().equals("null")) { %>
         <tr><th><img src="resources/thumbnail/<%= krc.getKrRenameFile() %>"></th></tr>
         <% } else { %>
         <tr><th>첨부 파일</th></tr>             
         <%}} %>     
         <tr><td>    
         <% if(loginMember.getUserId().equals(krb.getAdminId())) {%>
         <button onclick="javascript:location.href='/jmtgr/krupview?krno=<%= krb.getKrBoardNo() %>'"> 글 수정 </button>
         <button onclick="javascript:location.href='/jmtgr/krdelete?krno=<%= krb.getKrBoardNo() %>'"> 글 삭제 </button>
         <% if(keyword != null && searchKeyword !=null) {%>
         <button onclick="javascript:location.href='/jmtgr/krsearchT.en?page=<%= currentPage %>&keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'"> 목록으로</button>
         <%} else { %>
         <button onclick="javascript:location.href='/jmtgr/krlist?page=<%= currentPage %>'"> 목록으로</button>
         <% } %>
         <% }else { %>
         <% if(keyword != null && searchKeyword !=null) {%>
         <button onclick="javascript:location.href='/jmtgr/krsearchT.en?page=<%= currentPage %>&keyword=<%= keyword %>&searchKeyword=<%= searchKeyword %>'"> 목록으로</button>
         <%} else { %>
         <button onclick="javascript:location.href='/jmtgr/krlist?page=<%= currentPage %>'"> 목록으로</button>
         <%}} %>
         </td></tr>
          </table>
                 </div>
         <div id="comment">
         <table align="center" width="600" border="1" cellspacing="0" cellpadding="5">
         <tr align="center"><th colspan="4">댓글</th></tr>
         <% for(Comment co : com) { %>
         <tr>
         <td width="450"><%= co.getUserId() %></td><td><%= co.getCommDate() %></td>
         </tr>
         <tr>
         <% if(co.getUserId().equals(loginMember.getUserId()) && co.getCommNo() == cono) {%>
         <form action="/jmtgr/comupdate.en">
         <input type="hidden" name="krno" value="<%= co.getKrBoardNo() %>">
         <input type="hidden" name="cono" value="<%= cono %>">
         <input type="hidden" name="page" value="<%= currentPage %>">
         <th id="reply">댓글 내용</th><td colspan="2"><textarea rows="1" cols="50" name="comcontent"><%= co.getCommContent() %></textarea></td>
         <td><input type="submit" value="저장"><br>
         <input type="button" onclick="javascript:history.go(-1)" value="취소"></td>
         </form>
         <% } else if(co.getUserId().equals(loginMember.getUserId())) {%>
         <td id="reply"><%= co.getCommContent() %></td>
         <td><button onclick="javascript:location.href='/jmtgr/krcomdelete?cono=<%= co.getCommNo() %>&krno=<%= co.getKrBoardNo() %>&page=<%= currentPage %>'">댓글 삭제</button>
         <button onclick="javascript:location.href='/jmtgr/krdetail.ss?krno=<%= co.getKrBoardNo() %>&cono=<%= co.getCommNo() %>&page=<%= currentPage %>'">댓글 수정</button>
         </td>
         <% } else { %>
         <td><%= co.getCommContent() %></td>
         <%}}%>
         </tr>    
         </table>
         </div>
         
         <div id="insertcom">
         <table align="center" width="600" border="1" cellspacing="0" cellpadding="5">              
         <form action="/jmtgr/krcominsert.en" method="post">
         <input type="hidden" name="krno" value="<%= krb.getKrBoardNo() %>">
         <input type="hidden" name="userid" value="<%= loginMember.getUserId() %>">
         <input type="hidden" name="page" value="<%= currentPage %>">
         <tr><th>댓글 달기 </th><td><textarea rows="1" cols="50" name="comcontent"></textarea></td></tr>
         <tr><th colspan="2"><input type="submit" value="댓글 저장" style="width:200px;"></th></tr>
         </form>
         </table>
         </div>

         <form method="post" id="addform" action="/jmtgr/pinsert?USER_ID=<%= loginMember.getUserId() %>&KR_BOARD_NO=<%= krb.getKrBoardNo() %>">
         <table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
         <tr><th colspan="2">구매용 재료를 추가해주세요.</th></tr>
         <tr><th>재료이름</th><th>그램(g)</th></tr>
         <% for (MaterialList m : mvlist) { %>
         <tr><td><%=m.getMaName()%></td><td><%=m.getGram()%></td>
         <% }	%></tr>
         <tr><th width="120">*인분</th><td><input type="number" name="PEOPLE" required></td></tr>
         <tr><th colspan="2"><input type="submit" value="재료 저장"></tr>
         </table>
         </form>
        <div style="padding-top:100px;"></div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>















