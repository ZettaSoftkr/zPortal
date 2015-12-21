
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id="bodysub" style="height: 500px;">

	<ul class="nav nav-tabs">
		<li class="active"><a href="#init" data-toggle="tab">시스템 메뉴 초기화</a></li>
		<li><a href="#menuSet" data-toggle="tab">시스템 메뉴 생성 및 권한 설정</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="init">
			<div id="initBox" class="alert alert-danger fade in">
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getMenuDeleteAll">메뉴 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getMenuGroupDeleteAll">메뉴그룹 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getMenuGroupMappDeleteAll">메뉴그룹&메뉴 맵핑 초기화</button>
				</div>
				<br> <br>
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getUserGroupDeleteAll">사용자그룹 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getUserGroupDeptMappDeleteAll">사용자그룹&조직맵핑 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getUserGroupUserMappDeleteAll">사용자그룹&사용자맵핑 초기화</button>
				</div>
				<br> <br>
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getMenuGroupUserGroupMappDeleteAll">메뉴그룹&사용자그룹 초기화</button>
				</div>
				<br><br>
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getdeleteUserAll">사용자초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getdeleteLoginInfoAll">로그인사용자 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getdeleteDeptAll">조직 초기화</button>
				</div>
				<br><br>
				
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getDeleteSetMain">메인화면 설정 초기화</button>
					<button type="button" class="btn btn-warning btn-sm" id="getDeleteBookMark">즐겨찾기 초기화</button>	
					<button type="button" class="btn btn-warning btn-sm" id="getDeleteReportCycle">레포트사이클 초기화</button>	
					<button type="button" class="btn btn-warning btn-sm" id="getDeletePortlet">포틀릿초기화</button>										
				</div>
			</div>
		</div>
		<div class="tab-pane" id="menuSet">
			<div id="menuBox" class="alert alert-danger fade in">
				<div class="btn-group">
					<button type="button" class="btn btn-warning btn-sm" id="getAllMenuMakeExcel">포탈 기본설정</button>
				</div>
				<br> 
				<br>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	

	$("#menuBox .btn-group button").click(function(){	
		

		/*
		1)메뉴 등록  				-- getAllMenuMakeExcel
		2)관리 조직 등록 			-- setAdminDeptExcel
		3)관리 사용자 등록 			-- setAdminUserExcel , setAdminLoginExcel
		4)관리 사용자 그룹 등록 		-- getAdminGroupExcel
		5)관리 사용자 그룹-관리사용자 맵핑 	-- getAdminUserGroupMappExcel
		6)관리 메뉴 그룹 등록 			-- getAdminMenuGroupExcel
		7)관리메뉴 그룹 메뉴 맵핑 		-- getAdminMenuGroupMappExcel
		8)관리사용자 그룹-관리메뉴그룹 맵핑 	-- getAdminMenuUserGroupMappExcel
		*/
		//defaultSet($(this).attr("id"));
		
		var methodArr=[	"portalInit"];
		
		
		$.each(methodArr, function(index, item) {
			
				//alert(index);
				//alert(item);
		        var returnMsg  = defaultSet(item);
				
		        if ( returnMsg == '200') {
		   
		        	return true; // continue;
		        }else{
		        	
		        
		             return false; // break;
		        }
		       

		});
	
	
		
		/*
		
	     var url = "/"+serviceName+"/setData/"+$(this).attr("id")+".do";	      
	    
	   	 $.ajax({
	   			url : url,
	   			type : "post",	   		
	   			beforeSend : function() { //
	   				
	   				$("#progress_modal").modal('show');		
					$(".progress").show();
					$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

	   			},
	   			success : function(data, textStatus, jqXHR) {

	   				//alert(data);
	   				$("#progress_modal").modal('hide');	
	   				$(".progress").hide();
					$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
	   				
	   				$("#msg").html(data);
	   				$("#msg_modal").modal('show');
	   				
	   				

	   			},
	   			error : function(jqXHR, textStatus, errorThrown) {
	   				// if fails
	   			}
	   		});
	   	 
	    */
	    
		 
	});
	
   function defaultSet(mathodNm){
	   
	   var returnMsg = "";
	   var url = "/"+serviceName+"/setData/"+mathodNm+".do";	      
	    
	   	 $.ajax({
	   			url : url,
	   			type : "post",	   		
	   			beforeSend : function() { //
	   				
	   				$("#progress_modal").modal('show');		
					$(".progress").show();
					$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
					
					

	   			},
	   			success : function(data, textStatus, jqXHR) {

	   				//alert(data);
	   				$("#progress_modal").modal('hide');	
	   				$(".progress").hide();
					$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
	   				
	   				$("#msg").html(data);
	   				$("#msg_modal").modal('show');
	   				
	   				returnMsg = "200";

	   			},
	   			error : function(jqXHR, textStatus, errorThrown) {
	   				// if fails
	   				
	   				returnMsg = "400";

	   			}
	   		});
	   
	   	 
	   	 return returnMsg;
   }
	
	</script>
</body>
</html>