<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="searcharea">
	<select name="searchTitle" id="searchTitle">
		<option value="0">선택하세요</option>
		<option value="1">제목</option>
		<option value="2">내용</option>
		<option value="3">재목  + 내용</option>
	</select>
	<input type="text" name="searchContent" id="searchContent"> <a href="#" onclick="searchList();"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_search.gif" alt="검색"></a>
	<span id="searchClear"></span>
</div>
<div class="boardarea" id="listContent">
	<section class="boardlist">
		<h3>
			<span class="textt">QLIKVIEW 수집목록</span>
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

<div class="boardarea" id="viewContent">
	<input type="hidden" name="bi_nct_sn"  id="view_bi_nct_sn" value="">
 	<section class="boardlist">
		<h3>
			<span class="textt">QLIKVIEW 수집목록</span>
		</h3>
		<h4 class="viewtitle mt5" id="view_bi_sj"></h4>
		<ul class="viewmen">
			<li><span>작성자 : </span> <span id="view_bi_user_nm"></span></li>
			<li><span>조회수 : </span><span id="view_bi_rdcnt"></span></li>
			<li><span id="view_parseModifyDate"></span></li>
		</ul>
		<div class="viewcon" id="view_bi_cn">
			
		</div>
		<p class="tac mt20">
			<span class="board-tableT"><span><a href="#" onclick="getListPage(1);">목록</a></span></span> 
			
		</p>
		<ul class="prenext mt20">
			<li class="middle"><span class="m01">이전글</span> <span class="m02" id="prevTitle"><a href="#"></a></span> <span class="m03" id="prevDate"></span></li>
			<li><span class="m01">다음글</span> <span class="m02" id="nextTitle"><a href="#"></a></span> <span class="m03" id="nextDate"></span></li>
		</ul>
	</section>
</div>

<!-- Modal -->
<form class="form-horizontal" role="form"  method="post" name="writeForm" id="writeForm">
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">QLIKVIEW > 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">메뉴선택</label>
						<div class="input-group col-sm-8">
							<select name="bi_portal_menu_id1" id="bi_portal_menu_id1" size="5">
								<option value=''>:::::: 선택하세요 :::::::: </option>
							</select> 
							<select	name="bi_portal_menu_id2" id="bi_portal_menu_id2" size="5">
								<option value=''>:::::: 선택하세요 :::::::: </option>
							</select> 
							<select	name="bi_portal_menu_id3" id="bi_portal_menu_id3" size="5">
								<option value=''>:::::: 선택하세요 :::::::: </option>
							</select> 
<!-- 							<select	name="bi_portal_menu_id4" id="bi_portal_menu_id4" size="5"></select> -->
						</div>
					</div>
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<div class="input-group col-sm-8">
							<input class="form-control input-sm" type="text" id="bi_titl" name="bi_titl" placeholder="제목" required type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="content" class="col-sm-2 control-label">내용</label>
						<div class="input-group col-sm-8">
							<textarea class="form-control" id="bi_cn" name="bi_cn" rows="8" placeholder="설명" required></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" data-loading-text="loading..." class="btn btn-success btn-xs" >저장</button>
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
<form class="form-horizontal" role="form"  method="post" name="editForm" id="editForm">
<input class="form-control input-sm" type="hidden" id="edit_bi_bbs_sn" name="bi_bbs_sn" placeholder="제목">
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">QLIKVIEW > 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">위치지정</label>
						<div class="input-group col-sm-8">
						<select name="bi_portal_menu_id1" id="edit_bi_portal_menu_id1">
							<option value=''>:::::: 선택하세요 :::::::: </option>
						</select> 
						<select	name="bi_portal_menu_id2" id="edit_bi_portal_menu_id2">
							<option value=''>:::::: 선택하세요 :::::::: </option>
						</select> 
						<select	name="bi_portal_menu_id3" id="edit_bi_portal_menu_id3">
							<option value=''>:::::: 선택하세요 :::::::: </option>
						</select> 
<!-- 							<select	name="bi_portal_menu_id4" id="edit_bi_portal_menu_id4"></select> -->
						</div>
					</div>
				
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<div class="input-group col-sm-8">
							<input class="form-control input-sm" type="text" id="edit_bi_titl" name="bi_titl" placeholder="제목" required type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="content" class="col-sm-2 control-label">내용</label>
						<div class="input-group col-sm-8">
							<textarea class="form-control" id="edit_bi_cn" name="bi_cn" rows="8" placeholder="설명" required></textarea>
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

<script type="text/javascript">
$(document).ready(function() {
	
	
	var currentPage = 1;
	var searchtitle = 0;
	var searchContent = "";
	
	
	getListPageData(currentPage,searchtitle,searchContent);
	
	
	
	var clearSelect = [ 'bi_portal_menu_id1', 'bi_portal_menu_id2',	'bi_portal_menu_id3', 'bi_portal_menu_id4' ];
	getMenuList("ajson1", "bi_portal_menu_id1", clearSelect);

	$("#bi_portal_menu_id1").change(function() {
			var clearSelect = [ 'bi_portal_menu_id2', 'bi_portal_menu_id3', 'bi_portal_menu_id4'];
			getMenuList($("#bi_portal_menu_id1 option:selected").val(),"bi_portal_menu_id2", clearSelect);
	});
	$("#bi_portal_menu_id2").change(function() {
		var clearSelect = [ 'bi_portal_menu_id3', 'bi_portal_menu_id4'];
		getMenuList($("#bi_portal_menu_id2 option:selected").val(),"bi_portal_menu_id3", clearSelect);
	});
//		$("#bi_portal_menu_id3").change(function() {
//			var clearSelect = [ 'bi_portal_menu_id4'];
//			getMenuList($("#bi_portal_menu_id3 option:selected").val(),"bi_portal_menu_id4", clearSelect);
//		});
	
	

	var clearSelect = [ 'edit_bi_portal_menu_id1', 'edit_bi_portal_menu_id2',	'edit_bi_portal_menu_id3', 'edit_bi_portal_menu_id4' ];
	getMenuList("ajson1", "edit_bi_portal_menu_id1", clearSelect);

	$("#edit_bi_portal_menu_id1").change(function() {
			var clearSelect = [ 'edit_bi_portal_menu_id2', 'edit_bi_portal_menu_id3', 'edit_bi_portal_menu_id4'];
			getMenuList($("#edit_bi_portal_menu_id1 option:selected").val(),"edit_bi_portal_menu_id2", clearSelect);
	});
	$("#edit_bi_portal_menu_id2").change(function() {
		var clearSelect = [ 'edit_bi_portal_menu_id3', 'edit_bi_portal_menu_id4'];
		getMenuList($("#edit_bi_portal_menu_id2 option:selected").val(),"edit_bi_portal_menu_id3", clearSelect);
	});
	
});
	
</script>
