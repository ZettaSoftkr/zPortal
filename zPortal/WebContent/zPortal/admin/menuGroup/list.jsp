<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
  <div class="box_type">
      
 </div>	

<div class="boardarea">
	<section class="boardlist">
	<div class="row">
			<ul class="nav nav-tabs" id="#myTab">
		
				<li id="menugroup_menu_status_tab" class="active"><a href="#menugroup_menu_status_view"	data-toggle="tab"><span class="textt"> 메뉴그룹관리 </span></a></li>
				<li id="menugroup_menu_mapp_tab" class=""><a href="#menugroup_menu_mapp_view" data-toggle="tab"><span class="textt"> 메뉴그룹 메뉴 맵핑 </span></a></li>
			
			</ul>
		 <div class="tab-content">
		 		<!--  메뉴 그룹 관리 시작 -->
		 		<div class="tab-pane  active" id="menugroup_menu_status_view"> <br>
		 			<div class="col-md-3">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span>  메뉴그룹 현황
								 &nbsp; <a href="javascript: d0.openAll();">open all</a> | <a href="javascript: d0.closeAll();">close all</a>
							</div>
							<div class="panel-body">
								
									<div class="panel-body" id="menuGroup_list"></div>
								
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 메뉴그룹 목록
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
			
				
				<!--  메뉴 그룹 관리 끝  -->
				<!-- 메뉴 그룹 메뉴 매핑  -->
		 		<div class="tab-pane" id="menugroup_menu_mapp_view">
		 			
				
		 		<!-- 메뉴 그룹 모달 시작 -->		
							     <form class="form-horizontal" role="form" method="POST" name="menuGroupForm" id="menuGroupForm"> 
							        <div class="searcharea">
							           <div class="box_second">                                  
							            <div class="lm_select_left">
							                <ul>
							                    <li class="box_title">메뉴그룹</li>
							                    <li class="box_form">
							                       <select name="bi_menu_group_id" id="mapp_menuGroup_id" class="combobox_free_class">
							                            <option value="">선택하세요</option>
														
							                        </select>
							                    </li>                          
							                </ul>                 
							            </div>
										<button type="button" class="search_button" title="조회" onclick="searchMenuMapp();">
							                <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
							            </button>  
<!-- 										<button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();"> -->
<!-- 							                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span> -->
<!-- 							             </button> -->
						             </div>
						             </div>
							    
									<div class="col-md-5">
										<div class="panel panel-info">
											<div class="panel-heading">
												<span class="glyphicon glyphicon-th-list">전체메뉴</span>
											</div>
											<div class="panel-body" id="menu_list"></div>
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
														<button type="button" id="group_menu_add" class="btn btn-success btn-xs">
															<span class="glyphicon glyphicon-chevron-right"></span>
														</button>
														<button type="button" id="group_menu_remove" class="btn btn-success btn-xs">
															<span class="glyphicon glyphicon-chevron-left"></span>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-5">
										<div class="panel panel-info">
											<div class="panel-heading">
												<span class="glyphicon glyphicon-th-list">선택메뉴</span>
											</div>
											<div class="panel-body" id='menu_selected_list'></div>
										</div>
									</div>
							
								
							<div class="col-md-12">
								<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
								<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
							</div>
							</form>	
		 				
				</div>
<!-- 메뉴 그룹 모달 끝 -->
		   </div>
	 </div>
</section>
</div>
<!-- 수정 모달 시작 -->
<form class="form-horizontal" role="form" method="POST" name="editAdminForm" id="editAdminForm">
<input type="hidden" id="edit_bi_menu_group_id" name="bi_menu_group_id">
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">수정폼</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">메뉴그룹명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="edit_bi_menu_group_nm" name="bi_menu_group_nm" placeholder="그룹명" required type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">메뉴그룹설명</label>
						<div class="input-group col-sm-8">
							<textarea class="form-control" id="edit_bi_dc" name="bi_dc" rows="8" placeholder="설명" required></textarea>
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
<!-- 수정 모달 끝 -->

