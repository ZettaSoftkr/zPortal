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
                      <li class="box_title">검색내용</li>  
                      <li class="box_form">
                     		 <input type="text" name="searchContent" id="searchContent">
	                </li>                                  
	            </ul>                 
	        </div>
	        <button type="button" class="search_button" title="조회" onclick="searchList();">
                <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
            </button>
        </div>
    </div>   
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
                        <td colspan="5" class="notice_txt"><textarea class="Notice_Write_textarea" name="bi_cn" id="bi_cn" cols="45" rows="3" required></textarea>
                        </td>                             
                    </tr>
                </tbody>
            </table> 
            </div>
            <div class="btn_area">
                <button type="submit" class="button_Area">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
                </button>
                <button type="button" class="button_Area">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-refresh"></span>다시작성</span>
                </button>
                <button type="button" class="button_Area" id="listForm_button">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
            </div>   
          </form>
        </div>
	</div>

<script type="text/javascript">

	$(document).ready(function() {
		
		getMenuNmdept(currentId);
		
		
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
