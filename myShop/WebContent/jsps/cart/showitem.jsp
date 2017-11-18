<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>showitem.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/cart/showitem.css'/>">
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script src="<c:url value='/js/round.js'/>"></script>
<style type="text/css">
#addr {
	width: 500px;
	height: 32px;
	border: 1px solid #7f9db9;
	padding-left: 10px;
	line-height: 32px;
}
</style>

<script type="text/javascript">
	//计算合计
	$(function() {
		var total = 0;
		$(".subtotal").each(function() {
			total += Number($(this).text());
		});
		$("#total").text(round(total, 2));
	});
	
	function showThis(){
		$("#addr").val("");
	}
	function checkValue(){
		if($("#addr").val()==""){
			alert("请填写收货地址");
			return fasle;
		}else if($("#addr").val().length<11){
			alert("请输入正确的收货地址！！");		
			return false; 
		}else if($("#nameid").val()==""){
			alert("请输入收货人姓名");
		}else if($("#phoneid").val()==""){
				alert("请填写电话号码");
				return fasle;
				}
		else if( !/^1[34578]\d{9}$/.test($("#phoneid").val())){
			alert("请输入正确的手机号");return fasle;
		}else{
		$('#form1').submit();
		}
	}
</script>
</head>

<body>
	<c:choose>
		<c:when test="${empty cartItemList }">嘻嘻~</c:when>
		<c:otherwise>
			<form id="form1" action="<c:url value='/sp/createOrder'/>"
				method="post">
				<input type="hidden" name="cartItemIds" value="${cartItemIds }" /> 
				<table width="95%" align="center" cellpadding="0" cellspacing="0">
					<tr bgcolor="#efeae5">
						<td width="400px" colspan="5"><span style="font-weight: 900;">生成订单</span>
						</td>
					</tr>
					<tr align="center">
						<td width="10%">&nbsp;</td>
						<td width="50%">手机名称</td>
						<td>单价</td>
						<td>数量</td>
						<td>小计</td>
					</tr>


					<c:forEach items="${cartItemList }" var="cartItem">
						<tr align="center">
							<td align="right"><a class="linkImage"
								href="<c:url value='/sp/load?pid=${cartItem.phone.pid }'/>"><img border="0"
									width="54" align="top"
									src="<c:url value='/${cartItem.phone.image_b }'/>" />
							</a></td>
							<td align="left"><a
								href="<c:url value='/sp/load?pid=${cartItem.phone.pid }'/>"><span>${cartItem.phone.pname
										}</span>
							</a></td>
							<td>&yen;${cartItem.phone.currprice }</td>
							<td>${cartItem.quantity }</td>
							<td><span class="price_n">&yen;<span class="subtotal">${cartItem.subtotal
										}</span>
							</span></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" align="right"><span>总计：</span><span
							class="price_t">&yen;<span id="total">${total }</span>
						</span></td>
					</tr>
					<tr>
						<td colspan="5" bgcolor="#efeae5"><span
							style="font-weight: 900">收货地址</span>
						</td>
					</tr>
					<tr>
						<td colspan="6"><input id="addr" type="text" name="address"
							value="地址演示实例 如：湖南省益阳市赫山区湖南城市学院(XX街道XX号)" title="地址演示实例 如：湖南省益阳市赫山区湖南城市学院(XX街道XX号)" onfocus="showThis()"/></td>
					</tr>
					<tr>
						<td width="120px">
							&nbsp;&nbsp;收货人姓名：
						</td>
						<td colspan="5"><input id="nameid" type="text" name="shouname"
							value="" title="请填写准确的收货人姓名" />
						</td>
					</tr>
					<tr>
						<td width="100px">
							&nbsp;电话号码：
						</td>
						<td colspan="5"><input id="phoneid" type="text" name="phonename"
							value="" title="电话示例：18811112222十一位数字" />
						</td>
					</tr>

					<tr>
						<td style="border-top-width: 4px;" colspan="5" align="right">
							<a id="linkSubmit" href="javascript:void(0)" onclick="checkValue()">提交订单</a>
						</td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>
