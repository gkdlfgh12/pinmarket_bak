<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container-fluid">

   <!-- Page Heading -->
   <h1 class="h3 mb-2 text-gray-800">회원 관리</h1>
	<!-- Topbar Search -->
	<div class="mb-3">
    <form action="/admin/member/list" method="get" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
        <div class="input-group">
            <input type="text" name="strId" id="strId" class="form-control border-0 small" placeholder="아이디 검색"
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
            <form action="/admin/member/memberDelete" method="post" name="member-form" id="member-form">
            <input type="hidden" name="page" value="${page}">
            <input type="hidden" name="countPerPage" value="${countPerPage}">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                        	<th><input type="checkbox" name="allDelChk" id="allDelChk"></th>
                        	<th>아이디</th>
                            <th>이름</th>
                            <th>성별</th>
                            <th>로그인 타입</th>
                            <th>가입일</th>
                            <th>상태</th>
                            <th style="width: 136px;">보기</th>
                        </tr>
                    </thead>
                    <tbody class="auction-list">
                    	<c:forEach items="${list}" var="vo" varStatus="i">
                        <tr>
                        	<td><input type="checkbox" name="delChk" value="${vo.id}"></td>
                            <td>${vo.str_id}</td>
                            <td>${vo.name}</td>
                            <c:if test="${vo.gender == 'man'}">
                            	<td>남자</td>
                            </c:if>
                            <c:if test="${vo.gender == 'woman'}">
                            	<td>여자</td>
                            </c:if>
                            <c:if test="${vo.login_type == 'local'}">
                            	<td>핀마켓 로그인</td>
                            </c:if>
                            <c:if test="${vo.login_type == 'naver'}">
                            	<td>네이버 로그인</td>
                            </c:if>
                            <td>${vo.regdate}</td>
                            <td>${vo.status}</td>
                            <td>
                            	<button type="button" class="btn btn-primary detail-view" data-memberid="${vo.id}">자세히 보기</button>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
            	<button class="btn btn-danger float-right" id="del-btn" type="button">선택 삭제</button>
            	<button class="btn btn-primary float-right mr-2 write-member" type="button">멤버 생성</button>
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

<!-- 멤버 수정  Modal -->
<div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="memberModal" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">멤버 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
			<div class="modal-body">
				<div class="container">
					<div class="row mt-3">
						<h2><span class="member_str_id"></span>님 정보</h2>
					</div>
					<div class="row mt-5">
						<div class="col-12">
							<form id="product-form" action="/admin/member/writeMember" class="product-form" method="POST" enctype="multipart/form-data">
								<input type="hidden" name="id" id="id" value="0">
								<div class="row">
							        <div class="col-lg-12">
									    <div style="float:left;width:20%">
											<img src="" id="profile-img" class="img-responsive img-rounded" alt="Cinque Terre" style="width:204px;height:auto;">
										</div>
									</div>
								</div>
								<div class="input-group mb-3">
									<span>프로필 이미지</span>
								</div>
								<div class="custom-file mb-3">
									<input type="file" class="custom-file-input" id="profileImage"
										name="profileImage" aria-describedby="inputGroupFileAddon01">
									<label class="custom-file-label" for="profileImage">Choose
										file</label> <span class="save-file"></span>
								</div>
								<div class="form-group">
									<label class="form-label" for="name">이름</label> <input
										type="text" class="form-control" id="name"
										name="name" required style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="pw">비밀번호 수정</label> <input
										type="text" class="form-control" id="pw"
										name="pw" style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="chk_password">비밀번호 확인</label> <input
										type="text" class="form-control" id="chk_pw"
										name="chk_password" style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="address1">주소</label> <input
										type="text" class="form-control" id="address1"
										name="address1" required readonly>
								</div>
								<div class="form-group">
									<label class="form-label" for="address2">상세 주소</label> <input
										type="text" class="form-control" id="address2"
										name="address2" required>
								</div>
								<div class="form-group">
									<label class="form-label" for="zipcode">zipcode</label> <input
										type="text" class="form-control" id="zipcode"
										name="zipcode" tabindex="1" required readonly
										style="width: 25%">
								</div>
								<div class="form-group">
									<label class="form-label" for="tel">전화번호</label> <input
										type="text" class="form-control" id="tel"
										name="tel" required>
								</div>
								<div class="form-group">
									<label class="form-label" for="email">이메일</label> <input
										type="text" class="form-control" id="email"
										name="email" tabindex="1" required
										>
								</div>
								<div class="form-group">
									<label class="form-label" for="birth">생일</label> <input
										type="text" class="form-control" id="birth"
										name="birth" tabindex="1" required readonly style="width: 25%"
										>
								</div>
								<div class="form-group">
									<label class="form-label" for="login_type">로그인 타입</label> <input
										type="text" class="form-control" id="login_type"
										name="login_type" required readonly
										style="width: 25%">
								</div>
								
								<div class="form-group">
									<label class="form-label">멤버 레벨</label><br>
									<select class="form-control" name="member_level" id="member_level" style="width: 8%;">
										<c:forEach begin="0" end="5" var="i">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label class="form-label" for="item_cnt">남은 아이템</label> <input
										type="text" class="form-control" id="item_cnt"
										name="item_cnt" tabindex="1" required
										style="width: 25%">
								</div>
								<div class="form-group">
									<label class="form-label" for="regdate">가입일</label> <input
										type="text" class="form-control" id="regdate"
										name="regdate" tabindex="1" required readonly
										style="width: 25%">
								</div>

								<div class="form-group">
									<label class="form-label" for="nomal">정상</label> <input
										type="radio" id=nomal name="status" value="정상"> 
									<label class="form-label" for="withdrawal">탈퇴</label> <input
										type="radio" id="withdrawal" name="status" value="탈퇴">
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">수정</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
  </div>
