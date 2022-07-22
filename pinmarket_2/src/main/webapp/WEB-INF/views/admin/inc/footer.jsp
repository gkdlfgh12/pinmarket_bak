<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">로그아웃</span>
                </button>
            </div>
            <div class="modal-body">현재 세션을 종료할 준비가 되었으면 아래에서 "로그아웃"을 선택하십시오.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/admin/member/logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2021</span>
        </div>
    </div>
</footer>

<script type="text/javascript">
var pathname = $(location).attr('pathname');
if(pathname == '/admin/member/list'){
	$(".member-menu").addClass("active");
}else if(pathname == '/admin/auction/list'){
	$(".auction-menu").addClass("active");
}else if(pathname == '/admin/product/list'){
	$(".product-menu").addClass("active");
}else if(pathname == '/admin/notice/freeList' || pathname == '/admin/notice/bestList'){
	$(".notice-menu").addClass("active");
	if(pathname == '/admin/notice/freeList'){
		$(".freeList-menu").addClass("active");
	}else if(pathname == '/admin/notice/bestList'){
		$(".bestList-menu").addClass("active");
	}
}
</script>