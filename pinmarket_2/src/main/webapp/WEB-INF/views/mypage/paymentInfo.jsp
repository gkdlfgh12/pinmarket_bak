<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section id="wrapper">
<div class="container mt-5 pt-5">
	<c:forEach var="vo" items="${orderVO}">
	<div class="card mb-3" style="max-width: 100%;">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="${vo.attachmentVO.file_path}${vo.attachmentVO.real_name}" class="rounded-start" alt="..." 
				style="max-width:93%; height:auto;">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">${vo.product_name}</h5>
					<p class="card-text">${vo.productVO.descript}</p>
					<small class="text-muted">주문 번호 : ${vo.order_id}</small><br>
					<small class="text-muted">결제 타입 : ${vo.method_type}, 카드 번호 : ${vo.card_num}</small><br>
					<small class="text-muted">결제금액 : ${vo.price} 원</small><br>
					<c:if test="${vo.status == 'comp'}">
						<small class="text-muted">결과 : 완료</small><br>
					</c:if>
					<c:if test="${vo.status == 'cancel'}">
						<small class="text-muted">결과 : 취소</small><br>
					</c:if>
					<c:if test="${vo.status == 'err'}">
						<small class="text-muted">결과 : 에러</small><br>
					</c:if>
					<c:if test="${vo.status == 'wait'}">
						<small class="text-muted">결과 : 대기</small><br>
					</c:if>
					<small class="text-muted">결제 완료일 : ${vo.purchased_at}</small>
				</div>
			</div>
		</div>
	</div>
	</c:forEach>
</div>
</section>
