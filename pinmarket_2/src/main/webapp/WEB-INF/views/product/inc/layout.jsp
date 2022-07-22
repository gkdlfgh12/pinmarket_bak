<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>

<!-- 추후에 css 파일로 바꾸어 저장하기 -->
<style>
.btn_area {
  position: fixed; /* 이 부분을 고정 */
  bottom: 0; /* 하단에 여백 없이 */
  width: 100%; /* 가로 사이즈를 브라우저에 가득 채움 */
}
</style>
	<!-- 에디터 -->
	<!-- <script type="text/javascript" src="/resources/dist/js/service/HuskyEZCreator.js" charset="utf-8"></script> -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/resources/css/widget.css">
	<link rel="stylesheet" href="/resources/css/chatting.css">
	<meta charset="UTF-8">
<style>
html,
body {
    margin:0;
    padding:0;
    height:100%;
}
#wrapper {
	position:relative;
    min-height:80%;
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
<title>Insert title here</title>
</head>
<body >
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