<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>Yellow Container : Buying</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
/*/[발주버튼]*/
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

th, td {
	text-align: center;
}
</style>

</head>

<body>
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
		<header class="header-area clearfix">
			<!-- Close Icon -->
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
				<ul>
					<li><a href="index.jsp">Home</a></li>
					<li class="active"><a href="OrdersProcServlet?action=buyinglist&field=${memberField}">발주신청</a></li>
					<li><a href="OrdersProcServlet?action=buyingall&field=${memberField}">발주내역</a></li>
				</ul>
			</nav>
		</header>
		<!-- Header Area End -->


		<!-- 테이블 -->
		<div class="single-product-area section-padding-100 clearfix" style="margin: auto">
			<h4><span style="color:#fbb810; font-weight:bold">${memberName}</span>님 발주요청내역 (${requestScope.currentTime})</h4>
			<br>
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<nav aria-label="breadcrumb">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>제품코드</th>
										<th>제품명</th>
										<th>가격</th>
										<th>수량요청</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="blist" value="${requestScope.buyingList}" />
									<c:forEach var="buying" items="${blist}">
										<tr>
											<td>${buying.p_id}</td>
											<td style="text-align: left">${buying.p_name}</td>
											<td><fmt:formatNumber value="${buying.p_price}" pattern="#,###" /></td>
										<td>${buying.p_quantity}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</nav>
						<% if(request.getAttribute("buyingList") != null){ %>
						<div align=center>
							<a href="OrdersProcServlet?action=buying&field=${memberField}" class="myButton">발주완료</a>
						</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
		<!-- Product Details Area End -->
	</div>
	<!-- ##### Main Content Wrapper End ##### -->

<%@ include file="/footer.jspf" %>

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
