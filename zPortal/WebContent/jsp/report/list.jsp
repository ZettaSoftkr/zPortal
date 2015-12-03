<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="Main_Title_Area">
        <div class="title" id="reportTitle"><!-- 대시보드 <span class="title_name">[담당자 : 한범종 ]</span--></div>                
        <button type="button" class="button_Area" title="도움말" id="help_btn">
            <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>도움말</span>
        </button>
        <button type="button" class="button_Area" title="고급분석" id="goAdvenced_btn"  data-toggle="modal" data-target="#advenced_modal">
            <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>고급분석</span>
        </button>
        <button type="button" class="button_Area" title="백업분석" id="goBackup_btn"  data-toggle="modal" data-target="#backup_modal">
            <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>보고서백업</span>
        </button>
        <button type="button" class="button_Area" title="이력보기" id="goHistory_btn" data-toggle="modal" data-target="#history_modal">
            <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>이력보기</span>
        </button>
        <button type="button" class="button_Area" title="본 보고서" id="goReport_btn">
            <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>본 보고서</span>
        </button>
        <div class="sitemap"><!-- 경로 출력<span class="sel_txt">대시보드</span> --></div>
</div>

<div class="box_type">
    <div class="box_first"></div>   
</div>  
 <p><iframe id="reportUrl" name="reportUrl" src=""  width="1040px" height="790px"  style="overflow:x-hidden;" scrolling="no" frameBorder="0"></iframe></p> 
<input type="hidden" name="aurl" id="aurl" value="">
<input type="hidden" name="prevTitle" id="prevTitle" value="">

<div class="modal fade bs-example-modal-lg" id="history_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">보고서 이력</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
			    		
						   <table class="Notice_Board_table">
				            <colgroup>
				               <col width="50"/> 
				               <col width="80"/>  
				               <col />  
				               <col width="150"/> 
				           </colgroup>
				           <thead id="headTableContent">
				              
				           </thead>
				           <tbody id="tableContent">		
				               
				           </tbody>
				       </table> 
		      		 
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">닫기</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal fade bs-example-modal-lg" id="backup_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">보고서백업 이력</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
			    		
						   <table class="Notice_Board_table">
				            <colgroup>
				                <col width="50"/> 
				               <col />  
				               <col width="200"/>  
				               <col width="200"/>   
				           </colgroup>
				           <thead id="backupHeadContent">
				              
				           </thead>
				           <tbody id="backupTableContent">		
				               
				           </tbody>
				       </table> 
		      		 
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">닫기</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade bs-example-modal-lg" id="advenced_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">고급분석</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
			    		
						   <table class="Notice_Board_table">
				            <colgroup>
				               <col width="50"/> 
				               <col />  
				               <col width="200"/>  
				               <col width="200"/>  
				           </colgroup>
				           <thead id="advencedHeadContent">
				              
				           </thead>
				           <tbody id="advencedTableContent">		
				               
				           </tbody>
				       </table> 
		      		 
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">닫기</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript"> 



var headerTitle = ["번호", "이력일자 ", "이력내역", "보고서보기"];
var attrKey = "bi_h_mng_sn";  //keyvalue
var attrVal =        //attributy
[
 
{
	  name : "parseCreateDate"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "htmlFormtWithCn"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "bi_h_mng_sn"
	, type : "button"
	, val : "보고서보기"
	, funcNm:""
	, href:"N"
}
 
 
];



$(document).ready(function() {
	
	
	$("#help_btn").hide();
	$("#aqvw_btn").hide();
	$("#goHistory_btn").hide();
	$("#goBackup_btn").hide();
	$("#goAdvenced_btn").hide();
	$("#goReport_btn").hide();
	
	
	qlikviewUserChek();
	
	//이력보여주기 
    getHistoryData(1, currentId);
	
	
	
	
  //백업 
    getBackupData(1, currentId);
    //백업 
    getAdvencedData(1, currentId);
	

});


$("#help_btn").click(function(){
	
	var gotoPage = "/"+ serviceName +"/pdf/gotoPage.do?pageNm=list&currentId="+currentId;
	window.open(gotoPage, "newWin", "width=1100, height=830, toolbar=no, scrollbars=yes, resizable = yes").focus();
	
});
$("#aqvw_btn").click(function(){ // 고급분석가기 
	
	$("#reportUrl").attr("src",$("#aurl").val());
	
});

$("#goReport_btn").click(function(){ //기존 보고서 보기 
	
	$("#goReport_btn").hide();
	 reportList(currentId);
});


$("#goHistory_btn").click(function(){ //보고서 URL 빈값으로 호출 
	
	$("#reportUrl").attr("src","");
});


$("#goAdvenced_btn").click(function(){ //보고서 URL 빈값으로 호출 
	
	$("#reportUrl").attr("src","");
});


$("#goBackup_btn").click(function(){ //보고서 URL 빈값으로 호출 
	
	$("#reportUrl").attr("src","");
});
// 도움말 보여주기 



	
</script> 
  