<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>组合查询</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
table {
	color: #404040;
	font-size: 10pt;
}
</style>
</head>

<body>
	<form action="<c:url value='/sp/findByCombination'/>" method="post">
		<table align="center">
			<tr>
				<td>名称：</td>
				<td><input type="text" name="pname" />
				</td>
			</tr>
			<tr>
				<td>公司：</td>
				<td><input type="text" name="pcompany" title="直接输入公司名称即可"/>
				</td>
			</tr>
			<tr>
				<td>颜色：</td>
				<td><input type="text" name="color" title="直接输入喜欢的颜色即可" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" value="搜　　索" />
					 <input type="reset" value="重新填写" />
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>
