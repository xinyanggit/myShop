<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <title>实体页</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <script type="text/javascript">
	   $(function(){
      // 初始化轮播
     /*  $(".start-slide").click(function(){
         $("#myCarousel").carousel('cycle');
      });
      // 停止轮播
      $(".pause-slide").click(function(){
         $("#myCarousel").carousel('pause');
      }); */
      // 循环轮播到上一个项目
      $(".prev-slide").click(function(){
         $("#myCarousel").carousel('prev');
      });
      // 循环轮播到下一个项目
      $(".next-slide").click(function(){
         $("#myCarousel").carousel('next');
      });
      // 循环轮播到某个特定的帧 
      $(".slide-one").click(function(){
         $("#myCarousel").carousel(0);
      });
      $(".slide-two").click(function(){
         $("#myCarousel").carousel(1);
      });
      $(".slide-three").click(function(){
         $("#myCarousel").carousel(2);
      });
      $(".slide-four").click(function(){
         $("#myCarousel").carousel(3);
      });
      $("#myCarousel").carousel({
 			 interval: 2000 
		});
   });
   </script>
</head>
<body>

<div id="myCarousel" class="carousel slide">
   <!-- 轮播（Carousel）指标 -->
   <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>
   </ol>   
   <!-- 轮播（Carousel）项目 -->
   <div class="carousel-inner">
      <div class="item active" align="center">
      	 <a href='<c:url value="/sp/findByPcompany?pcompany=苹果"/>'>
       	  <img src="../book_img/ipone2_d.jpg" alt="苹果">
         </a>
      </div>
      <div class="item" align="center">
      	<a href="<c:url value='/sp/findByPcompany?pcompany=huawei' />">
        	 <img src="../book_img/m_1d.jpg" alt="华为">
         </a>
      </div>
      <div class="item" align="center">
      		<a href="<c:url value='/sp/findByPcompany?pcompany=xiaomi' />">
         <img src="../book_img/x_m5_1d.jpg" alt="小米">
         	</a>
      </div>
      <div class="item" align="center"> 
      		<a href="<c:url value='/sp/findByPcompany?pcompany=三星' />">
        		 <img src="../book_img/sa_1d.jpg" alt="三星">
         	</a>
      </div>
      <div class="item" align="center"> 
      		<a href="<c:url value='/sp/findByPcompany?pcompany=魅族 ' />">
        		 <img src="../book_img/m1_d.jpg" alt="魅族手机">
         	</a>
      </div>
      <div class="item" align="center"> 
      		<a href="<c:url value='/sp/findByPcompany?pcompany=OPPO' />">
        		 <img src="../book_img/o_3d.jpg" alt="OPPO手机">
         	</a>
      </div>
   </div>
   <!-- 轮播（Carousel）导航 -->
   <a class="carousel-control left" href="#myCarousel" 
      data-slide="prev">&lsaquo;</a>
   <a class="carousel-control right" href="#myCarousel" 
      data-slide="next">&rsaquo;</a>
         <div style="text-align:center;">
     <!--  <input type="button" class="btn start-slide" value="Start">
      <input type="button" class="btn pause-slide" value="Pause"> -->
      <input type="button" class="btn prev-slide" value="上一张">
      <input type="button" class="btn next-slide" value="下一张">
      <input type="button" class="btn slide-one" value="苹果">
      <input type="button" class="btn slide-two" value="华为">            
      <input type="button" class="btn slide-three" value="小米">
      <input type="button" class="btn slide-four" value="三星">
   </div>
      
	</div> 

</body>
</html>