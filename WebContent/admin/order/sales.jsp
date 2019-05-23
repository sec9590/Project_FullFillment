<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ page import="product.*, member.*, waybill.*"%>
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
<link rel="stylesheet" type="text/css" href="css/core-style.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
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

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>
	// 즉시실행함수
	$(function() {
		//달력 옵션 설정
		$.datepicker.regional['ko'] = {
			closeText : '닫기',
			prevText : '이전달',
			nextText : '다음달',
			currentText : '오늘',
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
					'9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
					'9월', '10월', '11월', '12월' ],
			dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			weekHeader : 'Wk',
			dateFormat : 'yy-mm-dd',
			firstDay : 0,
			isRTL : false,
			duration : 200,
			showAnim : 'show',
			showMonthAfterYear : true,
			yearSuffix : '년'
		};

		$.datepicker.setDefaults($.datepicker.regional['ko']);

		// 실질적인 달력생성 부분
		$('#schDate').datepicker({
			changeMonth : false,
			changeYear : false,
			defaultDate : $('#schDate').val()
		});

	});
</script>
<style>
h4 {
	margin-left: 15px;
	margin-bottom: 20px;
}

th {
	text-align: center;
}

input[type=submit] {
	background-color: #fbb810;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-weight: bold;
	font-size: 13px;
	padding: 5px 10px;
	text-decoration: none;
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
		<li class="active"><a
			href="OrdersProcServlet?action=orderAll&page=1">주문내역</a></li>
		<li><a href="OrdersProcServlet?action=orderhistoryall">발주내역</a></li>
		<li><a href="WaybillProcServlet?action=waybilllist&page=1">운송내역</a></li>
		<li><a href="WaybillProcServlet?action=nowaybilllist">미운송내역</a></li>
		<li><a href="OrdersProcServlet?action=grossprofit">매출 총 이익</a></li>
		</nav> </header>
		<!-- Header Area End -->

		<div class="amado_product_area section-padding-100">
			<div class="row">
				<div style="width: 100%; position: relative;">
					<h4>총 주문내역</h4>

					<div style="float: right; padding-bottom: 10px;">
						<form action="OrdersProcServlet?action=selecttime&page=1"
							method="post">
							<input type="text" id="schDate" name="dateInventory" value="#"
								style="border-bottom: 1px solid #cccccc;"> <input
								type="submit" style="background-color: #fbb810; border: none"
								value="검색">
						</form>
					</div>
				</div>
				<!-- Single Product Area -->
				<table class="table table-hover">
					<thead>
						<tr>
							<th>주문번호</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>주소</th>
							<th>주문개수</th>
							<th>주문시간</th>
							<th>주문상태</th>
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
								<c:if test="${order.status eq '운송'}">
									<td style="color : blue; text-align: center; font-weight:bold">${order.status}</td>
								</c:if>
								<c:if test="${order.status eq '지연'}">
									<td style="color : red; text-align: center; font-weight:bold">${order.status}</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div align=center style="margin-left: 200px">
				<c:set var="pageList" value="${requestScope.pageList}" />
				<c:forEach var="pageNo" items="${pageList}">
								${pageNo}
							</c:forEach>
			</div>
		</div>
	</div>
	<!-- ##### Main Content Wrapper End ##### -->

	<%@ include file="/footer.jspf"%>
	<script src="js/main.js"></script>

	<!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
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