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
			          <form id="contact-form" class="form" action="/notice/freeModify" method="post" role="form">
			          	  <input type="hidden" name="id" value="${boardVO.id}">
			          	  <input type="hidden" name="page" value="${pageVO.page}">
			          	  <input type="hidden" name="countPerPage" value="${pageVO.countPerPage}">
			              <div class="form-group">
			                  <label class="form-label" for="name">제목</label>
			                  <input type="text" class="form-control" id="title" name="title" value="${boardVO.title}">
			              </div>                            
			              <div class="form-group">
			                  <label class="form-label" for="content">내용</label>
			                  <textarea class="form-control" name="content" id="content" rows="10" cols="300">${boardVO.content}</textarea>                               
			              </div>
				          <!-- 자기가 쓴 게시글에서만 표출 -->
			              <c:if test="${boardVO.member_id == loginVO.id}">
				              <div class="text-center">
				                  <button type="submit" class="btn btn-start-order">Send Message</button>
				              </div>
			              </c:if>
			          </form>
			          <div class="float-right">
				          <button class="btn btn-primary" onclick="goFreeFaqList();">목록</button>
			          </div>
			  </div>
			</div>
	</div>
</div>
<script type="text/javascript">
	function goFreeFaqList(){
		
		location.href="/notice/freeFaqList?page=${pageVO.page}&countPerPage=${pageVO.countPerPage}";
	}
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