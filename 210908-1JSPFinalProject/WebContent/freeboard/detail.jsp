<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="wrapper row3">
    <main class="container clear">
	  <form method="post" action="../freeboard/list.do">
      <table class="table">
        <tr>
          <th width=20% class="text-center" values="${vo.name }">이름</th>
          <td width=80%>
            <input type=text name="name" size=20 class="input-sm">
          </td>
        </tr>
        <tr>
          <th width=20% class="text-center">제목</th>
          <td width=80%>
            <input type=text name="subject" size=60 class="input-sm">
          </td>
        </tr>
        <tr>
          <th width=20% class="text-center">내용</th>
          <td width=80%>
            <textarea rows=10 cols=60 name="content"></textarea>
          </td>
        </tr>
        <tr>
          <th width=20% class="text-center">비밀번호</th>
          <td width=80%>
            <input type=password name="pwd" size=15 class="input-sm">
          </td>
        </tr>
        <tr>
          <td class="text-center" colspan="2">
            <input type="submit" value="글쓰기" class="btn btn-sm btn-danger">
            <input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      <div id="comments">
        <h2>Comments</h2>
        <ul>
        <c:forEach var="rvo" items="${list }">
          <li>
            <article>
              <header>
                <figure class="avatar">
                  <c:if test="${sessionScope.id==rvo.id }">
                    <a href="#" class="btn btn-xs btn-danger" style="color:black">수정</a>
                    <a href="#" class="btn btn-xs btn-success" style="color:black">삭제</a>
                  </c:if>  
                </figure>
                <address>
                By <a href="#">${rvo.name }</a>
                </address>
                <span>(${rvo.dbday })</span>
              </header>
              <div class="comcont">
                <p>${rvo.msg }</p>
              </div>
            </article>
          </li>
        </c:forEach>
        </ul>
      </div>
      </form>
    </main>
  </div>
</body>
</html>