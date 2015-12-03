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
                               <td colspan="3" class="listView" id="fileView">
                               
                               </td>
                              
                           </tr> 
                           <tr>
                               <td colspan="3" class="Grid_Contents" id="bi_cn"></td>
                           </tr> 
                          
                       </tbody>
                   </table>  
           </div>
          <div class="btn_area">
                <button type="button" class="button_Area" id="goMyPageDeleteForm_btn">
					<span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>삭제</span>
				</button>
            	<button type="button" class="button_Area" id="goMyPageEditForm_btn">
            		<span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>수정</span>
            	</button>
                <button type="button" class="button_Area" id="goMyPageListForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
            </div>   
          <div class="Grid_Area">
          <table class="Notice_Board_table">
   	 		<colgroup>
                    <col width="100"/>  
                    <col />   
                    <col width="100"/> 
                </colgroup>
                <tbody>		
                    <tr>
                        <th>이전글</th>
                        <td id="prevTitle"></td>
                        <td id="prevDate"></td>
                    </tr> 
                    <tr>
                        <th>다음글</th>
                        <td id="nextTitle"></td>
                        <td id="nextDate"></td>
                    </tr>  
                </tbody>
          </table>
          </div>
   </form>
<script type="text/javascript">


var attrKey = "bi_bbs_sn";  //Primary-Key
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
	 }];

  
	$(document).ready(function() {
		
		getMenuNmdept(currentId);
		// var bi_bbs_sn = getUrlVar("bi_bbs_sn");  
	    $("#keyVal").val("${bi_bbs_sn}");
		getViewPage("${bi_bbs_sn}");
		getFileView(secAliasName,"${bi_bbs_sn}"); //파일업로드 리스트
		getDeleteCheck("${bi_bbs_sn}");
		
		
	});


	
	
</script>