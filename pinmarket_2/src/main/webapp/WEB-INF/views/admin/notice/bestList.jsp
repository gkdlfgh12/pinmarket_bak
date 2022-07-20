<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
   <div class="d-sm-flex align-items-center justify-content-between mb-4">
       <h1 class="h3 mb-0 text-gray-800">자주묻는 질문 게시판 관리</h1>
   </div>

   <div class="row">
   	   <c:forEach items="${list}" var="bestVO" varStatus="i">
       <div class="col-lg-12 best-card" data-test="test">
	        <!-- Dropdown Card Example -->
	        <div class="card shadow mb-1">
	            <!-- Card Header - Dropdown -->
	            <div
	                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
	                <input type="text" class="m-0 font-weight-bold text-primary form-control title" name="title" value="${bestVO.title}" placeholder="질문을 입력하세요." tabindex="1" required>
	                <div class="dropdown no-arrow">
	                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
	                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
	                    </a>
	                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
	                        aria-labelledby="dropdownMenuLink">
	                        <div class="dropdown-header">관리</div>
	                        <a class="dropdown-item updateBest" data-faqid="${bestVO.id}" data-test1="test">수정</a>
	                        <a class="dropdown-item deleteBest" data-faqid="${bestVO.id}">삭제</a>
	                    </div>
	                </div>
	            </div>
	            <!-- Card Body -->
	            <div class="card-body">
	                <textarea class="form-control content" name="content" rows="3" cols="300" placeholder="답변을 입력하세요." required>${bestVO.content}</textarea>
	            </div>
	        </div>
        </div>
        </c:forEach>
        
    </div>
    
    <div class="row mt-2 mb-2">
    	<div class="col-12">
    		<button class="btn btn-primary float-right" id="writeBestFaq">작성</button>
    	</div>
    </div>
</div>
<!-- 자주묻는질문 입력  Modal -->
<div class="modal fade" id="bestFaqModal" tabindex="-1" aria-labelledby="bestFaqModal" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">자주묻는 질문 작성</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      		<div class="container">
			  <div class="row mt-3">
			        <h2>자주묻는 질문 작성</h2>
			  </div>
			  <div class="row mt-5">
		          <form action="/admin/notice/bestFaqWrite" id="product-form" class="product-form" method="POST">
					 <div class="form-group mt-3">
		                  <label class="form-label" for="product_name">질문</label>
		                  <input type="text" class="form-control" id="title" name="title" placeholder="질문을 입력하세요." tabindex="1" required>
		              </div>   
					  <div class="form-group">
		                  <label class="form-label" for="descript">답변</label>
		                  <textarea class="form-control" name="content" id="content" rows="10" cols="300" placeholder="답변을 입력하세요." required></textarea>                               
		              </div>
		              <div class="text-center">
		                  <button type="submit" class="btn btn-start-order">작성</button>
		              </div>
		          </form>
			  </div>
			</div>
      </div>
    </div>
  </div>
</div>
<!-- /.container-fluid -->

<script type="text/javascript">
$("#writeBestFaq").click(function(){
	$("#bestFaqModal").modal("show");
});

$(".updateBest").on("click",function(){
	var id = $(this).data("faqid");
	
	//console.log($(this).parent(".best-card").data("test"));
	var parent_tag = $(this).closest(".best-card");
	var title = $(parent_tag).find(".title").val();
	var content = $(parent_tag).find(".content").val()
	
	var data = {
		"id" : id,
		"title" : title,
		"content" : content
	}
	$.ajax({
		url : "/api/admin/notice/updateBest",
		type : "post",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		success : function(result){
			alert("수정 성공");
			location.reload();
			//$(".reply-list").empty();
			//moreList(1);
		},
		error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
			alert('수정 실패');
		}
	});
});

$(".deleteBest").on("click",function(){
	console.log($(this).data("faqid"));
	var board_id = $(this).data("faqid");
	$.ajax({
		url : "/api/admin/notice/bestDel?id="+board_id,
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
});

</script>