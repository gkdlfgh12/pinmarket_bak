<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="wrapper">
	<div class="container">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">내가 올린 경매</h1>
	</div>
	<c:if test="${listSet != null}">
		<c:forEach items="${listSet}" var="vo" varStatus="i">
			<div class="card mb-3">
			<c:if test="${vo.status == 'comp'}">
				<div class="card-header" style="background: #80f0d3;"><span class="fa fa-star" >경매 성사</span><span class="fa fa-star-o" ></span></div>
			</c:if>
			<div class="row g-0">
				<div class="col-md-3">
			      <img src="${vo.attachmentVO.thumbnail_name}" class="img-fluid rounded-start" alt="...">
			    </div>
				
				<div class="col-md-9">
			      <div class="card-body">
			        <h5 class="card-title h4">${vo.str_id}님의 경매</h5>
			        <p class="card-title"><small class="text-muted">제목 : ${vo.title}</small></p>
					<%-- <c:out value="${vo.RankingVO}"></c:out> --%>
					<p class="card-text"><small class="text-muted">내용 : ${vo.content}</small></p>
					<p class="card-text"><small class="text-muted">등록 날짜 : ${vo.regDate}</small></p>
					<p class="card-text"><small class="text-muted">기간 : ${vo.startDate} ~ ${vo.endDate}</small></p>
					<c:choose>
						<c:when test="${vo.status == 'open'}">
							<c:set value="<span class='text-primary'>활동중</span>" var="status"></c:set>
						</c:when>
						<c:when test="${vo.status == 'wait'}">
							<c:set value="대기" var="status"></c:set>
						</c:when>
						<c:when test="${vo.status == 'comp'}">
							<c:set value="<span class='text-success'>경매완료</span>" var="status"></c:set>
						</c:when>
						<c:when test="${vo.status == 'end'}">
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
				<div class="col-md-12">
					<button class="btn btn-dark float-right mr-3" onclick="goDetail(${vo.id});">자세히 보기</button>
				</div>
			</div>
			<hr>
			<div class="row g-0">
				<div class="col-md-12 ml-3" style="margin-bottom: 20px;">
				<c:choose>
					<c:when test="${vo.rankingVO.size() == 0}">
						<p class="card-text text-center">
							<span class="h3">랭크 미등록</span>
						</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${vo.rankingVO}" var="rank" varStatus="idx">
							<c:if test="${vo.status == 'comp'}">
								<p class="card-text">
									경매 신청자 : <span class="text-muted">${rank.str_id}</span>
									경매 신청 상품 : <span class="text-muted">${rank.title}</span>
									경매 신청 상품 설명 : <span class="text-muted">${rank.content}...</span>
									신청 날짜 : <span class="text-muted">${rank.regDate}</span>
								</p>
							</c:if>
							<c:if test="${vo.status != 'comp'}">
								<p class="card-text">
									<span class="h3">${idx.count} . </span>
									경매 신청자 : <span class="text-muted">${rank.str_id}</span>
									경매 신청 상품 : <span class="text-muted">${rank.title}</span>
									경매 신청 상품 설명 : <span class="text-muted">${rank.content}...</span>
									신청 날짜 : <span class="text-muted">${rank.regDate}</span>
								</p>
							</c:if>
						</c:forEach>
						<c:if test="${vo.rankingVO.size() >= 6}">
							<div class="text-center font-weight-bold">.<br>.<br>.</div>
						</c:if>
					</c:otherwise>
				</c:choose>		
				</div>	    
			</div>
			</div>
		</c:forEach>
	</c:if>
	</div>
	<div aria-label="Page navigation example" style="margin-left: 50%;"> 
		<ul class="pagination">
			<c:if test="${pc.prev }">
				<li class="page-item">
				    <a class="page-link" href="/mypage/myAutionList?page=${pc.beginPage - 1}&countPerPage=${pc.countPerPage}">
				    	Next
				    </a>
			   	</li>
			</c:if>
			<c:forEach begin="${pc.beginPage }" end="${pc.endPage}" var="i">
				<li class="page-item ${ pc.page == i ? ' active' : ' '}"><a class="page-link" href="/mypage/myAutionList?page=${i}&countPerPage=${pc.countPerPage}">${i}</a></li>
			</c:forEach>
			<c:if test="${pc.next }">
			<li class="page-item">
				<a class="page-link" href="/mypage/myAutionList?page=${pc.endPage + 1}&countPerPage=${pc.countPerPage}">Next</a>
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