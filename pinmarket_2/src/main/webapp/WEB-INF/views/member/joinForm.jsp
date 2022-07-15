<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="container mb-3">
	<a href="/member/logout">로그아웃</a>
	<form action="/member/join" name="joinForm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="idDupleChk" value="false">
		<div class="form-group">
			<label for="str_id">아이디 *</label>
			<input type="text" class="form-control" name="str_id" id="str_id">
			<button type="button" class="mt-1 btn btn-light idDupleCheck">중복체크</button>
			<small id="idDuplHelp" class="form-text text-danger" style="display: none;">ID가 중복 되었습니다.</small>
			<small id="idHelp" class="form-text text-danger" style="display: none;">5글자 이상 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label for="password">비밀번호 *</label>
			<input type="password" class="form-control" name="pw" id="password">
			<small id="pwHelp" class="form-text text-danger" style="display: none;">비밀번호를 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label for="password_chk">비밀번호 확인 *</label>
			<input type="password" class="form-control" id="password_chk">
			<small id="pwchHelp" class="form-text text-danger" style="display: none;">비밀번호 확인을 입력하세요.</small>
			<small id="pwchHelp2" class="form-text text-danger" style="display: none;">비밀번호가 일치하지 않습니다.</small>
		</div>
		
		<div class="form-group">
			<label for="name">이름 *</label>
			<input type="text" class="form-control" name="name" id="name">
			<small id="nameHelp" class="form-text text-danger" style="display: none;">이름을 입력하세요.</small>
		</div>

		<div class="form-group">
			<label for="email">이메일 *</label>
			<input type="email" class="form-control" name="email" id="email">
			<small id="emailHelp" class="form-text text-danger" style="display: none;">이메일을 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label>성별 *</label> <br>
			<label for="man">남자</label>
			<input type="radio" name="gender" id="man" value="man" checked>
			<label for="woman">여자</label>
			<input type="radio" name="gender" id="woman" value="woman">
		</div>
		
		<div class="form-group">
			<label for="address1">주소*</label>
			<input type="text" class="form-control mb-3" name="address1" id="address1" readonly>
			<div class="row">
				<div class="col-2">
					<input type="text" class="form-control mb-3" name="zipcode" id="zipcode" readonly>
				</div>
			</div>
			<label for="address2">상세 주소 *</label>
			<input type="text" class="form-control" name="address2" id="address2">
			<small id="emailHelp" class="form-text text-danger" style="display: none;">주소를 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label for="birth">생일 *</label>
			<input type="text" class="form-control" name="birth" id="birth" readonly >
			<small id="birthHelp" class="form-text text-danger" style="display: none;">생일은 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label for="tel">전화번호 *</label>
			<input type="text" class="form-control" name="tel" id="tel">
			<small id="telHelp" class="form-text text-danger" style="display: none;">전화번호를 입력하세요.</small>
		</div>
		
		<div class="form-group">
			<label for="file">프로필 사진 *</label><br>
			<input type="file" id="file" name="profileImage">
			<small id="fileHelp" class="form-text text-danger" style="display: none;">프로필 이미지를 입력하세요.</small>
		</div>
		<button type="button" class="btn btn-primary validation">Submit</button>
	</form>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">


$(function(){
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
	
	//jquery-ui.js 달력 api
	$("#birth").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	});
	
	//중복체크
	$(".idDupleCheck").on("click",function(){
		var str_id = $("#str_id").val();
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
	
	$(".validation").on("click",function(){
		//8 ~ 16자 영문, 숫자 조합
		var passReg = new RegExp(/^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/);
		//이메일 정규식
		var emailReg = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
		
		if($("#idDupleChk").val() == "false"){ alert("id 중복체크가 필요합니다."); $("#idDupleChk").show(); return false; }
		if($("#str_id").val() == ""){ alert("id를 입력하세요."); $("#idHelp").show(); return false; }
		if($("#str_id").val().length < 5){ alert("id는 5글자 이상입력하세요."); $("#idHelp").show(); return false; }
		if($("#password").val() == ""){ alert("비밀번호를 입력하세요."); $("#pwHelp").show(); return false; }
		if(!passReg.test($("#password").val())){
			alert("비밀번호 규칙이 일치하지 않습니다.");
			return false;
		}
		if($("#password_chk").val() == ""){ alert("비밀번호 확인을 입력하세요."); $("#pwchHelp").show(); return false; }
		if($("#password").val() != $("#password_chk").val()){ alert("비밀번호와 비밀번호 확인이 일치하지 않습니다."); $("#pwchHelp2").show(); return false; }
		if($("#name").val() == ""){ alert("이름을 입력하세요."); $("#nameHelp").show(); return false; }
		if($("#email").val() == ""){ alert("이메일을 입력하세요."); $("#emailHelp").show(); return false; }
		if(!emailReg.test($("#email").val())){
			alert("이메일 규칙이 일치 하지 않습니다.");
			return false;
		}
		if($("#address1").val() == "" || $("#zipcode").val() == ""){ alert("주소를 입력하세요."); $("#emailHelp").show(); return false; }
		if($("#address2").val() == ""){ alert("상세주소를 입력하세요."); $("#emailHelp").show(); return false; }
		if($("#birth").val() == ""){ alert("생일을 입력하세요."); $("#birthHelp").show(); return false; }
		if($("#tel").val() == ""){ alert("전화번호를 입력하세요."); $("#telHelp").show(); return false; }
		if($("#file").val() == ""){ alert("프로필 이미지를 등록하세요."); $("#fileHelp").show(); return false; }
		document.joinForm.submit();
	});
});

</script>