<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>�����ȣ ã�� | �����������</title>
<meta name="author" content="Song jungsun,Kim hyejin"> 
<meta name="keywords" content="�����������">
<meta name="description" content="����������ܿ� ���Ű��� ȯ���մϴ�.�� �������� HTML5 DTD�� �ؼ��ϰ�, WCAG, KWCAG, IWCAG �� ������� �����ý����� ����, � ��� ��ħ�� �ؼ��Ͽ� �����Ͽ����ϴ�.">
<link rel="stylesheet" type="text/css" href="./base.css">
<link rel="stylesheet" type="text/css" href="./popup.css">
<script type="text/javascript" src="./jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="./content.js"></script>
</head>
<body>
<script type="text/javascript">

//var apiUrl = "http://localhost:8080/api/getData/apiData.do";
//var apiUrl = "http://10.59.32.176/api/getData/apiData.do";
var apiUrl = "http://10.59.35.55:89/api/getData/apiData.do";
//var apiUrl = "http://10.59.35.39/api/getData/apiData.do";


		$(document).ready(function() {
			
			// ��/�� �ּ� ����	
			var postData = $("#searchForm").serializeArray();
			postData.push({name:"key", value:"6982d695-0dd1-4d0a-aed5-b946a362272f"});
		 	postData.push({name:"pParam", value:"SIDO_NM"});
		 	postData.push({name:"whereType", value:"eq"});
			//alert(JSON.stringify(postData));
			
			$.ajax({
				
				url : apiUrl,
				type : "GET",
				data : postData,
				contentType: "application/json; charset=euc-kr;",
				dataType : 'jsonp',
				jsonpCallback:"getDataView",
				error:function(xhr,status,error){
					

					
				
				},
				success : function(data){
				

				}
			});
			
			
		});



	jQuery(function(){
		
		
	
		
		//�ε��� ����
		jQuery("#loading").css("display", "none");
		
		var sigunguCnt = "16";
		if(sigunguCnt > 0){
			jQuery("#sigunguArea").attr("class", "selectbox fl");
			jQuery("#sigunguArea").attr("style", "");
		}else{
			jQuery("#sigunguArea").attr("class", "selectbox_disable fl");
			jQuery("#sigunguArea").attr("style", "opacity:0.3;filter:alpha(opacity=30)");
		}
		
		var eupMyeonDongCnt = "13";
		if(eupMyeonDongCnt > 0){
			jQuery("#eupArea").attr("class", "selectbox fl");
			jQuery("#eupArea").attr("style", "");
		}else{
			jQuery("#eupArea").attr("class", "selectbox_disable fl");
			jQuery("#eupArea").attr("style", "opacity:0.3;filter:alpha(opacity=30)");
		}
		
		var initialCodeCnd = "14";
		if(initialCodeCnd > 0){
			jQuery("#initialArea").attr("class", "selectbox fl mr3");
			jQuery("#initialArea").attr("style", "");
		}else{
			jQuery("#initialArea").attr("class", "selectbox_disable fl mr3");
			jQuery("#initialArea").attr("style", "opacity:0.3;filter:alpha(opacity=30)");
		}
		
		var rdnmCnt = "11";
		if(rdnmCnt > 0){
			jQuery("#rdnmArea").attr("class", "selectbox fl");
			jQuery("#rdnmArea").attr("style", "");
		}else{
			jQuery("#rdnmArea").attr("class", "selectbox_disable fl");
			jQuery("#rdnmArea").attr("style", "opacity:0.3;filter:alpha(opacity=30)");
		}
	});
	
	//�õ� ���ý� �ñ��� ����Ʈ�� ��ȸ. (�������� ��� ���鵿 ����Ʈ ��ȸ)
	function ufn_selectSido(code){
		
		
	
		
		jQuery("#loading").css("display", "block");
		jQuery("#cgg").text(code);
		jQuery("#sido").val(code);	//�õ� �� ����
		
		
		
		jQuery("#sigungu").val("");	//�ñ��� �� �ʱ�ȭ
		jQuery("#dong").val("");	//���鵿 �� �ʱ�ȭ
		jQuery("#initial").val("");	//�ʼ� �� �ʱ�ȭ
		
		/*
		if(code == "����Ư����ġ��"){
			jQuery("#form").attr("action","/popup/post/sslInqEupMyeonDong.do");
			jQuery("#form").submit();
		}else{
			jQuery("#form").attr("action","/popup/post/sslInqSigungu.do");
			jQuery("#form").submit();
		}
		*/
		
		
		var postData = $("#searchForm").serializeArray();
		postData.push({name:"key", value:"47de3a97-5e51-47f1-b314-a72554938bc3"});
    	postData.push({name:"pParam", value:"SIGNGU_NM"});
    	postData.push({name:"whereType", value:"eq"});
    	postData.push({name:"SIDO_NM", value:code});
    	
    	//alert(JSON.stringify(postData));
		
		$.ajax({
			
			url : apiUrl,
			type : "GET",
			data : postData,
			contentType: "application/json; charset=euc-kr;",
			dataType : 'jsonp',
			jsonpCallback:"getSignguDataView",
			error:function(xhr,status,error){
				
				//console.log(status + '; ' + error);
				
			
			},
			success : function(data){
			
				//alert('callback success: ', data);	
			}
		});
		
		
		
	}
	
	//�ñ��� ���ý� ���鵿 ����Ʈ�� ��ȸ.
	function ufn_sigungu(code){
		
		jQuery("#loading").css("display", "block");
		
		jQuery("#sigungu").val(code);	//�ñ��� �� ����
		jQuery("#sigungu_txt").html(code);
		jQuery("#dong").val("");		//���鵿 �� �ʱ�ȭ
		jQuery("#initial").val("");		//�ʼ� �� �ʱ�ȭ
		jQuery("#rdnm").val("");		//���θ� �� �ʱ�ȭ
// 		jQuery("#form").attr("action","/popup/post/sslInqEupMyeonDong.do");
// 		jQuery("#form").submit();
		
		
		
		
		var postData = $("#searchForm").serializeArray();
		postData.push({name:"key", value:"8a41667a-7f15-4f29-9d9d-cff13b9c6705"});
    	postData.push({name:"pParam", value:"LGAL_EMD_NM"});
    	postData.push({name:"whereType", value:"eq"});
    	postData.push({name:"SIDO_NM", value:$("#sido").val()});
    	postData.push({name:"SIGNGU_NM", value:code});
    	
    	//alert(JSON.stringify(postData));
		
		$.ajax({
			
			url : apiUrl,
			type : "GET",
			data : postData,
			contentType: "application/json; charset=euc-kr;",
			dataType : 'jsonp',
			jsonpCallback:"getEmdDataView",
			error:function(xhr,status,error){
				
				//console.log(status + '; ' + error);
				
			
			},
			success : function(data){
			
				//alert('callback success: ', data);	
			}
		});
		
	}
	
	//���鵿 ���ý� ������
	function ufn_selectEupMyeonDong(code,gubun){
		
		jQuery("#loading").css("display", "block");
		
		jQuery("#dong").val(code);		//���鵿 �� ����
		jQuery("#dong_txt").html(code);		//���鵿 �� ����
		jQuery("#gubun").val(gubun);	//���鵿 ���а� ����(�� : 1 ���� : 2)
		jQuery("#initial").val("");		//�ʼ� �� �ʱ�ȭ
		jQuery("#rdnm").val("");		//���θ� �� �ʱ�ȭ
		
		
		var postData = $("#searchForm").serializeArray();
		postData.push({name:"key", value:"8a41667a-7f15-4f29-9d9d-cff13b9c6705"});
    	postData.push({name:"pParam", value:"RN"});
    	postData.push({name:"whereType", value:"eq"});
    	postData.push({name:"SIDO_NM", value:$("#sido").val()});
    	postData.push({name:"SIGNGU_NM", value:$("#sigungu").val()});
    	postData.push({name:"LGAL_EMD_NM", value:code});
    	
    	//alert(JSON.stringify(postData));
		
		$.ajax({
			
			url : apiUrl,
			type : "GET",
			data : postData,
			contentType: "application/json; charset=utf-8;",
			dataType : 'jsonp',
			jsonpCallback:"getEupMyeonDongDataView",
			error:function(xhr,status,error){
				
				//console.log(status + '; ' + error);
				
			
			},
			success : function(data){
			
				//alert('callback success: ', data);	
			}
		});
		
		
		
		//jQuery("#form").attr("action","/popup/post/sslInqEupMyeonDong.do");
		//jQuery("#form").submit();
	}
	
	//�ʼ� ���ý� ���θ� ����Ʈ�� ��ȸ
	function ufn_inqInitialSearch(code){
		
		jQuery("#loading").css("display", "block");
		
		jQuery("#initial").val(code);
		jQuery("#form").attr("action","/popup/post/sslInqInitialSearch.do");
		jQuery("#form").submit();
	}
	
	//���θ� ���ý� ������
	function ufn_selectRdnm(code){
		
		jQuery("#loading").css("display", "block");
		
		//alert(code);
		
		jQuery("#rdnm").val(code);
		jQuery("#rdnm_txt").html(code);
		
		
		
		var postData = $("#searchForm").serializeArray();
		postData.push({name:"key", value:"8a41667a-7f15-4f29-9d9d-cff13b9c6705"});
    	postData.push({name:"pParam", value:"ZIP,SIDO_NM,SIGNGU_NM,LGAL_EMD_NM,RN"});
    	postData.push({name:"whereType", value:"eq"});
    	postData.push({name:"SIDO_NM", value:$("#sido").val()});
    	postData.push({name:"SIGNGU_NM", value:$("#sigungu").val()});
    	postData.push({name:"LGAL_EMD_NM", value:$("#dong").val()});
    	postData.push({name:"RN", value:code});
    	
    	//alert(JSON.stringify(postData));
		
		$.ajax({
			
			url : apiUrl,
			type : "GET",
			data : postData,
			contentType: "application/json; charset=utf-8;",
			dataType : 'jsonp',
			jsonpCallback:"getFullAddrDataView",
			error:function(xhr,status,error){
				
				//console.log(status + '; ' + error);
				
			
			},
			success : function(data){
			
				//alert('callback success: ', data);	
			}
		});
		
		
	}
	
	//�˻���ư Ŭ���� �ּҰ˻�
	function inqListAdr(){

		if(jQuery("#sido").val() == ""){
			alert("��/���� �������ּ���.");
			return;
		}
		
		var cgg = jQuery("#cgg").text();
		cgg = jQuery.trim(cgg);
		if(cgg != "����Ư����ġ��"){
			if(jQuery("#sigungu").val() == ""){
				alert("��/��/���� �������ּ���.");
				return;
			}
		}
		
		jQuery("#loading").css("display", "block");
		
		jQuery("#form").attr("action","/popup/post/sslInqListAdr.do");
		jQuery("#form").submit(); 
	}
	 
	//�˻���ư Ŭ���� �ּҰ˻�  
	function ufn_postSet(a,b,c,d,e,f){
		var mode = opener.document.getElementById("mode").value;
		
		if(mode == "pass"){
					opener.document.getElementById('zip1').value = a;
					opener.document.getElementById('zip2').value = b;
					opener.document.getElementById('adres').value = c+d+e+f;
		}else if(mode == "mbs"){
						opener.document.getElementById('zip1').value = a;
						opener.document.getElementById('zip2').value = b;
						opener.document.getElementById('adres').value = c+d+e+f;
		}else{
			opener.document.getElementById('zip1').value = a;
			opener.document.getElementById('zip2').value = b;
			opener.document.getElementById('adres').value = c+d+e+f;
		}
		opener.document.getElementById('area').value = c;
		window.close();
	}
	
	
	
	/*
	 �õ� �ּ� ����
	*/
	//��/�� 
	function getDataView(data) {
		
		var rowData = data.rows;

		var liHtml = "";
		$.each(data.rows, function(key, value) {
			
			liHtml +="<li style=\"width:131px\"><a href=\"#"+this["CMMN_CD_NM"]+"\" onclick=\"ufn_selectSido('"+this["SIDO_NM"]+"'); return false;\" >"+this["SIDO_NM"]+"</a></li>";
			//$("#SIDO_NM").append("<li style=\"width:131px\"><a href=\"#"+this["SIDO_NM"]+"\" onclick=\"ufn_selectSido('"+this["SIDO_NM"]+"'); return false;\" >"+this["SIDO_NM"]+"</a></li>");
		});
		$("#loading").hide();
		$("#SIDO_NM").html(liHtml);
		
	}
	//��/��/��
	function getSignguDataView(data) {
		 
		
		var rowData = data.rows;
		var liHtml = "";
		$.each(data.rows, function(key, value) {
			liHtml +="<li style=\"width:161px\"><a href=\"#sigunguList\" onclick=\"ufn_sigungu('"+this["SIGNGU_NM"]+"');\">"+this["SIGNGU_NM"]+"</a></li>";
		});
		$("#loading").hide();
		$("#SIGNGU_NM").html(liHtml);
		
	}

	//��/��/�� �ּ�
	function getEmdDataView(data) {
		

		
		var liHtml = "";
		var rowData = data.rows;
		$.each(data.rows, function(key, value) {
			
			liHtml +="<li style=\"width:131px\"><a href=\"#"+this["LGAL_EMD_NM"]+"\" onclick=\"ufn_selectEupMyeonDong('"+this["LGAL_EMD_NM"]+"','1');\">"+this["LGAL_EMD_NM"]+"</a></li>";
			
			//$("#LGAL_EMD_NM").append("<option value='"+this["LGAL_EMD_NM"]+"'>"+this["LGAL_EMD_NM"]+"</option>");
			
		});
		$("#loading").hide();
		$("#LGAL_EMD_NM").html(liHtml);
		
	    
	}
	//���θ� �ּ� 
	function getEupMyeonDongDataView(data){
		
		var liHtml = "";
		var rowData = data.rows;
		
		$.each(data.rows, function(key, value) {
			
			liHtml +="<li style=\"width:97px\"><a href=\"#"+this["RN"]+"\" onclick=\"ufn_selectRdnm('"+this["RN"]+"');\" title=\""+this["RN"]+"\" class=\"ellipsis\" style=\"width:90%\">"+this["RN"]+"</a></li>";	
			
			//$("#LGAL_EMD_NM").append("<option value='"+this["LGAL_EMD_NM"]+"'>"+this["LGAL_EMD_NM"]+"</option>");
		});
		$("#loading").hide();
		$("#RN").html(liHtml);
		
		
	}
	//��ü �ּ� ü�� 
    function getFullAddrDataView(data){
		
		
		
		var liHtml = "";
		var rowData = data.rows;
		
		$.each(data.rows, function(key, value) {
			//$("#addr").append("<option value='"+this["LGAL_EMD_NM"]+"'>"+this["ZIP"]+" "+this["SIDO_NM"]+"  "+this["SIGNGU_NM"]+"  "+this["LGAL_EMD_NM"]+" "+this["RN"]+" "+this["LEGALLI_NM"]+"</option>");
			
			liHtml +="<tr>";
			liHtml +="<td>"+this["ZIP"]+"</td>";
			liHtml +="<td><a href=\"#\" class=\"lnk_blue\"  onclick=\"ufn_postSet('"+this["ZIP"]+"','"+this["SIDO_NM"]+"','"+this["SIGNGU_NM"]+"','"+this["LGAL_EMD_NM"]+"','"+this["RN"]+"');\">";
			liHtml +=this["SIDO_NM"]+"  "+this["SIGNGU_NM"]+"  "+this["LGAL_EMD_NM"]+" "+this["RN"];
			liHtml +="</a></td>";
			liHtml +="</tr>";
			
			
		});
		$("#loading").hide();
		$("#fulladdr").html(liHtml);
		
		
	}
