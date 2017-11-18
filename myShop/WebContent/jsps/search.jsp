<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按手机名称查询</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function(){
	
		$("#imgId").hover(
			function(){
				$("#imgId").attr("src","../images/sousuo3.jpg");
			},
			
			function(){
				$("#imgId").attr("src","../images/sousuo2.jpg");
			}
		);
	});
</script>
	
	
<style type="text/css">
body {
	margin-top: 5px;
	margin-bottom: 0px;
	margin-left: 200px;
	color: #404040;
}

input {
	width: 300px;
	height: 30px;
	border: 1px solid #2d59a7;
	margin: 0px;
}

a {
	text-transform: none;
	text-decoration: none;
	border-width: 0px;
}

a:hover {
	text-decoration: underline;
	border-width: 0px;
}

span {
	margin: 0px;
}

</style>
</head>

<body>
	<form action="<c:url value='/sp/findByPname'/>" method="post" target="body"
		id="form1">
		<input type="text" name="pname" /> <span> 
			<a
			href="javascript:document.getElementById('form1').submit();" style="text-decoration: none">
			<img id="imgId"	align="top" border="0" src="../images/sousuo2.jpg"  height="30"/>
		</a>
		<a href="<c:url value='/jsps/gj.jsp'/>"
			style="font-size: 12pt; color: #37caca;" target="body">多条件搜索</a> </span>
	</form>
</body>
</html>
