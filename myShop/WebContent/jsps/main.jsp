<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>main</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/main.css'/>">
	<style type="text/css">
		a.hover{
			color: #FF2D2D;
			text-decoration: none;
		}
		
		a {
	text-decoration: none;
	color: #018BD3;
}
	#test{animation:change 10s linear 0s infinite;font-size:17px;font-weight:70;}
			@keyframes change{
				0% {color:#EA0000;}
				20%{color: #FF5151;}
				35%{color:#FFD2D2;}
				50%{color:#53FF53;}
				65%{color:#00A600;}
				80%{color:#B9B9FF;}
				100%{color:#0000E3;}
			}

	.bg{
		font: #921AFF;
	}
	</style>
</head>

<body >
	
									<!-- 左侧侧动态提示 -->
	<div id="hint">
			<div class="bg" id="loginNote">
					 <marquee scrollamount="1" scrolldelay="60"  direction="up" onmouseover="this.stop();" onmouseout="this.start();" >
					   <p>
					   	 <strong><span id="test">欢迎光临</span></strong>
					   </p>
						<p>1，客官，等您好久了。。本网站主要是做<span style="color: red;font: 13px bold red">手机销售</span>方面业务，客观可以根据你的喜爱选择需要的手机。</p>
						<p>2，在购物过程中，若您有疑问，可以点击右侧<b>QQ交谈</b>与我们的客服人员取得联系，解决您在购物过程中的困难。</p>
					 </marquee>
		</div>
	</div>
	<table class="table" align="center">
		<tr class="trTop">
			<td colspan="2" class="tdTop" style="width:100px;height;100px;"><iframe frameborder="0" 
					src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe></td>
		</tr>
		<tr>
			<td class="tdLeft" rowspan="2"><iframe frameborder="0"
					src="<c:url value='/sp/findAll'/>" name="left"></iframe>
			</td>
			<td class="tdSearch" style="border-bottom-width: 0px;"><iframe
					frameborder="0" src="<c:url value='/jsps/search.jsp'/>"
					name="search"></iframe>
			</td>
		</tr>
		<tr>
			<td style="border-top-width: 0px;">
			<iframe frameborder="0"
					src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe></td>
		</tr>
	</table>
	<ins 
		style="border: 0px none #921AFF; 
		width: 80px; height: 30px; 
		position: fixed; 
		display: block !important; 
		top: 240px; 
		right: 5px; 
		background: #E6CAFF"
		>
		<a href="tencent://message/?uin=2821234075"> 联系客服（QQ）</a>
	</ins>

</body>
</html>
