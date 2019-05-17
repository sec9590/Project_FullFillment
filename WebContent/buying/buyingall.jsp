<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, member.*, product.*, waybill.*"%>
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

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
    <script>
    $(function () {
        //input을 datepicker로 선언
    	 var datepicker_default = {
                 closeText: '닫기',
                 prevText: '이전달',
                 nextText: '다음달',
                 currentText: '오늘',
                 dateFormat: 'yy-MM' //Input Display Format 변경
                 , showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                 , showMonthAfterYear: true //년도 먼저 나오고, 뒤에 월 표시
                 , changeYear: true //콤보박스에서 년 선택 가능
                 , changeMonth: true //콤보박스에서 월 선택 가능
                 , buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                 , buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
                 , yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
                 , monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
                 , monthNames: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 Tooltip 텍스트
                 , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
                 , dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 부분 Tooltip 텍스트
                 , minDate: "-10Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                 , maxDate: "+1D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
                 , showButtonPanel: true
             };
  
             datepicker_default.closeText = "선택";
             datepicker_default.onClose = function (dateText, inst) {
                 var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                 var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                 $(this).datepicker("option", "defaultDate", new Date(year, month, 1));
                 $(this).datepicker('setDate', new Date(year, month, 1));
             }
  
             datepicker_default.beforeShow = function () {
                 var selectDate = $("#sdate").val().split("-");
                 var year = Number(selectDate[0]);
                 var month = Number(selectDate[1]) - 1;
                 $(this).datepicker("option", "defaultDate", new Date(year, month, 1));
             }
             $("#sdate").datepicker(datepicker_default);
    });

    </script>
<style>
	th, td{text-align : center;}
	h4 { margin-left:15px; margin-bottom:20px; } 
	input[type=submit]{
		background-color: #fbb810;
		-moz-border-radius: 15px;
		-webkit-border-radius: 15px;
		border-radius: 15px;
		cursor: pointer;
		color: #ffffff;
		font-family: Arial;
		font-weight: bold;
		font-size:13px;
		padding: 5px 10px;
		text-decoration: none;
}
</style>
<style> 
table.ui-datepicker-calendar { display:none; }
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
					<li><a
						href="OrdersProcServlet?action=buyinglist&field=${memberField}">발주신청</a></li>
					<li class="active"><a
						href="OrdersProcServlet?action=buyingall&field=${memberField}">발주내역</a></li>
				</ul>
			</nav>
		</header>
		<!-- Header Area End -->


		<!-- 테이블 -->
		<div class="amado_product_area section-padding-100 clearfix" style="margin:auto">
		<div class="row">
		<div style="width: 100%; position: relative;">
			<h4>
				<span style="color: #fbb810; font-weight: bold">${memberName}</span>님 발주내역
			</h4>
			<div style="float:right; padding-bottom:10px;">
					<form action="OrdersProcServlet?action=buyingselectall&field=${requestScope.field}" method="post" autocomplete=off>
					<input type="text" id="sdate" name="dateInventory" value="#" style="border-bottom:1px solid #cccccc;">
					<input type="submit" style="background-color: #fbb810; border: none" value="검색"> 
					</form>
				</div>
				</div>
			<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<div class="single-product-wrapper">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>발주번호</th>
										<th>제품코드</th>
										<th>제품명</th>
										<th>가격</th>
										<th>발주수량</th>
										<th>발주시간</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="blist" value="${requestScope.buyingall}" />
									<c:forEach var="buying" items="${blist}">
										<tr>
											<td>${buying.b_id}</td>
											<td>${buying.p_id}</td>
											<td>${buying.p_name}</td>
											<td><fmt:formatNumber value="${buying.p_price}" pattern="#,###" /></td>
											<td>${buying.p_quantity}</td>
											<td>${buying.b_time}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
					</div>
				</div>
			</div>
			</div>
	<!-- ##### Main Content Wrapper End ##### -->

<%@ include file="/footer.jspf" %>
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
