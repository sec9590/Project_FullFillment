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
<title>Yellow Container : product_insert</title>

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
	border-bottom: 1px solid #ffffff;
	border-top: 1px solid #ffffff;

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

input[type='radio'] {
	-webkit-appearance: none;
	width: 16px;
	height: 16px;
	border: 1px solid darkgray;
	border-radius: 50%;
	outline: none;
	background: #e6e6e6;
	vertical-align: -3px;
	margin-left: 15px;
}

input[type='radio']:before {
	content: '';
	display: block;	
	height: 90%;
	border-radius: 50%;
}

input[type='radio']:checked:before {
	background: #008675;
}

label {
	font-size: 14px;
	color: #6b6b6b;
}

input[type='text'] {
	background-color: #eeeeee;
	width: 50%;
	height: 40px;
	margin-left: 18px;
	padding-left: 10px;
}

.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	line-height: normal;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}

.filebox input[type="file"] {
	/* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

/* named upload */
.filebox .upload-name {
	display: inline-block;
	padding: .5em .75em;
	/* label의 패딩값과 일치 */
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none;
	/* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}

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

.myButton:hover {
	background-color: #fbb810;
}

.myButton:active {
	position: relative;
	top: 1px;
}

th{
text-align:right;
width:30%;
}

td{
width:70%;
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
		<li class="active"><a
			href="ProductProcServlet?action=product_list&page=1">제품목록</a></li>
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
				<div>
					<h4>제품추가</h4>
				</div>
				<!-- Single Product Area -->
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<div class="single-product-wrapper">
						<form name="Signupform"
							action="ProductProcServlet?action=insert_product" method="post"
							enctype="multipart/form-data">
							<table class="table" style="width:100%; align:center; margin:auto;">	
								<tr>
									<th style="color:white">제품명</th>
									<td style="color:white">제품명</td>
								</tr>																	
								<tr>
									<th>제품명</th>
									<td><input type=text id="p_name"></td>
								</tr>
								<tr>
									<th>제품사진</th>
									<td class="filebox"><input type="text"
										id="file_route" class="upload-name" readonly="readonly"
										title="File Route">&nbsp;&nbsp; <label
										for="ex_filename">업로드</label> <input type="file"
										id="ex_filename" name="file" class="upload-hidden"
										onchange="javascript:document.getElementById('file_route').value=this.value">
									</td>
								</tr>
								<tr>
									<th>가격</th>
									<td><input type=text id="p_price"></td>
								</tr>
								<tr>
									<th>제품분류</th>
									<td><label><input type="radio" name="buycode"
											value="A">&nbsp;의자&nbsp;&nbsp;</label>
									<label><input type="radio" name="buycode"
											value="B">&nbsp;침대&nbsp;&nbsp;</label>
									<label><input type="radio" name="buycode"
											value="C">&nbsp;테이블&nbsp;&nbsp;</label>
									<label><input type="radio" name="buycode"
											value="D">&nbsp;수납장&nbsp;&nbsp;</label>
									<label><input type="radio" name="buycode"
											value="E">&nbsp;조명/리빙</label></td>
								</tr>
							</table>
							<br><br>
							<div align=center>
								<input class="myButton" type="submit" value="제품추가" name="B1">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- ##### Main Content Wrapper End ##### -->

	<!-- ##### Footer Area Start ##### -->
	<%@ include file="/footer.jspf"%>
	<!-- ##### Footer Area End ##### -->
	<!-- ##### Footer Area End ##### -->

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