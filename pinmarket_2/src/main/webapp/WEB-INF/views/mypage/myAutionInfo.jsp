<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="wrapper">
<a id="back-to-top"></a>
<a id="back-to-chat"></a>
<a id="back-to-write"></a>
	<div class="container">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">내가 올린 경매</h1>
	</div>
	<c:if test="${listSet != null}">
		<c:forEach items="${listSet}" var="vo" varStatus="i">
			<div class="card mb-3">
			
			<c:choose>
				<c:when test="${vo.status == 'open'}">
					<div class="card-header" style="background: #e9ecef;">
						<span class="fa fa-star" ></span>경매 진행중<span class="fa fa-star-o" ></span>
					</div>
				</c:when>
				<c:when test="${vo.status == 'wait'}">
					<div class="card-header" style="background: #ecb309;">
						<span class="fa fa-star" ></span>경매 대기중<span class="fa fa-star-o" ></span>
					</div>
				</c:when>
				<c:when test="${vo.status == 'comp'}">
					<div class="card-header" style="background: #80f0d3;">
						<span class="fa fa-star" ></span>경매 성사<span class="fa fa-star-o" ></span>
					</div>
				</c:when>
				<c:when test="${vo.status == 'end'}">
					<div class="card-header" style="background: #f65464;">
						<span class="fa fa-star" ></span>경매 불발<span class="fa fa-star-o" ></span>
					</div>
				</c:when>
			</c:choose>
			<div class="row g-0">
				<div class="col-md-3">
			      <img src="${vo.attachmentVO.thumbnail_name}" class="img-fluid rounded-start" alt="...">
			    </div>
				
				<div class="col-md-9">
			      <div class="card-body">
			        <h5 class="card-title h4">${vo.str_id}님의 경매</h5>
			        <small class="text-muted">제목 : ${vo.title}</small><br>
					<%-- <c:out value="${vo.RankingVO}"></c:out> --%>
					<small class="text-muted">내용 : ${vo.content}.....</small><br>
					<small class="text-muted">등록 날짜 : ${vo.regDate}</small><br>
					<small class="text-muted">기간 : ${vo.startDate} ~ ${vo.endDate}</small><br>
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

<script type="text/javascript" src="/resources/js/util.js"></script>
<script type="text/javascript">
function goDetail(id){
	location.href="/auction/detailForm?id="+id;
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