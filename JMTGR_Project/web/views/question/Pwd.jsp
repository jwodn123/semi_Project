<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int qnum = Integer.parseInt(request.getParameter("qnum"));
int currentPage = Integer.parseInt(request.getParameter("page"));
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>오늘 뭐 먹지 ?</title>
    <style type="text/css">
    @font-face {
    font-family: 'Bazzi';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }
      body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #eceffc;
      font-family: 'Bazzi';
}

.btn {
  padding: 8px 20px;
  border-radius: 0;
  overflow: hidden;

  &::before {
    position: absolute;
    content: "";
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(
      120deg,
      transparent,
      var(--primary-color),
      transparent
    );
    transform: translateX(-100%);
    transition: 0.6s;
  }

  &:hover {
    background: transparent;
    box-shadow: 0 0 20px 10px hsla(204, 70%, 53%, 0.5);

    &::before {
      transform: translateX(100%);
    }
  }
}

.form-input-material {
  --input-default-border-color: white;
  --input-border-bottom-color: white;

  input {
    color: white;
  }
}

.login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px 40px;
  color: white;
  background: rgba(0, 0, 0, 0.8);
  border-radius: 10px;
  box-shadow: 0 0.4px 0.4px rgba(128, 128, 128, 0.109),
    0 1px 1px rgba(128, 128, 128, 0.155),
    0 2.1px 2.1px rgba(128, 128, 128, 0.195),
    0 4.4px 4.4px rgba(128, 128, 128, 0.241),
    0 12px 12px rgba(128, 128, 128, 0.35);
}
  h1 {
    margin: 0 0 24px 0;
  }

  .form-input-material {
    margin: 12px 0;
  }

  .btn {
    width: 100%;
    margin: 18px 0 9px 0;
  }
}


      </style>
      <script
      	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
      <script>
      function pwdCheck(){

      	$.post("/jmtgr/pwdcheck", function(data){
      		location.href="/jmtgr/qdetail?qnum=pno&page=page";
      	})
      	$.ajax({
      		url : "/jmtgr/pwdcheck",
      		type : "post",
      		data : {qno : $("#qno").val(), page : $("#page").val(), pwd : $("#qpwd").val()}

      	});
      }
      </script>
  </head>
  <body>
    <form class="login-form" action="javascript:void(0);">
  <h1>문의 하기</h1>
  <div class="form-input-material">
    <input type="password" name="password" id="password" placeholder=" " autocomplete="off" class="form-control-material" required />
    <label for="password">Password</label>
  </div>
  <button type="submit" class="okbut">확인</button>
</form>
  </body>
</html>
