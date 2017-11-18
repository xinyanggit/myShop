<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>手机详细</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		
	
	</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/pager/pager.css'/>" />
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<style type="text/css">
		#test{animation:change 10s linear 0s infinite;font-size:30px;font-weight:700;}
			@keyframes change{0% {color:#1739C7;}20%{color:#88A0FF;}40%{color:#F6F2FB;}60%{color:#cf0;}100%{color:#f00;}
			}
			
			.alignleft{
				align:"left";
			}
			.alignright{
				align:"right";
			}
			#tableid{
				font-size: 12px;
			}
			
			.bottom1{
				margin-left: 200px;
			}
			.tishi{
				margin-left: 80px;
				margin-top: 5px;
			}
		
			
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/book/desc.css'/>">
<script src="<c:url value='/jsps/js/book/desc.js'/>"></script>
<script type="text/javascript">
</script>
</head>

<body>
	<div class="divBookName" id="test">${phone.pname }</div>
	<div style="float: left ;width: 100%">
		<img align="top" src="<c:url value='/${phone.image_w }'/>"
			class="img_image_w" style="width: 400px"/>
		<div class="divBookDesc">
			<ul>
				<li>商品编号：${phone.pid }</li>
				<li><font size="4">SHOPPING_GO价格：</font><span class="price_n">&yen;${phone.currprice }</span>
				</li>
				<li>定价：<span class="spanPrice">&yen;${phone.price }</span> 折扣：<span
					style="color: #c30;">${phone.discount }</span>折</li>
			</ul>
			<hr class="hr1" />
			<table style="font-size: 12pt;	margin-top: 15px;
			margin-left: 50px;
			width: 500px;">
				<tr>
					<td colspan="3"> 产品公司：${phone.pcompany } </td>
				</tr>
				<tr>
					<td colspan="3">上市时间：${phone.publishTime }</td>
				</tr>
				<tr>
					<td>版本类型：${phone.pversion }</td>
					<td>机身颜色：${phone.color }</td>
					<td>套餐类型：${phone.taocan }</td>
				</tr>
			</table>
			<div class="divForm">
				<form id="form1" action="<c:url value='/sp/add'/>"
					method="post">
					 <input
						type="hidden" name="pid" value="${phone.pid }" /> 我要买：<input
						id="cnt" style="width: 40px;text-align: center;" type="text"
						name="quantity" value="1" />件
				</form>
				<a id="btn" href="javascript:$('#form1').submit();"></a>
			</div>
			<div class="tishi">
				温馨提示：<br/>
					&nbsp;1，本商品支持7天无理由退货<br/>
					&nbsp;2，有问题可以随时联系客服	
			</div>	
			

		</div>
	</div>
	<div style="width: 100;height: 120px">
		&nbsp;<br/>
			<div>&nbsp;</div>
	</div>
	<hr color="#CCC8BC" width="100%">
	
		<div style="width: 100%;font-size: 13px">
			<div style="margin-left: 10px;margin-top: 10px"><font size="3px"><b>商品详情</b></font></div>
			<hr color="#CCC8BC" width="100%">
			<div>
				<table width="100%" id="tableid">
						<tr >
							<th colspan="6" align="left">
						&nbsp;&nbsp;<font size="2.5">产品参数：</font>
							</th>
						</tr>
					<tr >
						<td width="100px" align="right">证书编号：</td>
						<td align="left" width="110px">2015011606799885</td>
						<td  align="right">证书状态：</td>
						<td align="left" ><a href="https://baike.taobao.com/view.htm?id=11605460" target="_blank">中国强制性产品认证（CCC）编号</a>(有效)</td>
						<td  align="right"> 产品名称：</td>
						<td align="left">TD-LTE 数字移动电话机</td>
					</tr>
					<tr >
						<td width="100px" align="right">手机名称：</td>
						<td align="left" width="110px">${phone.pname}</td>
						<td align="right">CPU型号:</td>
						<td align="left" >其他</td>
						<td  align="right"> 运行内存RAM：</td>
						<td align="left">不详</td>
					</tr>
					<tr><td colspan="6"></td></tr>
					<tr>
						<th colspan="6" align="left">&nbsp;&nbsp;套餐介绍</th>
					</tr>
					<tr>
						<td align="center" colspan="2">基本套餐（官方标配）：</td>
						<td align="left" colspan="4">主机一台 + 充电头一个 + 数据线一根 + 保修卡一个 + 快速入门指南一个</td>
					</tr>
					<tr>
						<td align="center" colspan="2">套餐一：</td>
						<td align="left" colspan="4">主机一台 + 充电头一个 + 数据线一根  + 保修卡一个 + 快速入门指南一个 + 耳机一件 + 透明保护壳一件</td>
					</tr>
					<tr>
						<td align="center" colspan="2">套餐二：</td>
						<td align="left" colspan="4">主机一台 + 充电头一个 + 数据线一根  + 保修卡一个 + 快速入门指南一个 + 耳机一件 + 透明保护壳一件 + 充电宝（10000mh） + 延长1年保修时间</td>
					</tr>
				</table>
			</div>
			<br/>
			<br/>
			<br/>
		</div>
		
		
		
		
			<hr width="100%" color="#FFF8D7">
				<div style="margin-left: 330px">
					关于我们 | 联系我们 | 媒体报道 | 品牌授权 | 商务合作 <br/>
				</div>
				<div style="margin-left: 290px">	
					ICP备案号： Copyright © 2013 All Rights Reserved 城院 版权所有,违者必究<br/>
				</div>	
				<div class="bottom1">
				<img src="../images/111.png"><img src="../images/222.png"><img src="../images/333.jpg"><img src="../images/444.png">
<img src="../images/555.png"><img src="../images/666.png">
		</div>
		
</body>
</html>
