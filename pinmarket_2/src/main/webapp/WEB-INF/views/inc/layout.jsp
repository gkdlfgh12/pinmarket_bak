<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<meta charset="UTF-8">
<title>Insert title here</title>
<style tyle="text/css">

html,
body {
    margin:0;
    padding:0;
    height:100%;
}
#wrapper {
	position:relative;
    min-height:100%;
}
#header {
	height:70px;
    background:#ccc;
}
#container {
    padding:20px;
}
#footer {
    position:absolute;
    bottom:0;
    width:100%;
    height:70px;   
    background:#ccc;
}
</style>
</head>
<body>
	<!-- 헤더 -->
	<tiles:insertAttribute name="header" />
	<!-- //헤더 -->
	
	<!-- 바디 -->
	<tiles:insertAttribute name="body" />
	<!-- //바디 -->
	
	<!-- 푸터 -->
	<tiles:insertAttribute name="footer" />
	<!-- //푸터 -->
	
	
</body>
</html>