<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="wrapper">
	<div class="container">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">내가 올린 랭크</h1>
	</div>
	
	<c:forEach items="${listSet}" var="rankVO">
	<div class="card mb-3">
	
		<c:choose>
			<c:when test="${rankVO.auctionVO.status == 'comp'}">
				<c:if test="${rankVO.aucResult == 1}">
					<div class="card-header" style="background: #80f0d3;">
						<span class="fa fa-star" ></span>경매 성사<span class="fa fa-star-o" ></span>
					</div>
				</c:if>
				<c:if test="${rankVO.aucResult == 0}">
					<div class="card-header" style="background: #f65464;">
						<span class="fa fa-star" ></span>경매 불발<span class="fa fa-star-o" ></span>
					</div>
				</c:if>
			</c:when>
			<c:when test="${rankVO.auctionVO.status == 'open'}">
				<div class="card-header" style="background: #e9ecef;">
					<span class="fa fa-star" ></span>경매 진행중<span class="fa fa-star-o" ></span>
				</div>
			</c:when>
			<c:when test="${rankVO.auctionVO.status == 'wait'}">
				<div class="card-header" style="background: #ecb309;">
					<span class="fa fa-star" ></span>경매 대기중<span class="fa fa-star-o" ></span>
				</div>
			</c:when>
			<c:when test="${rankVO.auctionVO.status == 'end'}">
				<div class="card-header" style="background: #f65464;">
					<span class="fa fa-star" ></span>경매 불발<span class="fa fa-star-o" ></span>
				</div>
			</c:when>
		</c:choose>
		
		<div class="row g-0">
			<div class="col-md-3">
		      <img src="${rankVO.auctionVO.attachmentVO.thumbnail_name}" class="img-fluid rounded-start" alt="...">
		    </div>
			
			<div class="col-md-9">
		      <div class="card-body">
		        <h5 class="card-title h4">${rankVO.auctionVO.str_id}님의 경매</h5>
		        <small class="text-muted">제목 : ${rankVO.auctionVO.title}</small><br>
				<%-- <c:out value="${vo.RankingVO}"></c:out> --%>
				<small class="text-muted">내용 : ${rankVO.auctionVO.content}</small><br>
				<small class="text-muted">등록 날짜 : ${rankVO.auctionVO.regDate}</small><br>
				<small class="text-muted">만남 장소 : ${rankVO.auctionVO.address1} ${rankVO.auctionVO.address2}</small><br>
				<small class="text-muted">기간 : ${rankVO.auctionVO.startDate} ~ ${rankVO.auctionVO.endDate}</small><br>
				<c:choose>
					<c:when test="${rankVO.auctionVO.status == 'open'}">
						<c:set value="<span class='text-primary'>활동중</span>" var="status"></c:set>
					</c:when>
					<c:when test="${rankVO.auctionVO.status == 'wait'}">
						<c:set value="대기" var="status"></c:set>
					</c:when>
					<c:when test="${rankVO.auctionVO.status == 'comp'}">
						<c:set value="<span class='text-success'>경매완료</span>" var="status"></c:set>
					</c:when>
					<c:when test="${rankVO.auctionVO.status == 'end'}">
						<c:set value="<span class='text-danger'>기간만료</span>" var="status"></c:set>
					</c:when>
				</c:choose>
				<p class="card-text"><small class="text-muted">상태 : ${status}</small></p>
				<div>
				</div>
		      </div>
		    </div>
		</div>
		
		<div class="row g-0">
			<div class="col-md-3">
		      <img src="${rankVO.attachmentVO.thumbnail_name}" class="img-fluid rounded-start" alt="...">
		    </div>
			
			<div class="col-md-9">
		      <div class="card-body">
		        <h5 class="card-title h4">나의 랭크 정보</h5>
		        <small class="text-muted">제목 : ${rankVO.title}</small><br>
		        <small class="text-muted">내용 : ${rankVO.content}</small><br>
		        <small class="text-muted">신청날짜 : ${rankVO.regDate}</small><br>
		        <c:choose>
					<c:when test="${rankVO.payment_status == 1}">
						<c:set value="사용" var="payment_status"></c:set>
					</c:when>
					<c:when test="${rankVO.payment_status == -1}">
						<c:set value="미사용" var="payment_status"></c:set>
					</c:when>
				</c:choose>
		        <small class="text-muted">상품 사용여부 : ${payment_status}</small><br>
		      </div>
		    </div>
		</div>
		<div class="row g-0">
			<div class="col-md-12">
				<button class="btn btn-dark float-right mr-3 mb-2" onclick="goDetail(${rankVO.auctionVO.id});">자세히 보기</button>
			</div>
		</div>
	</div>
	</c:forEach>
	
	</div>
	<div aria-label="Page navigation example" style="margin-left: 50%;"> 
		<ul class="pagination">
			<c:if test="${pc.prev }">
				<li class="page-item">
				    <a class="page-link" href="/mypage/myRankList?page=${pc.beginPage - 1}&countPerPage=${pc.countPerPage}">
				    	Next
				    </a>
			   	</li>
			</c:if>
			<c:forEach begin="${pc.beginPage }" end="${pc.endPage}" var="i">
				<li class="page-item ${ pc.page == i ? ' active' : ' '}"><a class="page-link" href="/mypage/myRankList?page=${i}&countPerPage=${pc.countPerPage}">${i}</a></li>
			</c:forEach>
			<c:if test="${pc.next }">
			<li class="page-item">
				<a class="page-link" href="/mypage/myRankList?page=${pc.endPage + 1}&countPerPage=${pc.countPerPage}">Next</a>
			</li>
			</c:if>
		</ul>
	</div>
</section>
<script type="text/javascript">
function goDetail(id){
	location.href="/auction/detailForm?id="+id;
}
</script>