</div>

<!-- 상품 작성  Modal -->
<div class="modal fade" id="memberWriteModal" tabindex="-1" aria-labelledby=""memberWriteModal"" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">멤버 관리</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
			<div class="modal-body">
				<div class="container">
					<div class="row mt-3">
						<h2>회원 생성</h2>
					</div>
					<div class="row mt-5">
						<div class="col-12">
							<form id="product-form" action="/admin/member/writeMember" class="product-form" method="POST" enctype="multipart/form-data">
								<input type="hidden" name="id" id="id" value="0">
								<div class="input-group mb-3">
									<span>프로필 이미지</span>
								</div>
								<div class="custom-file mb-3">
									<input type="file" class="custom-file-input" name="profileImage" aria-describedby="inputGroupFileAddon01">
									<label class="custom-file-label" for="profileImage">Choose
										file</label> <span class="save-file"></span>
								</div>
								<div class="form-group">
									<label class="form-label" for="str_id">아이디</label> <input
										type="text" class="form-control str_id" name="str_id" required style="width: 35%">
									<button type="button" class="mt-1 btn btn-light idDupleCheck">중복체크</button>
									<small id="idDuplHelp" class="form-text text-danger" style="display: none;">ID가 중복 되었습니다.</small>
									<small id="idHelp" class="form-text text-danger" style="display: none;">5글자 이상 입력하세요.</small>
								</div>
								<div class="form-group">
									<label class="form-label" for="name">이름</label> <input
										type="text" class="form-control" name="name" required style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="gender">성별</label> 
									<label class="form-label" for="gender">남자</label><input
										type="radio" name="gender" id="man" value="man" checked> 
									<label class="form-label" for="gender">여자</label> <input
										type="radio" name="gender" id="woman" value="woman">
								</div>
								<div class="form-group">
									<label class="form-label" for="pw">비밀번호 수정</label> <input
										type="password" class="form-control" name="pw" style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="chk_pw">비밀번호 확인</label> <input
										type="password" class="form-control" name="chk_pw" id="chk_pw" style="width: 35%">
								</div>
								<div class="form-group">
									<label class="form-label" for="address1">주소</label> <input
										type="text" class="form-control" name="address1" id="write_address1" required readonly>
								</div>
								<div class="form-group">
									<label class="form-label" for="address2">상세 주소</label> <input
										type="text" class="form-control" name="address2" id="write_address2" required>
								</div>
								<div class="form-group">
									<label class="form-label" for="zipcode">zipcode</label> <input
										type="text" class="form-control" name="zipcode" tabindex="1" id="write_zipcode" required readonly
										style="width: 25%">
								</div>
								<div class="form-group">
									<label class="form-label" for="tel">전화번호</label> <input
										type="text" class="form-control" name="tel" required>
								</div>
								<div class="form-group">
									<label class="form-label" for="email">이메일</label> <input
										type="text" class="form-control" name="email" tabindex="1" required>
								</div>
								<div class="form-group">
									<label class="form-label" for="birth">생일</label> <input
										type="text" class="form-control" name="birth" id="write_birth" tabindex="1" required readonly style="width: 25%">
								</div>
								<div class="form-group">
									<label class="form-label" for="login_type">로그인 타입</label> <input
										type="text" class="form-control" name="login_type" value="local" required readonly
										style="width: 25%">
								</div>
								
								<div class="form-group">
									<label class="form-label">멤버 레벨</label><br>
									<select class="form-control" name="member_level" style="width: 8%;">
										<c:forEach begin="0" end="5" var="i">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label class="form-label" for="item_cnt">남은 아이템</label> <input
										type="text" class="form-control" name="item_cnt" tabindex="1" required
										style="width: 25%">
								</div>

								<div class="form-group">
									<label class="form-label" for="nomal">정상</label> <input
										type="radio" name="status" value="정상" checked> 
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">생성</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
  </div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script type="text/javascript">
