<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
  <div class="box_type">
      
 </div>	
<div class="boardarea">
	<section class="boardlist">
		
			<ul class="nav nav-tabs" id="#myTab">		
				<li id="userGroup_tab" class="active"><a href="#userGroup_view"	data-toggle="tab"><span class="textt"> 사용자 그룹관리</span></a></li>
				<li id="userGroup_dept_mapp_tab" class=""><a href="#userGroup_dept_mapp_view"	data-toggle="tab"><span class="textt"> 사용자그룹 - 조직 맵핑 </span></a></li>
				<li id="userGroup_user_mapp_tab" class=""><a href="#userGroup_user_mapp_view"	data-toggle="tab"><span class="textt"> 사용자 그룹 사용자 맵핑 </span></a></li>
			</ul>
			<div class="tab-content">
	<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--################################               사용자그룹  목록                              ################################### -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->				 
				 <!--  사용지관리 -->
				<div class="tab-pane active" id="userGroup_view">
					<br>
					<div class="col-md-4">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 사용자그룹보기
								 &nbsp; <a href="javascript: d0.openAll();">open all</a> | <a href="javascript: d0.closeAll();">close all</a>
							</div>
							<div class="panel-body">
								
									<div class="panel-body" id="ugdm_list"></div>
								
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 사용자목록
							</div>
							<div class="panel-body">
							  <!--  시작 -->
							     <div class="Grid_Area">
									 <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
									 
							         <table class="table_Type">
							            <colgroup>
							               <col width="50"/> 
							               <col width="80"/>  
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
								 <div class="paging_sample" id="paging">
								 </div> 
								 <div class="btn_area">
							           <button type="button" class="button_Area" id="writeUserGroupForm_button"  data-toggle="modal" data-target="#write_modal">
							               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
							           </button>
							           <button type="button" class="button_Area" id="all_AdminDelete">
							               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>선택삭제</span>
							           </button>
							     </div> 								 <!--  끝 -->
							   
							</div>
						</div>
					</div>
				</div>
			 <!--  사용관리 끝-->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--################################               사용자그룹  조직 맵핑                              ################################### -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->		 
			  <!--  사용지그룹 - 조직 맵핑  시작 -->
				<div class="tab-pane" id="userGroup_dept_mapp_view">
				  <div class="searcharea">
				  
				  	  <div class="box_second">                                  
			             <div class="lm_select_left">
			                <ul>
			                    <li class="box_title">사용자그룹</li>
			                    <li class="box_form">
			                       <select name="bi_menu_group_id" id="ugd_mapp" class="combobox_free_class">
			                            <option value="">선택하세요</option>
										
			                        </select>
			                    </li>                          
			                </ul>                 
			            </div>
						<button type="button" class="search_button" title="조회" onclick="searhGroupMapp('ugd_mapp');">
			                <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
			            </button>  
