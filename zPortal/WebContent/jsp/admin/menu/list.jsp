<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
  <div class="box_type"></div>	
 <div class="Grid_Area">
	<section class="boardlist">
		<div class="row">
			<ul class="nav nav-tabs" id="#myTab">
				<li class="active"><a href="#menuCreate" data-toggle="tab"><span class="textt">메뉴 생성 </span></a></li>
				<li class=""><a href="#menuEdit" data-toggle="tab"><span class="textt"> 메뉴 수정  및 삭제 </span></a></li>
				
			</ul>
			<div class="tab-content">
				<!--  메뉴 생성 -->
				<div class="tab-pane active" id="menuCreate">
					<br>
					<div class="col-md-5">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span>  전체 메뉴
								 &nbsp; <a href="javascript: d0.openAll();">open all</a> | <a href="javascript: d0.closeAll();">close all</a>
							</div>
							<div class="panel-body">								
									<div class="panel-body" id="menu_list"></div>								
							</div>
						</div>
					</div>
					<div class="col-md-7">					
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 메뉴 등록 폼
							</div>
							<div class="panel-body">
								<form class="form-horizontal" role="form" method="post"	name="writeMenuForm" id="writeMenuForm">
									<input type="hidden" id="bi_portal_menu_id"	name="bi_portal_menu_id" value=""> 
									<input type="hidden" id="bi_portal_menu_parent_id" name="bi_portal_menu_parent_id" value="">
									<div class="form-group">
										<label for="dept" class="col-sm-3 control-label">메뉴형태</label>
										<div class="input-group col-sm-8">
											<input type="radio" id="bi_menu_type_yn_1" name="bi_menu_type_yn" value="F" required> 폴더
											<input type="radio" id="bi_menu_type_yn_2" name="bi_menu_type_yn" value="P" required> 페이지
											<input type="radio" id="bi_menu_type_yn_3" name="bi_menu_type_yn" value="R" required> 보고서		
										</div>
									</div>
									<div class="form-group">
										<label for="dept" class="col-sm-3 control-label">기능</label>
										<div class="input-group col-sm-8">
										    <input type="radio" id="edit_bi_menu_fm_yn_1" name="bi_menu_fm_yn" value="1" required checked> 바로보기
											<input type="radio" id="edit_bi_menu_fm_yn_2" name="bi_menu_fm_yn" value="2" required> 새창
										</div>
									</div>
									<div class="form-group">
										<label for="text" class="col-sm-3 control-label">상위메뉴</label>
										<div class="input-group col-sm-5">
											<input class="form-control input-sm" type="text"
												id="bi_parent_menu_nm" required readonly>
											<div class="input-group-btn ">
												<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" id="dropdwon_menu_create">메뉴선택 <span class="caret"></span></button>
										        <ul class="dropdown-menu pull-right" id="dropdown_menu_create_view">
										        </ul>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="text" class="col-sm-3 control-label">메뉴명</label>
										<div class="input-group col-sm-8">
											<input type="text" class="form-control input-sm" id="bi_menu_nm" name="bi_menu_nm" placeholder="메뉴명" required>
										</div>
									</div>
									<div class="form-group">
										<label for="url" class="col-sm-3 control-label">URL</label>
										<div class="input-group col-sm-8">
											<input type="text" class="form-control input-sm"	id="bi_menu_url_addr" name="bi_menu_url_addr" placeholder="URL">
										</div>
									</div>
									<div class="form-group">
										<label for="dept" class="col-sm-3 control-label">메뉴순서</label>
										<div class="input-group col-sm-3">
											<input type="text" class="form-control input-sm"
												id="bi_menu_sort_sn" name="bi_menu_sort_sn" placeholder="순서" required>
										</div>
									</div>
									<div id="reportOption">
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">QVW명</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm"
													id="bi_qvw_nm" name="bi_qvw_nm">
											</div>
										</div>
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">검색키워드</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm"
													id="bi_search_keyword" name="bi_search_keyword">
											</div>
										</div>
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">설명</label>
											<div class="input-group col-sm-8">
												<textarea class="form-control" id="bi_dc" name="bi_dc"
													rows="5" placeholder="설명"></textarea>
											</div>
										</div>
									</div>
									<div class="col-sm-10">
									 <button type="button" class="button_Area" id="canBtn">
							               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>취소</span>
							           </button>
									  <div class="btn_area">
							           <button type="submit" class="button_Area" >
							               <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>저장</span>
							           </button>
							           </div> 
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
				<!--  메뉴 생성  -->
				<!--  메뉴 수정  -->
				<div class="tab-pane" id="menuEdit">
					<br>
					<div class="col-md-5">
						<div class="panel panel-info">
							<div class="panel-heading">
								<button type="button" class="btn btn-danger btn-xs"	id="deleteMenu_button">
									<i class="glyphicon glyphicon-remove"></i> 메뉴삭제
								</button>

							</div>
							<div class="panel-body">
								<form name="listForm" id="listForm" method="post">
										<input type="hidden" id="lev1_menuOpenId" name="lev1_menuOpenId"> 
										<input type="hidden" id="lev2_menuOpenId" name="lev2_menuOpenId"> 
										<input type="hidden" id="lev3_menuOpenId" name="lev3_menuOpenId"> 
										<input type="hidden" id="currentId" name="currentId">
									<div class="panel-body" id="menu_edit_list"></div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list"></span> 메뉴
							</div>
							<div class="panel-body">
								<div class="tab-pane">
									<br>
									<form class="form-horizontal" role="form" method="post"	name="editMenuForm" id="editMenuForm">
										<input type="hidden" id="lev1_menuOpenId" name="lev1_menuOpenId"> 
										<input type="hidden" id="lev2_menuOpenId" name="lev2_menuOpenId"> 
										<input type="hidden" id="lev3_menuOpenId" name="lev3_menuOpenId">
										<input type="hidden" id="edit_bi_portal_menu_id" name="bi_portal_menu_id">
										<input type="hidden" id="edit_bi_portal_menu_parent_id" name="bi_portal_menu_parent_id">
										<div class="form-group">
											<label for="dept" class="col-sm-3 control-label">메뉴형태</label>
											<div class="input-group col-sm-8">
												<input type="radio" id="edit_bi_menu_type_yn_1" name="bi_menu_type_yn" value="F"> 폴더 
												<input type="radio" id="bi_menu_type_yn_2" name="bi_menu_type_yn" value="P" required> 페이지
												<input type="radio" id="bi_menu_type_yn_2" name="bi_menu_type_yn" value="R" required> 보고서
											</div>
										</div>
										<div class="form-group">
											<label for="dept" class="col-sm-3 control-label">기능</label>
											<div class="input-group col-sm-8">
											    <input type="radio" id="edit_bi_menu_fm_yn" name="bi_menu_fm_yn" value="1" required checked> 바로가기
											    <input type="radio" id="edit_bi_menu_fm_yn" name="bi_menu_fm_yn" value="2" required> 새창
											</div>
										</div>
										<div class="form-group">
											<label for="text" class="col-sm-3 control-label">상위메뉴</label>
											<div class="input-group col-sm-5">
												<input class="form-control input-sm" type="text"
													id="edit_bi_parent_menu_nm" required readonly>
												<div class="input-group-btn ">
													<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" id="dropdwon_menu_edit"> 메뉴선택
													 <span class="caret"></span>
														
													</button>
													<ul class="dropdown-menu pull-right" id="dropdown_menu_edit_view">
													</ul>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="text" class="col-sm-3 control-label">메뉴명</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm" id="edit_bi_menu_nm" name="bi_menu_nm" placeholder="메뉴명"	required>
											</div>
										</div>
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">URL</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm" id="edit_bi_menu_url_addr" name="bi_menu_url_addr" 	placeholder="URL">
											</div>
										</div>
										<div class="form-group">
											<label for="dept" class="col-sm-3 control-label">메뉴순서</label>
											<div class="input-group col-sm-3">
												<input type="text" class="form-control input-sm" id="edit_bi_menu_sort_sn" name="bi_menu_sort_sn" placeholder="순서" required>
											</div>
										</div>	
										<div id="edit_reportOption">
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">QVW명</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm" id="edit_bi_qvw_nm" name="bi_qvw_nm">
											</div>
										</div>
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">검색키워드</label>
											<div class="input-group col-sm-8">
												<input type="text" class="form-control input-sm" id="edit_bi_search_keyword" name="bi_search_keyword">
											</div>
										</div>
										<div class="form-group">
											<label for="url" class="col-sm-3 control-label">설명</label>
											<div class="input-group col-sm-8">
												<textarea class="form-control" id="edit_bi_dc" name="bi_dc"	rows="5" placeholder="설명"></textarea>
											</div>
										</div>
										</div>
										<div class="col-sm-10">
											<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">수정</button>
											<button type="button" class="btn btn-warning btn-xs" id="canBtn">취소</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!--  메뉴 수정  -->
				</div>
				</div>
			</div>
	</section>
