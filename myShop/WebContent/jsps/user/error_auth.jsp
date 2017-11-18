<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error_auth.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{background:url('/myShop/images/wunai.jpg') repeat-x;}
	</style>
	
		<script type="text/javascript">  
		    onload=function(){  
		        setInterval(go, 1000);  
		    };  
		    var x=3; //利用了全局变量来执行  
		    function go(){  
		    x--;  
		        if(x>0){  
		        document.getElementById("sp").innerHTML=x;  //每次设置的x的值都不一样了。  
		        }else{  
		       parent.location.href=${path}+":8080/myShop/jsps/user/login.jsp";  
		        }  
		    }  
</script>  

  </head>
  
  <body >
  		您还没有登录，<span id="sp">3</span>秒之后跳转到登陆界面
<!--   		<a href='' >点击立即去登录 </a> -->
  </body>
</html>
