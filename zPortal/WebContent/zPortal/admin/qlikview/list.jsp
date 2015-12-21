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
						<option value="1">제목</option>
						<option value="2">내용</option>
						<option value="3">재목  + 내용</option>
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
         </div>
     </div>   
 </div> 
 <div class="Grid_Area">
      <form class="form-horizontal" role="form" action="" method="post" name="listForm" id="listForm">
         <table class="table_Type">
            <colgroup>
               <col width="50"/> 
               <col width="80"/>  
               <col />  
               <col width="180"/>  
               <col width="180"/>  
               <col width="180"/>
               <col width="100"/>
               
           </colgroup>
           <thead id="headTableContent">
              
           </thead>
           <tbody id="tableContent">		
               
           </tbody>
       </table> 
       </form> 
     </div>
<!-- 	 <div class="paging_sample" id="paging"> -->
<!-- 	 </div>  -->
<!-- 	 <div class="btn_area"> -->
<!--            <button type="button" class="button_Area" id="goAdminWriteForm_btn"> -->
<!--                <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span> -->
<!--            </button> -->
<!--            <button type="button" class="button_Area" id="all_AdminDelete"> -->
<!--                <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-remove"></span>선택삭제</span> -->
<!--            </button> -->
<!--      </div>  -->

<!-- Modal -->
<form class="form-horizontal" role="form" action="" method="post" name="editAdminForm" id="editAdminForm">
<input type="hidden" class="form-control" id="edit_bi_perm_yn" name="bi_perm_yn" value="y">
	<div class="modal fade bs-example-modal-lg" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">수정폼</h4>
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

<!-- Modal -->
<form class="form-horizontal" role="form" action="" method="POST" name="writeUserForm" id="writeUserForm">
<input type="hidden" id="bi_unity_cust_id" name="bi_unity_cust_id" value=""> 
	<div class="modal fade bs-example-modal-lg" id="write_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-4 input-group input-group-sm">
							<input type="text" class="form-control" id="search_bi_user_nm" placeholder="사용자명" >
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="dropdwon_menu">
									검색 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu pull-right" id="droup_down_menu_1">

								</ul>
							</div>
						</div>
					</div>
				<div class="form-group">
						<label for="bi_cttpc_no" class="col-sm-2 control-label">사용자명</label>
						<div class="col-sm-5 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_user_nm" name="bi_user_nm" placeholder="사용자명"  readonly required>
						</div>
					</div>
					<div class="form-group">
						<label for="bi_cttpc_no" class="col-sm-2 control-label">클릭뷰ID</label>
						<div class="col-sm-5 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_qlikview_user_id" name="bi_qlikview_user_id" placeholder="ID" required >
						</div>
					</div>
					<div class="form-group">
						<label for="bi_email" class="col-sm-2 control-label">큐릭뷰비밀번호</label>
						<div class="col-sm-5 input-group input-group-sm">
							<input type="text" class="form-control" id="bi_qlikview_user_pwd" name="bi_qlikview_user_pwd" placeholder="비밀번호" required >
						</div>
					</div>
				
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

<div  class="modal fade bs-example-modal-sm" id="search_user_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script type="text/javascript">


var headerTitle = ["번호", "사용자명","사용자ID", "큐릭뷰ID  ", "큐릭뷰비밀번호" , "등록일", "사용자 로그아웃"];
var attrKey = "bi_sn";  //keyvalue
var attrVal =        //attributy
[
{
	  name : "bi_unity_cust_id",
	  type : "getUserNm"
	, val : ""
	, funcNm:"getUserNm"
	, href:"N"
},
{
	 name : "bi_unity_cust_id",
	 type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "bi_qlikview_user_id",
	 type : "input"
	, val : ""
	, funcNm:""
	, href:"N"
}, {
	 name : "bi_qlikview_user_pwd"
	,type : "input"
	,val : ""
	,funcNm:""
	,href:"N"
},
{
	 name : "parseModifyDate"
	,type : "input"
	,val : ""
	,funcNm:""
	,href:"N"
},
{
	 name : "bi_unity_cust_id"
	,type : "button"
	,val : "로그아웃"
	,funcNm:"qvLogout"
	,href:"N"
}
]; 



var viewStatus = "modal"; //상세 페애지 성절[상세 페에지 -view,수정페이지 -edit]]


$(document).ready(function() {
			
				
				getMenuNmdept(currentId);
				
				var currentPage = 1;
				var searchTitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchTitle, searchContent);	
				
				

				
  });
	
	
	$("#dropdwon_menu").click(function(){		   
	     
		
		var searchId = $("#search_bi_user_nm").val();
		
		if(searchId == new String("")){
			
			$("#msg").html("사용자명을 입력해주세요.");
			$("#msg_modal").modal("show");
			return;
		}else{
		
	    var url = "/"+ serviceName+"/admin/userInfo/getUserList.do";
	    
	     
	     
	    $.ajax({
			
		    url:  url,
		    type: "post",
		    data: { bi_user_nm : searchId},
		    datatype: 'json',
		    success: function(data){
		    	
		    	
		    	
		    	var strContent = "";
		    	
		    	//&parentId="+data[key][1]+"&currentId="+data[key][0]+"
		    	
		    	$.each( data, function( key, value ) {
		    		
		    		
		    		strContent +="  <li>";
		    		strContent +=" <a tabindex='-1' href='#'  onclick='getSelectName(\""+this['bi_unity_cust_id']+"\",\""+this['bi_user_nm']+"\");'>";
		    		strContent +=  this['bi_deptnm'] + "/"+ this['bi_user_nm']; 
		    		strContent +=" </a>";		    		
		    		strContent +="</li>";		    	   
		    	
		    		
		    		
				
		    	});
		    	
		    	
		    	$("#droup_down_menu_1").html(strContent);
		    	
		    
		    }	
		 });
	    
		}
    	
  });
	

function getSelectName(userid , userNm){
	
	$("#search_bi_user_nm").val("");
	$("#bi_unity_cust_id").val(userid);
	$("#bi_user_nm").val(userNm);
	
}



</script>
