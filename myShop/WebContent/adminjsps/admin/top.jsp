<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    <base target="body">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {font-size: 13pt;}
	a {color: #550033;}
</style>
  </head>
  
  <body style="background: rgb(95,158,160);color:#4E8F3E;">
<h1 style="text-align: center; line-height: 30px;">SHOPPING_GO手机销售系统后台管理</h1>
<div style="line-height: 10px;">
	<span>管理员：${sessionScope.admin.adminname }</span>
	<a target="_top" href="<c:url value='/adminjsps/login.jsp'/>">退出</a>
	<span style="padding-left:50px;">
		<a href="<c:url value='/sp/admin/findAll'/>">分类管理</a>
		<a href="<c:url value='/adminjsps/admin/book/main.jsp'/>">商品管理</a>
		<a href="<c:url value='/sp/admin/findAll_order'/>">订单管理</a>
	</span>
</div>
  </body>
</html>
