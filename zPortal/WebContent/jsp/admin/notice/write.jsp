<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
  <div class="box_type">
      
</div>
  <div id="Contents_Area">
        <div id="Container">
          <form class="form-horizontal" role="form" action="" method="post" name="writeForm" id="writeForm">
          <input type="hidden" name="lev1_parent_id" id="lev1_parent_id" value="">
			 <input type="hidden" name="lev2_parent_id" id="lev2_parent_id" value="">
			 <input type="hidden" name="lev3_parent_id" id="lev3_parent_id" value="">
			 <input type="hidden" name="lev1_menuOpenId" id="lev1_menuOpenId" value="">
			 <input type="hidden" name="lev2_menuOpenId" id="lev2_menuOpenId" value="">
			 <input type="hidden" name="lev1_menuOpen" id="lev1_menuOpen" value="">
			 <input type="hidden" name="lev2_menuOpen" id="lev2_menuOpen" value="">
			 <input type="hidden" name="currentId" id="currentId" value="">
            <div class="Grid_Area">
                <table class="Notice_Board_table">
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
                        <td colspan="5" class="notice_title"><input title="" class="Notice_Write_title" type="text" id="bi_titl" name="bi_titl" value="" required></td>                             
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colspan="5" class="notice_txt"><textarea  class="Notice_Write_textarea" name="bi_cn" id="bi_cn" cols="45" rows="3" required></textarea>
                        </td>                             
                    </tr> 
                    <tr>
                        <th>팝업</th>
                        <td colspan="5" class="notice_txt">
                        	<input type="radio" id="edit_bi_popup_yn" name="bi_popup_yn"  value="y" required> YES
                        	<input type="radio"  id="edit_bi_popup_yn" name="bi_popup_yn" value="n" required> NO
                        </td>                             
                    </tr> 
                    <tr>
                        <th>시작일</th>
                        <td colspan="2" class="notice_txt">
                        	<div class="input-group col-sm-10" id='datetimepicker1'>
							<input type="text" class="form-control input-sm" id="edit_parseStartDate" name="bi_popup_bgn_dt" data-format="YYYY-MM-DD" > 
							<span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
                        </td>   
                         <th>종료일</th> 
                         <td colspan="2" class="notice_txt">
							<div class="input-group col-sm-10" id='datetimepicker2'>
							<input type="text" class="form-control input-sm" id="edit_parseEndDate" name="bi_popup_end_dt" data-format="YYYY-MM-DD"> 
							<span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
                        </td>                        
                    </tr>                     
                </tbody>
            </table> 
            </div>
            <div class="btn_area">
                <button type="submit" class="button_Area">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
                </button>
                <button type="button" class="button_Area" id="reflash">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-refresh"></span>다시작성</span>
                </button>
                <button type="button" class="button_Area" id="goAdminListForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
            </div>   
          </form>
        </div>
	</div>

<script type="text/javascript">

	$(document).ready(function() {
		
		
		

		$("#lev1_parent_id").val(lev1_parent_id);
		$("#lev2_parent_id").val(lev2_parent_id);
		$("#lev3_parent_id").val(lev3_parent_id);
		$("#lev1_menuOpenId").val(lev1_menuOpenId);
		$("#lev2_menuOpenId").val(lev2_menuOpenId);
		$("#lev1_menuOpen").val(lev1_menuOpen);
		$("#lev2_menuOpen").val(lev2_menuOpen);
		$("#currentId").val(currentId);
		
		getMenuNmdept(currentId);
		
	
	});
	
	
	$(function() {

		$('#datetimepicker1').datetimepicker({
			language : 'en',
			pick12HourFormat : true
		});

		$('#datetimepicker2').datetimepicker({
			language : 'en',
			pick12HourFormat : true
		});

		
	});
	
</script>
