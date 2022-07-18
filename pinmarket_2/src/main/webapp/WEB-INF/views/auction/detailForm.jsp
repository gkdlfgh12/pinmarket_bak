<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- pricing -->
<div class="container">
	<div class="text-center">
		<h1 class="display mt-5 mb-5">상품 이미지</h1>
	</div>
</div>
<div class="container" style="width:500px;">
<div id="demo" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>

  <!-- The slideshow -->
<div class="carousel-inner">
	<c:forEach var="attachVO" items="${auctionInfo.attach}" varStatus="status">
		<c:if test="${status.index == 0}">
			<div class="carousel-item active">
				<img src="${attachVO.file_path}${attachVO.save_name}" class="d-block w-100" alt="이미지 파일">
				<div class="carousel-caption">
					<h3 style="color: black;">${attachVO.real_name}</h3>
				</div>
		  	</div>
		</c:if>
		<c:if test="${status.index >= 1}">
			<div class="carousel-item">
				<img src="${attachVO.file_path}${attachVO.save_name}" class="d-block w-100" alt="이미지 파일">
				<div class="carousel-caption mt-5">
					<h3 style="color: black;">${attachVO.real_name}</h3>
				</div>
		  	</div>
		</c:if>
  	</c:forEach>
  <!-- <div class="carousel-item">
    <img src="/upload/thumbAuctionImage/th_야구공1.jpg" class="d-block w-100 " alt="Chicago">
    <div class="carousel-caption">
  <h3>Los Angeles</h3>
  <p>We had such a great time in LA!</p>
</div>
  </div>
  <div class="carousel-item">
    <img src="/upload/thumbAuctionImage/th_악세서리2.jpg" class="d-block w-100" alt="New York">
    <div class="carousel-caption">
  <h3>Los Angeles</h3>
  <p>We had such a great time in LA!</p>
</div>
  </div> -->
</div>

  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev" style="background: black;">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next" style="background: black;">
    <span class="carousel-control-next-icon"></span>
  </a>

</div>
</div>
<div class="container mt-5 mb-5">
	<form action="/auction/write" method="post" enctype="multipart/form-data">
	  	<div class="form-group mb-3">
	    	<p class="font-weight-bolder">작성자</p>
	    	<p class="font-weight-light">${auctionInfo.auction.str_id}</p>
	  	</div>
	  	<div class="form-group mb-3">
	    	<p class="font-weight-bolder">제목</p>
	    	<p class="font-weight-light">${auctionInfo.auction.title}</p>
	  	</div>
	   	<hr>
	  	<div class="form-group">
	    	<p class="font-weight-bolder">거래 주소</p>
	    	<p class="font-weight-light">${auctionInfo.auction.address1} ~ ${auctionInfo.auction.address2}</p>
	  	</div>
	  	<div class="form-group">
	    	<p class="font-weight-bolder">상세 위치</p>
	    	<p class="font-weight-light">${auctionInfo.auction.address2}</p>
	  	</div>
	   	<hr>
	  	<div class="form-group">
	  		<p class="font-weight-bolder">거래 위치</p>
	  		<div id="map" style="width:500px;height:400px;">
	  			
	  		</div>
	  	</div>
	  	<hr>
	  	<div class="form-group">
    		<p class="font-weight-bolder">내용</p>
    		<p class="font-weight-light">${auctionInfo.auction.content}</p>
  		</div>
  		<hr>
  		<div class="form-group">
			<p class="font-weight-bolder">경매 기간</p>
			<p class="font-weight-light">${auctionInfo.auction.startDate} ~ ${auctionInfo.auction.endDate}</p>
		</div>
		<c:if test="${auctionInfo.auction.status == 'comp'}">
	  		<div class="form-group">
				<p class="font-weight-bolder">경매 상태</p>
				<p class="font-weight-light fa fa-star"><p class="font-weight-light fa fa-star-o">경매 완료</p>
				<p class="font-weight-light fa fa-star-o" /><p class="font-weight-light fa fa-star" />
			</div>
			<span>경매 호스트 : ${auctionInfo.auction.str_id}</span><br>
			<span>경매 게스트 : ${guest_id}</span>
		</c:if>
		<hr>
		<!-- <div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" disabled>
			<label class="form-check-label" for="inlineRadio3">3 (disabled)</label>
		</div> -->
		<br>
		<!-- Button trigger modal -->
		<div class="float-right">
		  	<c:if test="${loginVO.id != auctionInfo.auction.member_id && auctionInfo.auction.status != 'comp'}">
		  		<!-- data-toggle="modal" data-target="#myModal" 부트스트랩으로 모달 띄우는 방법 -->
		  		<button type="button" id="rankInputModal" class="btn btn-success mt-3" >경매 신청</button>  <!-- 글 작성자가 아닌 사람들한테만 표출 -->
		  	</c:if>
		  	<button type="button" onclick="goList();" class="btn btn-primary mt-3">목록</button>
	  	</div>
	</form>
	</div>
	<br>
