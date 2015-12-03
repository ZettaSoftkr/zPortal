<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<input type="hidden" name="reportId" id="reportId"value="${reportId}">
<div class="boardarea" id="listContent">
     <section class="boardlist" >
	 <h3 class="line"><span class="textt" id="reportTitle"></span></h3>
	 <div class="searcharea">
	 <section class="boardlist">
	 		<form name="searchForm">
		    <ul class="faqlist" id="searchContent">
		    </ul>
		    </form>
	</section>
	</div>
	 <ul class="faqlist" id="tableContent">
				
	 </ul>
	 </section>
</div>



<script type="text/javascript">

$(document).ready(function() {
	
	
	var url = "/" + serviceName + "/popup/getReport.do";	
	
	
	reportList(url);
	
	function reportList(url){
	
	$.ajax({

		url : url,
		type : "post",
		
		data : {
			bi_portal_menu_id : currentId
		},
		datatype : 'json',
		
		beforeSend : function() {

			$(".progress").show();
			$('.progress-bar').progressbar();

		},
		success : function(data) {
	
			$(".progress").hide();
			
// 			alert(JSON.stringify(data));
// 			alert(JSON.stringify(data.searchParam));
// 			alert(JSON.stringify(data.resultStr));
//		    alert(JSON.stringify(data.defaultSelected));
			
			$("#searchContent").html(data.searchParam);
			$("#tableContent").html(data.resultStr);
			$("#reportTitle").html(data.reportNm + " 담당자 : [" + data.reportKeyword+"]");
			
			var defaultSelected = data.defaultSelected;
			
			for (var i in defaultSelected) {
				//alert('key is: ' + i + ', value is: ' + defaultSelected[i]);
				
				$("#"+i).val(defaultSelected[i]).attr("selected", "selected");
			}
		
			//data.defaultSelected;
			//alert(JSON.stringify(data));
			
		},
		error : function() {
			alert("false");

		}
	});
	
	
	}
	
	
     
});

function searhReport(){
	
	  
	var url = "/" + serviceName + "/popup/getReport.do";	
	
	var postData = $("#searchForm").serializeArray();
	
	
	
	postData.push({ name: "bi_portal_menu_id", value: currentId });
	postData.push({ name: "p_yyyy", value: $("#p_yyyy").val() });
	postData.push({ name: "p_mm", value: $("#p_mm").val() });
	postData.push({ name: "p_week", value: $("#p_week").val() });
	postData.push({ name: "p_area", value: $("#p_area").val() });
	postData.push({ name: "p_inspect", value: $("#p_inspect").val() });
	postData.push({ name: "p_car", value: $("#p_car").val() });
	postData.push({ name: "p_maker", value: $("#p_maker").val() });		
	postData.push({ name: "p_front", value: $("#p_front").val() });	
	postData.push({ name: "p_front_cycle", value: $("#p_front_cycle").val() });	
	postData.push({ name: "p_cycle", value: $("#p_cycle").val() });	
    

   
	$.ajax({

		url : url,
		type : "post",
		data :postData,
		datatype : 'json',
		
		beforeSend : function() {

			$(".progress").show();
			$('.progress-bar').progressbar();

		},
		success : function(data) {
	
			$(".progress").hide();

			$("#searchContent").html(data.searchParam);
			$("#tableContent").html(data.resultStr);
			$("#reportTitle").html(data.reportNm + " 담당자 : [" + data.reportKeyword+"]");
			
			var defaultSelected = data.defaultSelected;
			
			for (var i in defaultSelected) {
				//alert('key is: ' + i + ', value is: ' + defaultSelected[i]);
				
				$("#"+i).val(defaultSelected[i]).attr("selected", "selected");
			}
		
			//data.defaultSelected;
			//alert(JSON.stringify(data));
			
		},
		error : function() {
			alert("false");

		}
	});
  }

</script> 
  