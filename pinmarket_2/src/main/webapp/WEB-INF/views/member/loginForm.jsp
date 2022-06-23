<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <section id="wrapper">
    <a href="/member/logout">로그아웃</a>
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

</script>