<div class="container mt-5">
	<hr style="border: solid 1px black;">
	<h2 class="info-text">경매 신청자 리스트</h2>
<form method="post">
	<div class="row row-cols-1 row-cols-md-1 rank-list">
	  <!-- <div class="col mb-4">
	    <div class="card">
	      <div class="card-header"><input type="radio"> 작성자 : 아무개</div>
	      <div class="row">
	      	<div class="col-3">
		      <img src="/upload/thumbAuctionImage/th_악세서리2.jpg" class="card-img-top ml-4" alt="...">
		    </div>
	      	<div class="col-9">
		      <div class="card-body">
		        <h5 class="card-title">Card title</h5>
		        <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
		      </div>
		    </div>
		    
	      </div>
	    </div>
	  </div> -->
	</div>
</form>
</div>
<div class="container mb-3">
	<button type="button" id="rankMoreList" class="btn btn-light btn-lg btn-block">더보기</button>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">경매 신청</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<form action="/auction/rankInsert" method="post" enctype="multipart/form-data">
      		<input type="hidden" name="member_id" id="member_id" value="${loginVO.id}">
      		<input type="hidden" name="auction_id" id="auction_id" value="${auctionInfo.auction.id}">
	      	<div class="input-group mb-3">
				<span >상품 이미지</span>
			</div>
			<div class="custom-file mb-3">
				<input type="file" class="custom-file-input" id="profile" name="profile">
				<label class="custom-file-label" for="profile">Choose file</label>
			</div>
	        <div class="form-group mb-3">
		    	<label for="title">제목</label>
		    	<input type="text" class="form-control" id="title" name="title">
		  	</div>
		  	<div class="form-group">
	    		<label for="content">내용</label>
	    		<textarea class="form-control" id="content" rows="3" name="content" style="resize:none;"></textarea>
  			</div>
  			<%-- <c:if test="${loginVO.item_cnt > 0}"> --%>
	  			<!-- top 10 신청 여부 - 결제 시 생성 -->
  			<div class="form-check form-check-inline" id="item">
				<!-- <input class="form-check-input" type="checkbox" name="payment_status" id="payment_status" value="1">
				<label class="form-check-label" for="payment_status">top 5 쿠폰 사용</label> -->
			</div>
			<%-- </c:if> --%>
		    <div class="modal-footer mt-3">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="submit" class="btn btn-primary">Save changes</button>
		    </div>
	  	</form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e1a39caf77db54d18f2e59af576c57b"></script>
<script type="text/javascript">
console.log("${loginVO.item_cnt}");
$(function() {
	//경매 모달
	$('#myModal').on('shown.bs.modal', function () {
	  $('#myInput').trigger('focus')
	})
});
var latitude = ${auctionInfo.auction.latitude};
var longitude = ${auctionInfo.auction.longitude};

var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(longitude, latitude), //지도의 중심좌표.
	level: 3,  //지도의 레벨(확대, 축소 정도)
	mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

//지도에 마커를 생성하고 표시한다
var marker = new kakao.maps.Marker({
    position: new kakao.maps.LatLng(longitude, latitude), // 마커의 좌표
    map: map // 마커를 표시할 지도 객체
});

//지도에 원을 표시한다
var circle = new kakao.maps.Circle({
	map: map, // 원을 표시할 지도 객체
	center : new kakao.maps.LatLng(longitude, latitude), // 지도의 중심 좌표
	radius : 50, // 원의 반지름 (단위 : m)
	fillColor: '#FF0000', // 채움 색
	fillOpacity: 0.5, // 채움 불투명도
	strokeWeight: 3, // 선의 두께
	strokeColor: '#FF0000', // 선 색
	strokeOpacity: 0.9, // 선 투명도 
	strokeStyle: 'solid' // 선 스타일
});

function goList(){
	location.href="/auction/list";
}

$("#rankInputModal").on("click",function(){
	$.ajax({
		url : "/api/rank/check?id=${loginVO.id}&auctionId="+${auctionInfo.auction.id},
		type : "get",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			console.log(result);
			alert(result.split("_"));
			var result1 = result.split("_")[0];
			var item_cnt = result.split("_")[1];
			var str_top5 = '';
			if(result1 == 'permit'){
				$('#myModal').modal("show");
			}else{
				alert("이미 랭크를 등록했습니다.");
			}
			alert(item_cnt);
			if(item_cnt > 0){
				str_top5 += '<input class="form-check-input" type="checkbox" name="payment_status" id="payment_status" value="1">';
				str_top5 += '<label class="form-check-label" for="payment_status">top 5 쿠폰 사용</label>';
			}
			$("#item").empty();
			$("#item").append(str_top5);
		},
		error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
			alert('실패');
		}
	});
});

