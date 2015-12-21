<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div  id="mainReportContent">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel"></div>
</div>
<div class="mainContent">
	<p>
		<img src="${pageContext.request.contextPath}/assets/images/img/keyvisual_main.jpg" alt="transportation safety">
	</p>
</div>

<script type="text/javascript">

$(document).ready(function() {
	
	$.ajax({	
		
		url : "/" + serviceName + "/setMain/getListCnt.do",
		type : "post",
		data : {
			bi_portal_menu_parent_id : "M_2"
		},
		datatype : 'json',
		beforeSend : function() {

		

		},
		success : function(data) {
			
			if(data > 0){
				
				$("#mainContent").hide();
				$("#mainReportContent").show();
				
			}else{
				
				
				 reportList();
				
				$("#mainContent").show();
				$("#mainReportContent").hide();
				
			}
			
			
		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}

	});	
	



});
function reportList(){

	$.ajax({

		url : "/" + serviceName + "/report/getMainPromptUrl.do",
		type : "post",
		datatype : 'json',
		beforeSend : function() {

		},
		success : function(data) {
			
			 var htmlVal = "";
			 
			
			 htmlVal += "<ol class=\"carousel-indicators\"> ";
			  for (var i=0; i< data.length;i++) {
				  
			if(i == 0){
				 htmlVal += "<li data-target=\"#carousel-example-generic\" data-slide-to="+i+" class=\"active\"></li> ";
				 }else{
					 
				htmlVal += "<li data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"></li>"; 
				 }
				
			 }
			 htmlVal += " </ol> ";
			 
			 htmlVal += "<div class=\"carousel-inner\"> ";
			 for (var i=0; i< data.length;i++) {
				 if(i==0){
					 
					 htmlVal += " <div class=\"item active\" class=\"tales\"> ";
					 htmlVal += "<section class=\"boardlist\">";
					 htmlVal += "  <h3 class=\"line\"><span class=\"textt\">"+data[i]['reportNm']+"</span></h3> ";
					 htmlVal += " <ul>";
					 htmlVal += " <li>"+data[i]['param']+"</li>";
					 htmlVal += " </ul>";
					 htmlVal += "</section>";
					// htmlVal += " <object title=\"YouTube video player\" class=\"\" type=\"text/html\" width=\"1280\" height=\"750\" data=\""+data[i]+"\" allowFullScreen></object>";
					             //  iframe src
					             
					 htmlVal +=  data[i]['resultStr'];             
					// htmlVal += " <iframe src=\""+data[i]['url']+"\" ></iframe>";
					 //htmlVal += "     <img src=\""+$(data[i]).attr("src")+"\" alt=\""+$(data[i]).attr("alt")+"\" width='100%'> ";
					// htmlVal += JSON.stringify($(data[i]));
					 htmlVal += "   </div> ";
					 
				 }else{
					 
					 htmlVal += " <div class=\"item\" class=\"tales\" > ";
					 htmlVal += "<section class=\"boardlist\">";
					 htmlVal += "  <h3 class=\"line\"><span class=\"textt\">"+data[i]['reportNm']+"</span></h3> ";
					 htmlVal += " <ul>";
					 htmlVal += " <li>"+data[i]['param']+"</li>";
					 htmlVal += " </ul>";
					 htmlVal += "</section>";
					 //htmlVal += " <object title=\"YouTube video player\" class=\"\" type=\"text/html\" width=\"1280\" height=\"750\" data=\""+data[i]+"\" allowFullScreen></object>";
					// htmlVal += "     <img src=\""+$(data[i]).attr("src")+"\" alt=\""+$(data[i]).attr("alt")+"\" width='100%'> ";
					//  htmlVal += " <iframe src=\""+data[i]['url']+"\"></iframe>";
					 htmlVal +=  data[i]['resultStr'];     
					htmlVal += "   </div> ";
					 
				 }
			 }
			 htmlVal += " </div> ";
			 htmlVal += "  <a class=\"left carousel-control\" href=\"#carousel-example-generic\" data-slide=\"prev\">";
			 htmlVal += "    <span class=\"glyphicon glyphicon-chevron-left\"></span>";
			 htmlVal += "  </a>";
			 htmlVal += "  <a class=\"right carousel-control\" href=\"#carousel-example-generic\" data-slide=\"next\">";
			 htmlVal += "    <span  id=\"rightBtn\" class=\"glyphicon glyphicon-chevron-right\"></span>";
			 htmlVal += "  </a>";
			
		    $("#carousel-example-generic").html(htmlVal);
			
			
		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});


	}

</script>