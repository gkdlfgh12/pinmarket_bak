<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="wrapper">
<a id="back-to-top"></a>
<a id="back-to-chat"></a>
<a id="back-to-write"></a>
	<div class="container ">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">핀 마켓 경매 상품</h1>
		<p class="lead">경매의 랭크 등록시 랭크 등록 순서와 관계없이 각 상품의 기능별로 랭크가 등록 될 수 있다.</p>
		<p class="small">( 같은 상품을 사용한 사용자들끼리는 랭크를 등록한 순서별로 순위가 맺어집니다. )</p>
	</div>
	<div class="row">
	<c:forEach items="${list}" var="vo">
	<div class="col-md-4 mt-5 mb-3">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 shadow-sm" >
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">${vo.product_name}</h4>
				</div>
				<img class="card-img-top" src="${vo.attachmentVO.file_path}${vo.attachmentVO.save_name}" alt="Card image">
				<div class="card-body">
					<h1 class="card-title pricing-card-title"><small class="text-muted">￦</small>${vo.product_price}</h1>
					<ul class="list-unstyled mt-3 mb-4">
						<li>${vo.descript}</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block btn-primary" onclick="createOrder('${vo.product_name}','${vo.product_price}','${vo.id}')">결제하기</button>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
	  </div>
	  
	</div>
	
	<!-- 채팅 Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModal" aria-hidden="true">
	  <div class="modal-dialog modal-xl">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">채팅방</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container">
	<h3 class=" text-center">Messaging</h3>
	<div class="messaging">
	      <div class="inbox_msg">
	      
	        <div class="inbox_people">
	          <div class="headind_srch">
	            <div class="recent_heading">
	              <h4>채팅방 목록</h4>
	            </div>
	            <div class="srch_bar">
	              <div class="stylish-input-group">
	                <input type="text" class="search-bar auction-search" placeholder="옥션 제목 입력" >
	                <span class="input-group-addon">
	                <button type="button" class="auction-search-btn"> <i class="fa fa-search " aria-hidden="true"></i> </button>
	                </span> </div>
	            </div>
	          </div>
	          <div class="inbox_chat">
	          
	            
	            
	          </div>
	        </div>
	        
	        <div class="mesgs">
	          <div class="msg_history tmpMsg">
	          
	          <!-- 채팅 msg 출력 -->
	            
	          </div>
	          <div class="del-btn">
	          	<!-- <button type="button" class="btn btn-danger">버튼</button> -->
	          </div>
	          <div class="type_msg" style="display:none;">
	            <div class="input_msg_write">
	              <input type="text" class="write_msg" placeholder="Type a message" />
	              <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
	            </div>
	          </div>
	        </div>
	      </div>
	      
	      
	      
	    </div></div>
	      </div>
	    </div>
	  </div>
	</div>