// 조회 인덱스
var startIndex = 1;	// 인덱스 초기값
var searchStep = 5;	// 5개씩 로딩
if("${auctionInfo.auction.status}" == "comp"){
	//여기는 경매가 완료 됬을때임
	rankCompList("${auctionInfo.auction.id}");
	$("#rankMoreList").hide();
}else{
	rankMoreList(startIndex);
}
//경매가 완료 되었을때
function rankCompList(auction_id){
	$.ajax({
		url : "/api/rank/rankCompList?auction_id="+auction_id,
		type : "get",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			console.log(result);
			if(result.length != 0){
				var str_list = '';
				str_list += '<div class="col mb-4 total-card">';
				str_list += '<div class="card">';
				str_list += '  <div class="card-header" style="background: #80f0d3;"><span class="fa fa-star" >경매 성사</span><span class="fa fa-star-o" ></span><br>';
				str_list += '작성자 : '+result.str_id;
				str_list += '<span style="float:right;">'+result.regDate+'</span></div>';
				str_list += '  <div class="row">';
				str_list += '  	<div class="col-3">';
				str_list += '      <img src="'+result.attachmentVO.file_path+result.attachmentVO.save_name+'" class="card-img-top ml-4" alt="...">';
				str_list += '    </div>';
				str_list += '  	<div class="col-9">';
				str_list += '      <div class="card-body">';
				str_list += '        <h5 class="card-title">'+result.title+'</h5>';
				str_list += '        <p class="card-text">'+result.content+'</p>';
				str_list += '    </div>';
				str_list += '   </div>';
				str_list += '  </div>';
				str_list += '</div>';
				$('.rank-list').append(str_list);
			}
		},
		error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
			alert('실패');
		}
	});
}

$("#rankMoreList").on("click",function(){
	startIndex += searchStep;
	rankMoreList(startIndex);
});
//랭크들 가져오는 js 이젠 상태값을 기준으로 매칭된게 있으면 그것만 가져오기
function rankMoreList(startIndex){
	endIndex = searchStep + startIndex - 1;
	var auction_id = $("#auction_id").val();
	
	var param = {
				'startIndex' : startIndex,
				'endIndex'	: endIndex,
				'auction_id' : auction_id
			}
	$.ajax({
		url : "/api/rank/list",
		type : "post",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			console.log(result);
			if(result.length != 0){
				var str_list = '';
				for(var i=0; i<result.length ;i++){
					str_list += '<div class="col mb-4 total-card">';
					str_list += '<div class="card">';
					if(result[i].payment_status == 1){
						str_list += '  <div class="card-header" style="background: #F08080;"><span>top10 상품 사용자</span><br>';
					}else{
						str_list += '  <div class="card-header">';
					}
					str_list += '작성자 : '+result[i].str_id;
					str_list += '<span style="float:right;">'+result[i].regDate+'</span></div>';
					str_list += '  <div class="row">';
					str_list += '  	<div class="col-3">';
					str_list += '      <img src="'+result[i].attachmentVO.file_path+result[i].attachmentVO.save_name+'" class="card-img-top ml-4" alt="...">';
					str_list += '    </div>';
					str_list += '  	<div class="col-9">';
					str_list += '      <div class="card-body" data-rankId="'+result[i].id+'" data-auctionId="'+result[i].auction_id+'">';
					str_list += '        <h5 class="card-title">'+result[i].title+'</h5>';
					str_list += '        <p class="card-text">'+result[i].content+'</p>';
					if("${auctionInfo.auction.member_id}" == "${loginVO.id}"){
						str_list += '      <button type="button" class="btn btn-secondary" onclick="rankComp('+result[i].auction_id+','+result[i].id+','+result[i].member_id+');" style="margin-bottom: 5px; float:right;">상품 선택</button>';
					}
					str_list += '    </div></div>';
 					str_list += '   </div>';
					str_list += '  </div>';
					str_list += '</div>';
				}
				$('.rank-list').append(str_list);
			}else{
				if($(".rank-list").children().length == 0){
					var str_list = '<div class="col mb-4 total-card"><div class="alert alert-danger" role="alert">랭크가 존재 하지 않습니다.</div></div>';
					$(".rank-list").append(str_list);
					$("#rankMoreList").hide();
					$(".info-text").hide();
				}else{
					alert('리스트가 존재하지 않습니다.');
					$("#rankMoreList").hide();
				}
			}
		},
		error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
			alert('실패');
		}
	});
}

function rankComp(auction_id,rank_id,guest_id){
	if(confirm("해당 상품을 선택하시겠습니까?")){
		$.ajax({
			url : "/api/rank/rankComp?auction_id="+auction_id+"&rank_id="+rank_id+"&guest_id="+guest_id+"&host_id="+${auctionInfo.auction.member_id},
			type : "get",
			success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
				console.log(result);
				if(result) location.reload();
				else alert('알수 없는 오류로 실패했습니다.');
			},
			error : function(xhr, status, err){ //xhr:통신개체, status:http상태 코드 값, err:오류 내용
				alert('실패');
			}
		});
	}
}

</script>