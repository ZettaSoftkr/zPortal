<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>ZettaSoft setConfig</TITLE>
<link rel="icon" href="/zPortal/assets/images/favicon.ico">

<link href="/zPortal/jspanel/jquery.jspanel.css" rel="stylesheet">
<link href="/zPortal/assets/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<LINK type="text/css" rel="stylesheet" href="/zPortal/assets/css/jquery-ui.css" />

<script src="/zPortal/assets/js/jquery-2.1.4.min.js"></script>
<SCRIPT type="text/javascript" src="/zPortal/assets/js/jquery-ui/jquery-ui.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/zPortal/assets/js/mobile-detect.min.js"></SCRIPT>
<script src="/zPortal/assets/js/jquery.ui.touch-punch.min.js"></script>

<script src="/zPortal/jspanel/jquery.jspanel.js"></script>
<script src="/zPortal/assets/js/zetta/zSettings.js"></script>


<script type="text/javascript">
	window.onload = function() {		
		var userId = "${userId}";
		if(userId == "") {
			alert("사용자 정보가 확인되지 않았습니다.");
			document.location.href="/zPortal/";
		}else{
			processShow("${userId} 의 환경설정후 이동합니다.", 10000);
			
			loadInfos(userId);
		}
		
	}
	
	
	function loadInfos(userId){
		
		//menu 전체 메뉴-----------------------------------------------------------------------------------------
		$.ajax({
			type : "POST",
			url : "/zPortal/modelManager/load.do",
			dataType : "json",
			success : function(msg) {
				var menuAll = msg;

				//사용자그룹정보-----------------------------------------------------------------------------------------
				$.ajax({
					type : "POST",
					url : "/zPortal/modelManager/load.do",
					dataType : "json",
					success : function(msg) {
						var userGroupAll = msg;
						
						//사용자정보-----------------------------------------------------------------------------------------
						$.ajax({
							type : "POST",
							url : "/zPortal/modelManager/load.do",
							dataType : "json",
							success : function(msg) {
								var users = msg;

								//사용자과거정보-----------------------------------------------------------------------------------------
								$.ajax({
									type : "POST",
									url : "/zPortal/modelManager/load.do",
									dataType : "json",
									success : function(msg) {
										var currentUserInfo = msg;
										
										if(!currentUserInfo.userId){
											getNewInfos(userId, menuAll, userGroupAll, users);  //-----------------------------------------------------new setting---------------
										}
										else{
											getInfos(userId, currentUserInfo, menuAll, userGroupAll, users);  //-----------------------------------------------------setting---------------
										}										
									},
									error : function(request, status, error) {
										alert("userId가 상이합니다.");
									},
									data : {fileName : 'users', objectKey : userId}
								});
								
								
							},
							error : function(request, status, error) {
								alert("users error:" + error);
							},
							data : {fileName : 'system', objectKey : 'users'}
						});
						
					},
					error : function(request, status, error) {
						alert("userGroup error:" + error);
					},
					data : {fileName : 'system', objectKey : 'userGroup'}
				});
			},
			error : function(request, status, error) {
				alert("menu error:" + error);
			},
			data : {fileName : 'system', objectKey : 'menu'}
		});		
		
		
	}
	
	function getInfos(userId, currentUserInfo,  menuAll, userGroupAll, users){
		//사용자 정보를 얻어온다.
		var userInfo = getUserInfo(userId, users, userGroupAll);	
		currentUserInfo.userNm = userInfo.userNm;
		currentUserInfo.orgId = userInfo.orgId;
		
		//1. 해달 그룹을 찾는다.
		var userGroups = findGroups(userInfo.orgId, userGroupAll);
		//2. 그룹에 해당하는 메뉴를 얻는다.
		currentUserInfo.menu = getUserMenu(userGroups, menuAll);
		//증겨찾기 정보는 트리형태로 관리된다.
		//cart는 날짜를 넣어서 관리한다.
		saveUserInfo(userId, currentUserInfo);
	}
	
	function getUserInfo(userId, users, userGroupAll){
		//사용자에서 목찾으면
		for (var index in users){
			if(userId==users[index][1]){
				console.info("found getUserInfo", JSON.stringify(users[index]));
				return {userNm: users[index][2], orgId:users[index][0]};
			}
		}
		//새로 등록한 유저인지 찾는다.
		for (var index in userGroupAll.nodeDataArray){
			if(userGroupAll.nodeDataArray[index].category == "User"  && userId==userGroupAll.nodeDataArray[index].text){
				console.info("found getUserInfo", JSON.stringify(userGroupAll.nodeDataArray[index].text));
				return {userNm: userGroupAll.nodeDataArray[index].text, orgId:getParentGroupKey(userGroupAll.nodeDataArray[index].key, userGroupAll.linkDataArray)};
			}
		}

		return {userNm: userId, userDc: userId};
	}
	
	function getParentGroupKey(key, links){
		for(var index in links){
			if(links[index].from == key) return links[index].to;
		}
		return null;
	}
	
	function findGroups(orgId, userGroupAll){
		var orgTree = new Array();
		orgTree = getParents(orgId, userGroupAll.linkDataArray,  new Array());

		console.info("orgId", orgId, "orgTree", JSON.stringify(orgTree));
		
		var resultGroups = new Array();
		for (var index in orgTree){
			for(var index_d in userGroupAll.nodeDataArray){
				if(userGroupAll.nodeDataArray[index_d].category == "UserGroup" && orgTree[index] == userGroupAll.nodeDataArray[index_d].key){
					resultGroups.push(userGroupAll.nodeDataArray[index_d]);
				}
			}
		}
		return resultGroups;
	}
	
	function getParents(orgId, links, orgTree){
		if(orgId == null) return orgTree; 
		if(orgId == "null") return orgTree;
		for (var index in links){
			if(orgId==links[index].to){
				orgTree.push(orgId);				
			}
			getParents(links.from, links, orgTree);
		}
		return orgTree;
	}
	
	function getUserMenu(userGroups, menuAll){
		
		console.info("userGroups", JSON.stringify(userGroups));
		var keys = new Array();
		for(var index in menuAll.nodeDataArray){
			for(var index_u in userGroups){
				if(menuAll.nodeDataArray[index].category == "UserGroup"){
					console.info("userGroups[index_u]", userGroups[index_u],"menuAll.nodeDataArray[index].text", menuAll.nodeDataArray[index].text);
					if(userGroups[index_u].text == menuAll.nodeDataArray[index].text){
						keys.push(menuAll.nodeDataArray[index].key);
					}
				}
			}
		}		
		
		console.info("menuAll.linkDataArray" , JSON.stringify(menuAll.linkDataArray));
		var nodeKeys = new Array();
		for(var index in keys){
			var ary = getNodeDatas(keys[index], menuAll.linkDataArray, new Array());
			jQuery.merge(nodeKeys, ary);
		}
		nodeKeys.sort();
		
		nodeKeys = jQuery.unique(nodeKeys);			
		console.info("nodeKeys", JSON.stringify(nodeKeys));			
		
		var nodes = new Array();
		for(var index in nodeKeys){
			nodes.push(getNodeData(nodeKeys[index], menuAll.nodeDataArray));
		}
		
		nodes.sort(function(a,b) {
		     if ( a.data.sortSn < b.data.sortSn )
		         return -1;
		       if ( a.data.sortSn > b.data.sortSn )
		         return 1;
		       return 0;
		   });
		
		var links = new Array();
		for(var index in nodes){
			for(var index_l in menuAll.linkDataArray){
				if(nodes[index].key.toString() == menuAll.linkDataArray[index_l].to.toString()){
					links.push(menuAll.linkDataArray[index_l]);
				}
			}
		}
		
		//마지막으로 결과를 넣어준다.
		menuAll.nodeDataArray = nodes;
		menuAll.linkDataArray = links;
		
		return menuAll;
	}
	
	function getNodeDatas(key, linkDataArray, nodes){
		if(!key) return nodes; 
		if(key == "null") return nodes;
		nodes.push(key.toString());
		for (var index in linkDataArray){
			if(key.toString()==linkDataArray[index].from.toString()){
				getNodeDatas(linkDataArray[index].to, linkDataArray, nodes);
			}			
		}
		return nodes;
	}
	
	function getNodeData(key, nodeDataArray){
		for (var index in nodeDataArray){
			if(key.toString() == nodeDataArray[index].key.toString()){
				return nodeDataArray[index];
			}
		}
		return null;
	}
	
	//사용자 정보가 없을때 새로 생성 -----------------------------------------------------------------------------------------
	function getNewInfos(userId, menuAll, userGroupAll, users){
 		//사용자 정보 생성.
 		var currentUserInfo = {
 				  "userId": userId,
 				  "userNm": "",
 				  "userDc": "",
 				  "orgId": "",
 				  "orgNm": "",
 				  "menu": null,
 				  "cart": [],
 				  "favorite": [],
 				  "config": null
 				};
 		
 		var userInfo = getUserInfo(userId, users, userGroupAll);	
		currentUserInfo.userNm = userInfo.userNm;
		currentUserInfo.orgId = userInfo.orgId;
		
		//1. 해달 그룹을 찾는다.
		var userGroups = findGroups(userInfo.orgId, userGroupAll);
		//2. 그룹에 해당하는 메뉴를 얻는다.
		currentUserInfo.menu = getUserMenu(userGroups, menuAll);
		//증겨찾기 정보는 트리형태로 관리된다.
		//cart는 날짜를 넣어서 관리한다.
		var config = {editMode:false};
		currentUserInfo.config = config;
		saveUserInfo(userId, currentUserInfo);
	}
	
	function saveUserInfo(userId, config){
		
		var sendInfo = {
			fileName : "users",
			objectKey : userId,
			content : JSON.stringify(config)
 		};

		jQuery.ajax({
			type : "POST",
			url : "/zPortal/modelManager/save.do",
			dataType : "json",
			success : function(msg) {
				if (msg) {
					document.location.href = "/zPortal/zVM/zViewer.html";
				} else {
					alert("환경셋팅에 실패했습니다.");
					window.close();
				}
			},
			error : function(request, status, error) {
				alert("request:" + request.responseText + "\n error:" + error);
			},
			data : sendInfo
		});		
	}
	
</script>

</head>
<body oncontextmenu="return false;">
	<p></p>
</body>
</html>