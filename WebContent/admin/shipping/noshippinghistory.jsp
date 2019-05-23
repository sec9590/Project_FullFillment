<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Yellow Container : noshippinghistory</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">

  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
  <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
  <!--===============================================================================================-->
 	<style>
 		h4 { margin-left:15px; margin-bottom:20px; }
 		th, td { text-align:center;}
 		button{
 		 	margin-left:15px; margin-bottom:20px;
 			background-color:#fbb710;
 			color:#fff;
 			width:80px;
 			height:30px;
 		}
 		button:hover{
 			background-color:#333333;
 		}
 		.myButton {
	background-color:#fbb810;
	-moz-border-radius:42px;
	-webkit-border-radius:42px;
	border-radius:42px;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight : bold;
	padding:10px 20px;
	text-decoration:none;
}
.myButton:hover {
	background-color:#fbb810;
}
.myButton:active {
	position:relative;
	top:1px;
}
 	</style>
</head>

<body>

<div class="main-content-wrapper d-flex clearfix">
		<!-- Mobile Nav (max width 767px)-->
		<div class="mobile-nav">
			<!-- Navbar Brand -->
			<div class="amado-navbar-brand">
				<a href="OrdersProcServlet?action=index"><img src="img/core-img/logo.png" alt=""></a>
				<div style="text-align: center">
					<%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
				</div>
			</div>
			<!-- Navbar Toggler -->
		</div>

		<!-- Header Area Start -->
		<header class="header-area clearfix"> <!-- Close Icon -->
		<div class="nav-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<!-- Logo -->
		<div class="logo">
			<a href="OrdersProcServlet?action=index"><img src="img/core-img/logo.png" alt=""></a>
			<div style="text-align: center">
				<%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
			</div>
		</div>
		<!-- Amado Nav --> <nav class="amado-nav">
		<li><a href="OrdersProcServlet?action=index">HOME</a></li>
        	<li><a href="memberProcServlet?action=member&page=1">회원목록</a></li>	
		<li><a href="ProductProcServlet?action=product_list&page=1">제품목록</a></li>
            <li><a href="OrdersProcServlet?action=productlist">재고내역</a></li>
          <li><a href="OrdersProcServlet?action=order">주문하기</a></li>
            <li><a href="OrdersProcServlet?action=orderAll&page=1">주문내역</a></li>
            <li><a href="OrdersProcServlet?action=orderhistoryall">발주내역</a></li>
            <li><a href="WaybillProcServlet?action=waybilllist&page=1">운송내역</a></li>
            <li class="active"><a href="WaybillProcServlet?action=nowaybilllist">미운송내역</a></li>
            <li><a href="OrdersProcServlet?action=grossprofit">매출 총 이익</a></li>
        </nav>
    </header>
    <!-- Header Area End -->

	<div class="amado_product_area section-padding-100">
	    <div class="row">
	     <div>
	    	<h4>미운송내역</h4>
	    </div>
	    
	        <!-- Single Product Area -->
	        <div class="col-12 col-sm-6 col-md-12 col-xl-15">
	            <div class="single-product-wrapper">
	                  <table class="table table-hover">
	                      <thead>
	                          <tr>
	                              <th>주문번호</th>
	                              <th>수취인</th>
	                              <th>전화번호</th>
	                              <th>주소</th>
	                              <th>주문시간</th>
	                          </tr>
	                      </thead>
                          <tbody>
                          	<c:set var="nwlist" value="${requestScope.nwayList}" />
							<c:forEach var="nway" items="${nwlist}">
								<tr height="30">
									<td><a
										href="OrdersProcServlet?action=detail&name=${nway.o_name}&id=${nway.o_id}">${nway.o_id}</a>
									</td>
									<td>${nway.o_name}</td>
									<td>${nway.o_tel}</td>
									<td>${nway.o_address}</td>
									<td>${nway.o_time}</td>
								</tr>
							</c:forEach>
                    	</tbody>
                	</table>    
                	<%
							if(request.getAttribute("nwayList") != null){
							%>						
							<div align=center> 	
                    			<a href="OrdersProcServlet?action=nowaybill" class="myButton">운송신청</a>
                    		</div> 
                    		<%
                    			}
							%>					
	            </div>
	    	</div>
		</div>
	</div>
</div>
<!-- ##### Main Content Wrapper End ##### -->

<%@ include file="/footer.jspf" %>

    <!--===============================================================================================-->
    	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    	<script src="vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    	<script src="vendor/bootstrap/js/popper.js"></script>
    	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    	<script src="vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    	<script src="vendor/daterangepicker/moment.min.js"></script>
    	<script src="vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    	<script src="vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    	<script src="js/main.js"></script>

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
</body>

</html>