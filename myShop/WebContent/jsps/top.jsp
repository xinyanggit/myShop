<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function go_index(){
	var hidValue=$("#hidId").val();
	hidValue=hidValue.substring(0,hidValue.indexOf("myShop/")+7);
	parent.location.href=hidValue;
}
	
</script>
<style type="text/css">
	
	#test{animation:change 5s linear 0s infinite;font-size:30px;font-weight:700;}
			@keyframes change{0%   {color:#F7F7F7;}25%{color:#ff0;}50%{color:#f60;}75%{color:#cf0;}100% {color:#f00;}
			}

body {
	margin: 0px;
	color: #ffffff;
}

.QQ{
	margin-left: 1030px;
}

a {
	text-decoration: none;
	color:#F1E1FF;
	font-weight: 900;
}

a:hover {
	text-decoration: underline;
	color:#EE113D;
	font-weight: 900;
}
</style>
</head>

<body style=" background-image:url(../images/beiijng123.jpg); background-repeat :repeat-x" >
	<marquee scrollamount="1" scrolldelay="60"  direction="left" onmouseover="this.stop();" onmouseout="this.start();">
	
	<div id="test" style="height: 50px" >欢迎光临</div></h1>
	</marquee>
	<div style="font-size: 10pt; line-height: 10px; padding-left : 20px; width: 900px" >

		<%-- 根据用户是否登录，显示不同的链接 --%>
		<c:choose>
			<c:when test="${empty sessionScope.sessionUser }">
				<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent"><font size="3px">SHOPPING_GO会员登录</font></a> |&nbsp; 
		  <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent"><font size="3px">SHOPPING_GO会员注册</font></a>
			</c:when>
			<c:otherwise>
		      尊敬的会员：${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/sp/myCart'/>"
					target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/sp/myOrders'/>"
					target="body">我的shopping订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/sp/quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="http://www.hncu.net/" target="_top">联系我们</a>
			</c:otherwise>
		</c:choose>
	</div>
		<input type="hidden" value="<%=request.getRequestURL()%>" id="hidId" />
	<div style="height: 30px" class="QQ">
	<a href="javascript:void(0)" onclick="go_index();" style="text-decoration: none" >去首页</a>
		<img src="../images/qq.png" alt="QQ交谈">
		<a href="tencent://message/?uin=2821234075" style="text-decoration: none" >QQ交谈</a>
	</div>
</body>
</html>
