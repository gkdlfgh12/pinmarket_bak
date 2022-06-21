<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5 mb-5">
	<form action="/auction/write" method="post" enctype="multipart/form-data">
		<div class="input-group mb-3">
			<span >상품 이미지</span>
		</div>
		<div class="custom-file mb-3">
			<input type="file" class="custom-file-input" id="profile_file" name="profile_file" aria-describedby="inputGroupFileAddon01">
			<label class="custom-file-label" for="profile_file1">Choose file</label>
		</div>
		<div class="custom-file mb-3">
			<input type="file" class="custom-file-input" id="profile_file" name="profile_file" aria-describedby="inputGroupFileAddon01">
			<label class="custom-file-label" for="profile_file2">Choose file</label>
		</div>
		<div class="custom-file mb-3">
			<input type="file" class="custom-file-input" id="profile_file" name="profile_file" aria-describedby="inputGroupFileAddon01">
			<label class="custom-file-label" for="profile_file3">Choose file</label>
		</div>
		<div class="custom-file mb-3">
			<input type="file" class="custom-file-input" id="profile_file" name="profile_file" aria-describedby="inputGroupFileAddon01">
			<label class="custom-file-label" for="profile_file4">Choose file</label>
		</div>
		<div class="custom-file mb-3">
			<input type="file" class="custom-file-input" id="profile_file" name="profile_file" aria-describedby="inputGroupFileAddon01">
			<label class="custom-file-label" for="profile_file5">Choose file</label>
		</div>
		<hr>
	  	<div class="form-group mb-3">
	    	<label for="title">제목</label>
	    	<input type="text" class="form-control" id="title" name="title">
	  	</div>
	  	<div class="form-group">
	    	<label for="address1">거래위치</label>
	    	<input type="text" class="form-control" id="address1" name="address1" readonly>
	  	</div>
	  	<div class="form-group">
	    	<label for="address2">상세주소</label>
	    	<input type="text" class="form-control" id="address2" name="address2">
	  	</div>
	  	<input type="hidden" name="latitude" id="latitude">
	  	<input type="hidden" name="longitude" id="longitude">
	  	
	  	<div class="form-group">
    		<label for="content">내용</label>
    		<textarea class="form-control" id="content" rows="3" name="content" style="resize:none;"></textarea>
  		</div>
  		
  		<div class="form-group">
			<label for="startDate">경매 시작일</label>
			<input type="text" class="form-control" name="startDate" id="startDate" name="startDate" readonly >
		</div>
  		<div class="form-group">
			<label for="endDate">경매 종료일</label>
			<input type="text" class="form-control" name="endDate" id="endDate" name="endDate" readonly >
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="status" id="open" value="open">
			<label class="form-check-label" for="open">공개</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="status" id="private" value="private">
			<label class="form-check-label" for="private">비공개</label>
		</div>
		<!-- <div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" disabled>
			<label class="form-check-label" for="inlineRadio3">3 (disabled)</label>
		</div> -->
		<br>
	  	<button type="submit" class="btn btn-primary mt-3">Submit</button>
	</form>
	</div>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e1a39caf77db54d18f2e59af576c57b&libraries=services"></script>
	<script type="text/javascript">
	
	//jquery-ui.js 달력 api
	$("#startDate").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		onClose : function( selectedDate ) {  // 날짜를 설정 후 달력이 닫힐 때 실행
            if( selectedDate != "" ) {
                // yyy의 minDate를 xxx의 날짜로 설정
                let date = new Date(selectedDate.split("-")[0],selectedDate.split("-")[1],selectedDate.split("-")[2]);
                date.setDate(date.getDate() + 7);
                //$("#endDate").datepicker("option", "maxDate", date.getFullYear()+"-"+(date.getMonth())+"-"+date.getDate());
                $("#endDate").val(date.getFullYear()+"-"+(date.getMonth())+"-"+date.getDate());
            }
        },
		minDate: 0,
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	});

	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	
	//kakao postcode 주소 찾기
	$("#address1").on("click",function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				console.log("data : ");
				console.log(data);

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address1").value = data.address;
             	// 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                	console.log("status : "+status);
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {
                        var result = results[0]; //첫번째 결과의 값을 활용
                		$("#latitude").val(result.x);
                		$("#longitude").val(result.y);
                    }
                });
            }
	    }).open();
	});
	</script>
</body>
</html>