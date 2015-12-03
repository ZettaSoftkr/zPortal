<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="searcharea">
	<select name="searchTitle" id="searchTitle">
		<option value="0">선택하세요</option>
<!-- 	<option value="1">그룹ID</option> -->
		<option value="2">그룹명</option>
<!-- 	<option value="3">그룹ID + 그룹명</option> -->
	</select>
	<input type="text" name="searchContent" id="searchContent"> <a href="#" onclick="searchList();"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_search.gif" alt="검색"></a>
	<span id="searchClear"></span>
</div>
<div class="boardarea">
	<section class="boardlist">
		<h3>
			<span class="textt">그룹관리</span>
		</h3>
		<form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
			<table class="tablelist mt5" id="tableContent">

			</table>
		</form>
	</section>
	<div class="paging">
		<ul class="pagination pagination-sm" id="paging">
		</ul>
	</div>
	<div class="col-md-10 col-md-offset-10">
		<button type="button" id="writeForm_button" class="btn btn-primary btn-xs">등록</button>
		<button type="button" id="all_AdminDelete" class="btn btn-primary btn-xs">선택삭제</button>
	</div>
</div>


<!-- Modal -->
<form class="form-horizontal" role="form" method="POST" name="editForm" id="editForm">
<input type="hidden" name="bi_group_id" id="edit_bi_group_id" value="">
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
							<select  id="edit_bi_group_author_id" name="bi_group_author_id" class="form-control" required>
									<option value="">선택</option>
							 		<option value="ROLE_ANNOYMOUS">접속불가</option>
							 		<option value="ROLE_USER">일반사용자</option>
							 		<option value="ROLE_ADMINISTRATOR">관리사용자</option>
							 		<option value="ROLE_SYS_ADMIN">포탈관리자</option>
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



<!-- Modal -->
<form class="form-horizontal" role="form" action="" method="post" name="writeForm" id="writeForm">
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
							<select name="bi_group_author_id" id="bi_group_author_id"  class="form-control" required>
							  	    <option value="">선택</option>
							 		<option value="ROLE_ANNOYMOUS">접속불가</option>
							 		<option value="ROLE_USER">일반사용자</option>
							 		<option value="ROLE_ADMINISTRAOR">관리사용자</option>
							 		<option value="ROLE_SYS_ADMIN">포탈관리자</option>
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
<form class="form-horizontal" role="form" method="POST" name="deptGroupForm" id="deptGroupForm">
	
	<input type="hidden" id="bi_group_id" name="bi_group_id" value="">
	<div class="modal fade bs-example-modal-lg" id="dept_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"	aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dept_bi_group_nm"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-th-list">조직</span>
									</div>
									<div class="panel-body">
										<select id="bi_dept_id_list" name="bi_dept_id_list" multiple class="form-control" size="10" >

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2">
							 <div class="btn-group">
									<div class="panel panel-info">
										<div class="panel-heading">
											<span class="glyphicon glyphicon-th-list">선택</span>
										</div>
										<div class="panel-body">
											<div class="btn-group-vertical">
												<button type="button" id="dept_add"	class="btn btn-success btn-xs">
													<span class="glyphicon glyphicon-chevron-right"></span>
												</button>
												<button type="button" id="dept_remove" 	class="btn btn-success btn-xs">
													<span class="glyphicon glyphicon-chevron-left"></span>
												</button>
											</div>
	
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-5">
								<div class="panel panel-info">
									<div class="panel-heading">선택조직</div>
									<div class="panel-body">
										<select id="bi_dept_id_selected" name="bi_dept_id" multiple	class="form-control" size="10">

										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..."class="btn btn-success btn-xs">저장</button>
					<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">취소</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		var currentPage = 1;
		var searchtitle = 0;
		var searchContent = "";
		
		getAdminList(currentPage, searchtitle, searchContent);
		

		$.ajax({

			url : getDataLink.list,
			type : "post",
			data : "",
			datatype : 'json',
			success : function(data) {
				var keyCode = "G";
				var keyNum = 100000;
				var key = keyCode +(keyNum+data.totalNo);
				$("#bi_group_id").val(key);
				
				

			},
			error : function() {
				alert("false");

			}
		});
	});
</script>
