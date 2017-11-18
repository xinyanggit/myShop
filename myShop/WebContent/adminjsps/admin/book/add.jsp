<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/add.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
<script type="text/javascript">
$(function () {
	$("#publishTime").datepick({dateFormat:"yy-mm-dd"});
	
	$("#btn").addClass("btn1");
	$("#btn").hover(
		function() {
			$("#btn").removeClass("btn1");
			$("#btn").addClass("btn2");
		},
		function() {
			$("#btn").removeClass("btn2");
			$("#btn").addClass("btn1");
		}
	);
	
	$("#btn").click(function() {
	var pname = $("#pname").val();
	var currPrice = $("#currprice").val();
	var price = $("#price").val();
	var discount = $("#discount").val();
	var pversion = $("#pversion").val();
	var pcompany = $("#pcompany").val();
	var publishtime = $("#publishTime").val();
	var netType = $("#netType").val();
	var color = $("#color").val();
	var taocan = $("#taocan").val();
	var image_w = $("#image_w").val();
	var image_b = $("#image_b").val();
	
	var pid = $("#pid").val();
	var cid = $("#cid").val();
	
	if(!pname || !image_w || !image_b || !currPrice || !price || !discount || !pversion || !pcompany ||  !publishtime || !netType || !color || !taocan || !pid || !cid) {
		alert("商品名、当前价、定价、折扣、版本、所有公司、上市时间、网络类型、颜色、套餐、1级分类、2级分类 一个都不能为空！");
		return false;
	}
	
	if(isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
		alert("当前价、定价、折扣必须是合法小数！");
		return false;
	}
		$("#form").submit();
	});
});

function loadChildren() {
	/*
	1. 获取pid
	2. 发出异步请求，功能之：
	  3. 得到一个数组
	  4. 获取cid元素(<select>)，把内部的<option>全部删除
	  5. 添加一个头（<option>请选择2级分类</option>）
	  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	*/
	// 1. 获取pid
	var pid = $("#pid").val();
	// 2. 发送异步请求
	$.ajax({
		async:true,
		cache:false,
		url:"/myShop/sp/admin/ajaxFindChildren",
		data:{parentid:pid},
		type:"POST",
		dataType:"json",
		success:function(arr) {
			// 3. 得到cid，删除它的内容
			$("#cid").empty();//删除元素的子元素
			$("#cid").append($("<option>====请选择2级分类====</option>"));//4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
				$("#cid").append(option);
			}
		}
	});
}

	var n=0;
	function clearThis(){
		if(n==0){
		$("#pname").val("");
		n=1;
		}
	}
</script>
  </head>
  
  <body>
  <div>
   <p style="font-weight: 900; color: red;">${msg }</p>
   <form action="<c:url value='/AdminaddPhoneServlet'/>" enctype="multipart/form-data" method="post" id="form">
    <div>
	    <ul>
	    	<li>商品名称：　<input id="pname" type="text" name="pname" value="Apple iPhone 6s (A1700) 64G 玫瑰金色 移动联通电信4G手机" style="width:500px;" onfocus="clearThis()"/></li>
	    	<li>大图：　<input id="image_w" type="file" name="image_w"/></li>
	    	<li>小图：　<input id="image_b" type="file" name="image_b"/></li>
	    	<li>当前价：<input id="currprice" type="text" name="currprice" value="40.7" style="width:50px;"/></li>
	    	<li>定价：　<input id="price" type="text" name="price" value="4999.0" style="width:50px;"/>
	    	折扣：<input id="discount" type="text" name="discount" value="6.9" style="width:30px;"/>折</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table>
			<tr>
				<td colspan="3">
					版本：　　<input type="text" id="pversion" name="pversion" value="非合约机" style="width:150px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					所属公司：　<input type="text" name="pcompany" id="pcompany" value="苹果公司" style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">上市时间：<input type="text" id="publishTime" name="publishTime" value="2016-6-1" style="width:100px;"/></td>
			</tr>
			<tr>
				<td >网络类型：　<input id="netType" type="text" name="netType" style="width:100px;" value="全网通" style="width:40px;"/></td>
				<td width="250px">颜色：　　<input id="color" type="text" name="color" style="width:100px;" value="土豪金" style="width:80px;"/></td>
			</tr>
			<tr>
				<td >套餐：<input  type="text" id="taocan" style="width:100px;"  name="taocan" value="基本套餐" style="width:100px;"/></td>
			</tr>
			<tr>
				<td>
					一级分类：<select name="parentid" id="pid" onchange="loadChildren()">
						<option value="">====请选择1级分类====</option>
								<c:forEach items="${parents }" var="parent">
			    		<option value="${parent.cid }">${parent.cname }</option>
							</c:forEach>

					</select>
				</td>
				<td>
						二级分类：<select name="cid" id="cid">
						<option value="">====请选择2级分类====</option>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<input type="button" id="btn" value="新货上架">
<!-- 					class="btn"  -->
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
   </form>
  </div>

  </body>
</html>
