<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yellow Container : Carrier</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" type="text/css" href="css/core-style.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(function() {
		//input을 datepicker로 선언
		var datepicker_default = {
			closeText : '닫기',
			prevText : '이전달',
			nextText : '다음달',
			currentText : '오늘',
			dateFormat : 'yy-MM' //Input Display Format 변경
			,
			showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
			,
			showMonthAfterYear : true //년도 먼저 나오고, 뒤에 월 표시
			,
			changeYear : true //콤보박스에서 년 선택 가능
			,
			changeMonth : true //콤보박스에서 월 선택 가능
			,
			buttonImageOnly : true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
			,
			buttonText : "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
			,
			yearSuffix : "년" //달력의 년도 부분 뒤에 붙는 텍스트
			,
			monthSuffix : "월" //달력의 월 부분 뒤에 붙는 텍스트
			,
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
					'9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
			,
			monthNames : [ '01', '02', '03', '04', '05', '06', '07', '08',
					'09', '10', '11', '12' ] //달력의 월 부분 Tooltip 텍스트
			,
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 부분 텍스트
			,
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ] //달력의 요일 부분 Tooltip 텍스트
			,
			minDate : "-10Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
			,
			maxDate : "+1D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
			,
			showButtonPanel : true
		};

		datepicker_default.closeText = "선택";
		datepicker_default.onClose = function(dateText, inst) {
			var month = $("#ui-datepicker-div .ui-datepicker-month :selected")
					.val();
			var year = $("#ui-datepicker-div .ui-datepicker-year :selected")
					.val();
			$(this).datepicker("option", "defaultDate",
					new Date(year, month, 1));
			$(this).datepicker('setDate', new Date(year, month, 1));
		}

		datepicker_default.beforeShow = function() {
			var selectDate = $("#sdate").val().split("-");
			var year = Number(selectDate[0]);
			var month = Number(selectDate[1]) - 1;
			$(this).datepicker("option", "defaultDate",
					new Date(year, month, 1));
		}
		$("#sdate").datepicker(datepicker_default);
	});
</script>
<style>
th {
	text-align: center;
}

h4 {
	margin-left: 15px;
	margin-bottom: 20px;
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
<style>
table.ui-datepicker-calendar {
	display: none;
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
				<a href="OrdersProcServlet?action=index"><img src="img/core-img/logo.png" alt=""></a>
				<div style="text-align: center">
					<%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
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
				<a href="OrdersProcServlet?action=index"><img src="img/core-img/logo.png" alt=""></a>
				<div style="text-align: center">
					<%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
				</div>
			</div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
                  <li><a href="OrdersProcServlet?action=index">HOME</a></li>                 
                   <li class="active"><a href="WaybillProcServlet?action=carrierlist">CARRIER</a></li>    
                </ul>
            </nav>        
            </header>        

		<div class="amado_product_area section-padding-100 clearfix"
			style="margin: auto">
			<div class="row">
				<div style="width: 100%; position: relative;">
					<h4>
						<span style="color: #fbb810; font-weight: bold"><%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%></span>님
						운송내역
					</h4>
					<span style="margin-left: 20px;">${dateInventory} 운송내역</span>
					<div style="float: right; padding-bottom: 10px;">
						<form action="WaybillProcServlet?action=selectWaybill&field=${requestScope.field}"	method="post" autocomplete=off>
							<input type="text" id="sdate" name="dateInventory" value="#"
								style="border-bottom: 1px solid #cccccc;"> <input
								type="submit" style="background-color: #fbb810; border: none"
								value="검색">
						</form>
					</div>
				</div>
				<!-- Single Product Area -->
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<div class="single-product-wrapper">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>송장번호</th>
									<th>주문번호</th>
									<th>수취인</th>
									<th>전화번호</th>
									<th>주소</th>
									<th>배송시간</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="wlist" value="${requestScope.wayList}" />
								<c:forEach var="way" items="${wlist}">
									<tr height="30">
										<td>${way.w_id}</td>
										<td><a
											href="OrdersProcServlet?action=detail&name=${way.o_name}&id=${way.o_id}">${way.o_id}</a>
										</td>
										<td>${way.o_name}</td>
										<td>${way.o_tel}</td>
										<td>${way.o_address}</td>
										<td>${way.w_time}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
		<!-- Product Details Area End -->
	</div>
	<!-- ##### Main Content Wrapper End ##### -->

	<%@ include file="/footer.jspf"%>
	<!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
	<!-- Popper js -->


</body>
</html>