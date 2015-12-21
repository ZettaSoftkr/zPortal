<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="Main_Title_Area">
   <div class="title" id="reportTitle"></div>                
       <div class="sitemap"></div>
  </div>
 <div id="Contents_Area">
        <div id="Container">
<form class="form-horizontal" role="form"  method="post" name="BoardEditForm" id="BoardEditForm" enctype="multipart/form-data">
	<input type="hidden" name="bi_bbs_sn" id="bi_bbs_sn" value="">
	<input type="hidden" name="keyVal" id="keyVal" value="">
	 <input type="hidden" name="lev1_parent_id" id="lev1_parent_id" value="">
	 <input type="hidden" name="lev2_parent_id" id="lev2_parent_id" value="">
	 <input type="hidden" name="lev3_parent_id" id="lev3_parent_id" value="">
	 <input type="hidden" name="lev1_menuOpenId" id="lev1_menuOpenId" value="">
	 <input type="hidden" name="lev2_menuOpenId" id="lev2_menuOpenId" value="">
	 <input type="hidden" name="lev1_menuOpen" id="lev1_menuOpen" value="">
	 <input type="hidden" name="lev2_menuOpen" id="lev2_menuOpen" value="">
	 <input type="hidden" name="currentId" id="currentId" value="">
   <div class="Grid_Area">
              <table class="Notice_Board_table" >
                <colgroup>
                    <col width="150" />
                    <col /> 
                    <col width="150" />
                    <col />   
                    <col width="150" />
                    <col />                   
                </colgroup>
                <tbody>	
                    <tr>
                        <th>제목</th>
                        <td colspan="5" class="notice_title"><input id="bi_titl" name="bi_titl" class="Notice_Write_title" type="text"></td>                             
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td id="bi_user_nm"></td>  
                        <th>등록일</th>
                        <td id="bi_updt_dt"></td>
                        <th>조회수</th>
                        <td id="bi_inqire_num"></td>                       
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td colspan="5">
                            <ul>
<!--                             <li><input title="" class="notice_file" type="text" value=""></li> -->
                                <li><input type="file" id="fileupload" name="files" class="notice_file"  multiple value="첨부" size="50" /></li> 
                                <li id="file0"></li>                     
                            </ul>
                            <ul>
<!--                             <li><input title="" class="notice_file" type="text" value=""></li> -->
                                <li><input type="file" id="fileupload" name="files" class="notice_file"  multiple value="첨부" size="50" /></li> 
                                <li id="file1"></li>                      
                            </ul>
                            <ul>
<!--                             <li><input title="" class="notice_file" type="text" value=""></li> -->
                                <li><input type="file" id="fileupload" name="files" class="notice_file"  multiple value="첨부" size="50"/></li>   
                                 <li id="file2"></li>                    
                            </ul>
                            <span id="fileView"></span> 
                        </td>                             
                    </tr>   
                    <tr>
                        <th>내용</th>
                        <td colspan="5" class="notice_txt"><textarea  class="Notice_Write_textarea" id="bi_cn" name="bi_cn" cols="80" rows="3"></textarea>
                        </td>                             
                    </tr>                
                </tbody>
            </table> 
    
           </div>
          <div class="btn_area">
            	<button type="submit" class="button_Area">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-edit"></span>수정</span>
                </button>
                <button type="button" class="button_Area" id="goAdminDeleteForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-remove"></span>삭제</span>
                </button>
                <button type="button" class="button_Area" id="goAdminListForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
          </div>
 </form>
</div>
</div>
<script type="text/javascript">


var attrKey = "bi_bbs_sn";  //Primary-Key
var attrNm =        //attributy
	[
	{   
		  iptNm : "bi_titl"
		, colNm : "bi_titl"
		, type  : "val"
		, funcNm: ""
	},{
		  iptNm : "bi_user_nm"
		, colNm : "bi_user_nm"
		, type  : "html"
		, funcNm: ""
	},{
	   
		  iptNm : "bi_cn"
		, colNm : "bi_cn"
		, type  : "val"
		, funcNm: ""
	},{
				   
		  iptNm : "bi_updt_dt"
		, colNm : "parseModifyDate"
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
	    getAdminEditPage("${bi_bbs_sn}");
        //업로드 파일 확인
        getAdminFileEdit(aliasName2,"${bi_bbs_sn}");
        
        
        $("#lev1_parent_id").val(lev1_parent_id);
        $("#lev2_parent_id").val(lev2_parent_id);
        $("#lev3_parent_id").val(lev3_parent_id);
        $("#lev1_menuOpenId").val(lev1_menuOpenId);
        $("#lev2_menuOpenId").val(lev2_menuOpenId);
        $("#lev1_menuOpen").val(lev1_menuOpen);
        $("#lev2_menuOpen").val(lev2_menuOpen);
        $("#currentId").val(currentId);

		
	});


	
	
</script>
