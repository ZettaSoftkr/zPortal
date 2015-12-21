<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="boardarea">

	<section class="boardlist">
		<h3>
			<span class="textt">지표등록</span>
		</h3>
		<div class="searcharea">
			<input id="fileupload" type="file" name="files[]" multiple>

		</div>

		<div id="fileresult" class="files"></div>
		<button type="button" id="getIndicator" class="btn btn-primary btn-xs">화면 출력</button>

		<form name="excelForm" id="excelForm" method="post">
			<input type="hidden" name="fileName" id="fileName" value=""> <input type="hidden" name="filePath" id="filePath" value=""> <input
				type="hidden" name="fileTempNm" id="fileTempNm" value=""> <input type="hidden" name="sheetNum" id="sheetNum" value="1">

			<div class="col-md-10 col-md-offset-10"></div>
			<table>


			</table>


		</form>
	</section>


</div>


<script type="text/javascript">
	$(function() {

		$('#progress').hide();

		var url = '/ts/fileUpload/save.do'; // 사용

		$('#fileupload').fileupload({

			url : url,

			dataType : 'json',

			done : function(e, data) {

				$.each(data.result.files, function(index, file) {

					$('<p/>').text(file.name).appendTo('#files');

				});

			},

			progressall : function(e, data) {

				var progress = parseInt(data.loaded / data.total * 100, 10);
				$('#progress').show();
				$('#progress .progress-bar').css(

				'width',

				progress + '%'

				);

			},
			success : function(data, textStatus, jqXHR) {

				//alert(JSON.stringify(data));

				var fileInfo = "";
				
				fileInfo += " 파일이름 :[ " + data.file_name + " ]";
				fileInfo += " 파일사이즈 :[ " + data.file_size + " ]";
				fileInfo += " 파일경로 :[ " + data.fileUpload['filePath'] + "]";
				fileInfo += " 임시파일명:[ " + data.fileUpload['tempFileNmd'] + " ]";

				$("#fileresult").html(fileInfo);

				$("#fileName").val(data.file_name)
				$("#filePath").val(data.fileUpload['filePath'])
				$("#fileTempNm").val(data.fileUpload['tempFileNmd'])

				$('#progress').hide();
				$('#progress .progress-bar').css('width', '0%');

			}

		}).prop('disabled', !$.support.fileInput)

		.parent().addClass($.support.fileInput ? undefined : 'disabled');

	});

	$("#getIndicator").click(function() {

		var postData = $("#excelForm").serializeArray();
		//	alert(postData[2].value);
	
		var url = "/ts/fileLoad/getExcelData.do";
		alert(url);
		$.ajax({
			url : url,
			type : "post",
			data : postData,
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {

				alert("성공");

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("실패 ");
			}
		});

	});
</script>
