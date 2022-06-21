<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div id="wrapper">
	<div class="container">
	  <div class="container">
			  <div class="row mt-3">
			        <h2>자유 질문</h2>
			  </div>
			  <div class="row mt-5">
			          <form id="contact-form" class="form" action="/notice/freeWrite" method="POST" role="form">
			          	  <input type="hidden" name="page" value="1">
			          	  <input type="hidden" name="countPerPage" value="10">
			              <div class="form-group">
			                  <label class="form-label" for="name">제목</label>
			                  <input type="text" class="form-control" id="title" name="title" value="${boardVO.title}" readonly>
			              </div>                            
			              <div class="form-group">
			                  <label class="form-label" for="content">내용</label>
			                  <textarea class="form-control" name="content" id="content" rows="10" cols="300" readonly>${boardVO.content}</textarea>                               
			              </div>
			          </form>
			          <div class="float-right">
				          <button class="btn btn-primary" onclick="goFreeFaqList();">목록</button>
				          <!-- 자기가 쓴 게시글에서만 표출 -->
				          <c:if test="${boardVO.member_id == loginVO.id}">
					          <button class="btn btn-primary ml-2" onclick="goFreeFaqModify();">수정</button>
					          <button class="btn btn-primary ml-2" onclick="goFreeFaqDel();">삭제</button>
				          </c:if>
			          </div>
			  </div>
			</div>
	<c:if test="${loginVO.member_level == 0 }">
	<div class="row" style="margin-top:15px">
		<div class="fa fa-commenting-o">댓글 입력</div>
		<textarea class="form-control" name="replyContent" id="replyContent"></textarea>
		<div style="margin-top : 12px;"><button type="button" id="replySubmitBtn" class="btn btn-primary">글쓰기</button></div>
	</div>
	</c:if>
	<!-- 댓글 뿌려질 공간 -->
		<div class="row reply-inbox" style="margin-top:15px; display:none;">
			<div class="panel panel-default" style="width: 100%;">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw">Reply</i>
				</div>
				<div class="panel-body">
					<ul class="reply-list list-group">
						<!-- <li class="left clearfix list-group-item" data-rno="12">
							<div class="header">
								<strong>작성자</strong>
								<small class="pull-right text-mute">날짜 데이터</small>
							</div>
							<p>댓글이 나오는 부분</p>
						</li>
						
						<li class="left clearfix list-group-item dataRow">
							<div class="header">
							<strong>작성자!~~~~~~~</strong>
							<small class="pull-right text-mute">날짜 데이터~~</small>
							</div>
							<p>내용입니다~~~~~~~~~~~~
								<small class="pull-right"><button class="btn btn-info modifyBtn" data-rno='+list[i].rno+' data-content="'+list[i].content+'" data-toggle="modal" data-target="#myModal" style="margin-right: 5px;">수정</button>
								<button class="btn btn-danger delBtn" data-rno='+list[i].rno+'>삭제</button></small>
							</p>
						</li> -->
					</ul>
				</div>
			</div>
		</div>
		<div class="mt-3 mb-3 moreBtn" style="display:none;">
			<button type="button" id="moreList" class="btn btn-light btn-lg btn-block">더보기</button>
		</div>
		
	<!-- ------- -->
	</div>
</div>
<script type="text/javascript">
	function goFreeFaqList(){
		location.href="/notice/freeFaqList?page=${pageVO.page}&countPerPage=${pageVO.countPerPage}";
	}
	function goFreeFaqModify(){
		location.href="/notice/freeModifyForm?page=${pageVO.page}&countPerPage=${pageVO.countPerPage}&board_id=${boardVO.id}";
	}
	function goFreeFaqDel(){
		if(confirm("해당 게시글을 삭제하시겠습니까? \n\n 삭제 후 복구가 불가능 합니다.")) {
			location.href="/notice/freeDel?page=${pageVO.page}&countPerPage=${pageVO.countPerPage}&id=${boardVO.id}";
		}
	}
	
	$("#replySubmitBtn").on("click",function(){
		var board_id = "${boardVO.id}";
		alert(board_id);
		var content = $("#replyContent").val();
		var param = {
				'board_id' : board_id,
				'content'	: content
		}
		$.ajax({
			url : "/api/notice/freeReplyWrite",
			type : "post",
			data : JSON.stringify(param),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				console.log(result);
				$(".reply-list").empty();
				startIndex = 1;
				moreList(startIndex);
				alert('답변을 달았습니다.');
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('답변을 달지 못했습니다.');
			}
		});
	});
	
	// 조회 인덱스
	var startIndex = 1;	// 인덱스 초기값
	var searchStep = 5;	// 5개씩 로딩
	
	moreList(startIndex);
	
	$("#moreList").on("click",function(){
		startIndex += searchStep;
		moreList(startIndex);
	});
	
	//더보기 리스트
	function moreList(startIndex){
		endIndex = searchStep + startIndex - 1;
		board_id = "${boardVO.id}";
		var param = {
					'startIndex' : startIndex,
					'endIndex'	: endIndex,
					'board_id'	: board_id
				}
		$.ajax({
			url : "/api/notice/freeReplyList",
			type : "post",
			data : JSON.stringify(param),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				if(result.length != 0){
					var reply_list = '';
					console.log(result);
					for(var i=0;i<result.length;i++){
						reply_list += '<li class="mb-3 left clearfix list-group-item dataRow">';
						reply_list += '	<div class="header">';
						reply_list += '		<strong>관리자</strong>';
						reply_list += '		<small class="pull-right text-mute">'+result[i].regDate+'</small>';
						reply_list += '	</div>';
						reply_list += '	<p>'+result[i].content+'';
						if("${loginVO.member_level}" == 0){
							reply_list += '	<small class="pull-right"><button class="btn btn-danger delBtn" onclick="replyDelete('+result[i].id+');" data-replyid="'+result[i].id+'">삭제</button></small>';
						}
						reply_list += '	</p>';
						reply_list += '</li>';
					}
					$(".reply-inbox").show();
					$(".moreBtn").show();
					$(".reply-list").append(reply_list);
				}else{
					if($(".reply-list").children("li").length == 0){
						$(".reply-inbox").show();
						var str_list = '<div class="col mb-12 total-card"><div class="alert alert-danger" role="alert">답변이 존재 하지 않습니다.</div></div>';
						$(".reply-list").append(str_list);
					}else{
						alert('더 이상 글이 존재하지 않습니다.');
					}
					$("#moreList").hide();
				}
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('실패');
			}
		});
	}
	
	//$(document).ready(function () {
		//$(".delBtn").on("click",function(){
			function replyDelete(id){
				//alert($(this).data("replyid"));
				alert(id);
				$.ajax({
					url : "/api/notice/freeReplyDel?reply_id="+id,
					type : "get",
					success : function(result){
						alert("삭제 성공");
						location.reload();
						//$(".reply-list").empty();
						//moreList(1);
					},
					error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
						alert('삭제 실패');
					}
				});
				
			}
		//});
	//});
	
</script>
<!-- <script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef : oEditors,
	elPlaceHolder : "ir1", //저는 textarea의 id와 똑같이 적어줬습니다.
	sSkinURI : "/resources/dist/SmartEditor2Skin_ko_KR.html", //경로를 꼭 맞춰주세요!
	fCreator : "createSEditor2",
	htParams : {
		// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseToolbar : true,

		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : false,

		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : false
	}
});
</script> -->