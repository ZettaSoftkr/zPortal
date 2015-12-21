<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <div class="Main_Title_Area">
       <div class="title" id="reportTitle"></div>                
           <div class="sitemap"></div>
  	   </div>
	   <div class="box_type">
	       <div class="box_first">
	           <div class="box_second">                                  
	               <div class="lm_select_left">
	                   <ul>
	                     
	                       <li class="box_title">검색내용 </li>  
	                       <li class="box_form">
	                      		 <input type="text" name="searchContent" id="searchContent" class="input_free"> 
	                       </li>                                  
	                   </ul>                 
	               </div>
	                  <div class="lm_select_left">
	               		 <ul>
		                	<li class="box_title"><img src="${pageContext.request.contextPath}/assets/images/left/leaf_r.gif"> 정형보고서&nbsp;</li>
		                	<li class="box_title"><img src="${pageContext.request.contextPath}/assets/images/left/leaf_a.gif"> 고급분석</li>
	                	</ul>
	                </div>            
	                
	                  
	                <button type="button" class="search_button" title="초기화" id="searchClear" onclick="searchReportClear();">
	                   <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span>
	               </button> 
	               <button type="button" class="search_button" title="조회" onclick="searchReportList();">
	                   <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
	               </button>
	           </div>
	       </div>   
	   </div> 
	 <div class="Grid_Area">
      <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
         <table class="table_Type">
            <colgroup>
               
               <col width="80"/> 
               <col width="80"/>  
               <col width="200"/>  
               <col/>  
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
<script type="text/javascript">

// nameParam.add("bi_portal_menu_id");
// nameParam.add("bi_portal_menu_parent_id");
// nameParam.add("bi_menu_nm");
// nameParam.add("bi_menu_url_addr");
// nameParam.add("bi_sort_sn");
// nameParam.add("bi_menu_fm_yn");
// nameParam.add("bi_dc");
// nameParam.add("bi_updt_dt");

var headerTitle = ["번호","구분", "보고서명", "설명", "일자"];
var attrKey = "bi_portal_menu_id";  //keyvalue
var attrVal =        //attributy
[
{
	 name : "bi_menu_type_yn"
		, type : "button"
		, val : ""
		, funcNm:""
		, href:"Y"
},
{
	 name : "bi_menu_nm"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"Y"
},{
	 name : "bi_dc"
	, type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	name : "bi_updt_dt"
	,type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
} 
 
]; 

var viewStatus = "view"; //상세 페애지 성절[상세 페에지 -view,수정페이지 -edit]]
$(document).ready(function() {
	
	$("#searchClear").hide();
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	if("${searchKeyWord}" != ""){
		
		searchContent = "${searchKeyWord}";
		$("#searchClear").show();
		$("#searchContent").val(searchContent);
		
	}
	
	getSearchData(searchContent);



});
	
</script>
