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
						<option value="1">조직ID</option>
						<option value="2">조직명</option>
						<option value="3">조직ID  + 부서</option>
                         </select>
                     </li>  
                     <li class="box_title">검색내용 </li>  
                     <li class="box_form">
                    		 <input type="text" name="searchContent" id="searchContent" class="input_free"> 
                     </li>                                  
                 </ul>                 
             </div>
             <button type="submit" class="search_button" title="조회" onclick="searchList();">
                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
             </button>
              <button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();">
                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span>
             </button>
         </div>
     </div>   
 </div> 

<div class="boardarea">
	<section class="boardlist">
		<div class="row">
			
			 
				 <!--  사용지관리 -->
				
					<br>
					<div class="col-md-4">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span>  전체 조직보기
								 &nbsp; <a href="javascript: d0.openAll();">open all</a> | <a href="javascript: d0.closeAll();">close all</a>
							</div>
							<div class="panel-body">
								
									<div class="panel-body" id="deptUser_list"></div>
								
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 조직목록
							</div>
							<div class="panel-body">
							 <div id="Contents_Area">
        						<div id="Container">
							
							  <!--  시작 -->
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
							           <button type="button" class="button_Area" id="all_AdminDeptDelete">
							               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>선택삭제</span>
							           </button>
							       </div> 
								 <!--  끝 -->
								 </div>
								 </div>
							</div>
						</div>
					</div>
				</div>
			 <!--  사용관리 끝-->
</section>
	</div>

<!-- Modal -->
<form class="form-horizontal" role="form" action="" method="post" name="editAdminDeptForm" id="editAdminDeptForm">
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
							<input type="text" class="form-control" id="edit_bi_dept_id" name="bi_dept_id" placeholder="조직ID" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">조직명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_deptnm" name="bi_deptnm" placeholder="조직명" required >
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_user_nm" class="col-sm-2 control-label">조직설명</label> -->
<!-- 						<div class="input-group col-sm-8"> -->
<!-- 							<textarea class="form-control" id="edit_bi_dc" name="bi_dc" rows="8" placeholder="설명" ></textarea> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">갱신여부</label> -->
<!-- 						<div class="col-sm-2 input-group input-group-sm"> -->
<!-- 							<select  id="edit_bi_perm_yn" name="bi_perm_yn" class="form-control" required> -->
<!-- 							     <option value="">선택</option> -->
<!-- 								<option value="Y">허용</option> -->
<!-- 								<option value="N">미허용</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">수정</button>
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
<form class="form-horizontal" role="form"  method="post" name="writeAdminDeptForm" id="writeAdminDeptForm">
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
<!-- 					<div class="form-group"> -->
<!-- 						<label for="bi_perm_yn" class="col-sm-2 control-label">갱신여부</label> -->
<!-- 						<div class="col-sm-2 input-group input-group-sm"> -->
<!-- 							<select name="bi_perm_yn" id="bi_perm_yn" class="form-control" required> -->
<!-- 							    <option value="">선택</option> -->
<!-- 								<option value="Y">허용</option> -->
<!-- 								<option value="N">미허용</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
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
<!-- 사용자 등록 모달 끝-->


<script type="text/javascript">


$("#searchInit").hide();


var viewStatus = "modal";
var headerTitle = [ "번호", "조직ID", "조직명"];
//목록
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
//수정
var attrEditNm =        
	[{   
		  iptNm : "edit_bi_dept_id"
		, colNm : "bi_dept_id"
		, type  : "val"
		, funcNm: ""
	 },{   
		  iptNm : "edit_bi_deptnm"
		, colNm : "bi_deptnm"
		, type  : "val"
		, funcNm: ""
 	}];

$(document).ready(function() {
	
	
	getMenuNmdept(currentId);
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	getAdminListPageData(currentPage, searchtitle, searchContent);
	
	
	deptUserInit(); //tree 보기 
});

	
	/***************************************************************************************
	**
	**  메뉴 그룹 -메뉴 매핑 
	**  현황  트리 나타내기   dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/

	var duMappArray = [ [ '#', -1, '목록' ]];	
	function deptUserInit() {

		$.ajax({

			url : "/" + serviceName + "/admin/userInfo/getDeptUserList.do",
			type : "post",
			data : "",
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {

				for (var i = duMappArray.length - 1; i--;) {
					if (i != 0) {
						duMappArray.splice(i);
					}
				}

				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

				for (var i = 0; i < data.length; i++) {
					var addArray = [ data[i][0], data[i][1], data[i][2] ];
					duMappArray.push(addArray);
				}

				deptUserList(duMappArray);
				
				

			},
			error : function() {
				alert("false");

			}
		});

	}
	
	
	/***************************************************************************************
	**
	**  메뉴그릅관리 탭
	**  메뉴 그룹 - 메뉴 맵핑  트리 나타내기   dtree/dtreeMenu.js (d0)
	** 시작 **
	******************************************************************************************/
	

	function deptUserList(duMappArray) {

		d0 = new dTree('d0');

		for (var i = 0; i < duMappArray.length; i++) {
			
			d0.add(duMappArray[i][0], duMappArray[i][1], duMappArray[i][2]);
			
		}
		
		$('#deptUser_list').html(d0.toString());
	}

</script>