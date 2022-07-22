<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">옥션 글</h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
        	<c:forEach items="${attachVO}" var="vo">
			    <div style="float:left;width:20%">
					<img src="${vo.file_path}${vo.real_name}" class="img-responsive img-rounded" alt="Cinque Terre" style="width:204px;height:auto;">
				</div>
			</c:forEach>
		</div>
	</div>
	
    <div class="row">
        <div class="col-lg-12">
            <!-- Basic Card Example -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">${auctionVO.str_id} 님의 경매 글</h6>
                </div>
                <div class="card-body">
                	<div class="form-group">
                		제목 : ${auctionVO.title}
                	</div>
                	<div class="form-group">
                    	내용 : ${auctionVO.content}
                    </div>
                    <div class="form-group">
                    	만남 주소 : ${auctionVO.address1} ${auctionVO.address2}
                    </div>
                   	<div class="form-group">
				  		거래 위치
				  		<div id="map" style="width:500px;height:400px;">
				  			
				  		</div>
  					</div>
  					<div class="form-group">
                    	경매 기간 : ${auctionVO.startDate} ${auctionVO.endDate}
                    </div>
                    <div class="form-group">
                    	상태 : 
                    	<c:choose>
	                    	<c:when test="${auctionVO.status == 'comp'}">
	                    		거래 완료
	                    	</c:when>
	                    	<c:when test="${auctionVO.status == 'del'}">
	                    		관리자에 의해 삭제
	                    	</c:when>
	                    	<c:when test="${auctionVO.status == 'end'}">
	                    		마감
	                    	</c:when>
	                    	<c:when test="${auctionVO.status == 'wait'}">
	                    		대기
	                    	</c:when>
	                    	<c:when test="${auctionVO.status == 'open'}">
	                    		진행중
	                    	</c:when>
                    	</c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
    <div class="row rank-list">

        <!-- Pending Requests Card Example -->
        <!-- <div class="col mb-3">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col-3">
                            <div class="text-xs font-weight-bold text-warning text-uppercase">
                                Pending Requests
                            </div>
                            
                        </div>
                        <div class="col-9">
                            <div class="text-xs font-weight-bold text-warning text-uppercase">
                                Pending Requests
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
    </div>
    </div>
    <div class="container mb-3 mt-3">
	<button type="button" id="rankMoreList" class="btn btn-light btn-lg btn-block">더보기</button>
	</div>
</div>
<!-- /.container-fluid -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e1a39caf77db54d18f2e59af576c57b"></script>
<script type="text/javascript">
var latitude = ${auctionVO.latitude};
var longitude = ${auctionVO.longitude};

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

$("#rankMoreList").on("click",function(){
	startIndex += searchStep;
	rankMoreList(startIndex);
});

//조회 인덱스
var startIndex = 1;	// 인덱스 초기값
var searchStep = 5;	// 5개씩 로딩

rankMoreList(startIndex);

//랭크들 가져오는 js 이젠 상태값을 기준으로 매칭된게 있으면 그것만 가져오기
function rankMoreList(startIndex){
	endIndex = searchStep + startIndex - 1;
	var auction_id = ${auctionVO.id};
	var param = {
				'startIndex' : startIndex,
				'endIndex'	: endIndex,
				'auction_id' : auction_id
			}
	$.ajax({
		url : "/api/admin/rank/list",
		type : "post",
		data : JSON.stringify(param),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){ //result:리턴한 값, status:http상태 코드 값, xhr:통신 개체
			console.log(result);
			if(result.length != 0){
				var str_list = '';
				for(var i=0; i<result.length ;i++){
					/* str_list += '<div class="col mb-4 total-card">';
					str_list += '<div class="card">';
					if(result[i].payment_status == 1){
						str_list += '  <div class="card-header" style="background: #1cc88a;"><span>top10 상품 사용자</span><br>';
					}else{
						str_list += '  <div class="card-header">';
					}
					*/
					if(result[i].comp_status == 0){
						str_list += '<div class="col-xl-12 mb-3">';
						str_list += '<div class="card border-left-warning shadow h-100 py-2">';
					}else{
						str_list += '<div class="card-header" style="background: #1cc88a; margin-left: 12px;"><span class="icon text-white-50">';
						str_list += '<i class="fas fa-check"></i>';
						str_list += '</span>';
						str_list += '<span class="text" style="color: white;">선택된 랭크</span> </div><br>';
						str_list += '<div class="col-xl-12 mb-3">';
						str_list += '<div class="card border-left-success shadow h-100 py-2">';
					}
					str_list += '<div class="card-body">';
					str_list += '  <div class="row">';
					str_list += '  	<div class="col-3">';
					str_list += '      <img src="'+result[i].attachmentVO.file_path+result[i].attachmentVO.save_name+'" class="card-img-top ml-4" alt="...">';
					str_list += '   </div>';
					str_list += '  	<div class="col-9">';
					str_list += '      <div class="card-body">';
					str_list += '        <h5 class="card-title">작성자 : '+result[i].str_id+'</h5>';
					str_list += '        <p class="card-text">제목 : '+result[i].title+'</p>';
					str_list += '        <p class="card-text">내용 : '+result[i].content+'</p>';
					if(result[i].comp_status == 0){
						str_list += '        <p class="card-text">상태 : 미선택</p>';
					}else{
						str_list += '        <p class="card-text">상태 : 선택</p>';
					}
					str_list += '      </div>';
					str_list += '   </div>';
					str_list += '  </div>';
					str_list += '</div>';
					str_list += '</div>';
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
</script>