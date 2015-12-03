<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="Main_Title_Area">
    <div class="title" id="reportTitle"></div>                
        <div class="sitemap"></div>
   </div>
 <div class="box_type">
     <div class="box_first">
        
     </div>   
 </div> 
 <div class="Grid_Area">
      <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
         <table class="table_Type">
            <colgroup>
               <col width="50"/> 
               <col width="80"/>  
               <col />  
               <col width="100"/>  
               <col width="100"/>  
           </colgroup>
           <thead id="headTableContent">
              
           </thead>
           <tbody id="tableContent">		
               
           </tbody>
       </table> 
       </form> 
     </div>
	 <div class="paging_sample" id="paging">
	 </div> 
	 <div class="btn_area">
		<button type="button" class="button_Area" id="myPage_allDelete">
            <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>선택삭제</span>
        </button>
        <button type="button" class="button_Area" id="goMyPageWriteForm_btn">
            <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
        </button>
     </div> 

<script type="text/javascript">

var viewStatus = "view";
var headerTitle = ["번호", "보고서명", "등록자", "등록일" ];
var attrKey = "bi_bkmk_sn";  //keyvalue
var attrVal =        //attributy
[
{
	 name : "bi_menu_nm"
	, type : "link"
	, val : "bi_portal_menu_id"
	, funcNm:"goReport"
	, href:"Y"
}, {
	 name : "bi_user_nm"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "parseModifyDate"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}
]; 

$(document).ready(function() {
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	getMyPageListPageData(currentPage, searchtitle, searchContent);


});


	$("#dropdwon_menu").click(function(){
		   
	     
	    var url = "/"+ serviceName+"/menu/getRoleReportListData.do";
	    
	     
	     
	    $.ajax({
			
		    url:  url,
		    type: "post",
		    data: { bi_portal_menu_parent_id : 'M_2' ,bi_menu_type_yn : 'R'},
		    datatype: 'json',
		    success: function(data){
		    	var strTopMenu1 = "";
		    	
		    	//&parentId="+data[key][1]+"&currentId="+data[key][0]+"
		    	
		    	$.each( data, function( key, value ) {
		    		
		    		
		    	   
		    		strTopMenu1 +="  <li class=\"dropdown-submenu\" id=\"subMenu_"+data[key][0]+"\">";
		    		strTopMenu1 +=" <a tabindex='-1' href='#'  onclick='getSelectName(\""+data[key][0]+"\",\""+data[key][2]+"\");'  onmouseover='getSubList(\""+data[key][0]+"\");'>";
		    		strTopMenu1 +=  data[key][2]; 
		    		strTopMenu1 +=" </a>";
		    		
		    		strTopMenu1 +="</li>";
		    		
		    		
				
		    	});
		    	
		    	
		    	$("#droup_down_menu_1").html(strTopMenu1);
		    	
		    
		    }	
		 });
    	
  });
	
 function getSubList(parentId){
 	
 		  
 		    var url = "/"+ serviceName+"/menu/getRoleMenuListData.do";
 		    $.ajax({
 				
 			    url:  url,
 			    type: "post",
 			    data: { bi_portal_menu_parent_id : parentId},
 			    datatype: 'json',
 			    success: function(data){
 			    	
 			    	
 			    	$("#subMenu_"+ parentId+" ul").remove();
 			    	var strMenu = "";
 			    	if(data.length > 0){
 			    	strMenu +=" <ul class=\"dropdown-menu\" >"; 
 			    	
 			    	$.each( data, function( key, value ) {
 			    		
 			    		strMenu +="  <li class=\"dropdown-submenu\" id=\"subMenu_"+data[key][0]+"\">";
 			    		strMenu +=" <a tabindex='-1' href='#' onclick='getSelectName(\""+data[key][0]+"\",\""+data[key][2]+"\");'  onmouseover='getSubList(\""+data[key][0]+"\");'>";
 			    		strMenu +=  data[key][2]; 
 			    		strMenu +=" </a>";
 			    		
 			    		
 			    		
 			    	});
 			    	strMenu +=" </ul>";
 			    	}
 			    	$("#subMenu_"+ parentId).append(strMenu);
 			    	
 			    }	
 			 });
  		  
  	  }
 
	 function getSelectName(idVal,nameVal){
		 
		 $("#bi_portal_menu_id").val(idVal);
		 $("#bi_menu_nm").val(nameVal);
		 
		 $("#edit_bi_portal_menu_id").val(idVal);
		 $("#edit_bi_menu_nm").val(nameVal);
		 
	 }
	 
 
</script>

<style type="text/css">
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #ccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #fff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>

