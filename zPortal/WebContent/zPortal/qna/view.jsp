<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
<form class="form-horizontal" role="form"  method="POST" name="viewForm" id="viewForm">
  <input type="hidden" name="keyVal"  id="keyVal" value="">
   <div class="Grid_Area">
               <table class="table_Type">
                       <colgroup>
                           <col />  
                           <col width="200"/>   
                           <col width="150"/>  
                       </colgroup>
                       <thead>
                           <tr>
                               <th class="View" colspan="3" id="bi_titl"></th>                              
                           <tr>
                       </thead>
                       <tbody>		
                           <tr>
                               <td class="listView"><span>작성자</span><span class="view" id="bi_user_nm"></span></td>
                               <td class="listView"><span>작성일</span><span class="view" id=updtDt></span></td>
                               <td class="listView"><span>조회수</span><span class="view" id="bi_inqire_num"></span></td>
                           </tr> 
                            
                           <tr>
                               <td colspan="3" class="Grid_Contents" id="bi_cn"></td>
                           </tr> 
                           <tr>
                                <td  class="listView"><span> 답변상태</span><span class="view" id="bi_answer_yn"></span></td>
                                <td colspan="2" class="listView"><span>답변일</span><span class="view" id="bi_answer_updt_dt"></span></td>
                           </tr>
                            <tr>
                               <td colspan="3" class="Grid_Contents" id="bi_answer_cn"></td>
                           </tr> 
                           
                          
                       </tbody>
                   </table>  
           </div>
          <div class="btn_area">
                <button type="button" class="button_Area" id="goDeleteForm_btn"></button>
            	<button type="button" class="button_Area" id="goEditForm_btn"></button>
                <button type="button" class="button_Area" id="goListForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
            </div> 
   </form>
<script type="text/javascript">


var attrKey = "bi_qna_sn";  //Primary-Key
var attrNm =        //attributy
	[
	{   
		  iptNm : "bi_titl"
		, colNm : "bi_titl"
		, type  : "html"
		, funcNm: ""
	},{
		  iptNm : "bi_user_nm"
		, colNm : "bi_user_nm"
		, type  : "html"
		, funcNm: ""
	},{
	   
		  iptNm : "bi_cn"
		, colNm : "htmlFormtWithCn"
		, type  : "html"
		, funcNm: ""
	},{
	   
		  iptNm : "bi_inqire_num"
		, colNm : "bi_inqire_num"
		, type  : "html"
		, funcNm: ""
	},{   
		  iptNm : "updtDt"
		, colNm : "parseModifyDate"
		, type  : "html"
		, funcNm: ""
	 },{   
		  iptNm : "bi_answer_cn"
		, colNm : "htmlFormtWithAnswerCn"
		, type  : "html"
		, funcNm: ""
	 },{   
  		  iptNm : "bi_answer_updt_dt"
		, colNm : "parseAnswerModifyDate"
		, type  : "html"
		, funcNm: ""
	 },{   
		  iptNm : "bi_answer_yn"
		, colNm : "bi_answer_yn"
		, type  : "radioButton"
		, funcNm: ""
		, val:[ "S:대기", "C:확인중", "A:응답완료" ]
	 }
	 
	
	];

  
	$(document).ready(function() {
		
		getMenuNmdept(currentId);
		// var bi_bbs_sn = getUrlVar("bi_bbs_sn");  
	    $("#keyVal").val("${bi_qna_sn}");
		getViewPage("${bi_qna_sn}");
		getDeleteCheck("${bi_qna_sn}");
		
		
	});


	
	
</script>
