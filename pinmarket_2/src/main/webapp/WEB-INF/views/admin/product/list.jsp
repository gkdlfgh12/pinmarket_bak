<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Begin Page Content -->
<div class="container-fluid">

	<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-5">핀 마켓 경매 상품</h1>
		<p class="lead">경매의 랭크 등록시 랭크 등록 순서와 관계없이 각 상품의 기능별로 랭크가 등록 될 수 있다.</p>
		<p class="small">( 같은 상품을 사용한 사용자들끼리는 랭크를 등록한 순서별로 순위가 맺어집니다. )</p>
	</div>
	
	<div class="row">
	  <c:forEach items="${list}" var="vo">
	  <div class="col-md-4 mt-5 mb-3">
	    <div class="card-deck mb-3 text-center">
		    <div class="card" style="width: 18rem;">
			  <img class="card-img-top" src="${vo.attachmentVO.file_path}${vo.attachmentVO.save_name}" alt="Card image cap">
			  <div class="card-body">
			    <h5 class="card-title">${vo.product_name}</h5>
			    <h5>가격 : <span class="price">${vo.product_price} (￦)</span></h5>
			    <c:if test="${vo.status == 'ON'}">
			    	<h5>(공개)</h5>
			    </c:if>
			    <c:if test="${vo.status == 'OFF'}">
			    	<h5>(비공개)</h5>
			    </c:if>
			    <p class="card-text">${vo.descript}</p>
			    <a href="#" onclick="updateProduct('${vo.id}','${vo.product_name}','${vo.product_price}','${vo.descript}','${vo.attachmentVO.save_name}','${vo.status}');" class="btn btn-primary">수정하기</a>
			    <button id="deleteBtn" onclick="delProduct('${vo.id}');" class="btn btn-danger">삭제</button>
			  </div>
			</div>
		</div>
	  </div>
	  </c:forEach>
	</div>
	<div class="float-right">
		<button id="insertBtn" class="btn btn-primary">상품 등록</button>
	</div>
</div>

<!-- 상품  Modal -->
<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModal" aria-hidden="true">
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
			        <h2>상품 수정</h2>
			  </div>
			  <div class="row mt-5">
			          <form id="product-form" class="product-form" method="POST" enctype="multipart/form-data">
			          	 <input type="hidden" name="id" id="id" value="0">
						 <div class="input-group mb-3">
						 <span >상품 이미지</span>
						 </div>
						 <div class="custom-file mb-3">
							  <input type="file" class="custom-file-input" id="product_file" name="product_file" aria-describedby="inputGroupFileAddon01" >
							  <label class="custom-file-label" for="product_file">Choose file</label>
							  <span class="save-file"></span>
						 </div>
			              <div class="form-group mt-3">
			                  <label class="form-label" for="product_name">제목</label>
			                  <input type="text" class="form-control" id="product_name" name="product_name" placeholder="제목을 입력하세요." tabindex="1" required>
			              </div>                            
			              <div class="form-group">
			                  <label class="form-label" for="descript">내용</label>
			                  <textarea class="form-control" name="descript" id="descript" rows="10" cols="300" placeholder="내용을 입력하세요." required></textarea>                               
			              </div>
			              <div class="form-group mt-3">
			                  <label class="form-label" for="product_price">가격 (￦)</label>
			                  <input type="text" class="form-control" id="product_price" name="product_price" placeholder="가격" tabindex="1" required>
			              </div>
			              <div class="form-group">
			                  <label class="form-label" for="open">공개</label>
			                  <input type="radio" id="open" name="status" value="ON">
			                  <label class="form-label" for="private">비공개</label>
			                  <input type="radio" id="private" name="status" value="OFF">
			              </div>
			              <div class="text-center">
			                  <button type="submit" class="btn btn-start-order">등록</button>
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
//상품 작성 모달 보이게
$("#insertBtn").click(function(){
	$(".save-file").text("");
	$("#product_name").val("");
	$("#descript").val("");
	$("#product_price").val("");
	$("#open").prop("checked","true");
	
	$("#product-form").attr("action","/admin/product/insertProduct");
	$("#product_file").attr("required","true");
	
	$('#productModal').modal("show");
});
//상품 수정 모달 보이게
function updateProduct(id,product_name,product_price,descript,save_file,status){
	$(".save-file").text("저장된 파일 : "+save_file);
	$("#id").val(id);
	$("#product_name").val(product_name);
	$("#descript").val(descript);
	$("#product_price").val(product_price);
	
	$("#product-form").attr("action","/admin/product/updateProduct");
	$("#product_file").attr("required","false");
	
	console.log(id+" : "+product_name+" : "+descript+" : "+product_price);
	if(status == 'ON'){
		$("#open").prop("checked","true");
	}else{
		$("#private").prop("checked","true");
	}
	
	$('#productModal').modal("show");
}
function delProduct(id){
	alert(id);
	$.ajax({
		url : "/api/admin/product/delete?id="+id,
		type : "get",
		success : function(result){
			console.log("result : ~1 ");
			console.log(result);
			
		},
		error : function(){
		}
	})
}
</script>