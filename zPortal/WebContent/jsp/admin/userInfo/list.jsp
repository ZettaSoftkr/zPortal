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
							<option value="1">사용자ID</option>
							<option value="2">사용자명</option>
							<option value="3">사용자ID  + 사용자명</option>
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
             <button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();">
                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span>
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


<!-- 사용자 수정 모달 시작 -->
<form class="form-horizontal" role="form" action="" method="post" name="editAdminForm" id="editAdminForm">
<input type="hidden" class="form-control" id="edit_bi_perm_yn" name="bi_perm_yn" value="y">
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">사용자 관리 > 수정</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_id" class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-4 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_unity_cust_id" name="bi_unity_cust_id" placeholder="사용자ID" required readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">사용자명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_user_nm" name="bi_user_nm" placeholder="사용자명" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_cttpc_no" class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-8 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_mpno" name="bi_mpno" placeholder="연락처" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_email" class="col-sm-2 control-label">email</label>
						<div class="col-sm-8 input-group input-group-sm">
							<input type="email" class="form-control" id="edit_bi_email_addr" name="bi_email_addr" placeholder="E-MAIL" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_group_id" class="col-sm-2 control-label">부서</label>
						<div class="col-sm-3 input-group input-group-sm">
							<select name="bi_dept_id" id="edit_bi_dept_id" class="form-control" required>

							</select>
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">갱신여부</label> -->
<!-- 						<div class="col-sm-2 input-group input-group-sm"> -->
<!-- 							<select name="bi_bi_perm_yn" id="edit_bi_perm_yn" class="form-control" required> -->
<!-- 								<option value="">선택</option> -->
<!-- 								<option value="Y">허용</option> -->
<!-- 								<option value="N">미허용</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-xs btn-success">수정</button>
					<button type="button" class="btn btn-xs btn-warning" data-dismiss="modal">취소</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form>
<!-- 사용자 수정 모달 끝-->
<!-- 사용자 등록 모달 시작-->
<form class="form-horizontal" role="form" action="" method="POST" name="writeUserForm" id="writeUserForm">
<input type="hidden"  id="tf_id" name="tf_id" value="false">
<input type="hidden"  id="bi_perm_yn" name="bi_perm_yn" value="y">
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">사용자 관리 > 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_id" class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-4 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_unity_cust_id" name="bi_unity_cust_id" placeholder="사용자ID" required> 
							<span class="input-group-addon"><a href="#" id="idCheck" data-keycolumn="false"><span class="glyphicon glyphicon-user"></span> 중복확인</a></span>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">사용자명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_user_nm" name="bi_user_nm" placeholder="사용자명" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_cttpc_no" class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-8 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_mpno" name="bi_mpno" placeholder="연락처" required >
						</div>
					</div>
					<div class="form-group">
						<label for="bi_email" class="col-sm-2 control-label">email</label>
						<div class="col-sm-8 input-group input-group-sm">
							<input type="email" class="form-control" id="bi_email_addr" name="bi_email_addr" placeholder="E-MAIL" required >
						</div>
					</div>
					<div class="form-group">
						<label for="bi_group_id" class="col-sm-2 control-label">부서</label>
						<div class="col-sm-3 input-group input-group-sm">
							<select name="bi_dept_id" id="bi_dept_id" class="form-control" required>
							</select>
						</div>
					</div>
					
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">갱신여부</label> -->
<!-- 						<div class="col-sm-2 input-group input-group-sm"> -->
<!-- 							<select name="bi_perm_yn" id="bi_perm_yn" required class="form-control"> -->
<!-- 								<option value="">선택</option> -->
<!-- 								<option value="Y">허용</option> -->
<!-- 								<option value="N">미허용</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs" id="writeUser_button">저장</button>
					<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form>

<!-- 사용자 등록 모달 끝-->

<script type="text/javascript">

$("#searchInit").hide();



var headerTitle = ["번호", "ID", "사용자명", "연락처", "조직"];
var attrKey = "bi_unity_cust_id";  //keyvalue
var attrVal =        //attributy
[
{
	 name : "bi_unity_cust_id",
	 type : "input"
	, val : ""
	, funcNm:""
	, href:"Y"
}, {
	  name : "bi_user_nm",
	  type : "input"
	, val : ""
	, funcNm:""
	, href:"Y"
}, {
	 name : "bi_mpno",
	 type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "bi_deptnm"
	,type : "input"
	,val : ""
	,funcNm:""
	,href:"N"
}
]; 



var attrEditNm =        
	[{   
		  iptNm : "edit_bi_unity_cust_id"
		, colNm : "bi_unity_cust_id"
		, type  : "val"
		, funcNm: ""
	 },{   
		  iptNm : "edit_bi_user_nm"
		, colNm : "bi_user_nm"
		, type  : "val"
		, funcNm: ""
 	},{   
		  iptNm : "edit_bi_mpno"
		, colNm : "bi_mpno"
		, type  : "val"
		, funcNm: ""
	},{   
		  iptNm : "edit_bi_email_addr"
		, colNm : "bi_email_addr"
		, type  : "val"
		, funcNm: ""
	},{   
		  iptNm : "edit_bi_dept_id"
		, colNm : "bi_dept_id"
		, type  : "select"
		, funcNm: ""
	}];

var viewStatus = "modal"; //상세 페애지 성절[상세 페에지 -view,수정페이지 -edit]]


$(document).ready(function() {
	
	getMenuNmdept(currentId);
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	getAdminListPageData(currentPage, searchtitle, searchContent);	
	userDeptInfo(); //등록시 부서 정보 출력
	
	userDeptEditInfo();// 수정시 부서정보 출력
	
});
</script>
