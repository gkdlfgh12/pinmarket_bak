<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="wrapper">
	
	<div class="container ">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-4">핀 마켓 경매 상품</h1>
		<p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It’s built with default Bootstrap components and utilities with little customization.</p>
	</div>
	
  <div class="card-deck mb-3 text-center">
	    <!-- <div class="card mb-4 shadow-sm" >
	      <div class="card-header">
	        <h4 class="my-0 font-weight-normal">Free</h4>
	      </div>
	      <div class="card-body">
	        <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>
	        <ul class="list-unstyled mt-3 mb-4">
	          <li>10 users included</li>
	          <li>2 GB of storage</li>
	          <li>Email support</li>
	          <li>Help center access</li>
	        </ul>
	        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
	      </div>
	    </div> -->
	   <!--  <div class="row"> -->
	    <c:forEach items="${list}" var="vo">
	    	<!-- <div class="col-4"> -->
		    <div class="card mb-4 shadow-sm" >
		      <div class="card-header">
		        <h4 class="my-0 font-weight-normal">${vo.product_name}</h4>
		      </div>
		      <div class="card-body">
		        <h1 class="card-title pricing-card-title"><small class="text-muted">￦</small>${vo.product_price}</h1>
		        <ul class="list-unstyled mt-3 mb-4">
		          <li>${vo.descript}</li>
		        </ul>
	        	<button type="button" class="btn btn-lg btn-block btn-primary" onclick="createOrder('${vo.product_name}','${vo.product_price}','${vo.id}')">결제하기</button>
		      </div>
		    </div>
		    <!-- </div> -->
	    </c:forEach>
	    <!-- </div> -->
	    <!-- <div class="card mb-4 shadow-sm">
	      <div class="card-header">
	        <h4 class="my-0 font-weight-normal">Enterprise</h4>
	      </div>
	      <div class="card-body">
	        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
	        <ul class="list-unstyled mt-3 mb-4">
	          <li>30 users included</li>
	          <li>15 GB of storage</li>
	          <li>Phone and email support</li>
	          <li>Help center access</li>
	        </ul>
	        <button type="button" class="btn btn-lg btn-block btn-primary">Contact us</button>
	      </div>
	    </div> -->
	  </div>
	  
	</div>
</div>
<script src="https://cdn.bootpay.co.kr/js/bootpay-3.3.3.min.js" type="application/javascript"></script>
<script type="text/javascript">
function createOrder(name, price, product_id){
	$.ajax({
		url : "/api/product/createOrder?product_id="+product_id,
		type : "get",
		success : function(result){
			console.log("result : ~1 ");
			console.log(result);
			var order_id = result.order_id;
			var member_id = result.member_id;
			
			bootPayOn(name, price, product_id, order_id, member_id);
		},
		error : function(){
		}
	})
}
function bootPayOn(name, price, product_id, order_id, member_id){
	
	//실제 복사하여 사용시에는 모든 주석을 지운 후 사용하세요
	BootPay.request({
	    price: price, //실제 결제되는 가격
	
	    // 관리자로그인 -> 결제설치 -> 인증키 및 보안 -> WEB Application ID
	    application_id: "626657eb270180001ef696c2",
	    name: name, //결제창에서 보여질 이름
	    pg: 'nicepay',
	    method: 'card', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
	    show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
	    items: [
	        {
	            item_name: name, //상품명
	            qty: 1, //수량
	            unique: product_id, //해당 상품을 구분짓는 primary key
	            price: price, //상품 단가
	        }
	    ],
	    order_id: order_id, //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
		}).error(function (data) {
		    //결제 진행시 에러가 발생하면 수행됩니다.
		    alert("에러");
		    console.log(data);
		}).cancel(function (data) {
		    //결제가 취소되면 수행됩니다.
		    alert("취소");
		    console.log(data);
		})/* .confirm(function (data) {
			//결제가 실행되기 전에 수행되며, 주로 재고를 확인하는 로직이 들어갑니다.
			//주의 - 카드 수기결제일 경우 이 부분이 실행되지 않습니다.
			console.log("=======================");
			console.log(data);
			console.log("=======================");
			var enable = true; // 나는 order 테이블에 주문 정보 저장하는 용도로 사용
	 */		
		.close(function (data) {
		    // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
		    alert("닫기");
		    console.log(data);
		}).done(function (data) {
		    //결제가 정상적으로 완료되면 수행됩니다
		    console.log("-------------");
		    console.log(data);
		    console.log("-------------");
		    //주문 테이블 업데이트
	   		var vo = {
 		    	"card_name" : data.card_name,
 		    	"card_num" : data.card_no,
 		    	"product_name" : data.item_name,
 		    	"method_type" : data.method,
 		    	"order_id" : data.order_id,
 		    	"pg_type" : data.pg,
 		    	"price" : data.price,
 		    	"receipt_id" : data.receipt_id,
 		    	"requested_at" : data.requested_at,
 		    	"purchased_at" : data.purchased_at
  		    }
	   		updateOrder(vo);
		    
		    //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
		    //location.href="/bootPay/veri?receipt_id="+data.receipt_id;
		    alert(member_id);
		    $.ajax({
		    	url : "/api/product/veri?receipt_id="+data.receipt_id+"&member_id="+member_id,
		    	type : "get",
		    	success : function(result){
		    		alert("결제 성공");
		    	},
		    	error : function(){
		    		alert("실패");
		    	}
		    });
		});
	}
	function updateOrder(vo){
		//여기서 order테이블에 저장하는거 구현하기
		console.log("===========");
		console.log(vo);
		console.log("===========");
		$.ajax({
	    	url : "/api/product/updateOrder",
	    	type : "post",
	    	data : JSON.stringify(vo),
	    	contentType : "application/json; charset=utf-8",
	    	success : function(result){
	    		console.log("ㅋㅋㅋㅋ ~" + result)
	    	},
	    	error : function(){
	    	}
	    });		
	}
</script>