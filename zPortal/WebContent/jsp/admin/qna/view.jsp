<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
<form class="form-horizontal" role="form"  method="POST" name="viewForm" id="viewForm">
  <input type="hidden" name="bi_qna_sn"  id="bi_qna_sn" value="">
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
                               <td class="listView" colspan="3">
						           <ul class="viewmen">
						           <li>답변상태  </li>
									<li>
										<label class="radio-inline">
										  <input type="radio" id="bi_answer_yn" name="bi_answer_yn" value="S" required> 대기중
										</label>
										<label class="radio-inline">
										  <input type="radio" id="bi_answer_yn" name="bi_answer_yn"  value="C" required> 확인중
										</label>
										<label class="radio-inline">
										  <input type="radio" id="bi_answer_yn" name="bi_answer_yn"  value="A" required> 답변완료
										</label>
									</li>
									</ul>
                               </td>
                           </tr> 
                            <tr>
                               <td colspan="3" class="Grid_Contents">
									<textarea class="form-control" id="bi_answer_cn" name="bi_answer_cn" cols= "80" rows="10" placeholder="답변" required></textarea>
                               </td>
                           </tr> 
                          
                       </tbody>
                   </table>  
           </div>
          <div class="btn_area">
            	<button type="button" class="button_Area" id="answer_submit">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>답변</span>
                </button>
                <button type="button" class="button_Area" id="goAdminDeleteForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-remove"></span>삭제</span>
                </button>
                <button type="button" class="button_Area" id="goAdminEditForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-edit"></span>수정</span>
                </button>
                <button type="button" class="button_Area" id="goAdminListForm_btn">
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
		   
		  iptNm : "bi_answer_yn"
		, colNm : "bi_answer_yn"
		, type  : "radio"
		, funcNm: ""
	},{
		   
		  iptNm : "bi_answer_cn"
		, colNm : "bi_answer_cn"
		, type  : "val"
		, funcNm: ""
	},{   
		  iptNm : "updtDt"
		, colNm : "parseModifyDate"
		, type  : "html"
		, funcNm: ""
	 }];

  
	$(document).ready(function() {
		
		getMenuNmdept(currentId);
		
		$("#bi_qna_sn").val(keyVal);
	    $("#keyVal").val(keyVal);
		getAdminViewPage(keyVal);
		
		
		
	});


	
	
</script>