<!-- 등록 모달 시작 -->
<form class="form-horizontal" role="form" action="" method="post" name="writeAdminForm" id="writeAdminForm">
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">메뉴그룹 등록</h4>
				</div>
				<div class="modal-body">
					
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">메뉴그룹 명</label>
						<div class="col-sm-3 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_menu_group_nm" name="bi_menu_group_nm" placeholder="그룹 명" required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_user_nm" class="col-sm-2 control-label">메뉴그룹설명</label>

						<div class="input-group col-sm-8">
							<textarea class="form-control" id="bi_cn" name="bi_cn" rows="8" placeholder="설명" required></textarea>
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
<!-- 등록 모달 끝 -->


<script type="text/javascript">


var viewStatus = "edit";


var headerTitle = ["번호",  "메뉴그룹명", "메뉴그룹맵핑",];
var attrKey = "bi_menu_group_id";  //keyvalue
var attrVal =        //attributy
[{
  name : "bi_menu_group_nm",
  type : "input"
, val : ""
, funcNm:""
, href:"N"
},{
  name : "menuGroupNm",
  type : "tabLink"
, val : ""
, funcNm:"viewMenuMapp"
, href:"N"
}];



	$(document).ready(function() {
		
		
		getMenuNmdept(currentId);

		var currentPage = 1;
		var searchtitle = 0;
		var searchContent = "";
		var menuGroupAdminCd = "MG00001";

		getAdminListPageData(currentPage, searchtitle, searchContent);
	
		menuGroupMappInit(); // 메뉴그룹 목록 
		menuGroupSelectList(menuGroupAdminCd); //검색 목록
		
		
	});
	
	
	/***************************************************************************************
	**
	**  탭 선택시 화면 구성 
	**  수정 및 삭제 
	** 시작 **
	******************************************************************************************/

// 	$("#menu_modify").click(function() {

// 		$("#menuCreateTab").attr("class", "");
// 		$("#menuCreate").attr("class", "tab-pane");
// 		$("#menuEditTab").attr("class", "active");
// 		$("#menuEdit").attr("class", "tab-pane active");



// 		$("form").each(function() {

// 			if (this.id == new String("writeMenuForm"))
// 				this.reset();

// 		});