</div>


<!-- 끝 -->
<!--search Modal -->
<script type="text/javascript">
	var menuArray = [ [ '#', -1, '목록' ] ];
	
	$(document).ready(function() {
		
		
		getMenuNmdept(currentId);
		
		list();
		//reportList();
		
	});
	
	/***************************************************************************************
	**
	**  메뉴 시작시 트리 표현 
	**  생성 트리 나타내기   dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/
	
	function list() {

		$.ajax({

			url : "/" + serviceName + "/admin/menu/getListGroup.do",
			type : "post",
			data : "",
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {

				for (var i = menuArray.length - 1; i--;) {
					if (i != 0) {
						menuArray.splice(i);
					}
				}

				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0)
						.progressbar();

				for (var i = 0; i < data.length; i++) {
					var addArray = [ data[i][0], data[i][1], data[i][2] ];
					menuArray.push(addArray);
				}

				menuList(menuArray);
				menuEditList(menuArray);
				
				

			},
			error : function() {
				alert("false");

			}
		});

	}
	
	/*
	function reportList() {

		$.ajax({

			url : "/" + serviceName + "/menu/getSiteMap.do",
			type : "post",
			data : {bi_portal_menu_id:menuServCode},
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {
				
				//alert(JSON.stringify(data));

				for (var i = menuArray.length - 1; i--;) {
					if (i != 0) {
						menuArray.splice(i);
					}
				}

				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0)
						.progressbar();

				for (var i = 0; i < data.length; i++) {
					var addArray = [ data[i][0], data[i][1], data[i][2] ];
					menuArray.push(addArray);
				}
				
				menuExcelList(menuArray);
				

			},
			error : function() {
				alert("false");

			}
		});
	}
	*/
	
	
	/***************************************************************************************
	**
	**   메뉴 생성 > 전체 메뉴  트리 구현  d0
	**  생성 트리 나타내기   dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/
	

	function menuList(menuArray) {

		d0 = new dTree('d0');

		for (var i = 0; i < menuArray.length; i++) {

			d0.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
		}

		$('#menu_list').html(d0.toString());
	}
	
	
	/***************************************************************************************
	**
	**  수정 및 삭제 
	**  수정 트리 나타내기 dtree/dtreeMenu.js
	** 시작 **
	******************************************************************************************/
	
	
	function menuEditList(menuArray) {

		d1 = new dTree('d1');

		for (var i = 0; i < menuArray.length; i++) {

			d1.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
		}
		
		$('#menu_edit_list').html(d1.toString());
		
	}


	
	/***************************************************************************************
	**
	**  수정 및 삭제 
	**  메뉴 특정 메뉴 선택시  바로 위 상위까지 
	** 시작 **
	******************************************************************************************/

	function checkSelectMenu(objNm) {

		if (objNm.checked) {
			
			//checkParentId(menuArray, "menuId", objNm.value, true); //부보 체크 (array,메뉴ID,현재값,checked)
			checkChildId(menuArray, "menuId", objNm.value, true); //자식 체크(array,메뉴ID,현재값,checked)
			

		} else {

			checkChildId(menuArray, "menuId", objNm.value, false); //자식 체크(array,메뉴ID,현재값,checked)
			
		}

	}

	/***************************************************************************************
	**
	**  수정 및 삭제 
	**  메뉴 특정 메뉴 선택시  바로 위 상위까지 
	** 시작 **
	******************************************************************************************/
	

	function checkParentId(arrayVal, objNm, menuId, checkType) {

		var checkboxNmOj = document.getElementsByName(objNm);

		for (var i = 0; i < arrayVal.length; i++) {

			if (arrayVal[i][0] == menuId) {

				var parentId = arrayVal[i][1];

				for (var j = 0; j < checkboxNmOj.length; j++) {

					if (parentId == checkboxNmOj[j].value) {
						checkboxNmOj[j].checked = checkType;
					}
				}

				checkParentId(arrayVal, objNm, arrayVal[i][1], checkType);

			}
		}
	}
	
	/***************************************************************************************
	**
	**  수정 및 삭제 
	**  메뉴 특정 메뉴 선택시 하위 까지 바로 선택 
	** 시작 **
	******************************************************************************************/
	
	//하위 메뉴 선택
	function checkChildId(arrayVal, objNm, menuId, checkType) {

		var checkboxNmOj = document.getElementsByName(objNm);

		for (var i = 0; i < arrayVal.length; i++) {

			if (arrayVal[i][1] == menuId) {

				var currentId = arrayVal[i][0];

				for (var j = 0; j < checkboxNmOj.length; j++) {

					if (currentId == checkboxNmOj[j].value) {
						checkboxNmOj[j].checked = checkType;

					}
				}
				checkChildId(arrayVal, objNm, arrayVal[i][0], checkType);
			}

		}

	}
	
	
	/***************************************************************************************
	**
	** 생성
	**  메뉴 형태 선택시 QVW 관련 설명 작성 화면 나타내기 
	** 시작 **
	******************************************************************************************/

	$("#bi_menu_type_yn_1").click(function(){
		
		$("#reportOption").hide();
		$("#bi_menu_url_addr").val("");
		$("#edit_bi_qvw_nm").val("");
		$("#edit_bi_search_keyword").val("");
		$("#edit_bi_dc").val("");
		
	});
	
	
	$("#bi_menu_type_yn_2").click(function(){
		
		$("#reportOption").hide();
		$("#bi_menu_url_addr").val("");
		$("#edit_bi_qvw_nm").val("");
		$("#edit_bi_search_keyword").val("");
		$("#edit_bi_dc").val("");
	});
	
	
	$("#bi_menu_type_yn_3").click(function(){
		
		$("#reportOption").show();
		$("#bi_menu_url_addr").val("report/gotoPage.do?pageNm=list");
		
		
	});
	
	
	$("#edit_bi_menu_type_yn_1").click(function(){
		
		$("#edit_reportOption").hide();
		$("#bi_menu_url_addr").val("");
		$("#edit_bi_qvw_nm").val("");
		$("#edit_bi_search_keyword").val("");
		$("#edit_bi_dc").val("");
		
	});
	
	
	$("#edit_bi_menu_type_yn_2").click(function(){
		
		$("#edit_reportOption").hide();
		$("#edit_bi_qvw_nm").val("");
		$("#edit_bi_search_keyword").val("");
		$("#edit_bi_dc").val("");
		
	});
	
	
	$("#edit_bi_menu_type_yn_3").click(function(){
		
		$("#edit_reportOption").show();
		$("#bi_menu_url_addr").val("report/gotoPage.do?pageNm=list");
		
		
	});

				

	/***************************************************************************************
	**
	** 수정 및 삭제 
	**  트리 선택 후  - 메뉴 수정폼 값 나타내기 
	** 시작 **
	******************************************************************************************/
	
	
	
	
	function editMenu(objId) {

		if (objId.checked == true) {

			$.ajax({

				url : "/" + serviceName + "/menu/getParentMenuNm.do",
				type : "POST",
				data : {
					bi_portal_menu_id : objId.value
				},
				dataType : 'json',
				success : function(rowData) {

					//$("#menuCreateTab").attr("class", "");
					//$("#menuCreate").attr("class", "tab-pane");
					//$("#menuEditTab").attr("class", "active");
					//$("#menuEdit").attr("class", "tab-pane active");

					//alert(JSON.stringify(rowData));

					$("#edit_bi_parent_menu_nm").val(rowData['bi_menu_nm']);

					return;
				}
			});

			$.ajax({

				url : getDataLink.view,
				type : "POST",
				data : {
					bi_portal_menu_id : objId.value
				},
				dataType : 'json',
				success : function(rowData) {

					// alert(JSON.stringify(rowData));

					$("form").each(	function() {

						if(this.id == new String("editMenuForm")){
							this.reset();
						}

					});

					$("#edit_bi_portal_menu_parent_id").val(rowData['bi_portal_menu_parent_id']);

					if (rowData['bi_portal_menu_parent_id'] == new String("#")) {

						$("#edit_bi_parent_menu_nm").val("메뉴최상위목록");

					}

					$("#edit_bi_menu_nm").val(rowData['bi_menu_nm']);
					$("#edit_bi_portal_menu_id").val(rowData['bi_portal_menu_id']);
					$("#edit_bi_menu_sort_sn").val(rowData['bi_menu_sort_sn']);
					$("input:radio[name='bi_menu_type_yn']:radio[value='"+ rowData['bi_menu_type_yn'] + "']").prop("checked", true);
					$("input:radio[name='bi_menu_fm_yn']:radio[value='"+ rowData['bi_menu_fm_yn'] + "']").prop("checked", true);
					$("#edit_bi_menu_url_addr").val(rowData['bi_menu_url_addr']);
					$("#edit_bi_report_url_addr").val(rowData['bi_report_url_addr']);
					
					
					$("#edit_reportOption").hide();
					
					if(rowData['bi_menu_type_yn']== 'F'){
						
						$("#edit_bi_menu_type_yn_1").prop("checked", true);
						$("#edit_bi_qvw_nm").val("");
						$("#edit_bi_search_keyword").val("");
						$("#edit_bi_dc").val("");
						$("#edit_reportOption").hide();
						
					}else if(rowData['bi_menu_type_yn'] == 'P'){
						
						$("#edit_bi_menu_type_yn_2").prop("checked", true);
						$("#edit_bi_qvw_nm").val("");
						$("#edit_bi_search_keyword").val("");
						$("#edit_bi_dc").val("");
						$("#edit_reportOption").hide();
						
					}else if(rowData['bi_menu_type_yn'] == 'R'){
						
						$("#edit_bi_menu_type_yn_3").prop("checked", true);					
						$("#edit_reportOption").show();
					}
					
					
					//$("input:radio[name='bi_menu_type_yn']:radio[value='"+ rowData['bi_menu_type_yn'] + "']").prop("checked", true);
					$("#edit_bi_search_keyword").val(rowData['bi_search_keyword']);
					$("#edit_bi_dc").val(rowData['bi_dc']);
				
					$("#edit_bi_object_nm").val(rowData['bi_object_nm']);
					$("#edit_bi_qvw_nm").val(rowData['bi_qvw_nm']);
					
				}
			});

			//response.txt file call

		} else {

			$("form").each(function() {

				if (this.id == new String("editMenuForm") || this.id == new String("responseForm"))
					this.reset();

			});
			$("#tableContent").html("");
			//$("#edit_reportOption").hide();

		}

	}
	
	
	
	/***************************************************************************************
	**
	** 생성 탭
	** 메뉴 선택 기능 - 메뉴 나타나기 ( 트리 )
	** 시작 **
	******************************************************************************************/
	/* 생성  상위 메뉴트리 보기 */
	
	$(".dropdown-menu").click(function(e){
			e.stopPropagation();
	});
	
	
	var menuCreateArray = [ [ '#', -1, '목록' ] ];	
	$("#dropdwon_menu_create").click(function(){	   
		
	    var url = "/"+ serviceName+"/admin/menu/getListGroup.do";
	    $.ajax({
			
		    url:  url,
		    type: "post",
		    data:{} ,
		    datatype: 'json',
		    success: function(data){
		    	for(var i = menuCreateArray.length-1; i--;){
					 if(i != 0){
						 menuCreateArray.splice(i);
					 }
			}
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

			for (var i = 0; i < data.length; i++) {
				var addArray = [ data[i][0], data[i][1], data[i][2] ];
				menuCreateArray.push(addArray);
			}
			
			menuCreateList(menuCreateArray);
		    
		    }	
		 });
	    	
	  });
	/**
	
	 메뉴관리 > 메뉴 생성 > 메뉴등록폼 > 메뉴 선택버튼 
	
	
	***/
	 function menuCreateList(menuCreateArray) {

		d2 = new dTree('d2');

		for (var i = 0; i < menuCreateArray.length; i++) {

			d2.add(menuCreateArray[i][0], menuCreateArray[i][1], menuCreateArray[i][2]);
		}
		
		
		$("#dropdown_menu_create_view").html(d2.toString());
	}
	
	/* 상위 메뉴 선택후 아이디 */
	
	 function createParentId(objId){
		
		 $.ajax({

				url : getDataLink.view,
				type : "post",
				data : {
					bi_portal_menu_id : objId.value
				},
				dataType : 'json',
				beforeSend : function() {

				},
				success : function(data) {

					
						$("#bi_portal_menu_parent_id").val(data['bi_portal_menu_id']);
						$("#bi_parent_menu_nm").val(data['bi_menu_nm']);

					
				},
				error : function() {
					alert("false");

				}
			});
		 
		
	 }
	
	
