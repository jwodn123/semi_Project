<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <style type="text/css">
    @font-face {
    font-family: 'Bazzi';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }
        input#menuicon {
          display: none;
        }
        input#menuicon + label {
          display: block;
          margin: 30px;
          width: 30px;
          height: 20px;
          position: relative;
          cursor: pointer;
        }
        input#menuicon:checked + label {
          z-index: 2;
          left : 200px;
        }
        input#menuicon + label span {
          display: block;
          position: absolute;
          width: 100%;
          height : 5px;
          border-radius: 30px;
          background: #444444;
          transition: all .35s;
    }
      input#menuicon + label span:nth-child(1) {
        top : 0;
      }
      input#menuicon + label span:nth-child(2) {
        top : 50%;
        transform: translateY(-50%);
      }
      input#menuicon + label span:nth-child(3) {
        bottom : 0;
      }
    .sidebar {
      width: 200px;
      height: 100%;
      background: rgba( 47, 138, 241, 0);
      position: fixed;
      top : 0;
      left : -300px;
      z-index: 1;
      transition: all .35s;
      font-family: 'Bazzi';
    }
    input#menuicon:checked + label + div {
      left : 0;
    }
    .sidebar h1 {
      color: #2B2B2B;
    }
    .sidebar ul li {
      list-style: none;
      padding: 15px 10px;
      border-bottom: 1px solid #F29661;
    }
    .sidebar a{
      text-decoration: none;
      color:#7A7A7A;
    }
      </style>
  </head>
  <body>
    <input type="checkbox" id="menuicon">
     <label for="menuicon">
       <span></span>
       <span></span>
       <span></span>
     </label>
    <div class="sidebar" style="margin-top:150px;">
      <ul style="text-align:center;">        
        <li><a href="/jmtgr/index.jsp">Home</a></li>
        <li><a href="/jmtgr/krlist">한식</a></li>
        <li><a href="/jmtgr/mrlist.ss">나만의 레시피</a></li>
        <li><a href="/jmtgr/slist">맛남의 광장</a></li>
        <li><a href="/jmtgr/dlist">신고하기</a></li>
   		<li><a href="/jmtgr/qlist">문의하기</a></li>
      </ul>
    </div>
    
  </body>
</html>