</div>
<script src="https://cdn.bootpay.co.kr/js/bootpay-3.3.3.min.js" type="application/javascript"></script>
<script type="text/javascript" src="/resources/js/util.js"></script>
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
	
	
	//채팅 ------------------------------
	//방 나가기 프로세스
	function roomDel(room_id){
		if(confirm("해당 채팅방을 나가시겠습니까?")){
			$.ajax({
				url : "/api/chatting/roomDelete?room_id="+room_id,
				type : "get",
				success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
					//트리거로 해당 셀렉터 강제 클릭 이벤트 실행
					$("#back-to-chat").trigger("click");
					$(".msg_history").empty();
					$(".del-btn").empty();
					$(".type_msg").hide();
				},
				error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
					alert('실패');
				}
			})
		}
	}
	
	//웹 소켓 연결
	var socket;
	var room_id;
	var member_id;
	var opponent_id;
	$(document).ready(function () {
		
		$('.msg_send_btn').on('click', function(evt) {
			sendMsg(evt);
		});
		$('.write_msg').on('keypress',function(evt){
			if(evt.keyCode === 13) { 
				sendMsg(evt); 
			}
		});
		function sendMsg(evt){
			evt.preventDefault();
			if (socket.readyState !== 1) return;
				//room_id, 채팅 작성자 id, 채팅 상대자 id, 메시지
				let msg = room_id+','+member_id+','+opponent_id+','+$('.write_msg').val();
				$('.write_msg').val('');
				socket.send(msg);
		}
	})
	
	function connect(){
		var ws = new WebSocket("ws://localhost:8080/chatEcho");
		//var ws = new WebSocket("ws://192.168.35.239:8080/chatEcho?bno=1234");
		socket = ws;
		//연결이 됬을때 실행
		ws.onopen = function () {
		    console.log('Info: connection opened.');
		    //setTimeout( function(){ connect(); }, 1000); // retry connection!!
		};
		//메세지 받았을 때 실행
		ws.onmessage = function (event) {
		    console.log('메시지 성공 일때');
		    var str_msg = ''
		    if(event.data.split("|&,&|")[0] == "from me"){
		    	str_msg += '<div class="outgoing_msg">';
				str_msg += '  <div class="sent_msg">';
				str_msg += '    <p>'+event.data.split("|&,&| ")[1]+'</p>';
				str_msg += '    <span class="time_date"> '+dateComparison()+'</span> </div>';
				str_msg += '</div>';
		    }else if(event.data.split("|&,&|")[0] == "from you"){
		    	str_msg += '<div class="incoming_msg">';
				str_msg += '  <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>';
				str_msg += '  <div class="received_msg">';
				str_msg += '    <div class="received_withd_msg">';
				str_msg += '      <p>'+event.data.split("|&,&| ")[1]+'</p>';
				str_msg += '      <span class="time_date"> '+dateComparison()+'</span></div>';
				str_msg += '  </div>';
				str_msg += '</div>';
		    }
		    $(".msg_history").append(str_msg);
		    $('.msg_history').scrollTop($('.msg_history')[0].scrollHeight);
		};
		//연결이 끊겼을때
		ws.onclose = function (event) { 
			console.log('Info: connection closed.'); 
			console.log(event);
			connect();
		};
		//오류가 났을때
		ws.onerror = function (event) { console.log('Info: connection closed.'+event); };
	}
	
	//채팅 기록 불러오기
	$(".inbox_chat").on("click",".chat_list",function(){
		console.log($(this).data("room"));
		room_id = $(this).data("room");
		member_id = $(this).data("host");
		opponent_id = $(this).data("guest");
		
		//버튼 보이게 설정
		$(".type_msg").show();
		
		//기존에 클릭된 채팅방의 색깔 복귀
		$(".inbox_chat").find(".chat_list").removeClass('active_chat');
		//채팅 방 클릭스 색깔 변경
		$(this).addClass('active_chat');
		
		var btn_str = '';
		$(".del-btn").empty();
		if(member_id == "${loginVO.id}"){
			btn_str += '<button type="button" onclick="roomDel('+room_id+');" class="btn btn-danger btn-delete">방 나가기</button>';
			$(".del-btn").append(btn_str);
		}
		
		//채팅 전송 시 내가 보낸거랑 상대방이 보낸거랑 구분하기 위해
		if("${loginVO.id}" == $(this).data("host")) {
			member_id = $(this).data("host");
			opponent_id = $(this).data("guest");
		}else if("${loginVO.id}" == $(this).data("guest")){
			member_id = $(this).data("guest");
			opponent_id = $(this).data("host");
		}
		
		//소켓 연결
		connect();
		
		$(".msg_history").empty();
		$.ajax({
			url : "/api/chatting/getMsgList?room_id="+$(this).data("room"),
			type : "get",
			success : function(result, status, xhr){
				console.log(result);
				console.log("${loginVO.id}");
				var str_msg = '';
				if(result.length != 0){
					for(var i=0;i<result.length;i++){
						if(result[i].member_id == "${loginVO.id}"){
							str_msg += '<div class="outgoing_msg">';
							str_msg += '  <div class="sent_msg">';
							str_msg += '    <p>'+result[i].message+'</p>';
							str_msg += '    <span class="time_date">'+result[i].regDate+'</span> </div>';
							str_msg += '</div>';
						}else{
							str_msg += '<div class="incoming_msg">';
							str_msg += '  <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>';
							str_msg += '  <div class="received_msg">';
							str_msg += '    <div class="received_withd_msg">';
							str_msg += '      <p>'+result[i].message+'</p>';
							str_msg += '      <span class="time_date">'+result[i].regDate+'</span></div>';
							str_msg += '  </div>';
							str_msg += '</div>';
						}
					}
					$(".msg_history").append(str_msg);
					$('.msg_history').scrollTop($('.msg_history')[0].scrollHeight);
				}
			},
			error : function(xhr, status, err){
				alert('실패');
			}
		});
	});
	
	//채팅 방 정보 가져오기
	$(".auction-search-btn, #back-to-chat").on("click",function (){
		var auction_title = '';
		if($(".auction-search").val() != ''){
			auction_title = $(".auction-search").val();
		}
		
		//채팅 방 정보 가져오기
		$.ajax({
			url : "/api/chatting/getRoomList?host_id=${loginVO.id}&auction_title="+auction_title,
			type : "get",
			success : function(result, status, xhr){
				$(".inbox_chat").empty();
				var str_list = '';
				if(result.length != 0){
					for(var i=0;i<result.length;i++){
						str_list += '<div class="chat_list" data-room="'+result[i].room_id+'" data-host="'+result[i].host_id+'" data-guest="'+result[i].guest_id+'"';
						str_list += 'style="cursor: pointer">';
						str_list += '  <div class="chat_people">';
						str_list += '    <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>';
						str_list += '    <div class="chat_ib">';
						str_list += '       <h5>옥션 제목 : <span class="auction_title" style="float: none;">'+result[i].auction_title +'</span><span class="chat_date">'+result[i].regDate+'</span></h5>';
						str_list += '       <p> 호스트 : '+result[i].host_str+'</p>';
						str_list += '      <p> 게스트 : '+result[i].guest_str+'</p>';
						str_list += '    </div>';
						str_list += '   </div>';
						str_list += '  </div>';
					}
				}
				$(".inbox_chat").append(str_list);
			},
			error : function(xhr, status, err){
				alert("실패");
			}
		});
		
		//채팅 모달 보이게
		$('#myModal').modal("show");
	});
	
	$(function(){
			//우측 메뉴
			$('#back-to-chat').addClass('show fa fa-commenting fa-3x');
			$('#back-to-chat').on('click',function(e){
				
			});
			
			$('#back-to-top').addClass('show');
			$('#back-to-top').on('click',function(e){
				e.preventDefault();
				$('html,body').animate({scrollTop:0},600);
			});
			
			$('#back-to-write').addClass('show fa fa-plus fa-3x');
			$('#back-to-write').on('click',function(e){
				location.href="/auction/writeForm";
				//e.preventDefault();
				//$('html,body').animate({scrollTop:0},600);
			});
		  
		  $(window).scroll(function() {
		    if ($(document).scrollTop() > 100) {
		      $('#back-to-top').addClass('show');
		    } else {
		      $('#back-to-top').removeClass('show');
		    }
		  });
		});
</script>