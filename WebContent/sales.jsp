<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ page import="product.*, member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>Yellow Container : sales</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->

<style>
h4 {
	margin-left: 15px;
	margin-bottom: 20px;
}

th {
	text-align: center;
}
.right-box {
  float: right;
}

</style>

</head>

<body>
	<!-- Search Wrapper Area Start -->
	<div class="search-wrapper section-padding-100">
		<div class="search-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="search-content">
						<form action="#" method="get">
							<input type="search" name="search" id="search"
								placeholder="Type your keyword...">
							<button type="submit">
								<img src="img/core-img/search.png" alt="">
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Search Wrapper Area End -->

	<!-- ##### Main Content Wrapper Start ##### -->
	<div class="main-content-wrapper d-flex clearfix">

		<!-- Mobile Nav (max width 767px)-->
		<div class="mobile-nav">
			<!-- Navbar Brand -->
			<div class="amado-navbar-brand">
				<a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
				<div style="text-align: center">
					${memberName} <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
				</div>
			</div>
			<!-- Navbar Toggler -->
			<div class="amado-navbar-toggler">
				<span></span><span></span><span></span>
			</div>
		</div>

		<!-- Header Area Start -->
		<header class="header-area clearfix"> <!-- Close Icon -->
		<div class="nav-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<!-- Logo -->
		<div class="logo">
			<a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
			<div style="text-align: center">
				${memberName} <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
			</div>
		</div>
		<!-- Amado Nav --> 
		<nav class="amado-nav">
        	<li><a href="index.jsp">HOME</a></li>
            <li><a href="OrdersProcServlet?action=productlist">재고내역</a></li>
            <li><a href="order.jsp">주문하기</a></li>
            <li class="active"><a href="OrdersProcServlet?action=orderAll&page=1">주문내역</a></li>
            <li><a href="orderhistory.jsp">발주내역</a></li>
            <li><a href="WaybillProcServlet?action=waybilllist&page=1">운송내역</a></li>
            <li><a href="WaybillProcServlet?action=nowaybilllist">미운송내역</a></li>
            <li><a href="grossprofit.jsp">매출 총 이익</a></li>
        </nav>
        </header>
		<!-- Header Area End -->

		<div class="amado_product_area section-padding-100">
			<div class="row">
			<div style="width:100%; position:relative;">
				<h4>총 주문내역</h4>
				<div style="float:right; margin-bottom:20px;" >
					<select name="date" >
						<option value="#">모든날짜</option>
						<option value="#">오늘하루</option>
						<option value="#">지난1일</option>
						<option value="#">지난1개월</option>
						<option value="#">지난1년</option>
					</select>
					</div>
				</div>
				<!-- Single Product Area -->
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<div class="single-product-wrapper">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>주문번호</th>
									<th>이름</th>
									<th>전화번호</th>
									<th>주소</th>
									<th>주문개수</th>
									<th>주문시간</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="orderAll" value="${requestScope.orderAllList}" />
								<c:forEach var="order" items="${orderAll}">
									<tr height="30">
										<td style="text-align: center"><a
											href="OrdersProcServlet?action=detail&name=${order.o_name}&id=${order.o_id}">${order.o_id}</a>
										</td>
										<td style="text-align: center">${order.o_name}</td>
										<td style="text-align: center">${order.o_tel}</td>
										<td>${order.o_address}</td>
										<td style="text-align: center">${order.count}</td>
										<td style="text-align: center">${order.o_time}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<div align=center>
							<c:set var="pageList" value="${requestScope.pageList}" />
							<c:forEach var="pageNo" items="${pageList}">
								${pageNo}
							</c:forEach>
							<br>
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ##### Main Content Wrapper End ##### -->

	<!-- ##### Footer Area Start ##### -->
	<footer class="footer_area clearfix"
		style="padding:10px; position:absolute; margin-bottom:0; width:100%">
	<div class="container" style="text-align: center">
		<span style="color: white"> Copyright &copy;<script>
			document.write(new Date().getFullYear());
		</script> All rights reserved | YeonA & SeEun & MinJi
		</span>

	</div>
	</footer>
	<!-- ##### Footer Area End ##### -->

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