/***************************************************************************************
**
** 수정 및 삭제 탭
** 메뉴 선택 기능 - 메뉴 나타나기  (트리)
** 시작 **
******************************************************************************************/

	
	
$(".dropdown-menu").click(function(e){
		e.stopPropagation();
});

var menuEditMiniArray = [ [ '#', -1, '목록' ] ];	
	
$("#dropdwon_menu_edit").click(function(){	   
		
	    var url = "/"+ serviceName+"/admin/menu/getListGroup.do";
	    $.ajax({
			
		    url:  url,
		    type: "post",
		    data:{} ,
		    datatype: 'json',
		    success: function(data){
		    	for(var i = menuEditMiniArray.length-1; i--;){
					 if(i != 0){
						 menuEditMiniArray.splice(i);
					 }
			}
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

			for (var i = 0; i < data.length; i++) {
				var addArray = [ data[i][0], data[i][1], data[i][2] ];
				menuEditMiniArray.push(addArray);
			}
			
			menuEditMiniList(menuEditMiniArray);
		    
		    }	
		 });
	    	
	  });
	
	
	 function menuEditMiniList(menuEditMiniArray) {

		d3 = new dTree('d3');

		for (var i = 0; i < menuEditMiniArray.length; i++) {

			d3.add(menuEditMiniArray[i][0], menuEditMiniArray[i][1], menuEditMiniArray[i][2]);
		}
		
		
		$("#dropdown_menu_edit_view").html(d3.toString());
	}
	
	 
	/* 상위 메뉴 선택후 아이디 */
	
	 function editParentId(objId){
		
		 $.ajax({

				url : getDataLink.view,
				type : "post",
				data : {
					bi_portal_menu_id : objId.value
				},
				dataType : 'json',
				beforeSend : function() {

				},
				success : function(data) {

					
						$("#edit_bi_portal_menu_parent_id").val(data['bi_portal_menu_id']);
						$("#edit_bi_parent_menu_nm").val(data['bi_menu_nm']);

					
				},
				error : function() {
					alert("false");

				}
			});
		 
		
	 }
	
	
	 /***************************************************************************************
	 **
	 ** 보고서 일괄 등록 엑셀업로드
	 ** 
	 **  
	 ******************************************************************************************/
	 
	 $("#excelDataForm").submit(function(e) {	
			
			if($("#fileNm").val() != new String("")){
				
			      var ext = $("#fileNm").val().split('.').pop().toLowerCase();
			      
			      
			      if($.inArray(ext, ['xlsx','xls']) == -1) {
					 alert('엑셀  파일만 업로드 할수 있습니다.');
					 $("form").each(function() {  
			    		 
			    		 if(this.id  == new String("excelDataForm")) this.reset();  
			            
			          });
					 
					 e.preventDefault(); // STOP default action
					 e.unbind(); // unbind. to stop multiple form submit.
					 return;
				   }
			}
			
			$(this).validate();
			$(this).attr('action', actionLink.save).submit(); 
		});
	
	 
	 $("#excelDataReg").click( function(e) {
		 
		 $.ajax({

				url : "/" + serviceName + "/menu/reportInit.do",
				type : "post",
				dataType : 'json',
				beforeSend : function() {

				},
				success : function(data) {

					alert(data);
					
				},
				error : function() {
					alert("false");

				}
			});
		 e.preventDefault(); // STOP default action
	 });
	 
	 
</script>
