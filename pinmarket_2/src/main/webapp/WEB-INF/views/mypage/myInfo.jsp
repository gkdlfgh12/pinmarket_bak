<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="wrapper">
<a id="back-to-top"></a>
<a id="back-to-chat"></a>
<a id="back-to-write"></a>
	<div class="container">
		<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
			<h1 class="display-5">내 정보</h1>
		</div>
		<div class="row">
			<div class="col">
				<div class="form-group mb-3" style="text-align: center;">
				<!-- <img class="rounded-circle" src="/resources/image/naver_btnG.png" style="width: 448px;"><br> -->
				<c:if test="${memberVO.attachmentVO != null}">
					<img class="rounded-circle" src="${memberVO.attachmentVO.thumbnail_name}"><br>
				</c:if>
				<c:if test="${memberVO.attachmentVO == null}">
					<img class="rounded-circle" src="/resources/image/noProfile.jpg" style="width: 200px;"><br>
				</c:if>
				<label class="input-file-button mt-3" for="profileImg">
				  프로필 변경
				</label>
				<!-- <form action="post" enctype="multipart/form-data" id="profileUplaodForm"> -->
				<form id="profileUplaodForm">
					<input type="file" id="profileImg" name="profileImg" style=display:none >
					<input type="hidden" name="member_id" value="${memberVO.id}">
				</form>
				</div>
				</div>
				<div class="col">
				  	<div class="form-group mb-3" >
				 	<label for="title">아이디</label>
				 	<input type="text" class="form-control" id="id" name="id" value="${memberVO.str_id}" readonly>
				</div>
				 <div class="form-group mb-3" >
				 	<label for="title">이름</label>
				 	<input type="text" class="form-control" id="name" name="name" value="${memberVO.name}">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="address1">주소</label>
				<input type="text" id="address1" class="form-control mb-3" value="${memberVO.address1}" readonly>
			</div>
			<div class="col">
				<label for="address2">상세 주소</label>
				<input type="text" id="address2" class="form-control mb-3" value="${memberVO.address2}">
			</div>
		</div>
			<div class="row">
				<div class="col">
					<label for="zipcode">zipCode</label>
					<input type="text" id="zipcode" class="form-control mb-3" style="width: 200px;" value="${memberVO.zipcode}" readonly>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="tel">연락처</label>
					<input type="text" id="tel" class="form-control mb-3" value="${memberVO.tel}">
				</div>
				<div class="col">
					<label for="email">이메일</label>
					<input type="text" id="email" class="form-control mb-3" value="${memberVO.email}">
				</div>
		</div>
		<div class="row float-right mt-3">
			<div class="col">
				<button type="button" id="pwdChange" class="btn btn-secondary validation">비밀번호 변경</button>
				<button type="button" id="saveBtn" class="btn btn-primary validation">변경 저장</button>
			</div>
		</div>
	</div>
</div>
<!-- 채팅 Modal -->
<div class="modal fade" id="myPwdModal" tabindex="-1" aria-labelledby="myPwdModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">비밀번호 변경</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<h3 class=" text-center">비밀번호 변경</h3>
					<form>
						<div class="form-group">
							<label for="password">비밀번호</label> 
							<input type="password" class="form-control" id="password" >
							<small id="emailHelp" class="form-text text-muted">기존 비밀번호 입력</small>
						</div>
						<div class="form-group">
							<label for="newPassword">새 비밀번호</label> 
							<input type="password" class="form-control" id="newPassword">
						</div>
						<div class="form-group">
							<label for="chkNewPasseord">새 비밀번호 확인</label> 
							<input type="password" class="form-control" id="chkNewPasseord">
						</div>
						<button type="button" id="savePwd" class="btn btn-primary float-right">비밀번호 변경</button>
					</form>
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
	    </div></div>
	      </div>
	    </div>
	  </div>
	</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/js/util.js"></script>
<script type="text/javascript">
$("#profileImg").change(function(e){
	console.log("여기는 이미지 첨부하면 실행되는 부분이고 여기서 바로 프로필 변경하면 됨");
	console.log($(this));
	var form = $("#profileUplaodForm")[0];
	var data = new FormData(form);  
	console.log(data);
	console.log(form);
	
	 $.ajax({             
    	type: "post",          
        enctype: 'multipart/form-data',  
        url: "/api/mypage/profileUpload",        
        data: data,          
        processData: false,    
        contentType: false,      
        cache: false,
        timeout: 600000,
        success: function (data) { 
        	alert("변경 완료");
        	setTimeout(() => {location.reload()}, 1000);
        },          
        error: function (e) {  
            alert("fail");      
         }     
	});  
});


$("#saveBtn").on("click",function(){
	if($("#name").val() == "") { alert("이름을 입력하세요."); return false;}
	if($("#address1").val() == "") { alert("주소를 입력하세요."); return false;}
	if($("#address2").val() == "") { alert("상세주소를 입력하세요."); return false;}
	if($("#zipcode").val() == "") { alert("주소를 입력하세요."); return false;}
	if($("#tel").val() == "") { alert("전화번호를 입력하세요."); return false;}
	if($("#email").val() == "") { alert("이메일을 입력하세요."); return false;}
	var param = {
		'name' 		: $("#name").val(),
		'address1'	: $("#address1").val(),
		'address2'	: $("#address2").val(),
		'zipcode'	: $("#zipcode").val(),
		'tel'		: $("#tel").val(),
		'email'		: $("#email").val()	
	}
	$.ajax({
		url : "/api/mypage/changeInfo",
		type : "post",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		success : function(result){
			if(result == "success") alert("수정이 완료 되었습니다.");
			else if(result == "fail") alert("수정에 실패 했습니다.");
		},
		error : function(){
			alert("실패");
		}
	});
});

$("#savePwd").on("click",function(){
	var password= $("#password").val();
	var newPassword = $("#newPassword").val();
	var chkNewPasseord = $("#chkNewPasseord").val();
	
	if(newPassword != chkNewPasseord){
		alert("새로운 비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	$.ajax({
		url : "/api/mypage/changePwd?password="+password+"&newPassword="+newPassword,
		type : "get",
		success : function(result){
			console.log(result);
			if(result == "pwdFail"){
				alert("기존 비밀번호가 일치하지 않습니다.");
			}else if(result == "success"){
				alert("비밀번호가 수정 되었습니다.");
				location.reload();
			}
			
		},
		error : function(){
			alert("실패");
		}
	});
});
$("#pwdChange").on("click",function(){
	//비밀번호 변경 모달 보이게
	$('#myPwdModal').modal("show");
});
//kakao postcode 주소 찾기
$("#address1").on("click",function(){
	new daum.Postcode({
        oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			console.log("data : "+data.roadAddress);
			console.log("data : "+data.address);

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("address1").value = data.address;
        }
    }).open();
});

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