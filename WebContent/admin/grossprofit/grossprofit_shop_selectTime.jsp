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
<title>Yellow Container : grossprofit_shop</title>

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
h4 {
	margin-left: 15px;
	margin-bottom: 20px;
}

th, td {
	text-align: center;
}
/*/[발주버튼]*/
.myButton {
	background-color: #fbb810;
	-moz-border-radius: 42px;
	-webkit-border-radius: 42px;
	border-radius: 42px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	padding: 10px 20px;
	text-decoration: none;
}

button {
	margin-left: 15px;
	margin-bottom: 20px;
	background-color: #fbb710;
	color: #fff;
	width: 80px;
	height: 30px;
}

button:hover {
	background-color: #333333;
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
<%@ include file="header.jspf"%>
		<div class="amado_product_area section-padding-100">
			<div class="row">
				<div style="width: 100%; position: relative;">
					<h4>
						<span style="color: #fbb810; font-weight: bold">${dateInventory }</span>
						쇼핑몰 대금 청구
					</h4>
					<div
						style="float: right; padding-bottom: 10px; position: relative;">
						<form action="OrdersProcServlet?action=shopSelectTime"
							method="post" autocomplete=off>
							<input type="text" id="sdate" name="dateInventory" value="#"
								style="border-bottom: 1px solid #cccccc;"> <input
								type="submit" style="background-color: #fbb810; border: none"
								value="검색">
						</form>
					</div>
				</div>
				<!-- Single Product Area -->
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>쇼핑몰</th>
								<th>주문시간</th>
								<th>대금청구</th>
								<th>송장청구</th>
								<th>총 대금</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="slist" value="${requestScope.shopList}" />
							<c:set var="total" value="0" />
							<c:forEach var="shop" items="${slist}">
								<tr>
									<td><a
										href="OrdersProcServlet?action=shopprofit_detail&shopcode=${shop.shopcode}&o_time=${shop.o_time}">${shop.shopcode}</a></td>
									<td>${shop.o_time}</td>
									<td><fmt:formatNumber value="${shop.total}"
											pattern="#,###" /></td>
									<td><fmt:formatNumber value="${shop.shippay}"
											pattern="#,###" /></td>
									<td style="color: red; font-weight: bold"><fmt:formatNumber
											value="${shop.total + shop.shippay}" pattern="#,###" /></td>
									<c:set var="total" value="${total + shop.total + shop.shippay}" />
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<br>
					<div align=center>
						<h5 style="font-weight: bold;">
							총 합계 : <span style="color: red; font-weight: bold"><fmt:formatNumber
									value="${total}" pattern="#,###" /></span>
						</h5>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ##### Main Content Wrapper End ##### -->

	<%@ include file="/footer.jspf"%>

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