<!-- 						<button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();"> -->
<!-- 			                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span> -->
<!-- 			             </button> -->
		             </div>
		             </div>
					<br>
						<!--  시작 -->
						<form class="form-horizontal" role="form" method="POST" name="deptGroupForm" id="deptGroupForm">
							<input type="hidden" name="bi_group_id" id="dept_bi_group_id" value="">
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-th-list">
											부서 <input type="text" name="s_bi_deptNm"  id="s_bi_dept_Nm" value="">
											<button type="button" id="viewDeptSearchMapp" data-loading-text="loading..." class="btn btn-success btn-xs">검색</button>
											<button type="button" id="viewDeptSearchReset" data-loading-text="loading..." class="btn btn-success btn-xs">초기화</button>
										</span>
									</div>
									<div class="panel-body">
										<select id="bi_dept_id_list" name="bi_dept_id_list" multiple class="form-control" size="10">

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="panel panel-info">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-th-list">선택</span>
									</div>
									<div class="panel-body">
									    <div class="col-md-offset-4">
										<div class="btn-group-vertical">
											<button type="button" id="dept_add" class="btn btn-success btn-xs">
												<span class="glyphicon glyphicon-chevron-right"></span>
											</button>
											<button type="button" id="dept_remove" class="btn btn-success btn-xs">
												<span class="glyphicon glyphicon-chevron-left"></span>
											</button>
										</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading">선택부서</div>
									<div class="panel-body">
										<select id="bi_dept_id_selected" name="bi_dept_id" multiple class="form-control" size="10">

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
								<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
							</div>
							</form>
					    <!--  끝 -->
				</div>
			  <!--  사용지그룹 - 조직 맵핑  끝 -->
			  
	<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--################################               사용자그룹  사용자 맵핑                              ################################### -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
			  <!--  사용지그룹 - 사용자  맵핑  시작 -->
				<div class="tab-pane" id="userGroup_user_mapp_view">
				  <div class="searcharea">
				  
				  	  <div class="box_second">                                  
			             <div class="lm_select_left">
			                <ul>
			                    <li class="box_title">사용자그룹</li>
			                    <li class="box_form">
			                       <select name="bi_menu_group_id" id="ugu_mapp" class="combobox_free_class">
			                            <option value="">선택하세요</option>
										
			                        </select>
			                    </li>                          
			                </ul>                 
			            </div>
						<button type="button" class="search_button" title="조회" onclick="searhGroupMapp('ugu_mapp');">
			                <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
			            </button>  
<!-- 						<button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();"> -->
<!-- 			                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span> -->
<!-- 			             </button> -->
		             </div>
		             
					<br>
						<!--  시작 -->
						<form class="form-horizontal" role="form" method="POST" name="userGroupForm" id="userGroupForm">
							<input type="hidden" name="bi_group_id" id="user_bi_group_id" value="">
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading">
										<select id="dept_list" name="dept_list" class="form-control" ></select>
									</div>
									<div class="panel-body">
										<select id="user_list" name="bi_unity_cust_id1" multiple class="form-control" size="10"></select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="panel panel-info">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-th-list">선택</span>
									</div>
									<div class="panel-body">
									    <div class="col-md-offset-4">
										<div class="btn-group-vertical">
											<button type="button" id="user_add" class="btn btn-success btn-xs">
												<span class="glyphicon glyphicon-chevron-right"></span>
											</button>
											<button type="button" id="user_remove" class="btn btn-success btn-xs">
												<span class="glyphicon glyphicon-chevron-left"></span>
											</button>
										</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading"><span class="glyphicon glyphicon-th-list">선택 사용자</span></div>
									<div class="panel-body">
										<select id="user_list_selected" name="bi_unity_cust_id" multiple class="form-control" size="10">

										</select>
									</div>
								</div>
							</div>
							
							
						<div class="col-md-12">
							<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
							<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
						</div>
						</form>
						<!--  끝 -->
				</div>
			  <!--  사용지그룹 - 사용자 맵핑  끝 -->
			</div>
		</div>
	</section>
	
</div>
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--################################               사용자그룹 수정 모달 시작                                  ################################### -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->

<!--사용자 그룹 수정 모달 시작 -->
<form class="form-horizontal" role="form" method="POST" name="editAdminForm" id="editAdminForm">
<input type="hidden"  id="edit_bi_group_id" name="bi_group_id" value="" >
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">수정폼</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">그룹명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_group_nm" name="bi_group_nm" placeholder="사용자명" required type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">그룹설명</label>
						<div class="input-group col-sm-8">
							<textarea class="form-control" id="edit_bi_dc" name="bi_dc" rows="8" placeholder="설명" required></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">권한</label>
						<div class="col-sm-2 input-group input-group-sm">
							<select id="edit_bi_group_author_id" name="bi_group_author_id" class="form-control" required>
								<option value="">선택</option>
								<option value="ROLE_ANNOYMOUS">접속불가</option>
								<option value="ROLE_USER">일반사용자</option>
								<option value="ROLE_ADMINISTRATOR">관리사용자</option>
							</select>
						</div>
					</div>
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
<!--사용자 그룹 수정 모달 끝 -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--################################              사용자그룹 등록 모달 시작                                  ################################### -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<!--########################################################################################################## -->
<form class="form-horizontal" role="form" action="" method="post" name="writeAdminForm" id="writeAdminForm">
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">그룹 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">그룹 명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_group_nm" name="bi_group_nm" placeholder="그룹 명" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">그룹설명</label>

						<div class="input-group col-sm-8">
							<textarea class="form-control" id="bi_cn" name="bi_cn" rows="8" placeholder="설명" required></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_conect_perm_yn" class="col-sm-2 control-label">권한</label>
						<div class="col-sm-2 input-group input-group-sm">
							<select name="bi_group_author_id" id="bi_group_author_id" class="form-control" required>
								<option value="">선택</option>
								<option value="ROLE_ANNOYMOUS">접속불가</option>
								<option value="ROLE_USER">일반사용자</option>
								<option value="ROLE_ADMINISTRAOR">관리사용자</option>
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
<!--사용자 그룹 등록 모달 끝 -->

