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
	                <button type="button" class="search_button" title="초기화" id="searchClear" onclick="searchFaqClear();">
	                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-refresh"></span>초기화</span>
	             </button>
	             <button type="button" class="search_button" title="조회" onclick="searchFaqList();">
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
               <col />  
           
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

var headerTitle = ["번호", "질문  & 답변"];
var attrKey = "bi_faq_sn";  //keyvalue


$(document).ready(function() {

	
	$("#searchClear").hide();
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchTitle = 0;
	var searchContent = "";
	
	getFaqList(currentPage, searchTitle, searchContent);


});
	
	

	
</script>
