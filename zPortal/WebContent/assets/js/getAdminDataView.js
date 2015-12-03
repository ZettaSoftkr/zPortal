
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *******************************************목록  페이지 데이터 확인*********************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getAdminListPageData(currentPage, searchTitle, searchContent) {


	$.ajax({

		url : getDataLink.list,
		type : "post",
		data : {
			currentPage : currentPage,
			searchTitle : searchTitle,
			searchContent : searchContent
		},
		datatype : 'json',
		beforeSend : function() {

			$("#progress_modal").modal('show');		
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},

		success : function(data) {

		//	alert(JSON.stringify(data));

			$("#progress_modal").modal('hide');		
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

			var headtableString = "";
			var tableString = "";
			
			
			headtableString += "<tr>";
			headtableString += " <th><input type='checkbox' id='allCheck'/></th>";
			for (var i = 0; i < headerTitle.length; i++) {

				if (i == 0) {

					headtableString += "<th>" + headerTitle[i] + "</th>";

				} else if (i == 1) {

					headtableString += "<th>" + headerTitle[i] + "</th>";

				} else {

					headtableString += "<th>" + headerTitle[i] + "</th>";

				}

			}
			headtableString += "</tr>";
			$("#headTableContent").html(headtableString); // 목록;

			var noPage = (data.currentPage - 1);
			var no = data.totalNo - (noPage * 10);

			$.each(data.rows, function(key, value) {

				tableString += "<tr>";
				tableString += " <td><input type='checkbox' name='checkId' id='checkId' value='" + this[attrKey] + "'></td>";
				tableString += " <td>" + no + "</td>";

				for (var i = 0; i < attrVal.length; i++) {

					if (attrVal[i].type == 'radio') {

						var statVal = getStats("" + attrVal[i].val + "", "" + this[attrVal[i].name] + "");
						
						
						if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
							tableString += " <td><a href='#' onclick='goAdminViewPage(\"" + this[attrKey] + "\")'>" + statVal + " </a></td>";
							
						}else{
							
							tableString += " <td>" + statVal + " </td>";
							
						}

						

					} else if (attrVal[i].type == 'link') {
						
						if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							tableString += " <td><a href='#' onclick='goOtherPage(\"" + this[attrKey] + ",\"" + this[getId] + "\")'>"+ this[attrVal[i].name] + "</a></td>";
						}else{
							
							tableString += " <td>"+ this[attrVal[i].name] + "</td>";
						}
						
					}else if (attrVal[i].type == 'popup') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='" + attrVal[i].funcNm + "(\"" + this[attrVal[i].val] + "\");'>"+ this[attrVal[i].name] + "</a> ";
						tableString += "</td>";

					}else if (attrVal[i].type == 'button') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='"+ attrVal[i].funcNm +"(\"" + this[attrVal[i].name] + "\");'><button type=\"button\"  class=\"btn btn-warning btn-xs\">"+ attrVal[i].val + "</button></a> ";
						tableString += " </td>";

					} else if (attrVal[i].type == 'tabLink') {
						
						
						tableString += " <td>";
						tableString += " <a href='#' onClick='"+ attrVal[i].funcNm+"(\"" + this[attrKey] + "\");'>등록</a> ";
						tableString += "</td>";

					}else if (attrVal[i].type == 'getUserNm') {
						
						
						getUserNm(this[attrVal[0].name],key);
						
						tableString += "<td id=\"getUserNm_"+key+"\"></td>";
					
						

					} else {
						
						if(viewStatus=='edit'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goAdminEditPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
								
							}
							
						}else if(viewStatus=='view'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goAdminViewPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
						}else if(viewStatus=='modal'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goAdminEditModalPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
						}
						
						
						
					}
				}

				tableString += "</tr>";
				no = no - 1;

			});
			
			//alert(tableString);
			$("#tableContent").html(tableString); // 목록
			
			var strPage = "";
			var totalNo = data.totalNo;
			var countPerPage = 10;
			var endPageNum = endPage(currentPage, countPerPage, totalNo); // 전체페이지
			var startPage = 0;
			var endPage = 0;

			startPage = parseInt(parseInt(currentPage - 1) / countPerPage) * parseInt(countPerPage) + 1;

			if (endPageNum <= countPerPage) {

				endPage = parseInt(endPageNum + 1);

			} else {

				if (endPageNum - parseInt(endPageNum % countPerPage) > startPage) {

					endPage = parseInt(startPage + countPerPage);

				} else {

					endPage = parseInt(startPage + parseInt(endPageNum % countPerPage));

				}
			}

			strPage += " <a href=\"#\" onclick=\"getAdminListPageData(1,'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
		
			if(currentPage > 1){
				
			strPage += " <a href=\"#\" onclick=\"getAdminListPageData(" + parseInt(currentPage - 1) + ",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
			}
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\"' title=\"" + i + "\" onclick=\"getAdminListPageData(" + i + ",'"+searchTitle +"','"+ searchContent+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) <= endPageNum){
				strPage += " <a href=\"#\" onclick=\"getAdminListPageData(" + parseInt(currentPage+1)+ ",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
			}
			strPage += " <a href=\"#\" onclick=\"getAdminListPageData(" + parseInt(endPageNum) + ",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
			$("#paging").html(strPage); // pageing

			function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

				var extra = parseInt(totalNo % countPerPage);

				if (extra > 0) {

					return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

				} else {

					return parseInt(totalNo / countPerPage);

				}

			}

			
			$("#allCheck").click(function() {

				var status = $("#allCheck").is(':checked');

				if (status) {

					$("input[name=checkId]:checkbox").prop("checked", true);

				} else {

					$("input[name=checkId]:checkbox").prop("checked", false);

				}

			});
			
			
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



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *******************************************상세  페이지******************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getAdminViewPage(id) {

	

	var postData = attrKey + "=" + id;

	//alert(postData);
	//alert(getDataLink.view);
	//상세페이지 
	$.ajax({

		url : getDataLink.view,
		type : "post",
		data : postData,
		datatype : 'json',
		beforeSend : function() {

			$("#progress_modal").modal('show');		
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},
		success : function(data) {

			$("#progress_modal").modal('hide');		
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
		
			$("#" + attrKey).val(data.rows[attrKey]);

			for (var i = 0; i < attrNm.length; i++) {
				if (attrNm[i].type == 'radio') {
					
					$("input:radio[name='" + attrNm[i].iptNm + "']:radio[value='" + data.rows[attrNm[i].colNm] + "']").prop("checked", true);

			    }else if (attrNm[i].type == 'select') {
						    	
			    	 $("#"+ attrNm[i].iptNm+" > option[value='"+data.rows[attrNm[i].colNm]+"']").attr("selected", "ture");

			    }else if(attrNm[i].type == 'html'){

					 $("#" + attrNm[i].iptNm).html(data.rows[attrNm[i].colNm]);
					 
				}else{
					
					$("#" + attrNm[i].iptNm).val(data.rows[attrNm[i].colNm]);
				}

				
			}

			$("#prevTitle").html(data.prevTitle);
			$("#nextTitle").html(data.nextTitle);
			$("#prevDate").html(data.prevDate);
			$("#nextDate").html(data.nextDate);
			
			

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




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  수정 페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getAdminEditPage(id) {
	
	
	var postData = attrKey + "=" + id;
	
	
	$.ajax({

		url : getDataLink.view,
		type : "POST",
		data : postData,
		datatype : 'json',
		// / beforeSend: function () {
		// },
		success : function(data) {
			
		
			
			$("#" + attrKey).val(data.rows[attrKey]);

			for (var i = 0; i < attrNm.length; i++) {
				
				
				if (attrNm[i].type == 'radio') {
					
					$("input:radio[name='" + attrNm[i].iptNm + "']:radio[value='" + data.rows[attrNm[i].colNm] + "']").prop("checked", true);
				

			    }else if (attrNm[i].type == 'select') {
						    	
			    	 $("#"+ attrNm[i].iptNm+" > option[value='"+data.rows[attrNm[i].colNm]+"']").attr("selected", "ture");

			    }else if(attrNm[i].type == 'html'){

					$("#" + attrNm[i].iptNm).html(data.rows[attrNm[i].colNm]);
					
				}else{
					
					$("#" + attrNm[i].iptNm).val(data.rows[attrNm[i].colNm]);
				}
			
			}

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


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 파일첨부 목록 *********************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function  getFileView(aliasName,id){
	
	
	
	$.ajax({

		url : "/"+serviceName+"/fileUpload/getBoardListData.do",
		type : "post",
		data : {bi_sn:id,serviceNm:aliasName},
		dataType : 'json',
		beforeSend : function() {

			
		},
		success : function(data) {
			
			var tableString = "";			
			 
			
			$.each(data, function(key, value) {
				
				tableString += " <button type=\"button\"  class=\"btn btn-warning btn-xs\"  data-toggle=\"tooltip\" data-placement=\"top\" title=\""+this['bi_atch_file_nm']+"\" onclick=\"getFileDownLoad('"+this['bi_atch_file_sn']+"');\"><span class=\"glyphicon glyphicon-floppy-save\"></span></button>";
				
				
				
				
			});
			
			$("#fileView").html(tableString);
			
		  //alert(JSON.stringify(data));
		
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



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 파일첨부 목록 *********************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function  getAdminFileEdit(aliasName,id){
	
	$("#file0").html("");
	$("#file1").html("");
	$("#file2").html("");
	
	$.ajax({

		url : "/"+serviceName+"/fileUpload/getBoardListData.do",
		type : "post",
		data : {bi_sn:id,serviceNm:aliasName},
		dataType : 'json',
		beforeSend : function() {
		},
		success : function(data) {		 
				
			
		
			$.each(data, function(key, value) {
				var strHtml = "";
				strHtml += "<a href=\"#\" onclick=\"getFileDownLoad('"+this['bi_atch_file_sn']+"');\">"+this['bi_atch_file_nm']+"</a>";
				strHtml += "["+this['bi_atch_file_mg_byte']+"]";
				strHtml += "<button type=\"button\" class=\"button_Area\" onclick=\"fileDelete("+this["bi_atch_file_sn"]+","+id+")\">";
				strHtml +=  "<span class=\"whitishBtn button_small\"><span class=\"fontawesome_Btn fa-remove\"></span>삭제</span>";
				strHtml += "</button>";	
					
				$("#file"+key).html(strHtml);
				
			});
			
			
			//$("#fileView").html(strHtml);
			
		  
		
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


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 파일삭제 *********************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function  fileDelete(id,bi_sn){
	$.ajax({

		url : "/"+serviceName+"/fileUpload/fileDelete.do",
		type : "post",
		data : {bi_atch_file_sn:id},
		//dataType : 'json',
		beforeSend : function() {

			
		},
		success : function(data) {
			 
		
	
			$("#msg").html(data);
			$("#msg_modal").modal("show");
		
		    getAdminEditPage(bi_sn);
	        getAdminFileEdit(aliasName2,bi_sn);
	        
			
			
			
		  //  alert(JSON.stringify(data));
		  
		
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

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************댓글 목록페이지 구성 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getCommentList(bi_bbs_sn) {

	var listLink = "/" + serviceName + "/comment/" + getDataLink.list;
	//alert(bi_bbs_sn);
	$.ajax({

		url : listLink,
		type : "post",
		data : {
			bi_bbs_sn : bi_bbs_sn
		},
		datatype : 'json',
		beforeSend : function() {

		},

		success : function(data) {

			var tableString = "";
			$.each(data.rows, function(key, value) {
				
				
				
				tableString += "<tr>";
				tableString += " <td>" + this['bi_user_nm'] + "</td>";
				tableString += " <td>" + this['bi_ansr_cn'] + "</td>";
				tableString += " <td><a href='#' onclick='getCommentDelete(" + this['bi_bbs_ansr_sn'] + "," + bi_bbs_sn
						+ ")'><span class=\"glyphicon glyphicon-trash\"></span></a></td>";
				tableString += "</tr>";

			});

			///	alert(tableString);

			$("#commentList").html(tableString); // 목록

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

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************답변 상태  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getStats(val, id) {

	//alert(val);
	var statSplit = val.split(",");

	for (var i = 0; i < statSplit.length; i++) {

		var statVal = statSplit[i].split(":");
		var returnBut = "";
		//alert(statVal[0]);

		if (statVal[0] == id) {

			if (id == 'S') {

				returnBut = "<button type=\"button\" class=\"btn btn-default btn-xs btn-warning\">" + statVal[1] + "</button>";
			} else if (id == 'C') {

				returnBut = "<button type=\"button\" class=\"btn btn-default btn-xs btn-primary\">" + statVal[1] + "</button>";

			} else if (id == 'R') {

				returnBut = "<button type=\"button\" class=\"btn btn-default btn-xs btn-danger\">" + statVal[1] + "</button>";

			} else if (id == 'A') {

				returnBut = "<button type=\"button\" class=\"btn btn-default btn-xs btn-success\">" + statVal[1] + "</button>";

			}

			return returnBut;
		}

	}

	return "";

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************** 관리자 수정 페이지 ****************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/*
function getAdminEditPage(colName, id) {
	

	$("#edit_").val(id);

	var postData = colName + "=" + id;

	if (aliasName2 == "userInfo") {

		var deptUrl = "/" + serviceName + "/admin/dept/getDeptListData.do";

		$.ajax({

			url : deptUrl,
			type : "post",
			//	 		data : {
			//	 			bi_parent_id : "ajson1"
			//	 		},
			datatype : 'json',
			success : function(data) {

				$("#edit_bi_dept_id").append("<option value=''>선택</option>");
				$.each(data, function(key, value) {

					$("#edit_bi_dept_id").append("<option value='" + this['bi_dept_id'] + "'>" + this['bi_deptnm'] + "</option>");
				});

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

	$("#edit_aliasName").val(aliasName2);
	$.ajax({

		url : getDataLink.view,
		type : "post",
		data : postData,
		datatype : 'json',
		// / beforeSend: function () {
		// },
		success : function(data) {

			$("#edit_" + getId).val(data.rows[getId]);
			for (var i = 0; i < colEditSize; i++) {
				
				if (colEditName[i].type == 'refer') {
					
					if (i == 1) {
						getMenuSelected(menuServCode, colEditName[i].name, data.rows[colEditName[i].name]);

					} else {
						getMenuSelected(data.rows[colEditName[i - 1].name], colEditName[i].name, data.rows[colEditName[i].name]);

					}

				} else if (colEditName[i].type == 'radio') {

					$("input:radio[name='" + colEditName[i].name + "']:radio[value='" + data.rows[colEditName[i].name] + "']").prop("checked", true);

				} else if (colEditName[i].type == 'select') {
					
					
					//$("#edit_"+colEditName[i].name +" option:eq("+data.rows[colEditName[i].name] +")").attr("selected", "selected");
					 $("#edit_"+ colEditName[i].name+" > option[value='"+data.rows[colEditName[i].name]+"']").attr("selected", "ture");
					
					//$("#edit_" + colEditName[i].name).val(data.rows[colEditName[i].name]).attr("selected", "selected");
					
				}else if (colEditName[i].type == 'reportselect') {					

					getReportSelected(colEditName[i].name, data.rows[colEditName[i].name]);				

			    }else if (colEditName[i].type == 'check') {
					
					
					var menuId = data.rows[colEditName[i].name];
					//checkDtreeList(, data.rows[colViewName[i].name]);
					var spMenuId = menuId.split(",")
					
					
					getCheckDtree(spMenuId[0], colEditName[i].value,data.rows[colEditName[i].name],colEditName[i].reportNm);
					


				}else {
					$("#edit_" + colEditName[i].name).val(data.rows[colEditName[i].name]);
				}

			}

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");

		}
	});

	if (aliasName2 == 'board') {
		getCommentList(id, aliasName2); // 댓글 ... 기능
	}

}
*/
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ***************************************     검색       ****************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchList() {

	var searchTitle = $("#searchTitle").val();
	var searchContent = $("#searchContent").val();

	if (searchTitle == "") {

		$("#msg_title").html("검색!!");
		$("#msg").html("검색 조건을 선택해주세요");
		$("#msg_modal").modal("show");
		
		return;

	}
	if (searchContent == "") {
		
		$("#msg_title").html("검색!!");
		$("#msg").html("검색 내용을 입력해주세요");
		$("#msg_modal").modal("show");
		
		return;

	}

	getAdminListPageData(1, searchTitle, searchContent);
	$("#searchClear").show();
	

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ***************************************     검색초기화      *************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchClear() {

	
	$("#searchClear").hide();
	$("#searchTitle").val("0");
	$("#searchContent").val("");


	getAdminListPageData(1, "", "");

}




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* 관리실> 그룹관리> 그룹부서 검색 클릭 
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#viewDeptSearchMapp").click(function() {

	
	var deptNm = $("#s_bi_dept_Nm").val();
	var groupId = $("#dept_bi_group_id").val();
	
	if(deptNm == ''){
		
		
		$("#msg").html("부서명을 입력해주세요.");
		$("#msg_modal").modal('show');
		 return
		 
	}else{
		
		
		
	
		$.ajax({
			url : "/" + serviceName + "/admin/group/getDeptGroupData.do",
			type : "post",
			data : {
				 bi_group_id : groupId
				,bi_deptNm :deptNm 
			},
			datatype : 'json',
			success : function(data) {
	
				$('#bi_dept_id_list').empty(); //클리어
				$.each(data, function(key, value) {
	
					$("#bi_dept_id_list").append("<option value='" + data[key][0] + "'>" + data[key][1] + "</option>");
	
				});
	
			}
	
	    });
		
		
		$("#viewDeptSearchReset").show();

	}
});
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* 관리실> 그룹관리> 그룹부서 검색 초기화 
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#viewDeptSearchReset").click(function() {

	
	
	var groupId = $("#dept_bi_group_id").val();
	

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
		
		
	$("#viewDeptSearchReset").hide();

	
});




/* *****************************************************************************
 * ****************************** 삭제 권한 여부 ************************************
 * ******************************************************************************
 * *****************************************************************************/

function getDeleteCheck(id) {

	var postData = attrKey + "=" + id;

	$.ajax({

		url : actionLink.checkNum,
		type : "post",
		data : postData,
		datatype : 'json',
		beforeSend : function() {

		},
		success : function(data) {
				
			$("#goEditForm_btn").empty();
			$("#goDeleteForm_btn").empty();
		
			
			if (data > 0) {
				
				$("#goEditForm_btn").append("<span class=\"whitishBtn button_small\"><span class=\"fontawesome_Btn fa-list-alt\"></span>수정</span>");
				$("#goDeleteForm_btn").append("<span class=\"whitishBtn button_small\"><span class=\"fontawesome_Btn fa-list-alt\"></span>삭제</span>");
				
			}

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

/************************************************************************************************************************************/
/*****************************************************파일다운로드**********************************************************************/
/************************************************************************************************************************************/
function getFileDownLoad(fileSn){
	
	
	
	var url="/"+serviceName+"/download.do?fileSn="+fileSn;
	
	$(location).attr('href',url);
	
	//$(location).href="../download.do?fileNm="+fileNm+"tempFileNm="+tempFileNm+"filePath="+filePath;
							
}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* 권한별 메뉴 명 리턴
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */



function getCheckDtree(menuId,treeId,checkMenuId,nm) {	
	

	$.ajax({

		url : "/" + serviceName + "/sitemap/getSiteMap.do",
		type : "post",
		data : {bi_portal_menu_id:menuId},
		dataType : 'json',
		beforeSend : function() {
			
//			$("#progress_modal").modal('show');		
//			$(".progress").show();
//			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
			

		},
		success : function(data) {					
		
			 array= [ [ menuId, -1, nm ] ];
			
			for(var i = array.length-1; i--;){
					 if(i != 0){
						 array.splice(i);
					 }
			}

			

			
			var checkObj = checkMenuId.split(",");
			for (var i = 0; i < data.length; i++) {
				
			     for(var j=0;j<checkObj.length;j++){			    	 
			    	if(data[i][0] == checkObj[j]){ 
					 array.push([data[i][0], data[i][1], data[i][2]]);
					 break;
			    	}
				 
			     }
			}
			
			viewTreeList(array,treeId,checkMenuId);
			
		
		
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

function viewTreeList(array,treeId,checkMenuId) {
	   
	 if(treeId == "d3"){
		d3 = new dTree_default("d3");

		for (var i = 0; i < array.length; i++) {

			d3.add(array[i][0], array[i][1], array[i][2]);
		}
		
		$('#view_reportList1').html(d3.toString());
		
		
		
	}else if(treeId == "d4"){

		d4 = new dTree_default("d4");

		for (var i = 0; i < array.length; i++) {

			d4.add(array[i][0], array[i][1], array[i][2]);
		}

		
		$('#view_reportList2').html(d4.toString());
		
	}else if(treeId == "d5"){

		d5 = new dTree_default("d5");

		for (var i = 0; i < array.length; i++) {

			d5.add(array[i][0], array[i][1], array[i][2]);
		}

		
		$('#edit_reportList1').html(d5.toString());
		
	}else if(treeId == "d6"){

		d6 = new dTree_default("d6");

		for (var i = 0; i < array.length; i++) {

			d6.add(array[i][0], array[i][1], array[i][2]);
		}

		
		$('#edit_reportList2').html(d6.toString());
		
	}
	 
	
	
  }


function getMenuNmdept(currentId){
	

	$.ajax({

		url : "/" + serviceName + "/menu/getMenuIntro.do",
		type : "POST",		
		data : {
			bi_portal_menu_id : currentId
		},
		datatype : 'text/html',		
		beforeSend : function() {

//				$("#progress_modal").modal('show');		
//				$(".progress").show();
//				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
				//$("#reportUrl").attr("src",data.url);		
				$("#reportTitle").html(data.rows['bi_menu_nm']);	
				$(".sitemap").html(data.siteMapNm);	
				
				
			//data.defaultSelected;
		},
		error : function() {
			
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("관리자에게 문의하세요 <br> 보고서 수집되지 않았습니다.");
		}
	});

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************** 관리자 수정 페이지 ****************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goAdminEditModalPage(id) {

	var logUrl = "/" + serviceName + "/log/readPageLog.do";

	var logSearch = "?pageNm=edit";
	var logPage = $(location).attr("pathname");

	var fullLogUrl = logPage + logSearch;

	//fullLogUrl);
	$.ajax({
		url : logUrl,
		type : "post",
		data : {
			bi_connct_url_addr : fullLogUrl
		},
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});

	$("#edit_modal").modal('show');

	$("#edit_").val(id);

	$("#edit_aliasName").val(aliasName2);
	
	
	var postData = attrKey + "=" + id;
	
	$.ajax({

		url : getDataLink.view,
		type : "POST",
		data : postData,
		datatype : 'json',
		// / beforeSend: function () {
		// },
		success : function(data) {
			
			$("#" + attrKey).val(data.rows[attrKey]);

			for (var i = 0; i < attrEditNm.length; i++) {
				
				
				if (attrEditNm[i].type == 'radio') {
					
					$("input:radio[name='" + attrEditNm[i].iptNm + "']:radio[value='" + data.rows[attrEditNm[i].colNm] + "']").prop("checked", true);
				

			    }else if (attrEditNm[i].type == 'select') {
						    	
			    	 $("#"+ attrEditNm[i].iptNm+" > option[value='"+data.rows[attrEditNm[i].colNm]+"']").attr("selected", "ture");

			    }else if(attrEditNm[i].type == 'html'){

					$("#" + attrEditNm[i].iptNm).html(data.rows[attrEditNm[i].colNm]);
				}else{
					
					$("#" + attrEditNm[i].iptNm).val(data.rows[attrEditNm[i].colNm]);
				}
			
			}

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	  });


	if (aliasName2 == 'board') {
		getCommentList(id, aliasName2); // 댓글 ... 기능
	}

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** **************************************사용자 등록페이지 조직selected 출력하기 *************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function userDeptInfo(){

	var deptUrl = "/" + serviceName + "/admin/dept/getDeptListData.do";

	$.ajax({

		url : deptUrl,
		type : "post",
		datatype : 'json',
		success : function(data) {

			$("#bi_dept_id").append("<option value=''>선택</option>");
			$.each(data, function(key, value) {

				$("#bi_dept_id").append("<option value='" + this['bi_dept_id'] + "'>" + this['bi_deptnm'] + "</option>");
			});

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
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** **************************************사용자 등록페이지 조직selected 출력하기 *************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function userDeptEditInfo(){

	var deptUrl = "/" + serviceName + "/admin/dept/getDeptListData.do";

	$.ajax({

		url : deptUrl,
		type : "post",
		datatype : 'json',
		success : function(data) {

			$("#edit_bi_dept_id").append("<option value=''>선택</option>");
			$.each(data, function(key, value) {

				$("#edit_bi_dept_id").append("<option value='" + this['bi_dept_id'] + "'>" + this['bi_deptnm'] + "</option>");
			});

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



//사용자 명 출력 할 경우 

function getUserNm(userId,num){
	
	$.ajax({

		url : "/" + serviceName + "/admin/userInfo/getUserNm.do",
		type : "post",
		data: {bi_unity_cust_id : userId},
		datatype : 'json',
		success : function(data) {

			//alert(JSON.stringify(data));
			
			if(data != null){
				    
				$("#getUserNm_"+num).html(data["bi_user_nm"]);
				//alert("#getUserNm_"+num +":::"+ data["bi_user_nm"]);
			
			}else{
				
				$("#getUserNm_"+num).html("");
			}
		},
		error : function() {
			

		}
	});
}



function qvLogout(userId){
	
		
		$.ajax({

			url : "/" + serviceName + "/qlikview/qvLogtout.do",
			type : "post",
			data: {bi_unity_cust_id : userId},
			datatype : 'json',
			success : function(data) {

				alert(JSON.stringify(data));
				
				var currentPage = 1;
				var searchTitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchTitle, searchContent);	
				
			
			},
			error : function() {
				

			}
		});
	

	
}

