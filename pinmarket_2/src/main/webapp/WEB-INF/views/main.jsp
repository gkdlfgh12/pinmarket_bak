<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <div id="wrapper">
    <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-6">
                        <div class="text-center my-5">
                            <h1 class="display-5 fw-bolder text-white mb-2">당근 마켓을 뛰어 넘는 핀마켓</h1>
                            <p class="lead text-white-50 mb-4">머리핀으로 집까지 구할 수 있는 물물교환 사이트</p>
                            <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
                                <a class="btn btn-primary btn-lg px-4 me-sm-3" href="#features">Get Started</a>
                                <a class="btn btn-outline-light btn-lg px-4" href="#!">Learn More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    <!-- pricing -->
	<div class="container">
		<div class="text-center">
			<h3 class="display-5 mt-5 mb-5"><a href="/auction/list">거래 랭킹 탑 10</a></h3>
		</div>
	</div>
	<div class="container mt-5">
		<div class="row">
		<c:set var="TOP10Size1" value="${fn:length(TOP10_1)}" />
		<c:forEach items="${TOP10_1}" var="vo" varStatus="status">
<%-- 			<c:if test="${status.count <= 5}">
 --%>				<div class="card-group ml-3">
					<div class="card" style="width: 13rem;">
					  <div class="card-header">
					    	${vo.title}
					  </div>
					  <div class="card-body">
					    <h5 style="margin-bottom: 3.75rem;">${vo.content}......</h5>
					    <p class="card-text"></p>
					  </div>
					  <a href="/auction/detailForm?id=${vo.id}" class="btn btn-primary mb-1">자세히 보기</a>
					  <img src="${vo.attachmentVO.thumbnail_name}" style="height: 9rem; object-fit: cover;"/>
					</div>	
					</div>
			<%-- </c:if> --%>
		</c:forEach>
		</div>
		<hr>
		<div class="row mt-5">
		<c:set var="TOP10Size2" value="${fn:length(TOP10_2)}" />
		<c:forEach items="${TOP10_2}" var="vo" varStatus="status">
			<div class="card-group ml-3">
					<div class="card" style="width: 13rem;">
					  <div class="card-header">
					    	${vo.title}
					  </div>
					  <div class="card-body">
					    <h5 style="margin-bottom: 3.75rem;">${vo.content}......</h5>
					    <p class="card-text"></p>
					  </div>
					  <a href="#" class="btn btn-primary mb-1">자세히 보기</a>
					  <img src="${vo.attachmentVO.thumbnail_name}" style="height: 9rem; object-fit: cover;"/>
					</div>	
					</div>
		</c:forEach>
		</div>
		<hr>
		
		<div class="container px-4 px-lg-5">
		<!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center my-5">
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="https://dummyimage.com/900x400/dee2e6/6c757d.jpg" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">인기 결제 상품</h1>
                    <p>물물교환 랭크 등록 시 top5에 나의 물건을 보여지게 할 수 있다.</p>
                    <a class="btn btn-primary" href="#!">결제 상품 보기</a>
                </div>
            </div>
            <!-- Call to Action-->
            <div class="card text-white bg-secondary my-5 py-4 text-center">
                <div class="card-body"><p class="text-white m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p></div>
            </div>
        </div>
	</div>
	</div>