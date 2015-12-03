/*********************         log   start     **********************************/
/*********************         log   start     **********************************/
/*********************         log   start     **********************************/
/*********************         log   start     **********************************/

	var logUrl = "/"+serviceName+"/log/readPageLog.do";
	var logSearch = $(location).attr("search");
	var logPage   = $(location).attr("pathname");
	
    
	
	var fullLogUrl = logPage+logSearch;
	
	
	//alert(fullLogUrl);
	$.ajax({
		url : logUrl,
		type : "post",
		data : { bi_connct_url_addr: fullLogUrl ,bi_portal_menu_id : currentId},
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
	
			 
		},
		error : function(jqXHR, textStatus, errorThrown) {
		
		}
	});
	
	
	
	
	//리스트 페이지로 
	$("#listForm_button").click(function() {		
		
		
		var logUrl = "/"+serviceName+"/log/readPageLog.do";
		var logSearch = $(location).attr("search");
		var logPage   = $(location).attr("pathname");
		
		var fullLogUrl = logPage+logSearch;
		
		
		//alert(fullLogUrl);
		$.ajax({
			url : logUrl,
			type : "post",
			data : { bi_connct_url_addr: fullLogUrl,bi_portal_menu_id : currentId },
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
				
		
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
			
			}
		});
		
	});


	//수정페이지 
	$("#editForm_button").click(function() {	
		 
		   
			var logUrl = "/" + serviceName + "/log/readPageLog.do";

			var logSearch = "?pageNm=edit";
			var logPage = $(location).attr("pathname");

			var fullLogUrl = logPage + logSearch;

			//fullLogUrl);
			$.ajax({
				url : logUrl,
				type : "post",
				data : {
					bi_connct_url_addr : fullLogUrl,bi_portal_menu_id : currentId
				},
				beforeSend : function() { //

				},
				success : function(data, textStatus, jqXHR) {

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});

		
	});
	
	//등록페이지 
	$("#writeForm_button").click(function() {
		
		var logUrl = "/"+serviceName+"/log/readPageLog.do";
		
		var logSearch = "?pageNm=write";
		var logPage   = $(location).attr("pathname");	

		
		var fullLogUrl = logPage+logSearch;		
		
		//fullLogUrl);
		$.ajax({
			url : logUrl,
			type : "post",
			data : { bi_connct_url_addr: fullLogUrl ,bi_portal_menu_id : currentId},
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
				
		
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
			
			}
		});
		
	});
	
	
	//등록페이지 
	$("#writeUserGroupForm_button").click(function() {
		
		var logUrl = "/"+serviceName+"/log/readPageLog.do";
		
		var logSearch = "?pageNm=write";
		var logPage   = $(location).attr("pathname");	

		
		var fullLogUrl = logPage+logSearch;		
		
		//fullLogUrl);
		$.ajax({
			url : logUrl,
			type : "post",
			data : { bi_connct_url_addr: fullLogUrl },
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
				
		
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
			
			}
		});
		
	});
	
	//등록페이지 
	$("#writeMenuGroupForm_button").click(function() {
		
		var logUrl = "/"+serviceName+"/log/readPageLog.do";
		
		var logSearch = "?pageNm=write";
		var logPage   = $(location).attr("pathname");	

		
		var fullLogUrl = logPage+logSearch;		
		
		//fullLogUrl);
		$.ajax({
			url : logUrl,
			type : "post",
			data : { bi_connct_url_addr: fullLogUrl },
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
				
		
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
			
			}
		});
		
	});
	
	
	
