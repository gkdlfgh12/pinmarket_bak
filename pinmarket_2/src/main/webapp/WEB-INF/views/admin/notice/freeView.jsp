<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">이미지</h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
        	<c:forEach items="${attachVO}" var="vo">
			    <div style="float:left;width:20%">
					<img src="${vo.file_path}${vo.real_name}" class="img-responsive img-rounded" alt="Cinque Terre" style="width:204px;height:auto;">
				</div>
			</c:forEach>
		</div>
	</div>
	
    <div class="row">
        <div class="col-lg-12">
            <!-- Basic Card Example -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">${boardVO.str_id} 님의 경매 글</h6>
                </div>
                <div class="card-body">
                <form action="/admin/notice/freeSelDel" method="post">
                	<input type="hidden" name="delChk" value="${boardVO.id}">
                	<input type="hidden" name="page" value="${page}">
                	<input type="hidden" name="countPerPage" value="${countPerPage}">
                	<div class="form-group">
                		<label class="form-label" for="title">작성자</label>
                		<input type="text" class="form-control" name="title" id="title" value="${boardVO.title}" readonly>
                	</div>
                    
		            <div class="form-group">
			            <label class="form-label" for="content">내용</label>
			            <textarea class="form-control" name="content" id="content" rows="10" cols="300" readonly>${boardVO.content}</textarea>                               
		            </div>
		            
                    <div class="form-group">
			            <label class="form-label" for="regDate">작성날짜</label>
			            <input type="text" class="form-control" id="regDate" name="regDate" value="${boardVO.regDate}" readonly>
		            </div>    
                    <div class="form-group">
			            <button class="float-right btn btn-danger">삭제</button>
		            </div>
		        </form>
                </div>
            </div>
        </div>
    </div>
    
	<div class="row" style="margin-top:15px">
		<div class="fa fa-commenting-o">답변 입력</div><br>
		<textarea class="form-control" name="replyContent" id="replyContent"></textarea>
		<div style="margin-top : 12px;"><button type="button" id="replySubmitBtn" class="btn btn-primary">글쓰기</button></div>
	</div>
	<!-- 댓글 뿌려질 공간 -->
	<div class="row reply-inbox" style="margin-top:15px;">
		<div class="panel panel-default" style="width: 100%;">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw">Reply</i>
			</div>
			<div class="panel-body">
				<ul class="reply-list list-group">
					
				</ul>
			</div>
		</div>
	</div>
	<div class="mt-3 mb-3 moreBtn" style="display:none;">
		<button type="button" id="moreList" class="btn btn-light btn-lg btn-block">더보기</button>
	</div>
		
	<!-- ------- -->
</div>
<!-- /.container-fluid -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e1a39caf77db54d18f2e59af576c57b"></script>
<script type="text/javascript">

//조회 인덱스
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
		url : "/api/admin/notice/freeReplyList",
		type : "post",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			if(result.length != 0){
				var reply_list = '';
				console.log("========================");
				console.log(result);
				for(var i=0;i<result.length;i++){
					reply_list += '<li class="mb-3 left clearfix list-group-item dataRow">';
					reply_list += '	<div class="header">';
					reply_list += '		<strong>'+result[i].str_id+' [관리자]</strong>';
					reply_list += '		<small class="pull-right text-mute float-right">'+result[i].regDate+'</small>';
					reply_list += '	</div>';
					reply_list += '	<p>'+result[i].content+'';
					if("${loginVO.member_level}" == 0){
						reply_list += '	<small class="pull-right"><button class="btn btn-danger delBtn float-right" onclick="replyDelete('+result[i].id+');" data-replyid="'+result[i].id+'">삭제</button></small>';
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

$("#replySubmitBtn").on("click",function(){
	var board_id = "${boardVO.id}";
	alert(board_id);
	var content = $("#replyContent").val();
	var param = {
			'board_id' : board_id,
			'content'	: content
	}
	$.ajax({
		url : "/api/admin/notice/freeReplyWrite",
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
</script>