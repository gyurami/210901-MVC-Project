<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		let id=$('#log_id').val();
		if(id.trim()==""){
			$('#log_id').focus();
			return;
		}
		let pwd=$('#log_pwd').val();
		if(pwd.trim()==""){
			$('#log_pwd').focus();
			return;
		}
		//입력된 경우 데이터 전송
		$.ajax({
			type:'post',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			success:function(res){
				let result=res.trim();
				if(result=='NOID'){
					alert("아이디가 존재하지 않습니다\n다시 입력하세요");
					$('#log_id').val("");
					$('#log_pwd').val("");
					$('#log_id').focus();
				}else if(result=='NOPWD'){
					alert("비밀번호가 존재하지 않습니다\n다시 입력하세요");
					$('#log_pwd').val("");
					$('#log_pwd').focus();
				}else{
					//로그인 성공
					location.href="../main/main.do";
				}
			}
		})
	})
	$('#logoutBtn').click(function(){
		location.href="../member/logout.do";
	})
})
</script>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">추천 & 예약 시스템</a></h1>
    </div>
    <div class="fl_right">
      <!-- <ul class="inline">
        <li><i class="fa fa-phone"></i> +00 (123) 456 7890</li>
        <li><i class="fa fa-envelope-o"></i> info@domain.com</li>
      </ul> -->
      <c:if test="${sessionScope.id==null }">
	      <table class="table" style="border:none">
	      	<tr class="inline">
	      		<td style="border:none">
	      			ID : <input type="text" name=id id=log_id size="15" class="input-sm">
	      		</td>
	      		<td style="border:none">
	      			Password : <input type="password" name=pwd id=log_pwd size="15" class="input-sm">
	      		</td>
	      		<td style="border:none">
	      			<input type="button" value="로그인" class="btn btn-sm btn-danger" id=logBtn>
	      		</td>
	      	</tr>
	      </table>
		</c:if>
      	<c:if test="${sessionScope.id!=null }">
	      <table class="table" style="border:none">
	      	<tr class="inline">
	      		<td style="border:none">
	      			${sessionScope.name }(${sessionScope.admin=='y'?"관리자":"일반유저" })
	      		</td>
	      		<td style="border:none">
	      			로그인 중입니다!!
	      		</td>
	      		<td style="border:none">
	      			<input type="button" value="로그아웃" class="btn btn-sm btn-danger" id=logoutBtn>
	      		</td>
	      	</tr>
	      </table>
		</c:if>
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
    <ul class="clear" style="width: 1300px">
      <li class="active"><a href="../main/main.do">홈</a></li>
      <li><a class="drop" href="#">회원</a>
		<c:if test="${sessionScope.id==null }"><%-- 로그인 안 된 상태 --%>
	        <ul>
	          <li><a href="../member/join.do">회원가입</a></li>
	          <li><a href="pages/full-width.html">아이디 찾기</a></li>
	          <li><a href="pages/sidebar-left.html">비밀번호 찾기</a></li>
	        </ul>
        </c:if>
        <c:if test="${sessionScope.id!=null }"><%-- 로그인 상태 --%>
	        <ul>
	          <li><a href="pages/gallery.html">회원수정</a></li>
	          <li><a href="pages/full-width.html">회원탈퇴</a></li>
	        </ul>
        </c:if>
      </li>
      <!-- 맛집 -->
      <li><a class="drop" href="../food/food_main.do">맛집</a>
        <ul>
          <li><a href="../food/food_main.do">맛집 목록</a></li>
          <li><a href="pages/full-width.html">지역별 맛집 찾기</a></li>
        </ul>
      </li>
      <!-- 레시피 -->
      <li><a class="drop" href="../recipe/recipe_main.do">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_main.do">레시피 목록</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
          <li><a href="pages/full-width.html">레시피 만들기</a></li>
        </ul>
      </li>
      <!-- 영화 -->
      <li><a class="drop" href="../movie/movie_main.do">영화</a>
        <ul>
          <li><a href="../movie/movie_main.do">영화목록</a></li>
          <li><a href="pages/gallery.html">현재상영영화</a></li>
          <li><a href="pages/full-width.html">개봉예정영화</a></li>
        </ul>
      </li>
      <!-- 서울여행 -->
      <li><a class="drop" href="../seoul/seoul_main.do">서울여행</a>
        <ul>
          <li><a href="../seoul/seoul_main.do">서울여행</a></li>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/gallery.html">자연&관광</a></li>
          <li><a href="pages/full-width.html">호텔</a></li>
          <li><a href="../seoul/seoul_weather.do">날씨</a></li>
        </ul>
      </li>
      <!-- 커뮤니티 : 자료실, 묻고답하기, 자유게시판 -->
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="../freeboard/list.do">자유게시판</a></li>
          <li><a href="../replyboard/list.do">묻고답하기</a></li>
          <li><a href="pages/full-width.html">공지사항</a></li>
          <li><a href="../freeboard/list.do">이벤트</a></li>
        </ul>
      </li>
      <!-- 시스템 -->
      <c:if test="${sessionScope.id!=null }">
	      <li><a class="drop" href="#">시스템</a>
	        <ul>
	          <li><a href="pages/gallery.html">맛집예약</a></li>
	          <li><a href="pages/full-width.html">영화예약</a></li>
	          <li><a href="pages/gallery.html">맛집추천</a></li>
	          <li><a href="pages/gallery.html">영화추천</a></li>
	          <li><a href="pages/gallery.html">레시피추천</a></li>
	        </ul>
	      </li>
      </c:if>
      <li><a href="../news/news.do">뉴스</a></li>
      <c:if test="${sessionScope.id!=null }">
      	<c:if test="${admin=='n' }">
      		<li><a href="#">마이페이지</a></li>
      	</c:if>
      	<c:if test="${admin=='y' }">
      		<li><a href="#">admin페이지</a></li>
      	</c:if>
      </c:if>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>