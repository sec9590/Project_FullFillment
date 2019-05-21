<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Yellow Container : member_list</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
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

th, td {
	text-align: center;
}

button {
	margin-left: 15px;
	margin-bottom: 20px;
	background-color: #fbb710;
	color: #fff;
	width: 80px;
	height: 30px;
	font-weight: bold;
}

button:hover {
	background-color: #333333;
}
</style>
</head>

<body>

	<div class="main-content-wrapper d-flex clearfix">
		<!-- Mobile Nav (max width 767px)-->
		<div class="mobile-nav">
			<!-- Navbar Brand -->
			<div class="amado-navbar-brand">
				<a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
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
			<a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
			<div style="text-align: center">
				<%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
			</div>
		</div>
		<!-- Amado Nav --> <nav class="amado-nav">
		<li><a href="index.jsp">HOME</a></li>
		<li class="active"><a href="memberProcServlet?action=member&page=1">회원목록</a></li>	
		<li><a href="ProductProcServlet?action=product_list&page=1">제품목록</a></li>
		<li><a href="OrdersProcServlet?action=productlist">재고내역</a></li>
		<li><a href="OrdersProcServlet?action=order">주문하기</a></li>
		<li><a href="OrdersProcServlet?action=orderAll&page=1">주문내역</a></li>
		<li><a href="OrdersProcServlet?action=orderhistoryall">발주내역</a></li>
		<li><a href="WaybillProcServlet?action=waybilllist&page=1">운송내역</a></li>
		<li><a href="WaybillProcServlet?action=nowaybilllist">미운송내역</a></li>
		<li><a href="OrdersProcServlet?action=grossprofit">매출 총 이익</a></li>
		</nav> </header>
		<!-- Header Area End -->

		<div class="amado_product_area section-padding-100">
			<div class="row">
			<div style="width: 100%; position: relative;">
				<h4>회원목록</h4>
				
				</div>
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<div class="single-product-wrapper">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>아이디</th>
									<th>이름</th>
									<th>전화번호</th>
									<th>직업</th>
									<th>분류</th>
									<th>배송지</th>
	
								</tr>
							</thead>
							<tbody>
							<tbody>
								<c:set var="mAll" value="${requestScope.memberAll}" />
								<c:forEach var="all" items="${mAll}">
									<tr>
										<td>${all.m_id}</td>
										<td>${all.m_name}</td>
										<td>${all.m_tel }</td>
										<c:choose>
											<c:when test="${all.m_job == 1}">
												<td>구매처</td>
													<c:choose>
														<c:when test="${all.m_field == 'A' }">
															<td>의자</td>
														</c:when>
														<c:when test="${all.m_field == 'B' }">
															<td>침대</td>
														</c:when>
														<c:when test="${all.m_field == 'C' }">
															<td>테이블</td>
														</c:when>
														<c:when test="${all.m_field == 'D' }">
															<td>수납장</td>
														</c:when>
														<c:when test="${all.m_field == 'E' }">
															<td>조명/리빙</td>
														</c:when>
													</c:choose>
													<td></td>
											</c:when>
											<c:when test="${all.m_job == 2}">
												<td>운송회사</td>
												<td></td>
												<c:choose>
														<c:when test="${all.m_field == 'a' }">
															<td>서울/경기</td>
														</c:when>
														<c:when test="${all.m_field == 'b' }">
															<td>대전/세종/충청</td>
														</c:when>
														<c:when test="${all.m_field == 'c' }">
															<td>광주/전라</td>
														</c:when>
														<c:when test="${all.m_field == 'd' }">
															<td>부산/울산/대구/경상</td>
														</c:when>
														<c:when test="${all.m_field == 'e' }">
															<td>강원</td>
														</c:when>
													</c:choose>
											</c:when>
											<c:when test="${all.m_job == 0}">
												<td>관리자</td>
											</c:when>
											<c:when test="${product.p_quantity >= 10}">
												<td>${product.p_quantity}</td>
											</c:when>
											<c:when test="${product.p_quantity >= 10}">
												<td>${product.p_quantity}</td>
											</c:when>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
							</tbody>
						</table>
					
					</div>
					<div align=center>
							<c:set var="pageList" value="${requestScope.pageList}" />
							<c:forEach var="pageNo" items="${pageList}">
								${pageNo}
							</c:forEach>
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