//jquery-ui.js 달력 api
$("#write_birth").datepicker({
	changeMonth: true,
	changeYear: true,
	dateFormat: "yy-mm-dd",
	dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
});

//중복체크
$(".idDupleCheck").on("click",function(){
	var str_id = $(".str_id").val();
	if(str_id.length < 5){
		alert("아이디는 5글자 이상이여야 합니다.");
		$("#idDuplHelp").hide();
		$("#idHelp").show();
		return false;
	}
	
	$.ajax({
		url : "/api/idDupleCheck?str_id="+str_id,
		type : "get",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			if(result == 'success'){
				alert("해당 아이디는 사용 가능합니다.");
				$("#idDupleChk").val("true");
				$("#idDuplHelp").hide();
				$("#idHelp").hide();
			}else{
				alert("해당 아이디는 현재 사용중입니다.");
				$("#idDuplHelp").show();
			}
		},
		error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
			alert("실패");
		}
	});
});

$(".write-member").on("click",function(){
	$("#memberWriteModal").modal("show");
});

//kakao postcode 주소 찾기
$("#write_address1").on("click",function(){
	new daum.Postcode({
        oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			console.log("data : "+data.roadAddress);
			console.log("data : "+data.address);

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('write_zipcode').value = data.zonecode;
            document.getElementById("write_address1").value = data.address;
      
        }
    }).open();
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

$("#allDelChk").on("click",function(){
	if($("#allDelChk").is(":checked")) $("input[name=delChk]").prop("checked", true);
	else $("input[name=delChk]").prop("checked",false);
});

$("#del-btn").on("click",function(){
	if($("input[name=delChk]:checked").length == 0){
		alert("선택된 게시글이 없습니다.");
		return false;
	}
	document.getElementById('member-form').submit();
});

$(".detail-view").on("click",function(){
	var id = $(this).data("memberid");
	$("#memberModal").modal("show");
	$.ajax({
		url : "/api/admin/member/detailInfo?id="+id,
		type : "get",
		success : function(result){
			console.log("result : 결과 ");
			console.log(result);
			if(result.attachmentVO == null){
				$("#profile-img").attr("src","/resources/image/noProfile.jpg");
			}else{
				$("#profile-img").attr("src",result.attachmentVO.file_path+result.attachmentVO.save_name);
			}
			$("#id").val(result.id);
			$(".member_str_id").text(result.str_id);
			$("#name").val(result.name);
			$("#address1").val(result.address1);
			$("#address2").val(result.address2);
			$("#zipcode").val(result.zipcode);
			$("#tel").val(result.tel);
			$("#email").val(result.email);
			$("#birth").val(result.birth);
			if(result.login_type == 'local'){
				$("#login_type").val("핀마켓 회원");
			}else if(result.login_type == 'naver'){
				$("#login_type").val("네이버 회원");
			}
			$("#member_level").val(result.member_level);
			$("#item_cnt").val(result.item_cnt);
			$("#regdate").val(result.regdate);
			$(".member-btn").text("수정");
			if(result.status == '정상'){
				$("#nomal").prop("checked","true");
			}else if(result.status == '탈퇴'){
				$("#withdrawal").prop("checked","true");
			}
		},
		error : function(){
			alert("실패");
		}
	})
});
</script>
