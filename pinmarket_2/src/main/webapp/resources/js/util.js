//날짜 비교
function dateComparison(date1, date2){
	
	var startDate = new Date(date1);
	var endDate = new Date(date2);
	var today = new Date();
	
	if(today > endDate){
		return 'end';
	}else{
		return 'normal';
	}
}

//날짜 포맷
function dateComparison(){
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);
	
	var hours = ('0' + today.getHours()).slice(-2); 
	var minutes = ('0' + today.getMinutes()).slice(-2);
	var seconds = ('0' + today.getSeconds()).slice(-2);
	
	var dateString = year + '-' + month  + '-' + day +' '+ hours + ':' + minutes  + ':' + seconds;
	return dateString;
}

$(function(){
	//우측 메뉴
	$('#back-to-chat').addClass('show fa fa-commenting fa-3x');
	$('#back-to-chat').on('click',function(e){
		
	});
	
	$('#back-to-top').addClass('show');
	$('#back-to-top').on('click',function(e){
		e.preventDefault();
		$('html,body').animate({scrollTop:0},600);
	});
	
	$('#back-to-write').addClass('show fa fa-plus fa-3x');
	$('#back-to-write').on('click',function(e){
		location.href="/auction/writeForm";
		//e.preventDefault();
		//$('html,body').animate({scrollTop:0},600);
	});
  
  $(window).scroll(function() {
    if ($(document).scrollTop() > 100) {
      $('#back-to-top').addClass('show');
    } else {
      $('#back-to-top').removeClass('show');
    }
  });
});