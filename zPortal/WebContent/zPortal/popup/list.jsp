<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<input type="hidden" name="reportId" id="reportId"value="${reportId}">
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




<script type="text/javascript">

$(document).ready(function() {
	
	
	var url = "/" + serviceName + "/popup/getReport.do";	
	 
	var reportId = $("#reportId").val();
	
	
	$.ajax({

		url : url,
		type : "post",
		
		data : {
			bi_portal_menu_id : reportId
		},
		datatype : 'json',
		
		beforeSend : function() {

			$(".progress").show();
			$('.progress-bar').progressbar();

		},
		success : function(data) {
	
			$(".progress").hide();

			
			$("#searchContent").html(data.searchParam);
			$("#tableContent").html(data.resultStr);
			$("#reportTitle").html(data.reportNm);
			
			var defaultSelected = data.defaultSelected;
				
			for (var i in defaultSelected) {
				//alert('key is: ' + i + ', value is: ' + defaultSelected[i]);
				
				$("#"+i).val(defaultSelected[i]).attr("selected", "selected");
			}
		
			
		},
		error : function() {
			alert("false");

		}
	});
	
	
     
});

//검색조건 고정 
function selectChange(obj,selectNm,targetNm){
	
	
	//alert(obj.value);
	//alert(selectNm);
	//alert(targetNm);

	
	$.ajax({

		url : "/"+serviceName+"/popup/getSearchParamCascadingVal.do",
		type : "post",
		data : {
			 selectNm:selectNm
			,selectVal:obj.value
			,targetNm:targetNm
		},
		dataType : 'json',
		beforeSend : function() {

		

		},
		success : function(data) {
			
			//alert(JSON.stringify(data));
			
			$("#"+data.target).empty();
			
			$.each(data.rows,function(key, value) {
				
				
				$("#"+data.target).append('<option value="' + this['option'] + '">' + this['name'] + '</option>');

			});

	
			
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
  


function searhReport(){
	
	  
	var url = "/" + serviceName + "/popup/getReport.do";	
	
	    var postData = $("#searchForm").serializeArray();
	
		var reportId = $("#reportId").val();
	
		postData.push({ name: "bi_portal_menu_id", value: reportId });
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
			$("#reportTitle").html(data.reportNm);
			
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
  