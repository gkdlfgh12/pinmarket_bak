<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="wrapper">
<!-- pricing -->
<a id="back-to-top"></a>
<a id="back-to-chat"></a>
<a id="back-to-write"></a>
	<div class="container mt-3">
		<div class="form-row align-items-center">
		<!-- db에 대한민국 도 저장 하는 tb하나랑 구 저장하는 tb하나 만들어서 연결 시킨 후 도 셀박 클릭시 그에 해당되는 구를 가져온다. -->
		<!-- 도를 표시할 셀박 -->
			<div class="col-auto my-1">
				<select class="custom-select mr-sm-2" name="district1" id="DoSelect">
					<option selected value="">전체</option>
					<c:forEach items="${districtList}" var="doVO">
						<option value="${doVO.name}" data-code="${doVO.code}" >${doVO.name}</option>
					</c:forEach>
				</select>
			</div>
			<!-- 도 검색에 맜게 구 리스트 표시  -->
			<div class="col-auto my-1">
				<select class="custom-select mr-sm-2" name="district2" id="SiGuGunSelect">
					<option selected value="">선택</option>
				</select>
			</div>
			<div class="col-auto my-1">
				<input type="text" class="form-control" name="title" id="title" placeholder="물건 이름을 검색하세요.">
			</div>
			<div class="col-auto my-1">
			 	<button type="button" class="btn btn-primary" id="search">검색</button>
			</div>
		</div>
	
		<div class="text-center">
			<h3 class="display-5 mt-5 mb-5 sub-title">
				전국 인기 매물
			</h3>
		</div>
	</div>
	
	<div class="container mt-5">
		<div class="row row-cols-1 row-cols-md-3 auction-lists">
		<!-- 경매 리스트 출력 -->
			<%-- <c:forEach var="vo" items="${list}" varStatus="status">
				<div class="col">
					<div class="card mb-3">
					  <div class="card-header">
					    	${vo.title}
					  </div>
					  <div class="card-body">
					    <h5 class="card-title">${vo.content}</h5>
				    	<hr>
					    <c:set var="loop_flag" value="false" />
					    <!-- 경매글의 attach 기록 다 가져와서 맨 위에 하나만 표출 -->
					    <c:forEach var="atvo" items="${tmpAttachVO}">
					    	<c:if test="${not loop_flag }">
						    	<c:if test="${vo.id == atvo.fk_id}">
							    	<p class="card-text"><img src="${atvo.file_path}${atvo.save_name}" /></p>
							    	<hr>
								    <c:set var="loop_flag" value="true" />
						    	</c:if>
						    </c:if>
					    </c:forEach>
					    <p class="card-text">기간 : ${vo.startDate} ~ ${vo.endDate}</p>
					    <a href="#" class="btn btn-primary">자세히 보기</a>
					  </div>
					</div>	
				</div>
			</c:forEach> --%>
			<!-- 경매 리스트 출력 -->
		</div>
	</div>
	<div class="container mb-3">
		<button type="button" id="moreList" class="btn btn-light btn-lg btn-block">더보기</button>
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
          
            <!-- <div class="chat_list" data-room="test" style="cursor: pointer">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div> -->
            
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
	<script type="text/javascript" src="/resources/js/util.js"></script>
	<script type="text/javascript">
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
	
	
	//시 구 군 셀박 추가
	$("#DoSelect").change(function(){
		
		if($(this).find(':selected').val() == '') {
			$("#SiGuGunSelect").empty();
			$("#SiGuGunSelect").append("<option selected value=''>선택</option>");
			return false;
		}
		
		var doCode = $(this).find(':selected').data('code');
		$.ajax({
			url : "/api/auction/getSiGuGun?doCode="+doCode,
			type : "get",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				$("#SiGuGunSelect").empty();
				//var str_option = '<option selected value="">전체</option>';
				var str_option = '';
				for(var i=0;i<result.length;i++){
					str_option += '<option value='+result[i].name+'>'+result[i].name+'</option>';
				}
				$("#SiGuGunSelect").append(str_option);
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('실패');
			}
		});
	});
	//검색 개체
	var title;
	var district1;
	var district2;
	
	// 조회 인덱스
	var startIndex = 1;	// 인덱스 초기값
	var searchStep = 6;	// 5개씩 로딩
	$(document).ready(function(){
	
	moreList(startIndex);
	
	$("#moreList").on("click",function(){
		startIndex += searchStep;
		moreList(startIndex);
	});
	
	//더보기 리스트
	function moreList(startIndex){
		endIndex = searchStep + startIndex - 1;
		var param = {
					'startIndex' : startIndex,
					'endIndex'	: endIndex,
					'title'		: title,
					'district1'	: district1,
					'district2'	: district2	
				}
		$.ajax({
			url : "/api/auction/list",
			type : "post",
			data : JSON.stringify(param),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				console.log(result);
				if(result.length != 0){
					var str_list = '';
					var loop_flag;
					console.log(result);
					for(var i=0; i<result.length ;i++){
						str_list += '<div class="col">';
						str_list += '<div class="card mb-3">';
						str_list +=   '<div class="card-header">';
						str_list +=		result[i].title+' ('+result[i].rt_cnt+')</div>';
						str_list += '<div class="card-body">';
						str_list += '<h5 class="card-title">'+result[i].content+' .....</h5><hr>';
						if(result[i].attachmentVO != null && result[i].attachmentVO != ''){
							str_list += '<p class="card-text"><img src="'+result[i].attachmentVO.thumbnail_name+'" class="rounded-top" style="height: 9rem; object-fit: cover;"/></p><hr>';
						}else{
							str_list += '<p class="card-text"><img src="" class="rounded-top" style="height: 9rem; object-fit: cover;"/></p><hr>';
						}
						str_list += '<p class="card-text">작성자 : '+result[i].str_id+'</p><hr>';
						str_list += '<p class="card-text">거래 장소 : '+result[i].address1+'</p><hr>';
						var status = '';
						if(result[i].status == 'open') status = '진행중';
						else if(result[i].status == 'comp') status = '거래 완료'; 
						else if(result[i].status == 'wait') status = '대기중'; 
						else if(result[i].status == 'end') status = '기간 만료';  //스케쥴러 추가해야 할 듯
						str_list += '<p class="card-text">거래 상태 : '+status+'</p><hr>';
						str_list += '<p class="card-text">기간 : '+result[i].startDate+' ~ '+result[i].endDate+'</p><hr>';
						str_list += '   <a href="/auction/detailForm?id='+result[i].id+'" class="btn btn-primary">자세히 보기</a></div></div></div>';
					}
					$('.auction-lists').append(str_list);
				}else{
					if($(".auction-lists").children("div").length == 0){
						$(".auction-lists").removeClass("row row-cols-1 row-cols-md-3");
						var str_list = '<div class="col mb-12 total-card"><div class="alert alert-danger" role="alert">게시글이 존재 하지 않습니다.</div></div>';
						$("#moreList").hide();
						$(".auction-lists").append(str_list);
					}else{
						alert('리스트가 존재하지 않습니다.');
						$("#moreList").hide();
					}
					console.log($(".auction-lists").children("div").length);
				}
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('실패');
			}
		});
	}
	//검색
	$("#search").on("click",function(){
		title = $("#title").val();
		district1 = $("#DoSelect").val();
		district2 = $("#SiGuGunSelect").val();
		
		startIndex = 1;
		$("#moreList").show();
		
		if(district1 == '' && title == ''){
			$(".sub-title").html("전국 인기 매물");
		}else{
			$(".sub-title").html(district1+" "+district2+"<br>"+title);
		}
		$(".auction-lists").empty();
		moreList(startIndex);
		/*$.ajax({
			url : "/api/auction/list",
			type : "get",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				console.log(result);
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('실패');
			}
		});*/
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
	});
	</script>