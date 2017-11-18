<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>phone_desc.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/desc.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/book/desc.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/desc.js'/>"></script>

<script type="text/javascript">

$(function() {
	$("#box").attr("checked", false);
	$("#formDiv").css("display", "none");
	$("#show").css("display", "");	
	
	// 操作和显示切换
	$("#box").click(function() {
		if($(this).attr("checked")) {
			$("#show").css("display", "none");
			$("#formDiv").css("display", "");
		} else {
			$("#formDiv").css("display", "none");
			$("#show").css("display", "");		
		}
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
		data:{ parentid:pid},
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

/*
 * 点击编辑按钮时执行本函数
 */
function editForm() {
// 	$("#method").val("edit");
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
	var pid = $("#parentid").val();
	var cid = $("#cid").val();
	
	if(!pname || !currPrice || !price || !discount || !pversion || !pcompany ||  !publishtime || !netType || !color || !taocan || !pid || !cid) {
		alert("商品名、当前价、定价、折扣、版本、所有公司、上市时间、网络类型、颜色、套餐、1级分类、2级分类 一个都不能为空！");
		return false;
	}
	
	if(isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
		alert("当前价、定价、折扣必须是合法小数！");
		return false;
	}


	$("#form").attr("action","<c:url value='/sp/admin/edit'/>");
	$("#form").submit();
}
/*
 * 点击删除按钮时执行本函数
 */
 function deleteForm() {
// 	$("#method").val("delete");
		$("#form").attr("action","<c:url value='/sp/admin/delete'/>");
	$("#form").submit();	
}

</script>
  </head>
  
  <body>
    <input type="checkbox" id="box"><label for="box">编辑或删除</label>
    <br/>
    <br/>
  <div id="show">
    <div class="sm">${phone.pname }</div>
    <img align="top" src="<c:url value='/${phone.image_w }'/>" class="tp"/>
    <div id="book" style="float:left;">
	    <ul>
	    	<li>商品编号：${phone.pid }</li>
	    	<li><font size="4">SHOPPING_GO价格：</font><span class="price_n">&yen;${phone.currprice }</span>
				</li>
	    	<li>定价：<span style="text-decoration:line-through;">&yen;${phone.price }</span>　折扣：<span style="color: #c30;">${phone.discount }</span>折</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table>
				<tr>
					<td colspan="3">产品公司：${phone.pcompany } </td>
				</tr>
				<tr>
					<td colspan="3">上市时间：${phone.publishTime }</td>
				</tr>
				<tr>
					<td>版本类型：${phone.pversion }</td>
				</tr>
				<tr>
					<td>颜色：${phone.color }</td>
				</tr>
				<tr>
					<td>套餐类型：${phone.taocan }</td>
				</tr>
			</table>
	</div>
  </div>
  
  <div id='formDiv'>
   <div class="sm">&nbsp;</div>
   <form action="" method="post" id="form">
   	<input type="hidden" name="pid" value="${phone.pid }"/>
    <img align="top" src="<c:url value='/${phone.image_w }'/>" class="tp"/>
    <div style="float:left;">
	    <ul>
	    	<li>商品编号：${phone.pid }</li>
	    	<li>手机名称：　<input id="pname" type="text" name="pname" value="${phone.pname }" style="width:500px;"/></li>
	    	<li>当前价：<input id="currprice" type="text" name="currprice" value="${phone.currprice }" style="width:50px;"/></li>
	    	<li>定价：　<input id="price" type="text" name="price" value="${phone.price }" style="width:50px;"/>
	    	折扣：<input id="discount" type="text" name="discount" value="${phone.discount }" style="width:30px;"/>折</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table class="tab">
			<tr>
				<td colspan="3">
					版本：<input id="pversion" type="text" name="pversion" value="${phone.pversion }" style="width:150px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					所属公司：<input id="pcompany" type="text" name="pcompany" value="${phone.pcompany }" style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">上市时间：<input id="publishTime" type="text" name="publishTime" value="${phone.publishTime }" style="width:100px;"/></td>
			</tr>
			<tr>
				<td >网络类型：　<input id="netType" type="text" name="netType" style="width:100px;" value="${phone.netType }" style="width:40px;"/></td>
				<td width="250px">颜色：　　<input id="color" type="text" name="color" style="width:100px;" value="${phone.color }" style="width:80px;"/></td>
			</tr>
			<tr>
				<td >套餐：<input  type="text" id="taocan" style="width:100px;"  name="taocan" value="${phone.taocan }" style="width:100px;"/></td>
			</tr>
			<tr>
				<td>
					一级分类：<select name="parentid" id="parentid" onchange="loadChildren()">
							<option value="">==请选择1级分类==</option>
									<c:forEach items="${parents }" var="parent">
							  <option value="${parent.cid }" <c:if test="${phone.category.parent.cid eq parent.cid }">selected="selected"</c:if>>${parent.cname }</option>
									</c:forEach>
							</select>
				</td>
				<td>
					二级分类：<select name="cid" id="cid">
						<option value="">==请选择2级分类==</option>
							<c:forEach items="${children }" var="child">
						<option value="${child.cid }" <c:if test="${phone.category.cid eq child.cid }">selected="selected"</c:if>>${child.cname }</option>
							</c:forEach>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<input onclick="editForm()" type="button" name="method" id="editBtn" value="编　辑">
					<input onclick="deleteForm()" type="button" name="method" id="delBtn"  value="删　　除">
<!-- 					class="btn" -->
				</td>
				<td></td>
			</tr>
		</table>
	</div>
   </form>
  </div>

  </body>
</html>
