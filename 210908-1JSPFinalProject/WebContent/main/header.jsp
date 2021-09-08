<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">추천 & 예약 시스템</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <!-- 
        <li><i class="fa fa-phone"></i> +00 (02) 123 4567</li>
        <li><i class="fa fa-envelope-o"></i> borami@domain.com</li>
         -->
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">홈</a></li>
      <li><a class="drop" href="#">Pages</a>
      	<%--▼ 로그인이 안 된 상태(=세션에 id가 등록되지 않은 상태) --%>
        <c:if test="${sessionScope.id==null }">
	        <ul>
	          <li><a href="../member/join.do">회원가입</a></li>
	          <li><a href="#">아이디 찾기</a></li>
	          <li><a href="#">비밀번호 찾기</a></li>
	        </ul>
        </c:if>
        <%--▼ 로그인이 된 상태(=세션에 id가 등록된 상태) --%>
        <c:if test="${sessionScope.id!=null }">
	        <ul>
	          <li><a href="#">회원수정</a></li>
	          <li><a href="#">회원탈퇴</a></li>
	        </ul>
		</c:if>                
      </li>
      <!-- 맛집 -->
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/food_main.do">맛집 목록</a></li>
          <li><a href="#">지역별 맛집 찾기</a></li>
        </ul>
      </li>      
      <!-- 레시피 -->
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_main.do">레시피 목록</a></li>
          <li><a href="#">쉐프</a></li>
          <li><a href="#">레시피 만들기</a></li>
        </ul>
      </li>       
      <!-- 영화 -->
      <li><a class="drop" href="#">영화</a>
        <ul>
          <li><a href="../movie/movie_main.do">영화 목록</a></li>
          <li><a href="#">현재상영영화</a></li>
          <li><a href="#">개봉예정영화</a></li>
        </ul>
      </li>       
      <!-- 서울여행 -->
      <li><a class="drop" href="#">서울여행</a>
        <ul>
          <li><a href="../seoul/seoul_main.do">서울여행</a></li>
          <li><a href="#">명소</a></li>
          <li><a href="#">자연&관광</a></li>
          <li><a href="#">호텔</a></li>
          <li><a href="#">지역날씨</a></li>
        </ul>
      </li>       
      <!-- 커뮤니티 -->
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="#">자유게시판</a></li>
          <li><a href="#">묻고답하기</a></li>
          <li><a href="#">공지사항</a></li>
          <li><a href="#">이벤트</a></li>
        </ul>
      </li> 
      <%--▼ 로그인이 된 상태(=세션에 id가 등록된 상태) --%>
       <c:if test="${sessionScope.id!=null }">
	      <!-- 예약시스템 -->
	      <li><a class="drop" href="#">예약</a>
	        <ul>
	          <li><a href="#">맛집예약</a></li>
	          <li><a href="#">영화예매</a></li>
	        </ul>
	      </li>       
	      <!-- 추천시스템 -->
	      <li><a class="drop" href="#">추천</a>
	        <ul>
	          <li><a href="#">맛집추천</a></li>
	          <li><a href="#">영화추천</a></li>
	          <li><a href="#">레시피추천</a></li>
	        </ul>
	      </li>
      </c:if>

      <li><a href="#">오늘의 뉴스</a></li>

      <%--▼ 로그인이 된 상태(=세션에 id가 등록된 상태) -> 관리자 유무 --%>
      <!-- 마이페이지 -->
      <c:if test="${sessionScope.id!=null }">
      	<c:if test="${admin=='n' }">
      		<li><a href="#">마이페이지</a></li>
      	</c:if>
      	<c:if test="${admin=='y' }">
      		<li><a href="#">어드민페이지</a></li>
      	</c:if>
      </c:if>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
</body>
</html>