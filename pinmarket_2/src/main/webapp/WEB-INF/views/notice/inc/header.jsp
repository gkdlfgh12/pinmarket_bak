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
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/auction/list">물건사냥</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">결제상품</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link active dropdown-toggle" href=" id="dropdown01" data-toggle="dropdown" aria-expanded="false">고객센터</a>
	          <div class="dropdown-menu" aria-labelledby="dropdown01">
		         <a class="dropdown-item" href="/notice/freeFaqList">자유 질문</a>
		         <a class="dropdown-item" href="/notice/bestFaqList">자주묻는 질문</a>
	          </div>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">마이페이지</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/member/logout">로그아웃</a>
	        </li>
	        <button type="button" class="btn btn-outline-primary ml-3">
	        	Primary
	        </button>
	      </ul>
	    </div>
	  </div>
	</nav>