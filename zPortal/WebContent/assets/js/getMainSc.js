
var comuServCode = "7"; //정보마당 코드  정의


getUser(); //사용자
goMyMenu(); //나의 메뉴	

goTopMenu(menuServCode); // 상단메뉴 보기
getLogList();

getNoticePopUp();//공지사항 팝업

//getBookMark(); //즐겨찾기




//getCommunity("board", "board/getMainData.do", "board/gotoPage.do?pageNm=list&viewStatus=ok","bi_bbs_sn","lev1_parent_id="+comuServCode+"&currentId=27"); // 메인화면 출력 게시판
getCommunity("notice", "notice/getMainData.do", "notice/gotoPage.do?pageNm=view","bi_nct_sn","lev1_parent_id="+comuServCode+"&currentId=26"); // 메인화면 출력 공지사항
getCommunity("qna", "qna/getMainData.do", "qna/gotoPage.do?pageNm=view","bi_qna_sn","lev1_parent_id="+comuServCode+"&currentId=27"); // 메인화면 출력 QNA
getCommunity("faq", "faq/getMainData.do", "faq/gotoPage.do?pageNm=list","bi_faq_sn","lev1_parent_id="+comuServCode+"&currentId=25"); // 메인화면 출력 FAQ







function getPopUpQlikviewPage(gotoPage){
	
	window.open(gotoPage, "newWin", "width=800, height=600, toolbar=no, scrollbars=yes, resizable = yes");
	
	//$(this).attr("target", "_blank"); 
	//window.open(gotoPage);
	//$(location).attr('href', gotoPage);

	
}

function getCommunity(tableId, url, linkPage, keyParam, param) {

	$.ajax({

		url : "/"+serviceName+"/"+url,
		type : "POST",
		data : {},
		dataType : 'json',
		beforeSend : function() {

		},
		success : function(data) {
				
			
		
			var tableString = "";			
			if(data.totalNo > 0 ){				
				
				$.each(data.rows, function(key, value) {
					
					//alert(JSON.stringify(data.rows));
				
					var idx = this[keyParam];
					var title = this['bi_titl'];
					var regDate = this['parseModifyDate'];
	
					if (title.length > 20) {
	
						title = title.substring(0, 18);
	
					}
	
					var today = new Date();
					var dateArray = regDate.split("-");
					var dateObj = new Date(dateArray[0], Number(dateArray[1]) - 1, dateArray[2]);
					var betweenDay = (today.getTime() - dateObj.getTime()) / 1000 / 60 / 60 / 24;
	
					
						
						if (parseInt(betweenDay) < 4) {
							
							tableString += "<li>";
							tableString += "<a href=\"/"+serviceName+"/"+linkPage+"&keyVal="+idx+"&"+param+"\">" +title+" <img src='/"+serviceName+"/assets/images/btn/btn_s_new.gif' alt='신규'/></a> <span>"+regDate+"</span>";
							tableString += "</li>";   
							
						}else{
							
							tableString += "<li>";
							tableString += "<a href=\"/"+serviceName+"/"+linkPage+"&keyVal="+idx+"&"+param+"\">" +title+"</a> <span>"+regDate+"</span>";
							tableString += "</li>"; 
						} 
						
				});
				
				if(data.totalNo != 5){
					
					for(var i=data.totalNo;i<5;i++){
						
							tableString += "<li><a href=\"#\">&nbsp;</a></li>";
					}
				
				}
			}else{
				
				
				tableString += "<li><a href=\"#\">&nbsp;</a></li>";                       
				tableString += "<li><a href=\"#\">&nbsp;</a></li>";
				tableString += "<li><a href=\"#\">&nbsp;</a></li>";                      
				tableString += "<li><a href=\"#\">&nbsp;</a></li>";  
				tableString += "<li><a href=\"#\">&nbsp;</a></li>";                      
				
			
			}
			// $("#tableContent").html(tableString); //목록

			$("#"+ tableId).html(tableString); // 목록

		},
		error : function() {
			
			//$("#progress_modal").modal('hide');
			//$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다. [메인 공지]");

		}

	});

}

function newReport(id){
	

	
	var url = "/" + serviceName + "/popup/list.do?reportId="+id;	
	
	var popup = window.open(url, "" , "width=980,height=600,toolba=no,scrollbars=yes, resizable = yes");
	popup.moveTo(0,0);
	
	
	
};


