<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="/main"><img src="/resources/image/logo.PNG" class="display-1" alt="핀마켓" style="width:62px;" /></a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav ml-auto">
	      	<li class="nav-item" id="home">
	          <a class="nav-link active" aria-current="page" href="/main">HOME</a>
	        </li>
	        <li class="nav-item" id="auction">
	          <a class="nav-link active" aria-current="page" href="/auction/list">물건사냥</a>
	        </li>
	        <li class="nav-item" id="product">
	          <a class="nav-link active" aria-current="page" href="/product/list">결제상품</a>
	        </li>
	         <li class="nav-item dropdown" id="notice">
	          <a class="nav-link active dropdown-toggle" href="" id="dropdown01" data-toggle="dropdown" aria-expanded="false">고객센터</a>
	          <div class="dropdown-menu" aria-labelledby="dropdown01">
		         <a class="dropdown-item" href="/notice/freeFaqList">자유 질문</a>
		         <a class="dropdown-item" href="/notice/bestFaqList">자주묻는 질문</a>
	          </div>
	        </li>
	        <li class="nav-item dropdown" id="mypage">
	          <a class="nav-link active dropdown-toggle" href="" id="dropdown02" data-toggle="dropdown" aria-expanded="false">마이페이지</a>
	          <div class="dropdown-menu" aria-labelledby="dropdown02">
		         <a class="dropdown-item" href="/mypage/myInfo">내 정보</a>
		         <a class="dropdown-item" href="/mypage/paymentInfo">내 결제 정보</a>
		         <a class="dropdown-item" href="/mypage/myAutionList">내가 올린 경매</a>
		         <a class="dropdown-item" href="/mypage/myRankList">내가 올린 랭크</a>
	          </div>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/member/logout">로그아웃</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
<script type="text/javascript">
var url = $(location).attr('pathname');
if(url.search("main") !== -1){
	$("#home").css("background-color","style='#c2c6c9'");
}
if(url.search("auction") !== -1){
	$("#auction").css("background-color","style='#c2c6c9'");
}
if(url.search("product") !== -1){
	$("#product").css("background-color","style='#c2c6c9'");
}
if(url.search("notice") !== -1){
	$("#notice").css("background-color","style='#c2c6c9'");
}
if(url.search("mypage") !== -1){
	$("#mypage").css("background-color","style='#c2c6c9'");
}
</script>

