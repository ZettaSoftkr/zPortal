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
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㄱ');"> ㄱ </a> </li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㄴ');"> ㄴ </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㄷ');"> ㄷ   </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㄹ');"> ㄹ </a> </li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅁ');"> ㅁ </a> </li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅅ');"> ㅅ  </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅇ');"> ㅇ </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅈ');"> ㅈ </a> </li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅊ');"> ㅊ  </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅋ');"> ㅋ  </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅌ);"> ㅌ  </a></li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅍ');"> ㅍ </a>  </li>  
	                       <li class="box_title"><a href="#" onclick="getIndexSearchData('ㅎ');"> ㅎ  </a></li>  
	                      
	                   </ul>                 
	               </div>
	                <div class="lm_select_left">
	               		 <ul>
		                	<li class="box_title"><img src="${pageContext.request.contextPath}/assets/images/left/leaf_r.gif"> 정형보고서&nbsp;</li>
		                	<li class="box_title"><img src="${pageContext.request.contextPath}/assets/images/left/leaf_a.gif"> 고급분석</li>
	                	</ul>
	                </div>
	             
	           </div>
	       </div>   
	   </div> 
	 <div class="Grid_Area">
      <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
         <table class="table_Type">
            <colgroup>
               <col width="50"/> 
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
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	var searchKeyword = "${searchKeyWord}";
	if (searchKeyword == ""){
		
		searchKeyword = "ㄱ";
	}
	
	getIndexSearchData(searchKeyword);

});
	
</script>