function getLogList() {
	
	$.ajax({

		url : "/" + serviceName + "/log/getLogList.do",
		type : "POST",
		data:{bi_menu_type_yn:"R"},
		dataType : 'json',
		// / beforeSend: function () {
		// },
		success : function(data) {
			
			var reportViewString = "";
			
			// alert(JSON.stringify(data));

			$.each(data, function(key, value) {

				
				reportViewString += "<li><span>0" + parseInt(key + 1) + "</span><a href=\"#\" onClick=\"newReport('"+this[0]+"');\">" + this[1] + "</a></li>";

			});

			// $("#tableContent").html(tableString); //목록

			$("#reportListUp").html(reportViewString); // 목록


		},
		error : function() {
			
			//$("#progress_modal").modal('hide');
			//$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			
		}
	});

}

function popup(gotoPage) {

	window.open(gotoPage, "newWin", "width=800, height=600, toolbar=no, scrollbars=yes, resizable = yes");

}


function goMdm(){
	
	 var myForm = document.mdmWin;
	 var url = "https://dim.ts2020.kr/servlet/ArtServlet";
	 window.open("" ,"mdmWin", "toolbar=no, width=800, height=600, directories=no, status=no,   scrollorbars=yes, resizable=yes"); 
	 myForm.action = url; 
	 myForm.method="post";
	 myForm.target="mdmWin";
	 myForm.submit();
}



function getNoticePopUp() {

	$.ajax({

		url : "/"+serviceName+"/notice/mainPopUp.do",
		type : "POST",
		data:{bi_popup_yn:"y"},
		dataType : 'json',
		beforeSend : function() {

		},

		success : function(data) {
			
			 //  alert(data);
			 //  alert(JSON.stringify(data));
			   
			  if(data != null){
			
			   $("#notice_title").html(data['bi_titl']);
			   $("#notice_content").html(data['htmlFormtWithCn']);
			  
				var strStartDate = data['bi_popup_bgn_dt'];
				var strEndDate   = data['bi_popup_end_dt'];
				
				var startDate = strStartDate.substring(0,10);
				var endDate = strEndDate.substring(0,10);
				var today = new Date();
	
				var startDateArray = startDate.split("-");
				var endDateArray = endDate.split("-");
				var startDateObj = new Date(startDateArray[0], Number(startDateArray[1]) - 1, startDateArray[2]);
				var endDateObj = new Date(endDateArray[0], Number(endDateArray[1]) - 1, endDateArray[2]);
				var startDateNum = startDateObj.getTime() / 1000 / 60 / 60 / 24;
				var todayDateNum = today.getTime() / 1000 / 60 / 60 / 24;
				var endDateNum = endDateObj.getTime() / 1000 / 60 / 60 / 24;
				
			  
			// $("#tableContent").html(tableString); //목록
				
				
				if(parseInt(todayDateNum) >= parseInt(startDateNum)   &&  parseInt(todayDateNum) <= parseInt(endDateNum)){
					var blnCookie    = getCookie("ts2020_popup");
					if(!blnCookie){
					$("#notice_modal").modal('show');
					}
				}
			
			}

		},
		error : function() {
			
		//	$("#progress_modal").modal('hide');
		//	$(".progress").hide();
		//	$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
//		error : function() {
//
//			$(document).trigger("add-alerts", [
//	  			 {
//	  			      'message': "데이터 조회중...",
//	  			      'priority': 'warning'
//	  			 }
//	  		 ]);
//
//		}

	});

}



function closeWin(winName, expiredays) {   
	
   setCookie( winName, "done" , expiredays);   
   $("#notice_modal").modal('hide');
  
}  
function closeWinAt00(winName, expiredays) {   
	
   setCookieAt00( winName, "done" , expiredays);   
   $("#notice_modal").modal('hide');
}  


function getCookie( name ) {  
	   var nameOfCookie = name + "=";  
	   var x = 0;  
	   while ( x <= document.cookie.length )  
	   {  
	       var y = (x+nameOfCookie.length);  
	       if ( document.cookie.substring( x, y ) == nameOfCookie ) {  
	           if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )  
	               endOfCookie = document.cookie.length;  
	           return unescape( document.cookie.substring( y, endOfCookie ) );  
	       }  
	       x = document.cookie.indexOf( " ", x ) + 1;  
	       if ( x == 0 )  
	           break;  
	   }  
	   return "";  
}  


function setCookie( name, value, expiredays ) {   
	    var todayDate = new Date();   
	   todayDate.setDate( todayDate.getDate() + expiredays );   
	   document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
} 


function setCookieAt00( name, value, expiredays ) {   
	    var todayDate = new Date();   
	    todayDate = new Date(parseInt(todayDate.getTime() / 86400000) * 86400000 + 54000000);  
	    if ( todayDate > new Date() )  
	    {  
	    expiredays = expiredays - 1;  
	    }  
	    todayDate.setDate( todayDate.getDate() + expiredays );   
	     document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
}  




