<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe']
});
$(function(){
	$('#idBtn').click(function(){
		 Shadowbox.open({
			content:'../member/idcheck.jsp',
			player:'iframe',
			title:'아이디 중복체크',
			width:340,
			height:200
		}); 
	});
	// id,class(javascript/css) ,name(java에 값을 보낸다) 
	$('#postBtn').click(function(){
		 Shadowbox.open({
			content:'../member/postfind.jsp',
			player:'iframe',
			title:'우편번호 찾기',
			width:530,
			height:350
		}); 
	});
	
	// ▼ null값 강제입력
	$('#sendBtn').click(function(){

		// 비밀번호, 비밀번호 확인
		let pwd=$('#pwd').val();
		if(pwd.trim()==""){
			$('#pwd').focus();
			return;
		}	
		let pwd=$('#pwd1').val();
		if(pwd.trim()==""){
			$('#pwd1').focus();
			return;
		}	
		
		// 이름
		let name=$('#name').val();
		if(name.trim()==""){
			$('#name').focus();
			return;
		}
		
		// 생년월일
		let birthday=$('#birthday').val();
		if(birthday.trim()==""){
			$('#birthday').focus();
			return;
		}
		
		// 이메일
		let email=$('#email').val();
		if(email.trim()==""){
			$('#email').focus();
			return;
		}

		// 전화번호
		let tel2=$('#tel2').val();
		if(tel2.trim()==""){
			$('#tel2').focus();
			return;
		}
		let tel3=$('#tel3').val();
		if(tel3.trim()==""){
			$('#tel3').focus();
			return;
		}
		
		//▼ 정상 수행 시
		$('#joinFrm').submit();
	})
	
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear">
  	<div class="row">
  	<form method="post" action="../member/join_ok.do" id="joinFrm" name="joinFrm">
  	  <table class="table">
  		<tr>
		  <th class="text-center" width=15%>ID</th>
			<td width=85% class="inline">
			  <input type=text name=id size=15 class="input-sm" readonly id="id">
			  <input type=button value="아이디 중복 체크" id="idBtn" class="btn btn-sm btn-danger">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center" width=15%>PWD</th>
			<td width=85%>
			  <input type=password name=pwd size=15 class="input-sm" id="pwd">
			  <input type=password name=pwd1 size=15 class="input-sm" placeholder="비밀번호 확인" id="pwd1">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center" width=15%>이름</th>
			<td width=85%>
			  <input type=text name=name size=15 class="input-sm" id="name">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center">성별</th>
			<td width=85% class="inline">
			  <input type=radio value="남자" name=sex checked>남자
			  <input type=radio value="여자" name=sex>여자
			</td>  		  
  		</tr>
  		<tr>
          <th class="text-center" width=15%>생년월일</th>
         	<td width=85%>
           	  <input type="date" size=30 name="birthday" id="birthday">
        	</td>
        </tr>
  		<tr>
		  <th class="text-center" width=15%>이메일</th>
			<td width=85% >
			  <input type=text name=email size=55 class="input-sm" id="email">
			</td>  		  
  		</tr> 
  		<tr>
		  <th class="text-center" width=15%>우편번호</th>
			<td width=85% class="inline">
			  <input type=text name=post1 size=7 class="input-sm" readonly id="post1">-
			  <input type=text name=post2 size=7 class="input-sm" readonly id="post2">
			  <input type=button value="우편번호검색" class="btn btn-sm btn-primary" id="postBtn">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center" width=15%>주소</th>
			<td width=85%>
			  <input type=text name=addr1 size=45 class="input-sm">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center" width=15%>상세주소</th>
			<td width=85%>
			  <input type=text name=addr2 size=45 class="input-sm">
			</td>  		  
  		</tr>
  		<tr>
		  <th class="text-center" width=15%>전화번호</th>
			<td width=85% class="inline">
			  <select name="tel1" class="input-sm">
			  	<option>010</option>
			  </select>-
			  <input type=text name=tel2 size=7 class="input-sm" id="tel2">-
			  <input type=text name=tel3 size=7 class="input-sm" id="tel3">
			</td>  		  
  		</tr> 
  		<tr>
  		  <td colspan="2" class="text-center inline">
  		  	<input type=button value="회원가입" class="btn btn-sm btn-info" id="sendBtn">
  		  	<input type=button value="취소" class="btn btn-sm btn-success" onclick="javascript:history.back()">
  		  </td>
  		</tr>
  	  </table>
  	</form>
  	</div> 
  </main>
</div>
</body>
</html>


