<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="Main_Title_Area" >
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
							<option value="1">조직ID</option>
							<option value="2">조직명</option>
							<option value="3">조직ID  + 조직명</option>
                        </select>
                    </li>  
                    <li class="box_title">검색내용 </li>  
                    <li class="box_form">
                   		 <input type="text" name="searchContent" id="searchContent" class="input_free">
                    </li>                                  
                </ul>                 
            </div>
            <button type="button" class="search_button" title="조회" onclick="searchList();">
                <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
            </button>
        </div>
    </div>  
</div>

<div id="listContent">
   <div class="Grid_Area">
      <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
         <table class="table_Type">
            <colgroup>
               <col width="50"/> 
               <col width="100"/>  
               <col />  
               <col width="150"/>
           </colgroup>
           <thead id="headTableContent">
              
           </thead>
           <tbody id="tableContent">		
               
           </tbody>
       </table> 
       </form> 
     </div>
      <div class="paging_sample" id="paging"></div> 
       <div class="btn_area">
           <button type="button" class="button_Area" id="writeU_button" data-toggle="modal"  data-target="#write_modal">
               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
           </button>
           <button type="button" class="button_Area" id="all_AdminDelete">
               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>선택삭제</span>
           </button>
       </div> 
</div>

<!-- Modal -->
<form class="form-horizontal" role="form" action="" method="post" name="editAdminForm" id="editAdminForm">
	
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">조직관리 > 수정</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_id" class="col-sm-2 control-label">조직ID</label>
						<div class="col-sm-4 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_dept_id" name="bi_dept_id" placeholder="사용자ID" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">조직명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_deptnm" name="bi_deptnm" placeholder="사용자명" required >
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_user_nm" class="col-sm-2 control-label">조직설명</label> -->
						
<!-- 						<div class="input-group col-sm-8"> -->
<!-- 							<textarea class="form-control" id="edit_bi_dc" name="bi_dc" rows="8" placeholder="설명" ></textarea> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">갱신여부</label>
						<div class="col-sm-2 input-group input-group-sm">
							<select  id="edit_bi_perm_yn" name="bi_perm_yn" class="form-control" required>
							     <option value="">선택</option>
								<option value="Y">허용</option>
								<option value="N">미허용</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
					<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form>



<!-- Modal -->
<form class="form-horizontal" role="form"  method="post" name="writeDeptForm" id="writeDeptForm">
<input type="hidden"  id="tf_id" name="tf_id" value="false">
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">조직관리 > 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_id" class="col-sm-2 control-label">조직ID</label>
						<div class="col-sm-4 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_dept_id" name="bi_dept_id" placeholder="조직ID" required type="text"> 
							<span class="input-group-addon"><a href="#" id="deptIdCheck" data-keycolumn="false"><span class="glyphicon glyphicon-user"></span> 중복확인</a></span>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">조직명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_deptnm" name="bi_deptnm" placeholder="조직명" required type="text">
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_cn" class="col-sm-2 control-label">조직설명</label> -->
						
<!-- 						<div class="input-group col-sm-8"> -->
<!-- 							<textarea class="form-control" id="bi_dc" name="bi_dc" rows="8" placeholder="설명" ></textarea> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label for="bi_perm_yn" class="col-sm-2 control-label">갱신여부</label>
						<div class="col-sm-2 input-group input-group-sm">
							<select name="bi_perm_yn" id="bi_perm_yn" class="form-control" required>
							    <option value="">선택</option>
								<option value="Y">허용</option>
								<option value="N">미허용</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
					<button type="button" data-dismiss="modal" class="btn btn-warning btn-xs"> 취소 </button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form>

<script type="text/javascript">


var headerTitle = [ "번호", "조직ID", "조직명"];
var attrKey = "bi_dept_id";  //keyvalue
var attrVal =        //attributy
[{
  name : "bi_dept_id"
, type : "input"
, val : ""
, funcNm:""
, href:"Y"
}, {
   name : "bi_deptnm"
,  type : "input"
, val : ""
, funcNm:""
, href:"Y"
}
]; 

var viewStatus = "edit"; //상세 페애지 성절[상세 페에지 -view,수정페이지 -edit]]


$(document).ready(function() {
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	getAdminListPageData(currentPage, searchtitle, searchContent);
});
</script>
