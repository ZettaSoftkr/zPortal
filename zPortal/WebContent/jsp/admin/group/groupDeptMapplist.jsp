<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="boardarea">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="#manage" data-toggle="tab">매핑표 다운로드</a></li>
		<li><a href="#service" data-toggle="tab">매핑표 업로드</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="manage">
			<section class="boardlist">
			<table class="tablelist mt5">
				<thead>
					<tr>
						<th>매핑표</th>
						<th>다운로드</th>
					</tr>
				</thead>
				<tbody id="tableContent_mnt">
					<tr>
						<td>그룹조직 맵핑엑셀</td>
						<td><a href="#" onclick="makeGroupDeptExcel();"><button type="button"  class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-floppy-save"></span></button></a></td>
					</tr>
					<tr>
						<td>그룹메뉴 맵핑엑셀</td>
						<td><a href="#" onclick="makeGroupMenuExcel();"><button type="button"  class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-floppy-save"></span></button></a></td>
					</tr>

				</tbody>
			</table>
			</section>
		</div>
		<div class="tab-pane" id="service">
			<table class="tablelist mt5">
				<thead>
					<tr>
						<th></th>
						<th>매핑표</th>
						<th>다운로드</th>
					</tr>
				</thead>
				<tbody id="tableContent_ser">
				   <tr>
				   	<td></td>
				   	<td></td>
				   	<td></td>
				   	<td></td>
				   </tr>
				</tbody>
			</table>
		</div>
		<div class="tab-pane" id="myPage">
			<table class="tablelist mt5">
				
			</table>
		</div>
		<div class="tab-pane" id="settings">...</div>
	</div>

		
		
		

</div>



<script type="text/javascript">
	$(document).ready(function() {
		
		function makeGroupDeptExcel(){
			
			
			
			$.ajax({
				url : "makeGroupDeptExcel.do",
				type : "post",
				data : {},
				dataType : "json",
				beforeSend : function() { //

				},
				success : function(data, textStatus, jqXHR) {

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});
			
		}

	});
</script>
