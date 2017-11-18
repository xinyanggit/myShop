<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录</title>

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
	href="<c:url value='/jsps/css/user/login.css'/>" /> 
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jsps/js/user/login.js'/>"></script>


<script type="text/javascript">
	/* $(function() {/*Map<String(Cookie名称),Cookie(Cookie本身)>
		// 获取cookie中的用户名
		var loginname = window.decodeURI("${cookie.loginname.value}");
		if("${requestScope.user.loginname}") {
			loginname = "${requestScope.user.loginname}";
		}
		$("#loginname").val(loginname);
	});   */
	
</script>
<script type="text/javascript">
		function change(){
		var timedata=(new Date()).getTime();
			$("#vCode").attr("src","<c:url value='/VerfyCode' />?"+timedata);
		}
</script>
</head>

<body>
	<div class="main">
		<%-- <div>
		<c:url value="/"></c:url>
			<img src="<c:url value='/images/shouji.jpg'/>" />
		</div> --%>
		<div>
		 <div class="imageDiv">
				<a href='<c:url value="/index.jsp"/>' >
					<img class="img" src="<c:url value='/images/iphone2.jpg'/>" width="370px" height="400px"/>
				</a>
			</div> 
		<div class="login1">
				<div class="login2">
					<div class="loginTopDiv">
						<span class="loginTop">账号登录</span> <span> <a
							href="<c:url value='/jsps/user/regist.jsp'/>" class="registBtn"></a>
						</span>
					</div>
					<div>
						<form target="_top" action="<c:url value='/sp/login'/>"
							method="post" id="loginForm">
							<table>
								<tr>
									<td width="50"></td>
									<td><label class="error" id="msg">${msg }</label>
									</td>
								</tr>
								<tr>
									<td width="50">用户名</td>
									<td><input class="input" type="text" name="loginname"
										id="loginname" />
									</td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
									<td><label id="loginnameError" class="error"></label>
									</td>
								</tr>
								<tr>
									<td>密 码</td>
									<td><input class="input" type="password" name="loginpass"
										id="loginpass" value="${user.loginpass }" />
									</td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
									<td><label id="loginpassError" class="error"></label>
									</td>
								</tr>
								<tr>
									<td>验证码</td>
									<td>
									<input class="input yzm" type="text" name="verifyCode"
										id="verifyCode" value="${user.verifyCode }" /> <img id="vCode"
										src="<c:url value='/VerfyCode'/>"  onclick="change()"/> 
										<a  href="javascript:change()">换一张</a></td>
								</tr>
								<tr>
									<td height="20px">&nbsp;</td>
									<td><label id="verifyCodeError" class="error"></label>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td align="left"><input type="image" id="submit"
										src="<c:url value='/images/an_06.gif'/>" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href='<c:url value="/jsps/user/regist.jsp"/>'><img alt="注册" src="<c:url value='/images/dl_08.gif'/>">
										</a>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>
