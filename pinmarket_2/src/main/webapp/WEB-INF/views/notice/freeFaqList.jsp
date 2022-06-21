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
<div class="container">
	<!-- <h1>게시판 리스트 </h1> -->
	<h2 class="text-center mb-4 pb-2 text-primary fw-bold">FAQ</h2>
  <p class="text-center mb-5">
    	자유 질문
  </p>
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
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModal" aria-hidden="true">
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
			                  <button type="submit" class="btn btn-start-order">Send Message</button>
			              </div>
			          </form>
			  </div>
			</div>
      </div>
    </div>
  </div>
</div>

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
		$('#myModal').modal("show");
	}
</script>