<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container-fluid">

   <!-- Page Heading -->
   <h1 class="h3 mb-2 text-gray-800">자유질문 게시판 관리</h1>
	<!-- Topbar Search -->
	<div class="mb-3">
    <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
        <div class="input-group">
            <input type="text" name="title" id="title" class="form-control border-0 small" value="" placeholder="제목 검색"
                aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search fa-sm"></i>
                </button>
            </div>
        </div>
    </form>
    </div>
   <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
        </div>
        <div class="card-body">
            <form action="/admin/notice/freeSelDel" method="post" name="notice-form" id="notice-form">
            <input type="hidden" name="page" value="${page}">
            <input type="hidden" name="countPerPage" value="${countPerPage}">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                        	<th><input type="checkbox" name="allDelChk" id="allDelChk"></th>
                        	<th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>조회수</th>
                            <th>작성일</th>
                            <th style="width: 136px;">보기</th>
                        </tr>
                    </thead>
                    <tbody class="auction-list">
                    	<c:forEach items="${list}" var="vo" varStatus="i">
                        <tr>
                        	<td><input type="checkbox" name="delChk" value="${vo.id}"></td>
                            <td>${vo.rnum}</td>
                            <td>${vo.title}</td>
                            <td>${vo.str_id}</td>
                            <td>${vo.hit}</td>
                            <td>${vo.regDate}</td>
                            <td><button type="button" class="btn btn-primary" onclick="goDetail(${vo.id});">자세히 보기</button></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
            	<button class="btn btn-danger float-right" id="del-btn" type="button">선택 삭제</button>
            </div>
            </form>
            <div aria-label="Page navigation example" style="margin-left: 50%;"> 
			<ul class="pagination">
			<c:if test="${pc.prev }">
				<li class="page-item">
				    <a class="page-link" href="/admin/auction/list?page=${pc.beginPage - 1}&countPerPage=${pc.countPerPage}&title=${title}">
				    	Next
				    </a>
			   	</li>
			</c:if>
			<c:forEach begin="${pc.beginPage }" end="${pc.endPage}" var="i">
				<li class="page-item ${ pc.page == i ? ' active' : ' '}"><a class="page-link" href="/admin/auction/list?page=${i}&countPerPage=${pc.countPerPage}&title=${title}">${i}</a></li>
			</c:forEach>
			<c:if test="${pc.next }">
			<li class="page-item">
				<a class="page-link" href="/admin/auction/list?page=${pc.endPage + 1}&countPerPage=${pc.countPerPage}&title=${title}">Next</a>
			</li>
			</c:if>
		</ul>
	</div>
        </div>
    </div>
</div>

<script type="text/javascript">
function goDetail(id){
	location.href="/admin/notice/freeView?id="+id;
}
$("#allDelChk").on("click",function(){
	if($("#allDelChk").is(":checked")) $("input[name=delChk]").prop("checked", true);
	else $("input[name=delChk]").prop("checked",false);
});
$("#del-btn").on("click",function(){
	if($("input[name=delChk]:checked").length == 0){
		alert("선택된 게시글이 없습니다.");
		return false;
	}
	document.getElementById('notice-form').submit();
	
});
</script>
