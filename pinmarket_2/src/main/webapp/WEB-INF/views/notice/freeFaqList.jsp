<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
	.dataRow:hover{
		background: #eee;
		cursor: pointer;
	}
</style>
<!-- body -->
<div id="wrapper">
<a id="back-to-top"></a>
<a id="back-to-chat"></a>
<a id="back-to-write"></a>
<div class="container">
	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">FAQ - 자주 묻는 질문</h1>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th width="10%">번호</th>
				<th width="55%">제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${list != null}">
			<c:forEach var="vo" items="${list}">
				<tr class="dataRow" data-id="${vo.id}" data-memberid="${vo.member_id}">
					<td>${vo.rnum}</td>
					<td>${vo.title}</td>
					<td>${vo.str_id}</td>
					<td>${vo.hit}</td>
					<td>${vo.regDate}</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	
	<div style="margin-bottom: 70px;">
		<button class="btn btn-primary float-right" onclick="goModalForm();">작성</button>
	</div>
	
	<div aria-label="Page navigation example" style="margin-left: 50%;"> 
		<ul class="pagination">
			<c:if test="${pc.prev }">
				<li class="page-item">
				    <a class="page-link" href="/notice/freeFaqList?page=${pc.beginPage - 1}&countPerPage=${pc.countPerPage}">
				    	Next
				    </a>
			   	</li>
			</c:if>
			<c:forEach begin="${pc.beginPage }" end="${pc.endPage}" var="i">
				<li class="page-item ${ pc.page == i ? ' active' : ' '}"><a class="page-link" href="/notice/freeFaqList?page=${i}&countPerPage=${pc.countPerPage}">${i}</a></li>
			</c:forEach>
			<c:if test="${pc.next }">
			<li class="page-item">
				<a class="page-link" href="/notice/freeFaqList?page=${pc.endPage + 1}&countPerPage=${pc.countPerPage}">Next</a>
			</li>
			</c:if>
		</ul>
	</div>
</div>
</div>

<!-- 자유질문 입력 Modal -->
<div class="modal fade" id="myWriteModal" tabindex="-1" aria-labelledby="myWriteModal" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">자유 질문</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      		<div class="container">
			  <div class="row mt-3">
			        <h2>자유 질문</h2>
			  </div>
			  <div class="row mt-5">
			          <form id="contact-form" class="form" action="/notice/wrtieFreeFaq" method="POST" role="form">
			          	  <input type="hidden" name="page" value="1">
			          	  <input type="hidden" name="countPerPage" value="10">
			              <div class="form-group">
			                  <label class="form-label" for="name">제목</label>
			                  <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." tabindex="1" required>
			              </div>                            
			              <div class="form-group">
			                  <label class="form-label" for="message">내용</label>
			                  <textarea class="form-control" name="content" id="content" rows="10" cols="300" placeholder="내용을 입력하세요." required></textarea>                               
			              </div>
			              <div class="text-center">
			                  <button type="submit" class="btn btn-primary">작성</button>
			              </div>
			          </form>
			  </div>
			</div>
      </div>
    </div>
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
      
    	</div>
    	</div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/resources/js/util.js"></script>
<script type="text/javascript">
$(".dataRow").on("click",function(){
	var id = $(this).data("id");
	var member_id = $(this).data("memberid");
	var login_id = "${loginVO.id}";
	var login_member_level = "${loginVO.member_level}";
	if(member_id == login_id || login_member_level == 0){
		
	}else{
		alert("해당 게시글을 읽을 권한이 없습니다.");
		return false;
	}
	
	location.href="/notice/freeView?page=${pc.page}&countPerPage=${pc.countPerPage}&id="+id;
});

function goModalForm(){
	// 모달 보이게
	$('#myWriteModal').modal("show");
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
</script>