<!--사용자 등록 끝 시작 -->
<script type="text/javascript">

	$("#searchInit").hide();
	
	var viewStatus = "edit";
	
	
	var headerTitle = ["번호",  "사용자 그룹명", "권한","조직맵핑","사용자맵핑"];
	var attrKey = "bi_group_id";  //keyvalue
	var attrVal =        //attributy
	[{
	  name : "bi_group_nm",
	  type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
	}, {
	  name : "bi_group_author_id",
	  type : "input",
	  val : [ "ROLE_ANNOYMOUS:접속 불가","ROLE_USER:일반사용자","ROLE_ADMINISTRATOR:관리사용자"]
	, funcNm:""
	, href:"N"
	},{
	  name : "bi_group_nm",
	  type : "tabLink"
	, val : ""
	, funcNm:"viewDeptMapp"
	, href:"N"
	},{
	  name : "bi_group_nm",
	  type : "tabLink"
	, val : ""
	, funcNm:"viewUserMapp"
	, href:"N"
	}];
	
	
	
	
	
	$(document).ready(function() {
		
		
		getMenuNmdept(currentId);
		
		var currentPage = 1;
		var searchtitle = 0;
		var searchContent = "";
		
		getAdminListPageData(currentPage, searchtitle, searchContent);
		
		
		ugdmInit();
		
		userGroupList("ugd_mapp"); //사용자그룹 기본 selected
		userGroupList("ugu_mapp"); //사용자그룹 기본 selected
		
	});
		
	
	/***************************************************************************************
	**
	**  메뉴 그룹 -메뉴 매핑 
	**  현황  트리 나타내기   dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/

	var ugdmArray = [ [ '#', -1, '목록' ]];	
	function ugdmInit() {

		$.ajax({

			url : "/" + serviceName + "/admin/group/getMenuGroupMappAllStatus.do",
			type : "post",
			data : "",
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {

				for (var i = ugdmArray.length - 1; i--;) {
					if (i != 0) {
						ugdmArray.splice(i);
					}
				}

				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

				for (var i = 0; i < data.length; i++) {
					var addArray = [ data[i][0], data[i][1], data[i][2] ];
					ugdmArray.push(addArray);
				}

				ugdmList(ugdmArray);
				
				

			},
			error : function() {
				alert("false");

			}
		});

	}
	
	
	/***************************************************************************************
	**
	**  사용자 그릅 매핑 현황
	**  메뉴 그룹 - 메뉴 맵핑  트리 나타내기   dtree/dtreeMenu.js (d0)
	** 시작 **
	******************************************************************************************/
	

	function ugdmList(duMappArray) {

		d0 = new dTree('d0');

		for (var i = 0; i < duMappArray.length; i++) {
			
			d0.add(duMappArray[i][0], duMappArray[i][1], duMappArray[i][2]);
			
		}
		
		$('#ugdm_list').html(d0.toString());
	}
	
	
	
	
	
	/***************************************************************************************
	**
	** 관리시 >  사용자 관리  > 사용자그룹 목록 > 사용자그룹-조직 맵핑 조회
	** 
	** 
	******************************************************************************************/
	function viewDeptMapp(groupId) {

		
		$("#ugd_mapp").val(groupId);
		/* 등록  클릭시 해당 탭 변경 */
		$("#userGroup_dept_mapp_tab").attr("class", "active");
		$("#userGroup_dept_mapp_view").attr("class", "tab-pane active");
 		
		$("#userGroup_tab").attr("class", "");
		$("#userGroup_view").attr("class", "tab-pane");
		

		$.ajax({
			url : "/" + serviceName + "/admin/group/getDeptGroupData.do",
			type : "post",
			data : {
				bi_group_id : groupId
			},
			datatype : 'json',
			success : function(data) {

				$('#bi_dept_id_list').empty(); //클리어
				$.each(data, function(key, value) {

					$("#bi_dept_id_list").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");

				});

			}

		});

		$.ajax({
			url : "/" + serviceName + "/admin/group/getDeptGroupMapngData.do",
			type : "post",
			data : {
				bi_group_id : groupId
			},
			datatype : 'json',
			success : function(data) {

				$('#bi_dept_id_selected').empty(); //클리어
				$.each(data, function(key, value) {

					$("#bi_dept_id_selected").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");

				});

			}

		});
		
		
	}

		$('#dept_add').click(function() {
			return !$('#bi_dept_id_list option:selected').remove().appendTo('#bi_dept_id_selected');
		});

		$('#dept_remove').click(function() {
			return !$('#bi_dept_id_selected option:selected').remove().appendTo('#bi_dept_id_list');
		});

		$("#deptGroupForm").submit(function(e) {
			
			$("#bi_dept_id_selected option").prop("selected", "selected");

			var postData = $(this).serializeArray();
			

			var url = "/" + serviceName + "/admin/group/groupDeptsave.do";

			$.ajax({
				url : url,
				type : "post",
				data : postData,
				beforeSend : function() { //

				},
				success : function(data, textStatus, jqXHR) {
					
					$("#msg").html("수정 되었습니다.");
					$("#msg_modal").modal('show');

					$("#dept_modal").modal('hide');

				},
				error : function(jqXHR, textStatus, errorThrown) {
					// if fails
				}
			});
			e.preventDefault(); // STOP default action
			e.unbind(); // unbind. to stop multiple form submit.
		});

	

	/***************************************************************************************
	**
	** 관리시 >  사용자 관리  > 사용자그룹 목록 > 사용자그룹 사용자 맵핑 조회
	** 
	** 
	******************************************************************************************/

	function viewUserMapp(groupId) {

		//$("#user_bi_group_nm").html(groupNm);
		//$("#user_bi_group_id").val(groupId);
		//$("#user_modal").modal('show');
		$("#ugu_mapp").val(groupId);
		
		// 등록 버튼 클릭시 해당 탭으로 이동 
		
		
		$("#userGroup_user_mapp_tab").attr("class", "active");
		$("#userGroup_user_mapp_view").attr("class", "tab-pane active");
 		
		$("#userGroup_tab").attr("class", "");
		$("#userGroup_view").attr("class", "tab-pane");

		
		// 조직 변수 출력 
		
		$.ajax({
			url : "/" + serviceName + "/admin/dept/getDeptListData.do",   
			type : "post",
			datatype : 'json',
			success : function(data) {

				$('#dept_list').empty(); //클리어
				$("#dept_list").append("<option value=''>:::::::::: 선택::::::::::::</option>");
				$.each(data, function(key, value) {
					$("#dept_list").append("<option value='" + data[key]['bi_dept_id'] + "'>" + data[key]['bi_deptnm'] + "</option>");
				});
			}

		});

		// 사용자 그룹 - 사용자 맵핑 현황 
		
		$.ajax({
			url : "/" + serviceName + "/admin/group/getUgUserMapngData.do", 
			type : "post",
			data : {
				bi_group_id : groupId
			},
			datatype : 'json',
			success : function(data) {

				$('#user_list_selected').empty(); //클리어
				$.each(data, function(key, value) {

					$("#user_list_selected").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");

				});

			}

		});
		
		
	}
	
