<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 350px;
}
</style>
</head>
<body>
  <div class="wrapper row3">
    <main class="container clear">
  	  <div class="row">
  	    <form method="post" action="../freeboard/delete_ok.do">
  	    <table class="table">
  	      <tr>
  	        <td class="inline">
  	          비밀번호: <input type="password" name="pwd" size=15 class="input-sm">
  	          <input type="hidden" name="no" value="${no }">s
  	        </td>
  	      </tr>
  	      <tr>
  	        <td class="text-center">
  	          <input type="submit" name="삭제" class="btn btn-sm btn-danger">
  	          <input type="button" name="취소" class="btn btn-sm btn-success" onclick="javascript:history.back()">
  	        </td>
  	      </tr>
  	    </table>
  	    </form>
  	  </div>
    </main>
  </div>
</body>
</html>