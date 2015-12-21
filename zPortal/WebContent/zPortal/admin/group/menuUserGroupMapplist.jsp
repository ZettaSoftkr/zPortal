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
                    <li class="box_title">메뉴그룹</li>
                    <li class="box_form">
                       <select id="bi_menu_group_id" name="bi_menu_group_id" class="combobox_free_class">
                           
                        </select>
                    </li>  
                    <li class="box_title"> 메뉴 그룹을 선택 해주세요 </li>  
                </ul>                 
            </div>
<!--             <button type="button" class="search_button" title="조회" onclick="searchList();"> -->
<!--                 <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span> -->
<!--             </button> -->
<!--              <button type="button" class="search_button" title="조회" id="searchInit" onclick="searchClear();"> -->
<!--                  <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>초기화</span> -->
<!--              </button> -->
        </div>
    </div>  
</div>
<div class="boardarea">
	<form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
		<section class="boardlist">
			
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-5">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-th-list">사용자 그룹</span>
							</div>
							<div class="panel-body">
								<select id="bi_user_group_list" name="bi_user_group_list" multiple class="form-control" size="10">

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
									<button type="button" id="userGroup_add" class="btn btn-success btn-xs">
										<span class="glyphicon glyphicon-chevron-right"></span>
									</button>
									<button type="button" id="userGroup_remove" class="btn btn-success btn-xs">
										<span class="glyphicon glyphicon-chevron-left"></span>
									</button>
								</div>
							</div>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="panel panel-info">
							<div class="panel-heading"><span class="glyphicon glyphicon-th-list">메뉴그룹</span></div>
							<div class="panel-body">
								<select id="bi_user_group_select" name="bi_user_group_id" multiple class="form-control" size="10">

								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="col-md-10 col-md-offset-10">
			<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs">저장</button>
			<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
		</div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function() {	
	
	
	getMenuNmdept(currentId);
	
	
    var url = "/" + serviceName + "/admin/menuGroup/getMenuGroupList.do";
	
	$.ajax({
		url : url,
		type : "POST",
		dataType : 'json',
		beforeSend : function() { //
	
		},
		success : function(data) {
		
			$('#bi_menu_group_id').empty(); //클리어
			$("#bi_menu_group_id").append("<option value=''>:::::::::: 선택::::::::::::</option>");
			$.each(data, function(key, value) {
				$("#bi_menu_group_id").append("<option value='" + data[key]['bi_menu_group_id'] + "'>" + data[key]['bi_menu_group_nm'] + "</option>");
			});
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
		
	});
	
	function userGroupList(bi_menu_group_id){

		var url = "/" + serviceName + "/admin/group/getUserGroupListData.do";
	
		$.ajax({
			url : url,
			type : "POST",
			dataType : 'json',
			data : {bi_menu_group_id : bi_menu_group_id},
			beforeSend : function() { //
		
			},
			success : function(data) {
		
				//alert(JSON.stringify(data));
				$('#bi_user_group_list').empty(); //클리어
				//$("#bi_user_group_list").append("<option value=''>:::::::::: 선택::::::::::::</option>");
				$.each(data, function(key, value) {
					$("#bi_user_group_list").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");
				});
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
			
		});
	
	}
	
	
	
	$("#bi_menu_group_id").change(function() {

		var bi_menu_group_id = $("#bi_menu_group_id option:selected").val();
		userGroupList(bi_menu_group_id);

		$.ajax({
			url : "/" + serviceName + "/admin/menuGroup/getMenuUserGroupMappList.do",
			type : "POST",
			dataType : 'json',
			data : {
				
				bi_menu_group_id : bi_menu_group_id
			},			
			success : function(data) {

				$('#bi_user_group_select').empty(); //클리어
				$.each(data, function(key, value) {
					$("#bi_user_group_select").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");
				});
			}

		});

	});
	
});



$('#userGroup_add').click(function() {
	return !$('#bi_user_group_list option:selected').remove().appendTo('#bi_user_group_select');
});

$('#userGroup_remove').click(function() {
	return !$('#bi_user_group_select option:selected').remove().appendTo('#bi_user_group_list');
});


$("#listForm").submit(function(e) {

	$("#bi_user_group_select option").prop("selected", "selected");

	var postData = $(this).serializeArray();
	
    
	var url = "/" + serviceName + "/admin/menuGroup/menuUserGroupMappSave.do";

	$.ajax({
		url : url,
		type : "POST",
		data : postData,
		dataType:"json",
		beforeSend : function() { //
			
			$("#progress_modal").modal('show');		
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},
		success : function(data, textStatus, jqXHR) {
			
			$("#progress_modal").modal('hide');		
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');

			

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
	
});
	
</script>
