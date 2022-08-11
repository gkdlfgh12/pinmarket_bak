<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <section id="wrapper">
      <div class="container mt-5 pt-5">
        <div class="row">
          <div class="col-12 col-sm-7 col-md-6 m-auto">
            <div class="card border-0 shadow">
              <div class="card-body">
                <form action="/member/login" method="post">
                  <input type="text" name="str_id" id="str_id" class="form-control my-4 py-2" placeholder="Username" />
                  <input type="password" name="pw" id="pw" class="form-control my-4 py-2" placeholder="Password" />
                 	<div class="row">
                 		<div class="col-12">
		                  	<div class="text-center mt-3">
		                  		<button type="submit" class="btn btn-dark login-btn">Login</button> 
		                  		<a href="${naver_url}"><img src="/resources/image/naver_btnG.png" style="width: 143px;"></a>
		                  	</div>
                  		</div>
                 	</div>
                 	<div class="row">
                 		<div class="col-12">
                 			<div class="text-right">
								<input type="checkbox" class="form-check-input" id="saveId">
								<label class="form-check-label" for="saveId">아이디 저장</label>
							</div>
						</div>
					</div>
                    <a href="/member/joinForm" class="float-right nav-link">회원가입</a>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
<script type="text/javascript">

var isMsg = true;
<c:if test="${!empty msg}">
	if(isMsg){
		setTimeout(function() {
				alert("${msg}");
			}, 
			200
		);
		isMsg = false;
	}
</c:if>

$(".login-btn").on("click",function(){
	if($("#str_id").val() == ""){ alert("아이디를 입력하세요."); return false; }
	if($("#pw").val() == ""){ alert("비밀번호를 입력하세요."); return false; }
});

$(document).ready(function(){
	// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
    var saveId = getCookie("saveId");
    $("#str_id").val(saveId); 
     
    // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
    if($("#str_id").val() != ""){ 
        $("#saveId").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#saveId").change(function(){ // 체크박스에 변화가 있다면,
        if($("#saveId").is(":checked")){ // ID 저장하기 체크했을 때,
            setCookie("saveId", $("#str_id").val(), 7); // 7일 동안 id 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("saveId");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("#str_id").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#saveId").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            setCookie("saveId", $("#str_id").val(), 7); // 7일 동안 쿠키 보관
        }
    });
});

//쿠키 저장하기 
// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}
 
// 쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) { // 쿠키가 존재하면
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정 
			end = cookieData.length;
            console.log("end위치  : " + end);
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

</script>