// 	});
	
	
	/***************************************************************************************
	**
	**  메뉴 그룹 -메뉴 매핑 
	**  현황  트리 나타내기   dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/

	var menuGroupMappArray = [ [ '#', -1, '목록' ]];	
	function menuGroupMappInit() {

		$.ajax({

			url : "/" + serviceName + "/admin/menuGroup/getMenuGroupMappStatus.do",
			type : "post",
			data : "",
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {

				for (var i = menuGroupMappArray.length - 1; i--;) {
					if (i != 0) {
						menuGroupMappArray.splice(i);
					}
				}

				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

				for (var i = 0; i < data.length; i++) {
					var addArray = [ data[i][0], data[i][1], data[i][2] ];
					menuGroupMappArray.push(addArray);
				}

				menuGroupMappList(menuGroupMappArray);
				
				

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
	

	function menuGroupMappList(menuGroupMappArray) {

		d0 = new dTree('d0');

		for (var i = 0; i < menuGroupMappArray.length; i++) {
			
			d0.add(menuGroupMappArray[i][0], menuGroupMappArray[i][1], menuGroupMappArray[i][2]);
			
		}
		
		$('#menuGroup_list').html(d0.toString());
	}
	
	

	
	
	
	
	var menuArray = [ [ '#', -1, '메뉴목록' ]];	
    var menuCheckArray = [[ '#', -1, '메뉴목록' ]];
   
	
	function menuList(menuArray){
		
		d4 = new dTree('d4');
		for (var i = 0; i < menuArray.length; i++) {
	
			d4.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
		}
		
		$('#menu_list').html(d4.toString());
	}
	

	 function menuGroupList(menuCheckArray) {	
		 
		d5 = new dTree('d5');			 
		$('#menu_selected_list').empty();	
		if(menuCheckArray.length >0){
			
			//alert(menuCheckArray);
			
			for (var i = 0; i < menuCheckArray.length; i++) {				
				d5.add(menuCheckArray[i][0], menuCheckArray[i][1], menuCheckArray[i][2]);
				
				
			}
		}
		
		$('#menu_selected_list').html(d5.toString());

	}
	
	//*************************************************버튼 이동 및 선택 기능 *******************************************************//

	//메뉴 추가하기 	
	$("#group_menu_add").click(function() {

	
		  $(":checkbox[name='menuNodeId']:checked").each(function() {
				
			  
			  checkAdd($(this).val());

		});


		menuList(menuArray);
		menuGroupList(menuCheckArray);

	});
	
	$("#group_menu_remove").click(function() {

		checkReMove(menuCheckArray);
		// $(":checkbox[name='menuCheckId']:checked").each(function() {
		
			 
			
		//  $(":checkbox[name='menuCheckId']:not(:checked)").each(function() {
			//checkReMove($(this).val(), menuCheckArray);

		//});

		

	});
	
	
	//하위 메뉴 선택
	
	function checkAdd(checkId) {
		

		   var checkboxNmObj = document.getElementsByName("menuCheckId");
			
		    var valNum =0;	
		    
			for (var j = 0; j < checkboxNmObj.length; j++) {
				if (checkId == checkboxNmObj[j].value) {
					valNum++;
					break;
				}

			}
		
			
			if(valNum == 0){
				
				var pid;
				var id;
				var name;				
				for (var i = 0; i < menuArray.length; i++) {
						
					if (menuArray[i][0] == checkId) {
						id = menuArray[i][0];
						pid = menuArray[i][1];
						name = menuArray[i][2];
						menuCheckArray.push([id, pid, name]);
						//menuArray.splice(i,1);
						break;
					}
				}
				
				
			}
		
	}
	
	
	function checkReMove(menuCheckArray){
		   
		   
		 var menuCheckArray1 = [[ '#', -1, '메뉴목록' ]];
		 var checkboxNmObj = document.getElementsByName("menuCheckId");
		 
			for(var j=0;j<checkboxNmObj.length;j++){
				
			   if(checkboxNmObj[j].checked == false){
				    for (var i = 0; i < menuCheckArray.length; i++) {		    	
				    	 
						if (menuCheckArray[i][0] == checkboxNmObj[j].value) {
							menuCheckArray1.push([menuCheckArray[i][0],menuCheckArray[i][1],menuCheckArray[i][2]]);							
							break;
							
						}
			
					}
			   }
			    
			}   
			
		    menuGroupList(menuCheckArray1);
		    
		   
		    
		    
// 		    var valNum =0;
// 			for (var j = 0; j < checkboxNmObj.length; j++) {
				
// 				if (checkId == checkboxNmObj[j].value) {
// 					valNum = valNum + 1;
// 					break;
// 				}
// 			}	
			
// 			if(valNum > 0){
				
// 			}
	}
	
	
	 function checkMenu(inputNm){	
		 
	
		if(inputNm.checked){
			
			checkParentId1(menuArray, "menuNodeId", inputNm.value, true); //부보 체크 (array,메뉴ID,현재값,checked)
			checkChildId1(menuArray, "menuNodeId", inputNm.value, true); //자식 체크(array,메뉴ID,현재값,checked)
			
		}else{
			
			checkChildId1(menuArray, "menuNodeId", inputNm.value, false); //자식 체크(array,메뉴ID,현재값,checked)
		}
		
	}
	
	
    function checkSelectMenu(inputNm){
		
    	
		if(inputNm.checked){
			
			//checkParentId1(menuCheckArray, "menuCheckId", inputNm.value, true); //부보 체크 (array,메뉴ID,현재값,checked)
			checkChildId1(menuCheckArray,  "menuCheckId", inputNm.value, true); //자식 체크(array,메뉴ID,현재값,checked)
			
		}else{
			
			
			checkChildId1(menuCheckArray, "menuCheckId", inputNm.value, false); //자식 체크(array,메뉴ID,현재값,checked)
		}
		
	}
     
     
     
 	//상위 메뉴 선택

 	function checkParentId1(arrayVal, checkboxNm, menuId, checkType) {
 		

 		var checkboxNmOj = document.getElementsByName(checkboxNm);

 		for (var i = 0; i < arrayVal.length; i++) {
 			
 			if (arrayVal[i][0] == menuId) {
 				
 				 var  parentId = arrayVal[i][1];
 			
 				for(var j=0; j < checkboxNmOj.length;j++){
 					
 					if(parentId == checkboxNmOj[j].value){
 						checkboxNmOj[j].checked = checkType;
 					}
 				}
 				
 				checkParentId1(arrayVal, checkboxNm, arrayVal[i][1], checkType);
 					
 			}

 		}

 	}
 	
 	
 	//하위 메뉴 선택
 	function checkChildId1(arrayVal, checkboxNm, menuId, checkType) {

 		var checkboxNmOj = document.getElementsByName(checkboxNm);
 		
		for (var i = 0; i < arrayVal.length; i++) {
			
 			if (arrayVal[i][1] == menuId) {
 				
 				 var  currentId = arrayVal[i][0];
 				
 				for(var j=0; j < checkboxNmOj.length;j++){
 					
 					if(currentId == checkboxNmOj[j].value){ 					
 						checkboxNmOj[j].checked = checkType;
 									  
 					}
 				} 	
 				
 				checkChildId1(arrayVal, checkboxNm, arrayVal[i][0], checkType);
			
 			}

 		}


 	}
 	
	function menuGroupSelectList(mgAdminCd) {
			
		
		//var mgAdminCd  = $('#mapp_menuGroup_id').val();
		$('#mapp_menuGroup_id').empty(); //클리어
			
			$.ajax({

	 			url : "/" + serviceName + "/admin/menuGroup/getMenuGroupList.do",
	 			type : "post",
	 			data : "",
	 			datatype : 'json',
	 			success : function(data) { 				
	 				
	 			
	 				for(var i=0;i<data.length;i++){
	 					
	 					if(mgAdminCd == data[i]['bi_menu_group_id']){
	 						$("#mapp_menuGroup_id").append("<option value='"+ data[i]['bi_menu_group_id']+ "' selected>" + data[i]['bi_menu_group_nm'] +"</option>");
	 					}else{
	 						
	 						$("#mapp_menuGroup_id").append("<option value='"+ data[i]['bi_menu_group_id']+ "'>" + data[i]['bi_menu_group_nm'] +"</option>");
	 					}
	 				}
	 				
	 				//

	 			},
	 			error : function() {
	 				alert("false");

	 			}
	 		});

			
			
	}
		
 	
 	
 	/** *********************************************************************************************************** */
 	/** *********************************************************************************************************** */
 	/**
 	 * ************************************* 맵핑 화면으로 이동 
 	 * ******************************
 	 */
 	/** *********************************************************************************************************** */
 	/** *********************************************************************************************************** */

 	function viewMenuMapp(groupId, groupNm) {
 		
 		
 		
 		menuGroupSelectList(groupId);

 		$("#menuGroup_bi_menu_group_nm").html(groupNm);
 		$("#menuGroup_bi_menu_group_id").val(groupId);
 		//$("#menuGroup_modal").modal('show');
 		
 		
 		$("#menugroup_menu_mapp_tab").attr("class", "active");
		$("#menugroup_menu_mapp_view").attr("class", "tab-pane active");
 		
		$("#menugroup_menu_status_tab").attr("class", "");
		$("#menugroup_menu_status_view").attr("class", "tab-pane");

 		$.ajax({

 			url : "/" + serviceName + "/admin/menu/getListGroup.do",
 			type : "post",
 			data : "",
 			datatype : 'json',
 			success : function(data) {
 				
 				
 				for (var i = 0; i < data.length; i++) {
 					var addArray = [ data[i][0], data[i][1], data[i][2] ];
 					menuArray.push(addArray);
 				}
 				menuList(menuArray);

 			},
 			error : function() {
 				alert("false");

 			}
 		});

 		$.ajax({
 			url : "/" + serviceName + "/admin/menuGroup/menuGroupMappList.do",
 			type : "POST",
 			data : {
 				bi_menu_group_id : groupId
 			},
 			datatype : 'json',
 			success : function(data) {
 				
 				 //data.push([ '#', -1, '메뉴목록' ]);
 				
 				 for(var i = menuCheckArray.length-1; i--;){
 					 if(i != 0){
 						 menuCheckArray.splice(i);
 					 }
 			     }
 				  
 				 // menuCheckArray.push([ '#', -1, '메뉴목록' ]);
 				  
 				for (var i = 0; i < data.length; i++) {
 					var addArray = [ data[i][0], data[i][1], data[i][2] ];
 					menuCheckArray.push(addArray);
 				}
 				
 			   
 				menuGroupList(menuCheckArray);
 				
 				//menuArray.push(data);
 				
 			},
 			error : function() {
 				
 				$("#progress_modal").modal('hide');
 				$(".progress").hide();
 				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
 				$("#session_modal").modal("show");	
 				$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
 				

 			}

 		});


	}	
 		


 
 	
 	
 	
 	function searchMenuMapp() {
 		

 		
 		var groupId =$("#mapp_menuGroup_id").val();
 		
 		$.ajax({

 			url : "/" + serviceName + "/admin/menu/getListGroup.do",
 			type : "post",
 			data : "",
 			datatype : 'json',
 			success : function(data) {
 				
 				
 				for (var i = 0; i < data.length; i++) {
 					var addArray = [ data[i][0], data[i][1], data[i][2] ];
 					menuArray.push(addArray);
 				}
 				menuList(menuArray);

 			},
 			error : function() {
 				alert("false");

 			}
 		});

 		$.ajax({
 			url : "/" + serviceName + "/admin/menuGroup/menuGroupMappList.do",
 			type : "POST",
 			data : {
 				bi_menu_group_id : groupId
 			},
 			datatype : 'json',
 			success : function(data) {
 				
 				 //data.push([ '#', -1, '메뉴목록' ]);
 				
 				 for(var i = menuCheckArray.length-1; i--;){
 					 if(i != 0){
 						 menuCheckArray.splice(i);
 					 }
 			     }
 				  
 				 // menuCheckArray.push([ '#', -1, '메뉴목록' ]);
 				  
 				for (var i = 0; i < data.length; i++) {
 					var addArray = [ data[i][0], data[i][1], data[i][2] ];
 					menuCheckArray.push(addArray);
 				}
 				
 			   
 				menuGroupList(menuCheckArray);
 				
 				//menuArray.push(data);
 				
 			},
 			error : function() {
 				
 				$("#progress_modal").modal('hide');
 				$(".progress").hide();
 				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
 				$("#session_modal").modal("show");	
 				$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
 				

 			}

 		});

 		
 	}

 	
 	
 	 /***********************************************************************************************
 	 ************************************************************************************************		
 	 *******************************        메뉴 그룹 메뉴 맵핑 저장하기 시작         ********************************* 
 	************************************************************************************************/
 	 		$("#menuGroupForm").submit(function(e) {

 	 			var checkboxNmOj = document.getElementsByName("menuCheckId");
// 	 			if(checkboxNmOj.length == 0){
// 	 				
// 	 				$("#msg").html("선택된 메뉴목록이 없습니다.");
// 	 				$("#msg_modal").modal('show');	
// 	 				
// 	 				e.preventDefault(); // STOP default action
// 	 				e.unbind(); // unbind. to stop multiple form submit.
// 	 				return
// 	 			
 	 	//	
// 	 			
// 	 			}else{
 	 			
 	 				for (var i = 0; i < checkboxNmOj.length; i++) {
 	 		
 	 					checkboxNmOj[i].checked = true;
 	 				}
 	 		
 	 				var postData = $(this).serializeArray();
 	 		
 	 				var url = "/" + serviceName + "/admin/menuGroup/menuGroupMappSave.do";
 	 		
 	 				$.ajax({
 	 					url : url,
 	 					type : "POST",
 	 					data : postData,
 	 					dataType : 'json',
 	 					beforeSend : function() { //
 	 		
 	 					},
 	 					success : function(data, textStatus, jqXHR) {
 	 						
 	 		
 	 						$("#msg").html("저장 되었습니다.");
 	 						$("#msg_modal").modal('show');		
 	 						
 	 						$("form").each(function(){					
 	 							  this.reset();		
 	 						});
 	 		
 	 		
 	 					},
 	 					error : function(jqXHR, textStatus, errorThrown) {
 	 						// if fails
 	 					}
 	 					
 	 				});
 	 				e.preventDefault(); // STOP default action
 	 				e.unbind(); // unbind. to stop multiple form submit.
 	 			//}
 	 		});
</script>
