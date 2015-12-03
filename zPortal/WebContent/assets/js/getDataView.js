

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************************  검색   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getSearchData(searchKeyword){


	var url = "/" + serviceName + "/search/getSearchReportList.do";
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			searchKeyword : searchKeyword
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
			  // alert(JSON.stringify(data));
			   
			   $(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				$("#progress_modal").modal('hide');

				var headtableString = "";
				var tableString = "";
				
				headtableString += "<tr>";

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
				$("#headTableContent").html(headtableString); // 목록
				
		
				
				$.each(data.rows, function(key, value) {

					tableString += "<tr>";
					tableString += " <td>" + parseInt(key+1) + "</td>";

					for (var i = 0; i < attrVal.length; i++) {
					
						if(attrVal[i].type == 'button'){
							
							 tableString += "<td> <img src=\"/"+serviceName+"/assets/images/left/leaf_r.gif\"></td>";
							
						}else{
							
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
								
								tableString += " <td><a href='#' onclick=\"goReport('"+ this[attrKey] +"');\">" + this[attrVal[i].name]	+ "</a></td>";
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
							
						}	
						
					}

					tableString += "</tr>";
					

				});
			

				$("#tableContent").html(tableString); // 목록
				
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
/** ************************************************  검색   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getIndexSearchData(searchKeyword){


	var url = "/" + serviceName + "/indexSearch/getIndexSearchList.do";
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			searchKeyword : searchKeyword
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
		
			   
			   $(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				$("#progress_modal").modal('hide');

				var headtableString = "";
				var tableString = "";
				
				headtableString += "<tr>";
				headtableString += " <th><input type='checkbox' id='allCheck' /></th>";

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
				$("#headTableContent").html(headtableString); // 목록
				
				
				$.each(data.rows, function(key, value) {

					tableString += "<tr>";
					tableString += " <td><input type='checkbox' name='checkId' id='checkId' value='" + this[attrKey] + "'></td>";
					tableString += " <td>" +  parseInt(key+1)  + "</td>";

					for (var i = 0; i < attrVal.length; i++) {
					
						if(attrVal[i].type == 'button'){
							
							 tableString += "<td> <img src=\"/"+serviceName+"/assets/images/left/leaf_r.gif\"></td>";
							
						}else{
							
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
								
								tableString += " <td><a href='#' onclick='goReport(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
							
						}	
						
					}

					tableString += "</tr>";

				});
			

				$("#tableContent").html(tableString); // 목록
				
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
/** ************************************************  엑셀데이터   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getFileListPageData(currentPage, searchtitle, searchContent, serviceNm) {
	


	$.ajax({

				url : "/" + serviceName + "/fileUpload/getListData.do",
				type : "post",
				data : {
					currentPage : currentPage,
					searchtitle : searchtitle,
					searchContent : searchContent,
					serviceNm : serviceNm					
				},
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
					

					var tableString = "";
		

					var noPage = (data.currentPage - 1);
					var no = data.totalNo - (noPage * 10);
					$.each(data.rows,function(key, value) {
								
						
						             

										tableString += "<tr>";
										tableString += " <td><input type='checkbox' name='checkId' id='checkId' value='"+this['bi_atch_file_sn']+"'></td>";
										tableString += " <td>" + no + "</td>";
										tableString += " <td>" + this['bi_atch_file_nm'] + "</td>";
										tableString += " <td>" + this['bi_dc'] + "</td>";
									//	tableString += " <td>" + this['bi_atch_flpth_nm'] + "</td>";
										tableString += " <td>";
										tableString += " <button type=\"button\"  class=\"btn btn-primary btn-xs\" onclick=\"getFileDownLoad('"+this['bi_atch_file_sn']+"');\"><span class=\"glyphicon glyphicon-floppy-save\"></span></button></td>";
										tableString += " <td>" + this['parseModifyDate'] + "</td>";
										tableString += "</tr>";
										no = no - 1;
										i = i + 1;

									});

					

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
					
			
				
					strPage += " <a href=\"#\" onclick=\"getFileListPageData(1,'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
					
					if(currentPage > 1){
						
					strPage += " <a href=\"#\" onclick=\"getFileListPageData("+parseInt(currentPage - 1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
					
					}else{
						
						strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
						
					}
					strPage += " <span>";
					for (var i = startPage; i < endPage; i++) {
						
				
						if (currentPage == i) {
							strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
						} else {
							strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getFileListPageData("+ i +",'"+searchTitle +"','"+ searchContent+"');\">" + i + "</a>";
						}
					}
					strPage += " </span>";
					if(parseInt(currentPage+1) <= endPageNum){
						strPage += " <a href=\"#\" onclick=\"getFileListPageData("+ parseInt(currentPage+1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
						
						
					}else{
						
						strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
						
					}
					strPage += " <a href=\"#\" onclick=\"getFileListPageData("+ parseInt(endPageNum) +",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
				
					$("#paging").html(strPage); // pageing

					function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

						var extra = parseInt(totalNo % countPerPage);

						if (extra > 0) {

							return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

						} else {

							return parseInt(totalNo / countPerPage);

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
 * ************************************************ 목록
 * ****************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getListPageData(currentPage, searchTitle, searchContent) {
	

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
			
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');

			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";
			headtableString += " <th><input type='checkbox' id='allCheck' /></th>";

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
			$("#headTableContent").html(headtableString); // 목록
			
			
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
							
							tableString += " <td><a href='#' onclick='goViewPage(\"" + this[attrKey] + "\")'>" + statVal + " </a></td>";
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
						tableString += " <a href='#' onClick='" + attrVal[i].funNm + "(\"" + this[attrVal[i].value] + "\");'>"+ this[attrVal[i].name] + "</a> ";
						tableString += "</td>";

					}else if (attrVal[i].type == 'button') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='" + attrVal[i].funNm + "(\"" + this[attrVal[i].value] + "\");'><button type=\"button\"  class=\"btn btn-warning btn-xs\">"+ attrVal[i].val + "</button></a> ";
						tableString += " </td>";

					}else if (attrVal[i].type == 'radio') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='" + attrVal[i].funNm + "(\"" + this[attrVal[i].value] + "\");'><button type=\"button\"  class=\"btn btn-warning btn-xs\">"+ attrVal[i].val + "</button></a> ";
						tableString += " </td>";

					}  else {
						
						if(viewStatus=='edit'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goEditPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
								
							}
							
						}else if(viewStatus=='view'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href=\"#\" onclick=\"goViewPage('"+this[attrKey]+"')\">"+this[attrVal[i].name]+"</a></td>";
								
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
						}
					  }
				}

				tableString += "</tr>";
				no = no - 1;
				

			});
		

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
			
		
			strPage += " <a href=\"#\" onclick=\"getListPageData(1,'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > 1){
				
			strPage += " <a href=\"#\" onclick=\"getListPageData("+parseInt(currentPage - 1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
				
			}
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getListPageData("+ i +",'"+searchTitle +"','"+ searchContent+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) <= endPageNum){
				strPage += " <a href=\"#\" onclick=\"getListPageData("+ parseInt(currentPage+1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
				
				
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
				
			}
			strPage += " <a href=\"#\" onclick=\"getListPageData("+ parseInt(endPageNum) +",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
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
/**
 * ************************************************MyPage > 자료실 목록
 * ****************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getMyPageListPageData(currentPage, searchTitle, searchContent) {
	
	

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
			
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');

			
	
			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";
			headtableString += " <th><input type='checkbox' id='allCheck' /></th>";

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
			$("#headTableContent").html(headtableString); // 목록
			
			
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
							
							tableString += " <td><a href='#' onclick='goMyPageViewPage(\"" + this[attrKey] + "\")'>" + statVal + " </a></td>";
						}else{
							
							tableString += " <td>" + statVal + " </td>";
							
						}
						

					} else if (attrVal[i].type == 'link') {
						
						if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							tableString += " <td><a href='#' onclick='"+ attrVal[i].funcNm +"(\"" + this[attrVal[i].val] + "\");'>"+ this[attrVal[i].name] + "</a></td>";
						}else{
							
							tableString += " <td>"+ this[attrVal[i].name] + "</td>";
						}
						
					}else if (attrVal[i].type == 'button') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='" + attrVal[i].funcNm + "(\"" + this[attrVal[i].val] + "\");'><button type=\"button\"  class=\"btn btn-warning btn-xs\">"+ attrVal[i].val + "</button></a> ";
						tableString += " </td>";

					}else if (attrVal[i].type == 'radio') {

						tableString += " <td>";
						tableString += " <a href='#' onClick='" + attrVal[i].funcNm + "(\"" + this[attrVal[i].val] + "\");'><button type=\"button\"  class=\"btn btn-warning btn-xs\">"+ attrVal[i].val + "</button></a> ";
						tableString += " </td>";

					}  else {
						
						if(viewStatus=='edit'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goMyPageEditPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
								
							}
							
						}else if(viewStatus=='view'){
							
							if(attrVal[i].href=='Y'){ //하이퍼링크 여부
							
								tableString += " <td><a href='#' onclick='goMyPageViewPage(\"" + this[attrKey] + "\");'>" + this[attrVal[i].name]	+ "</a></td>";
							}else{
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
							}
						}
					}
				}

				tableString += "</tr>";
				no = no - 1;
				

			});
		
			
			

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
			
			strPage += " <a href=\"#\" onclick=\"getMyPageListPageData(1,'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > 1){
				
			strPage += " <a href=\"#\" onclick=\"getMyPageListPageData("+parseInt(currentPage - 1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
				
			}
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getMyPageListPageData("+ i +",'"+searchTitle +"','"+ searchContent+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) <= endPageNum){
				strPage += " <a href=\"#\" onclick=\"getMyPageListPageData("+ parseInt(currentPage+1)+",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
				
				
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
				
			}
			strPage += " <a href=\"#\" onclick=\"getMyPageListPageData("+ parseInt(endPageNum) +",'"+searchTitle +"','"+ searchContent+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
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
/**
 * ************************************************ 목록
 * ****************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getFaqList(currentPage,searchTitle,searchContent) {
	
	

	
	$.ajax({

		url : getDataLink.list,
		type : "post",
		data : {
			 currentPage : currentPage
			,searchTitle : searchTitle
			,searchContent : searchContent
		},
		datatype : 'json',
		beforeSend : function() {
			
			$("#progress_modal").modal('show');
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},

		success : function(data) {
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');

			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";

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
			$("#headTableContent").html(headtableString); // 목록
		

			var noPage = (data.currentPage - 1);
			var no = data.totalNo - (noPage * 10);
			
			$.each(data.rows, function(key, value) {
							
				tableString += "<tr  class=\"faq_open\">";				
				tableString += " <td>" + no + "</td>";			
				tableString += " <td class=\"faq_title\">" + this["bi_titl"]	+ "</td>";
				tableString += "</tr>";
				tableString += "<tr class=\"faq_a_back\">";
				tableString += "<td colspan=\"2\" class=\"faq\">" +this["htmlFormtWithCn"]+ "</td>";
				tableString += "</tr>";
				no = no - 1;
				
			});


			// $("#tableContent").html(tableString); //목록

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

			strPage += " <a href='#' onclick='getFaqList(1);'><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > 1){
				
				strPage += " <a href='#' onclick='getFaqList(" + parseInt(currentPage - 1) + ");'><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
			}
			
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href='#' class='on'>" + i + "</a>";
				} else {
					strPage += " <a href='#' title='" + i + "' onclick='getFaqList(" + i + ");'>" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) <= endPageNum){
				strPage += " <a href='#' onclick='getFaqList(" + parseInt(currentPage+1) + ");'><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
			}else{
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
			}
			strPage += " <a href='#' onclick='getFaqList(" + parseInt(endPageNum) + ");'><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
			$("#paging").html(strPage); // pageing

			function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

				var extra = parseInt(totalNo % countPerPage);

				if (extra > 0) {

					return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

				} else {

					return parseInt(totalNo / countPerPage);

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
/**
 * *******************************************상세
 * 페이지********************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getViewPage(id) {

	/** ******************* 로그 수집 ********************************* */

	var postData = attrKey + "=" + id;

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


			//alert(JSON.stringify(data));

			$("#" + attrKey).val(data.rows[attrKey]);

			for (var i = 0; i < attrNm.length; i++) {
				if (attrNm[i].type == 'radio') {
					
					$("input:radio[name='" + attrNm[i].iptNm + "']:radio[value='" + data.rows[attrNm[i].colNm] + "']").prop("checked", true);
				

			    }else if (attrNm[i].type == 'select') {
			    	
			    	 $("#"+ attrNm[i].iptNm+" > option[value='"+data.rows[attrNm[i].colNm]+"']").attr("selected", "ture");

			    }else if (attrNm[i].type == 'radioButton') {
			    	 
			    	 
			    	 var statVal = getStats("" + attrNm[i].val + "", "" + data.rows[attrNm[i].colNm] + "");
			    	 $("#" + attrNm[i].iptNm).html(statVal);
						

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
			 tableString += "<ul class=\"notice_file_Area\"> ";
			 tableString += "   <li class=\"notice_add_file_btn\">파일</li> ";
			 tableString += "   <li class=\"notice_add_file_name\"><span></span><a href=\"#\" onclick=\"getFileDownLoad('"+this['bi_atch_file_sn']+"');\">"+this['bi_atch_file_nm']+"</a></li> ";
			// tableString += "   <li class=\"notice_add_file_size\">"+byteCalculation(this['bi_atch_file_mg_byte'])+"</li>";
			//tableString += "  <li class=\"notice_add_file_btn\"><a href=\"#\"><img src=\"images/main/file_Del_btn.png\" style=\"vertical-align:middle;\"></a></li> ";                               
			 tableString += " </ul> ";
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
function  getFileEdit(aliasName,id){
	
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
				
				$("#file"+this['bi_file_num']).html("파일명:[<a href=\"#\"onclick=\"getFileDownLoad('"+this['bi_atch_file_sn']+"');\">"+this['bi_atch_file_nm']+"</a>] <a href=\"#\" onclick=\"fileDelete("+this["bi_atch_file_sn"]+","+id+");\">[삭제]</a>");
				
				
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
			
			getEditPage(bi_sn);
			getFileEdit(aliasName,bi_sn);
			
			
			
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
/** ************************************* 수정 페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getEditPage(id) {


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
/**
 * *************************************댓글 목록페이지 구성
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getCommentList(bi_bbs_sn) {

	var listLink = "/" + serviceName + "/comment/" + getDataLink.list;
	// alert(bi_bbs_sn);
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
				tableString += " <td >" + this['bi_user_nm'] + "</td>";
				tableString += " <td>" + this['bi_ansr_cn'] + "</td>";
				if($("#sessionId").val() == this['bi_unity_cust_id']){
				tableString += " <td><a href='#' onclick='getCommentDelete(" + this['bi_bbs_ansr_sn'] + "," + bi_bbs_sn + ")'><span class=\"glyphicon glyphicon-trash\"></span></a></td>";
				}else{
					
				tableString += " <td></td>";	
				}
				
				tableString += "</tr>";

			});

			// / alert(tableString);

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
/** *************************************답변 상태 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getStats(val, id) {

	// alert(val);
	var statSplit = val.split(",");

	for (var i = 0; i < statSplit.length; i++) {

		var statVal = statSplit[i].split(":");
		var returnBut = "";
		// alert(statVal[0]);

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
/**
 * *************************************** 보고서검색
 * ******************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchReportList() {
    
	

	var searchContent = $("#searchContent").val();
	

	if (searchContent == "") {
		
		$("#msg_title").html("검색!!");
		$("#msg").html("검색 내용을 입력해주세요");
		$("#msg_modal").modal("show");
		
		return;

	}

	getSearchData(searchContent);
	
	
	$("#searchClear").show();

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색초기화
 * ***************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchReportClear() {
	
	$("#searchClear").hide();
	$("#searchContent").val("");
	getSearchData("");

}




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색
 * ******************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchList() {
    
	var currentPage = 1;
	var searchTitle = $("#searchTitle").val();
	var searchContent = $("#searchContent").val();

	if (searchTitle == "0") {

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

	getListPageData(currentPage, searchTitle, searchContent);
	
	$("#searchClear").show();

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색초기화
 * ***************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchClear() {
	
	$("#searchClear").hide();
	$("#searchContent").val("");
	
	var currentPage = 1;
	var searchTitle = 0;
	var searchContent = "";
	getListPageData(currentPage, searchTitle, searchContent);

}



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색
 * ******************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchFaqList() {
    
	var currentPage = 1;
	var searchTitle = $("#searchTitle").val();
	var searchContent = $("#searchContent").val();

	if (searchTitle == "0") {

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

	getFaqList(currentPage, searchTitle, searchContent);
	
	$("#searchClear").show();

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색초기화
 * ***************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchFaqClear() {
	
	$("#searchClear").hide();
	$("#searchContent").val("");
	
	var currentPage = 1;
	var searchTitle = 0;
	var searchContent = "";
	getFaqList(currentPage, searchTitle, searchContent);

}


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색
 * ******************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchMyPageList() {

	var searchTitle = $("#searchTitle").val();
	var searchContent = $("#searchContent").val();

	if (searchTitle == "0") {

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

	getMyPageListPageData(1, searchTitle, searchContent);

	$("#searchMyPageClear").show();

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * *************************************** 검색초기화
 * ***************************************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function searchMyPageClear() {
	$("#searchMyPageClear").hide();
	$("#searchTitle").val("0");
	$("#searchContent").val("");
	
	
	getMyPageListPageData(1, "", "");

}

/***********************************************************************************************
 * ****************************** 삭제 권한 여부 확인*************************************************
 * *********************************************************************************************
 ***********************************************************************************************/

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
				
				$("#goEditForm_btn").append("<span class=\"whitishBtn button_small\"><span class=\"fontawesome_Btn fa-edit\"></span>수정</span>");
				$("#goDeleteForm_btn").append("<span class=\"whitishBtn button_small\"><span class=\"fontawesome_Btn fa-remove\"></span>삭제</span>");
				
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


function newReport(id){
	
	
	var url = "/" + serviceName + "/popup/list.do?reportId="+id;	
	
	var popup = window.open(url, "" , "width=980,height=600,toolba=no, scrollbars=yes, resizable = yes");
	popup.moveTo(0,0);
	
	
	
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
/** *************************************답변 상태 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getStats(val, id) {

	// alert(val);
	var statSplit = val.split(",");

	for (var i = 0; i < statSplit.length; i++) {

		var statVal = statSplit[i].split(":");
		var returnBut = "";
		// alert(statVal[0]);

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


function qlikviewUserChek(){
	
	$.ajax({

		url :  "/" + serviceName + "/qlikview/qlikviewLoginInfo.do",
		type : "POST",
		data :{},
		datatype : 'text/html',		
		beforeSend : function() {
				
				$("#progress_modal").modal('show');
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
			
		},
		success : function(data) {
				
			
				$("#progress_modal").modal('hide');	
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			
			//alert(JSON.stringify(data));
			     
			     if(data.size > 0){ //해당 데이터가 있으면 QlikView 로그인  , Qlikview 보기 
			    	 
			    	 $("#qlikviewUrl").attr("src",qlikViewUrl+"/qlikview/formLogin1.htm?userNm=" + data.rows[0]['bi_qlikview_user_id'] + "&userPw="+ data.rows[0]['bi_qlikview_user_pwd']);
			    	 
			     
			    	  setTimeout(function(){
			    		  
			    		 reportList(currentId);
							
					   }, 500);
			    	  
			    	  $("#goQlikview").html("<button type=\"button\" class=\"btn btn-default btn-xs\" onClick=\"goPdfView('"+ currentId +"');\"><span class=\"glyphicon glyphicon-list-alt\" ></span> 도움말 </button>");
			     
			    	 
			     }else{  // Qlikview 로그인 데이터 등록 , 그리고 로그인 
			    	 
			    	 
			    	 qlikviewMaxUser(); // null 값의 찾아낸다.
			    	 
			    	 
			    	 
			     }
			
			
			//data.defaultSelected;
		},
		error : function() {
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("해당보고서가 존재하지 않거나 잘못된 경로로 접근하였습니다.<br> 관리자에게 문의하세요");
		}
	});
}	

//사용자 추가하고 보고서 보기 

function qlikviewAddUser(bi_sn){
		
	var url = "/" + serviceName + "/qlikview/qlikViewAddUser.do";
	$.ajax({

		url : url,
		type : "post",
		data : {bi_sn:bi_sn},
		dataType : 'json',
		beforeSend : function() {

		},
		success : function(data) {

			qlikviewUserChek();

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

//사용자 체크 
function qlikviewMaxUser(){
	
	var url = "/" + serviceName + "/qlikview/qlikViewMaxUser.do";
	
	
	$.ajax({

		url : url,
		type : "post",
		dataType : 'json',
		beforeSend : function() {

		},
		success : function(data) {
			

			//alert(JSON.stringify(data));
			
			if(data.size == 0){ //20개 아이디가 다 차면  
				
				
				
				$("#msg_modal").modal('show');
				$("#msg").html("클릭뷰 사용자 20명으로 제한되었습니다. 다른 사용자가 이미 등록되여 분석중입니다. 관리자에게 문의 하세요. ");
				
				
				addFailLog();
				
			}else{				
				// 사용자 등록 
						
				qlikviewAddUser(data.rows['bi_sn']);
				
			}
			
		},
		error : function() {
			
			
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	  });
}

function reportList(currentId){

	var url = "/" + serviceName + "/report/getPromptUrl.do";
	
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			bi_portal_menu_id : currentId
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
			   // alert(JSON.stringify(data));
			    
			    
			   
				$("#reportUrl").attr("src",data.url);		
				$("#reportTitle").html(data.rows['bi_menu_nm']);	
				$("#prevTitle").val(data.rows['bi_menu_nm']);	
				$(".sitemap").html(data.siteMapNm);	
				$("#aurl").val(data.aurl);	
				
				
				if(data.rows['bi_help_yn']=='Y'){
					
					$("#help_btn").show();
				}else{
					
					$("#help_btn").hide();
				}
				
				if(data.rows['bi_aqvw_nm']!= null){
					
					$("#aqvw_btn").show();
				}else{
					
					$("#aqvw_btn").hide();
				}
				
				
			
				
			//data.defaultSelected;
		},
		error : function() {
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("해당보고서가 존재하지 않거나 잘못된 경로로 접근하였습니다.<br> 관리자에게 문의하세요");
		}
	});
}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************************  보고서 이력현황   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getHistoryData(currentPage, bi_portal_menu_id){
	

	$.ajax({

		url : "/"+ serviceName+"/history/getListData.do",
		type : "post",
		data : {
			 currentPage : currentPage
			,bi_portal_menu_id : bi_portal_menu_id
		},
		datatype : 'json',
		beforeSend : function() {
			
			$("#progress_modal").modal('show');
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},

		success : function(data) {
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');
			
			if(data.totalNo > 0 ){
				
			$("#goHistory_btn").show();
				
			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";
			

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
			$("#headTableContent").html(headtableString); // 목록
			
			
			var noPage = (data.currentPage - 1);
			var no = data.totalNo - (noPage * 10);
			
			$.each(data.rows, function(key, value) {

				tableString += "<tr>";
			
				tableString += " <td>" + no + "</td>";

				for (var i = 0; i < attrVal.length; i++) {

					 if (attrVal[i].type == 'button') {
						tableString += " <td>";
						tableString += "  <a href=\"#\" onclick=\"goHistoryReport('"+ this[attrVal[i].name]+"','"+ this[attrVal[0].name]+"');\"> <span class=\"button_small whitishBtn\"><span class=\"fontawesome_Btn fa-book\"></span>보고서보기</span></a>";
						tableString += " </td>";

					} else {
						
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
					}
				}

				tableString += "</tr>";
				no = no - 1;
				

			});
		

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
			
			
			
			
			
			strPage += " <a href=\"#\" onclick=\"getHistoryData(1,'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > 1){
				strPage += " <a href=\"#\" onclick=\"getHistoryData("+parseInt(currentPage - 1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			}else{
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
			}
			
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getListPageData("+ i +",'"+bi_portal_menu_id+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) <= endPageNum){
				
				strPage += " <a href=\"#\" onclick=\"getHistoryData("+ parseInt(currentPage+1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
			
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";	
			}
			strPage += " <a href=\"#\" onclick=\"getHistoryData("+ parseInt(endPageNum) +",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
			$("#paging").html(strPage); // pageing

			function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

				var extra = parseInt(totalNo % countPerPage);

				if (extra > 0) {

					return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

				} else {

					return parseInt(totalNo / countPerPage);

				}

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
/** ************************************************  보고서 이력서 보기   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goHistoryReport(id,regDate){
	
	
	$("#goReport_btn").show();	

	var url = "/" + serviceName + "/report/getHistoryUrl.do";
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			reportId : id
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
			    //var repotTitle = $("#prevTitle").val()+" ["+regDate+"]  ";
			   // repotTitle =+ "<a href=\"#\" onclick=\"reportList("+currentId+");\"> 기존보고서 보기</a>";
			    $("#reportTitle").html($("#prevTitle").val()+" ["+regDate+"]  ");
			    $("#history_modal").modal("hide")
			    $("#reportUrl").attr("src",data);
			
		},
		error : function() {
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("해당보고서가 존재하지 않거나 잘못된 경로로 접근하였습니다.<br> 관리자에게 문의하세요");
		}
	});
	
	
	
	
	
}



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************************  백업보고서 이력현황   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getBackupData(currentPage, bi_portal_menu_id){
	

	var metaParam=
	{
	   attrKey : "bi_h_mng_sn"
	  ,attrNm : "bi_qvw_nm"
	  ,headerTitle:["no","보고서명","등록일","보고서보기"]
      ,attrVal :[
		 { 
			  name : "bi_qvw_nm"
			, type : "input"
			, width: ""			
			, funcNm:""   
		  }
		 ,{
			  name : "bi_reg_dt"
			, type : "input"
			, width: ""	
			, funcNm:""
		   }, {
			   name : "bi_qvw_nm"
				, type : "button"
				, val : "보고서보기"
				, funcNm:""
				, href:"N"
			}
	   ]						
	};
	
	
	var headerTitle = metaParam.headerTitle;
	var attrVal = metaParam.attrVal;
	
	$.ajax({

		url : "/"+ serviceName+"/backup/getListData.do",
		type : "post",
		data : {
			 currentPage : currentPage
			,bi_portal_menu_id : bi_portal_menu_id
		},
		datatype : 'json',
		beforeSend : function() {
			
			$("#progress_modal").modal('show');
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},

		success : function(data) {
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');
			
			if(data.totalNo > 0 ){
				
			$("#goBackup_btn").show();
				
			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";
			

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
			$("#backupHeadContent").html(headtableString); // 목록
			
			
			var noPage = (data.currentPage - 1);
			var no = data.totalNo - (noPage * 10);
			
			$.each(data.rows, function(key, value) {

				tableString += "<tr>";
			
				tableString += " <td>" + no + "</td>";

				for (var i = 0; i < attrVal.length; i++) {

					 if (attrVal[i].type == 'button') {
						tableString += " <td>";
						tableString += "  <a href=\"#\" onclick=\"goBackupReport('"+ this[attrVal[0].name]+"','"+ this[attrVal[1].name]+"');\"> <span class=\"button_small whitishBtn\"><span class=\"fontawesome_Btn fa-book\"></span>보고서보기</span></a>";
						tableString += " </td>";

					} else {
						
								
								tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
					}
				}

				tableString += "</tr>";
				no = no - 1;
				

			});
		

			$("#backupTableContent").html(tableString); // 목록

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
			
			strPage += " <a href=\"#\" onclick=\"getBackupData("+parseInt(startPage)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > startPage){
				
			strPage += " <a href=\"#\" onclick=\"getBackupData("+parseInt(currentPage - 1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
			}
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getBackupData("+ i +",'"+bi_portal_menu_id+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) < endPage){
				
				strPage += " <a href=\"#\" onclick=\"getBackupData("+ parseInt(currentPage+1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
			}else{
				
				strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";
			}
			strPage += " <a href=\"#\" onclick=\"getBackupData("+ parseInt(endPage-1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
			$("#paging").html(strPage); // pageing

			function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

				var extra = parseInt(totalNo % countPerPage);

				if (extra > 0) {

					return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

				} else {

					return parseInt(totalNo / countPerPage);

				}

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
/** ************************************************  보고서 이력서 보기   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goBackupReport(nm,regDate){
	
	
	$("#goReport_btn").show();	

	var url = "/" + serviceName + "/report/getBackupUrl.do";
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			reportNm : nm
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
			    //var repotTitle = $("#prevTitle").val()+" ["+regDate+"]  ";
			   // repotTitle =+ "<a href=\"#\" onclick=\"reportList("+currentId+");\"> 기존보고서 보기</a>";
			    $("#reportTitle").html($("#prevTitle").val()+" ["+regDate+"]  ");
			    $("#backup_modal").modal("hide")
			    $("#reportUrl").attr("src",data);
			
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
/** ************************************************  백업보고서 이력현황   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getAdvencedData(currentPage, bi_portal_menu_id){

	var metaParam=
	{
	   attrKey : "bi_h_mng_sn"
	  ,attrNm : "bi_qvw_nm"
	  ,headerTitle:["no","보고서명","등록일","보고서보기"]
      ,attrVal :[
		 { 
			  name : "bi_qvw_nm"
			, type : "input"
			, width: ""			
			, funcNm:""   
			, href:"y"
		  }
		 ,{
			  name : "bi_reg_dt"
			, type : "input"
			, width: ""	
			, funcNm:""
			 ,href:"y"
		   }, {
			  name : "bi_qvw_nm"
			, type : "button"
			, val : "보고서보기"
			, funcNm:""
			, href:"N"
		}
		 
	   ]						
	};
	
	
	var headerTitle = metaParam.headerTitle;
	var attrVal = metaParam.attrVal;
	
	
	$.ajax({

		url : "/"+ serviceName+"/advenced/getListData.do",
		type : "post",
		data : {
			 currentPage : currentPage
			,bi_portal_menu_id : bi_portal_menu_id
		},
		datatype : 'json',
		beforeSend : function() {
			
			$("#progress_modal").modal('show');
			$(".progress").show();
			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},

		success : function(data) {
			
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#progress_modal").modal('hide');
			
			//alert("data::"+ JSON.stringify(data));
			
			if(data.totalNo > 0 ){
				
			$("#goAdvenced_btn").show();
				
			var headtableString = "";
			var tableString = "";
			
			headtableString += "<tr>";
			

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
			$("#advencedHeadContent").html(headtableString); // 목록
			
			
			var noPage = (data.currentPage - 1);
			var no = data.totalNo - (noPage * 10);
			
			$.each(data.rows, function(key, value) {

				tableString += "<tr>";
			
				tableString += " <td>" + no + "</td>";

				for (var i = 0; i < attrVal.length; i++) {

					 if (attrVal[i].type == 'button') {
						tableString += " <td>";
						tableString += "  <a href=\"#\" onclick=\"goAdvencedReport('"+ this[attrVal[0].name]+"','"+ this[attrVal[1].name]+"');\"> <span class=\"button_small whitishBtn\"><span class=\"fontawesome_Btn fa-book\"></span>보고서보기</span></a>";
						tableString += " </td>";

					} else {
						tableString += " <td>" + this[attrVal[i].name]	+ "</td>";
					}
				}

				tableString += "</tr>";
				no = no - 1;
				

			});
		

			$("#advencedTableContent").html(tableString); // 목록

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
			
			strPage += " <a href=\"#\" onclick=\"getAdvencedData("+parseInt(startPage)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev_2.gif' title='' alt='' /></a>";
			if(currentPage > startPage){
				strPage += " <a href=\"#\" onclick=\"getAdvencedData("+parseInt(currentPage - 1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' /></a>";
			}else{
				strPage += "<img src='/"+serviceName+"/assets/images/main/icoprev.gif' title='' alt='' />";	
			}
			strPage += " <span>";
			for (var i = startPage; i < endPage; i++) {
				if (currentPage == i) {
					strPage += " <a href=\"#\" class=\"on\">" + i + "</a>";
				} else {
					strPage += " <a href=\"#\" title=\"" + i + "\" onclick=\"getAdvencedData("+ i +",'"+bi_portal_menu_id+"');\">" + i + "</a>";
				}
			}
			strPage += " </span>";
			if(parseInt(currentPage+1) < endPage){
				
			strPage += "<a href=\"#\" onclick=\"getAdvencedData("+ parseInt(currentPage+1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' /></a>";
			
			}else{
			strPage += "<img src='/"+serviceName+"/assets/images/main/iconext.gif' title='' alt='' />";
			}
			strPage += " <a href=\"#\" onclick=\"getAdvencedData("+parseInt(endPage-1)+",'"+bi_portal_menu_id+"');\"><img src='/"+serviceName+"/assets/images/main/iconext_2.gif' title='' alt='' /></a>";
		
			$("#paging").html(strPage); // pageing

			function endPage(currentPage, countPerPage, totalNo) { // 마지막 페이지

				var extra = parseInt(totalNo % countPerPage);

				if (extra > 0) {

					return parseInt(totalNo - extra) / parseInt(countPerPage) + 1;

				} else {

					return parseInt(totalNo / countPerPage);

				}

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
/** ************************************************  보고서 이력서 보기   ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goAdvencedReport(nm,regDate){

	$("#goReport_btn").show();	

	var url = "/" + serviceName + "/report/getAdvencedUrl.do";
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
			reportNm : nm
		},
		datatype : 'text/html',		
		beforeSend : function() {

// 			$("#progress_modal").modal('show');		
// 			$(".progress").show();
// 			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
				
			
		},
		success : function(data) {
			
			    //var repotTitle = $("#prevTitle").val()+" ["+regDate+"]  ";
			   // repotTitle =+ "<a href=\"#\" onclick=\"reportList("+currentId+");\"> 기존보고서 보기</a>";
			    $("#reportTitle").html($("#prevTitle").val());
			    $("#advenced_modal").modal("hide");
			    $("#reportUrl").attr("src",data);
			
		},
		error : function() {
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("관리자에게 문의하세요 <br> 보고서 수집되지 않았습니다.");
		}
	});
	
	
	
	
	
}


function addFailLog(){

	
	var url = "/" + serviceName + "/log/qlikviewLoginFail.do";
	
	$.ajax({

		url : url,
		type : "POST",
		
		data : {
		},
		datatype : 'text/html',		
		beforeSend : function() {
			
		},
		success : function(data) {
			
		},
		error : function() {
			$("#progress_modal").modal('hide');	
			$("#msg_modal").modal('show');
			$("#msg").html("관리자에게 문의하세요 <br> 보고서 수집되지 않았습니다.");
		}
	});
	
}

function byteCalculation(bytes) {
    var bytes = parseInt(bytes);
    var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
    var e = Math.floor(Math.log(bytes)/Math.log(1024));
   
    if(e == "-Infinity") return "0 "+s[0]; 
    else 
    return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2)+" "+s[e];
}

