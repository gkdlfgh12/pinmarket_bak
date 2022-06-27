<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="wrapper">
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
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModal" aria-hidden="true">
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	$('#myModal').modal("show");
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
</script>