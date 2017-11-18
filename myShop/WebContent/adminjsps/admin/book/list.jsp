<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>手机分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/list.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
<script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
<script type="text/javascript"> 
	function changeThis(obj){
		$("#netTypeId").val($(obj).attr("title"));
		$("#form1").submit();
	}
	
	function changeThat(obj){
		$("#pcompanyId").val($(obj).attr("title"));
		$("#form2").submit();
	}
</script>
<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/list.js'/>"></script>
  </head>
  
  <body>
<div class="divBook">
<ul>

<c:forEach items="${pb.beanList }" var="phone">
 <li>
  <div class="inner">
    <a class="pic" href="<c:url value='/sp/admin/load?pid=${phone.pid }'/>"><img src="<c:url value='/${phone.image_b }' />" border="0"  height="200px"/></a>
    <p class="price" >${book.image_b }
		<span class="price_n">&yen;${phone.currprice }</span>
		<span class="price_r">&yen;${phone.price }</span>
		(<span class="price_s">${phone.discount }折</span>)
	</p>
	<c:url value="/sp/findByPcompany" var="pcompany">
		<c:param name="pcompany" value="${phone.pcompany }"/>
	</c:url>
	<c:url value="/admin/AdminBookServlet" var="pressUrl">
		<c:param name="method" value="findByPress"/>
		<c:param name="press" value="${book.press }"/>
	</c:url>
		<p>
			<a id="bookname" title="${phone.pname }" href="<c:url value='/sp/admin/load?pid=${phone.pid }'/>">${phone.pname }</a>
		</p>
		<p>
			<span>网络类型：</span>
			<a href="javaScript:void(0);" onclick="changeThis(this)" name='P_zz' title='${phone.netType }'>${phone.netType}</a>
		</p>
		<p class="publishing">
				<span>公司品牌：</span>
				<a href="javaScript:void(0);" onclick="changeThat(this)"    title='${phone.pcompany }'>${phone.pcompany }</a>
		</p>
		<p class="publishing_time">
				<span>上市时间：</span>${phone.publishTime }
		</p>
	
  </div>
 </li>
</c:forEach>



 


</ul>

<form action="<c:url value='/sp/findByNetType'/>"  method="post" id="form1">
			<input type="hidden" name="netType" value=""  id="netTypeId">
	</form>
	<form action=" <c:url value='/sp/findByPcompany'/>"  method="post" id="form2">
			<input type="hidden" name="pcompany" value=""  id="pcompanyId">
	</form>

</div>
<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/jsps/pager/pager.jsp" %>
</div>
  </body>
 
</html>

