<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body id="top">
<%-- ▼ header --%>
<%@ include file="../main/header.jsp" %>

<%-- ▼ content: 수시로 변경 --%>
<jsp:include page="${main_jsp }"></jsp:include>

<%-- ▼ footer --%>
<%@ include file="../main/footer.jsp" %>

<%-- ▼ 형식 --%>
	<script src="../layout/scripts/jquery.min.js"></script> 
	<script src="../layout/scripts/jquery.backtotop.js"></script> 
	<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
	<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>