/**************************************************************************************************************
 ******************************************사용자 그룹 사용자 맵핑  왼쪽이동 버턴*****************************************************
 **************************************************************************************************************/
		$('#user_add').click(function() {
			return !$('#user_list option:selected').remove().appendTo('#user_list_selected');
		});
/**************************************************************************************************************
 ******************************************사용자 그룹 사용자 맵핑  오른쪽이동 버턴*****************************************************
 **************************************************************************************************************/
		$('#user_remove').click(function() {
			return !$('#user_list_selected option:selected').remove().appendTo('#user_list');
		});

/**************************************************************************************************************
 ******************************************사용자 그룹 사용자 맵핑  저장*****************************************************
 **************************************************************************************************************/
		$("#userGroupForm").submit(function(e) {

			
			$("#user_list_selected option").prop("selected", "selected");

			var postData = $(this).serializeArray();	

			var url = "/" + serviceName + "/admin/group/userGroupsave.do";

			$.ajax({
				url : url,
				type : "post",
				data : postData,
				dataType:"json",
				beforeSend : function() { //

				},
				success : function(data, textStatus, jqXHR) {
					$("#msg").html("수정 되었습니다.");
					$("#msg_modal").modal('show');

					$("#user_modal").modal('hide');

				},
				error : function(jqXHR, textStatus, errorThrown) {
					// if fails
				}
			});
			e.preventDefault(); // STOP default action
			e.unbind(); // unbind. to stop multiple form submit.
		});

	
	
	
	/** *********************************************************************************************************** */
	/** *********************************************************************************************************** */
	/**
	 * ************************************* 관리실> 그룹관리> 사용자그룹 조직 사용자 목록 
	 * ******************************
	 */
	/** *********************************************************************************************************** */
	/** *********************************************************************************************************** */

	$("#dept_list").change(function() {

		//	alert( $("#dept_list option:selected").val());
		//	alert("dept_list:"+ $("#dept_list").val());

		var dept_id = $("#dept_list option:selected").val();
		var bi_group_id = $("#user_bi_group_id").val();

		$.ajax({
			url : "/" + serviceName + "/admin/group/getUserGroupList.do",
			type : "post",
			data : {
				bi_dept_id : dept_id,
				bi_group_id : bi_group_id
			},

			datatype : 'json',
			success : function(data) {

				$('#user_list').empty(); //클리어
				$.each(data, function(key, value) {
					$("#user_list").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");
				});
			}

		});

	});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* 관리실> 그룹관리>  사용자 그룹 출력 
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
	 
 function userGroupList(viewId){
	 
	 $.ajax({
			 url : "/" + serviceName + "/admin/group/getGroupListData.do"
			,type : "GET"
			//data : postData,
			,dataType:"json"
			,beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
			//	alert(JSON.stringify(data));
				$("#"+viewId).empty(); //클리어
				$.each(data, function(key, value) {
					$("#"+viewId).append("<option value='" + this['bi_group_id'] + "'>" + this['bi_group_nm'] + "</option>");
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
	 
		//e.preventDefault(); // STOP default action
		//e.unbind(); // unbind. to stop multiple form submit.
	
 }
 /** *********************************************************************************************************** */
 /** *********************************************************************************************************** */
 /**
  * ************************************* 관리실> 그룹관리>  사용자 그룹 별  나타내기 
  * ******************************
  */
 /** *********************************************************************************************************** */
 /** *********************************************************************************************************** */
 	 
 
function searhGroupMapp(id){
	  
	  
	  if(id == "ugu_mapp"){
		  
		  
		  
		  viewUserMapp($("#ugu_mapp").val());
		  
		  
		  
	  }else if(id == "ugd_mapp"){
		  
		  
		  viewDeptMapp($("#ugd_mapp").val());
		  
	  }
	  
  }
</script>
