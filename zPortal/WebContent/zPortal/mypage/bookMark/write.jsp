<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="Main_Title_Area">
     <div class="title" id="reportTitle"></div>                
         <div class="sitemap"></div>
	   </div>
  <div class="box_type">
      <div class="box_first">
      </div>
 </div>   

  <div id="Contents_Area">
        <div id="Container">
           <form class="form-horizontal" role="form"  method="post" name="myPageWriteForm" id="myPageWriteForm">
             <input type="hidden" id="bi_portal_menu_id" name="bi_portal_menu_id" value="">
           	<input type="hidden" name="lev1_parent_id" id="lev1_parent_id" value="">
			 <input type="hidden" name="lev2_parent_id" id="lev2_parent_id" value="">
			 <input type="hidden" name="lev3_parent_id" id="lev3_parent_id" value="">
			 <input type="hidden" name="lev1_menuOpenId" id="lev1_menuOpenId" value="">
			 <input type="hidden" name="lev2_menuOpenId" id="lev2_menuOpenId" value="">
			 <input type="hidden" name="lev1_menuOpen" id="lev1_menuOpen" value="">
			 <input type="hidden" name="lev2_menuOpen" id="lev2_menuOpen" value="">
			 <input type="hidden" name="currentId" id="currentId" value="">
            <div class="Grid_Area">
                <table class="Notice_Board_table" cellspacing="0" cellpadding="0">
                <colgroup>
                    <col width="150" />
                    <col /> 
                    <col width="150" />
                    <col />   
                    <col width="150" />
                    <col />                   
                </colgroup>
                <tbody>	
                    <tr>
                        <th>보고서선택</th>
                        <td colspan="5" class="notice_title">
                        <div class="input-group col-sm-5">
						<input class="form-control input-sm" type="text" id="bi_menu_nm" name="bi_menu_nm" required readonly>
						<div class="input-group-btn ">
							<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" id="dropdwon_menu_create">메뉴선택 <span class="caret"></span></button>
					        <ul class="dropdown-menu pull-right" id="dropdown_menu_create_view">
					        </ul>
							</div>
						</div>
                        
                       
                        </td>                             
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colspan="5" class="notice_txt"><textarea  class="Notice_Write_textarea" name="bi_cn" id="bi_cn" cols="45" rows="3" required></textarea>
                        </td>                             
                    </tr>                
                </tbody>
            </table> 
            </div>
            <div class="btn_area">
                <button type="submit" class="button_Area" >
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-pencil"></span>등록</span>
                </button>
                <button type="button" class="button_Area" id="reflash">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-refresh"></span>다시작성</span>
                </button>
                <button type="button" class="button_Area" id="goMyPageListForm_btn">
                    <span class="whitishBtn button_small"><span class="fontawesome_Btn fa-list-alt"></span>목록</span>
                </button>
            </div>   
          </form>
        </div>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		getMenuNmdept(currentId);	
		
		
		$("#lev1_parent_id").val(lev1_parent_id);
		$("#lev2_parent_id").val(lev2_parent_id);
		$("#lev3_parent_id").val(lev3_parent_id);
		$("#lev1_menuOpenId").val(lev1_menuOpenId);
		$("#lev2_menuOpenId").val(lev2_menuOpenId);
		$("#lev1_menuOpen").val(lev1_menuOpen);
		$("#lev2_menuOpen").val(lev2_menuOpen);
		$("#currentId").val(currentId);
		
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
			
		    var url = "/"+ serviceName+"/siteMap/getSiteMap.do";
		    $.ajax({
				
			    url:  url,
			    type: "post",
			    data:{bi_portal_menu_id:2} ,
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
		
		 function menuCreateList(menuCreateArray) {

			 d11 = new dTree('d11');

			for (var i = 0; i < menuCreateArray.length; i++) {

				d11.add(menuCreateArray[i][0], menuCreateArray[i][1], menuCreateArray[i][2]);
			}
			
			
			$("#dropdown_menu_create_view").html(d11.toString());
		}
		
		/* 상위 메뉴 선택후 아이디 */
		
		
		
	});
	
	
	
	 function createMenuId(objId){
			
		 $.ajax({

				url : "/"+serviceName+"/menu/getViewData.do",
				type : "post",
				data : {
					bi_portal_menu_id : objId.value
				},
				dataType : 'json',
				beforeSend : function() {

				},
				success : function(data) {
								
					
					
						$("#bi_portal_menu_id").val(data['bi_portal_menu_id']);
						$("#bi_menu_nm").val(data['bi_menu_nm']);

					
				},
				error : function() {
					alert("false");

				}
			});
		 
		
	 }
	
</script>