</script> 

<!-- [D}�˾� ������ 600*633px -->
<div id="pop_wrap" style="width:600px">
	<div id="pop_header">
		<h1>�����ȣ ã��</h1>
	</div>
	<div id="pop_content">
		<ul class="lst_tab mb25">
		<li class="on"><span></span>���θ��ּ� ã��</li>
		</ul>
		<form id="form" name="form" method="post">
		<input type="hidden" id="gubun" name="gubun" value="" />
		<input type="hidden" id="zipGubun" name="zipGubun" value="1" />
		<fieldset>
			<legend>�����ȣ ã��</legend>
			<div class="section20">
				<div class="post_srchbox2 round_box">
					<p>�ش� ��/��, ��/��/��, ��/��/���� �����Ͻ� ��<br>���θ��� �ʼ��� �����Ͻø� ���θ� �˻��� �����մϴ�.</p>
					<ul class="lst_srchroad">
					<li>
						<dl>
						<dt>��/��</dt>
						<dd>
							<div class="selectbox fl mr5">
								<input type="text" id="sido" name="sido" class="input-hidden" value="�λ걤����"><!-- layerView ��ũ Ŭ���� href�� ���ڰ��� hidden ��ǲ value�� �Է� -->
								<div class="selectbtn">
									<p class="select" style="width:148px">
										<a href="#" class="btnLink" title="��/�� ����">
												<span id="cgg" class="txt">
														�λ걤����
												</span>	
											<span class="btn"></span>
										</a>
									</p>
								</div>
								<ul class="layerview layerview_v4" id="SIDO_NM" name="SIDO_NM">

								</ul>
							</div>
							<noscript><p>JavaScript �� ����� �� �����ϴ�.<br />�Ϻ� �������� ���������� �۵����� ���� ���ɼ��� �ֽ��ϴ�.</p></noscript>
						</dd>
						</dl>
					</li>
					<li class="srch_r">
						<dl>
						<dt>��/��/��</dt>
						<dd>
							<div class="selectbox fl mr5" id="sigunguArea">
								<input type="text" id="sigungu" name="sigungu" class="input-hidden" value="������"><!-- layerView ��ũ Ŭ���� href�� ���ڰ��� hidden ��ǲ value�� �Է� -->
								<div class="selectbtn">
									<p class="select" style="width:176px">
										<a href="#" class="btnLink" title="��/��/�� ����">
												<span  id="sigungu_txt" class="txt">
														������
												</span>	
											<span class="btn"></span>
										</a>
									</p>
								</div>
								<ul class="layerview layerview_v4" id="SIGNGU_NM">

								</ul>
							</div>
							<noscript><p>JavaScript �� ����� �� �����ϴ�.<br />�Ϻ� �������� ���������� �۵����� ���� ���ɼ��� �ֽ��ϴ�.</p></noscript>
						</dd>
						</dl>
					</li>
					<li class="mt5">
						<dl>
						<dt>��/��/��</dt>
						<dd>
							<div class="selectbox fl mr5" id="eupArea">
								<input type="text" id="dong" name="dong" class="input-hidden" value=""/><!-- layerView ��ũ Ŭ���� href�� ���ڰ��� hidden ��ǲ value�� �Է� -->
								<div class="selectbtn">
									<p class="select" style="width:148px">
										<a href="#" class="btnLink" title="��/��/�� ����">
												<span class="txt" id="dong_txt">����</span>
											<span class="btn"></span>
										</a>
									</p>
								</div>
								<ul class="layerview layerview_v4" style="height:94px;" id="LGAL_EMD_NM">

								</ul>
							</div>
							<noscript><p>JavaScript �� ����� �� �����ϴ�.<br />�Ϻ� �������� ���������� �۵����� ���� ���ɼ��� �ֽ��ϴ�.</p></noscript>
						</dd>
						</dl>
					</li>
					<li class="srch_r mt5">
						<dl>
						<dt>���θ�</dt>
						<dd>
							<div class="selectbox fl mr3" id="initialArea">
								<input type="text" id="initial" name="initial" class="input-hidden" value="02"><!-- layerView ��ũ Ŭ���� href�� ���ڰ��� hidden ��ǲ value�� �Է� -->
								<div class="selectbtn">
									<p class="select" style="width:57px;">
										<a href="#" class="btnLink" title="�ʼ� ����">
												<span class="txt">
														��
												</span>	
											<span class="btn"></span>
										</a>
									</p>
								</div>
								<ul class="layerview layerview_v4" style="height:94px;" >
										<li style="width:40px"><a href="#01" onclick="ufn_inqInitialSearch('01');">��</a></li>	
										<li style="width:40px"><a href="#02" onclick="ufn_inqInitialSearch('02');">��</a></li>	
										<li style="width:40px"><a href="#03" onclick="ufn_inqInitialSearch('03');">��</a></li>	
										<li style="width:40px"><a href="#04" onclick="ufn_inqInitialSearch('04');">��</a></li>	
										<li style="width:40px"><a href="#05" onclick="ufn_inqInitialSearch('05');">��</a></li>	
										<li style="width:40px"><a href="#06" onclick="ufn_inqInitialSearch('06');">��</a></li>	
										<li style="width:40px"><a href="#07" onclick="ufn_inqInitialSearch('07');">��</a></li>	
										<li style="width:40px"><a href="#08" onclick="ufn_inqInitialSearch('08');">��</a></li>	
										<li style="width:40px"><a href="#09" onclick="ufn_inqInitialSearch('09');">��</a></li>	
										<li style="width:40px"><a href="#10" onclick="ufn_inqInitialSearch('10');">��</a></li>	
										<li style="width:40px"><a href="#11" onclick="ufn_inqInitialSearch('11');">��</a></li>	
										<li style="width:40px"><a href="#12" onclick="ufn_inqInitialSearch('12');">��</a></li>	
										<li style="width:40px"><a href="#13" onclick="ufn_inqInitialSearch('13');">��</a></li>	
										<li style="width:40px"><a href="#14" onclick="ufn_inqInitialSearch('14');">��</a></li>	
								</ul>
							</div>
							<div class="selectbox fl" id="rdnmArea" >
							<input type="text" id="rdnm" name="rdnm" class="input-hidden" value="�������"><!-- layerView ��ũ Ŭ���� href�� ���ڰ��� hidden ��ǲ value�� �Է� -->
								<div class="selectbtn">
									<p class="select" style="width:114px">
										<a href="#" class="btnLink" title="���θ� ����">
												<span class="txt ellipsis" style="width:90%" id="rdnm_txt">
														�������
												</span>	
											<span class="btn"></span>
										</a> 
									</p>
								</div>
								<ul class="layerview layerview_v4" style="height:94px;" id="RN" >				
								</ul>
							</div>
							<noscript><p>JavaScript �� ����� �� �����ϴ�.<br />�Ϻ� �������� ���������� �۵����� ���� ���ɼ��� �ֽ��ϴ�.</p></noscript>
						</dd>
						</dl>
					</li>
					</ul>
					<span class="bg_round rud_lt"></span>
					<span class="bg_round rud_rt"></span>
					<span class="bg_round rud_lb"></span>
					<span class="bg_round rud_rb"></span>
				</div>
				<p class="align_c"><a href="#" onclick="inqListAdr();" class="fron btn_v4 btn_v4_v2 ngb">�˻�<span class="rear btn_rgt_v4"></span></a></p>
				
			</div>
			<div class="table_area" style="height:144px">
				<div class="header_bg"></div>
				<div class="table_con">
					<table class="tbl_scroll_type">
					<caption>�����ȣ �˻� ����Ʈ</caption>
					<colgroup>
					<col style="width:22%">
					<col>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><span style="width:22%">�����ȣ</span></th>
							<th scope="col"><span style="width:78%">�ּ�</span></th>
						</tr>
					</thead>
					<tbody id="fulladdr">

					<tbody>
					</table>
				</div>
			</div>
			<p class="align_c">
				<a href="#" onclick=window.close(); class="fron btn_v1 ngb">�ݱ�<span class="rear btn_rgt_v1"></span></a>
			</p>
		</fieldset>
		</form>
	</div>
	<form name="searchForm" id="searchForm">
		
		<input type="hidden" name="countPerPage" id="countPerPage" value="100"><!--�������� ��°Ǽ�-->
		<input type="hidden" name="currentPage" id="currentPage" value="1"><!-- ���� ������ -->
		<input type="hidden" name="rType" id="rType" value="JSON"><!-- ������ ���� -->
	    <input type="hidden" name="groupBy" id="groupBy" value="Y"><!-- ������ ���� -->
	    <input type="hidden" name="andOr" id="andOr" value="and"><!-- ������ ���� -->
	 </form>
</div>


<div id="loading" style="display:none;position:absolute;width:100%;height:100%;top:50px;left:220px;background-color:#fffff;z-index:5;" >�˻� ���Դϴ�......</div>
<script type="text/javascript">
	$(".selectbox").selectbox({ layerView : ".layerview" });
	$(".selectbox_disable").selectbox({ layerView : ".layerview", disable : false });
</script>
</body>
</html>