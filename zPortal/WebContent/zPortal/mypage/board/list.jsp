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
	                       <li class="box_title">검색조건</li>
	                       <li class="box_form">
	                          <select name="searchTitle" id="searchTitle" class="combobox_free_class">
	                                <option value="0">선택하세요</option>
									<option value="1">제목</option>
									<option value="2">내용</option>
									<option value="3">재목  + 내용</option>
	                           </select>
	                       </li>  
	                       <li class="box_title">검색내용 </li>  
	                       <li class="box_form">
	                      		 <input type="text" name="searchContent" id="searchContent" class="input_free"> 
	                       </li>                                  
	                   </ul>                 
	               </div>
	                <button type="button" class="search_button" title="조회" onclick="searchMyPageList();">
                   <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
               </button>
               <button type="button" class="search_button" title="초기화" id="searchMyPageClear" onclick="searchMyPageClear();">
                   <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span>
               </button>
	           </div>
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
               <col width="80"/>
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

var headerTitle = ["번호", "제목", "작성자", "작성일", "조회"];
var attrKey = "bi_bbs_sn";  //keyvalue
var attrVal =        //attributy
[
 
{
	 name : "bi_titl"
	, type : "input"
	, val : ""
	, funcNm:""
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
}, {
	name : "bi_inqire_num"
	,type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
} 
 
 
]; 

var viewStatus = "edit"; //상세 페애지 성절[상세 페에지 -view,수정페이지 -edit]]
$(document).ready(function() {

	$("#searchMyPageClear").hide();
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	getMyPageListPageData(currentPage, searchtitle, searchContent);


});
	
</script>
