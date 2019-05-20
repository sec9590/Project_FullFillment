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
	text-align: left;
	border-bottom: 1px solid #ffffff;
	border-top: 1px solid #ffffff;
}
th { width:100px; }

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
}
input[type='radio'] {
	  -webkit-appearance:none;
	  width:16px;
	  height:16px;
	  border:1px solid darkgray;
	  border-radius:50%;
	  outline:none;
	  background:#e6e6e6;
	  vertical-align:-3px;
	  margin-left:15px;
	}
	input[type='radio']:before {
	  content:'';
	  display:block;
	  width:60%;
	  height:60%;
	  margin: 20% auto;   
	  border-radius:50%;   
	}
	input[type='radio']:checked:before {
	  background:#008675;
	}
	label {
	 font-size: 14px;
 	 color: #6b6b6b;}
 	 
 	 input[type='text'] {
 	 	background-color:#eeeeee;
 	 	width:100%;
 	 	height:40px;
 	 	margin-left:18px;
 	 	padding-left:10px;
 	 }
 	 input[type=submit]{
	  display: inline-block;
	  width: 100px;
	  height: 50px;
	  color: #ffffff;
	  background-color: #fbb710;
	  border: none;
	  padding: 0 7px;
	  font-size: 16px;
	  font-weight: 400;
	  cursor:pointer;
	}
.filebox label { 
display: inline-block; 
padding: .5em .75em; 
color: #999; 
font-size: inherit; 
line-height: normal; 
vertical-align: middle; 
background-color: #fdfdfd; 
cursor: pointer; 
border: 1px solid #ebebeb;
 border-bottom-color: #e2e2e2; 
 border-radius: .25em; }
		
		.filebox input[type="file"] { 
		/* 파일 필드 숨기기 */ 
		position: absolute; 
		width: 1px; 
		height: 1px; 
		padding: 0; 
		margin: -1px; 
		overflow: hidden; 
		clip:rect(0,0,0,0); 
		border: 0; }
		
		/* named upload */ 
		.filebox .upload-name 
		{ display: inline-block; 
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


</style>
<script type="text/javascript">
$(document).ready(function(){ 
	var fileTarget = $('.filebox .upload-hidden'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
	if(window.FileReader){ // modern browser 
		var filename = $(this)[0].files[0].name; 
	} else { // old IE 
		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	} 
	
	// 추출한 파일명 삽입 
	$(this).siblings('.upload-name').val(filename); 
	}); 
}); 

</script>
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
				<%-- <%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%> --%>${memberName} <a href="/project02/memberProcServlet?action=logout">로그아웃</a>
			</div>
		</div>
		<!-- Amado Nav --> <nav class="amado-nav">
		<li><a href="index.jsp">HOME</a></li>
		<li><a href="memberProcServlet?action=member&page=1">회원목록</a></li>
		<li class="active"><a href="ProductProcServlet?action=product_list&page=1">상품목록</a></li>
		<li><a href="OrdersProcServlet?action=productlist">재고내역</a></li>
		<li><a href="order.jsp">주문하기</a></li>
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
				<h4>제품추가</h4>
				</div>
				<div class="col-12 col-sm-6 col-md-12 col-xl-15">
					<form name="Signupform" action="ProductProcServlet?action=insert" method="post">
						<table class="table">
							<tr>
								<th>제품명</th>
								<td colspan=5>
									<input type=text id="p_name" >
								</td>
							</tr>
							<tr>
								<th>제품사진</th>
								<td colspan=5 class="filebox">
									<input class="upload-name" value="파일선택" disabled="disabled"> 
									<label for="ex_filename">업로드</label> 
									<input type="file" id="ex_filename" class="upload-hidden">
								</td>
							</tr>
							<tr>
								<th>가격</th>
								<td colspan=5><input type=text id="p_price" ></td>
							</tr>
							<tr>
								<th>제품분류</th>
									<td><label><input type="radio" name="m_field" value="A" >&nbsp;의자</label></td>
                          			<td><label><input type="radio" name="m_field" value="B" >&nbsp;침대</label></td>
                          			<td><label><input type="radio" name="m_field" value="C" >&nbsp;테이블</label></td>
                          			<td><label><input type="radio" name="m_field" value="D" >&nbsp;수납장</label></td>
                          			<td><label><input type="radio" name="m_field" value="E" >&nbsp;조명/리빙</label></td>
							</tr>
						</table>
						<div align=center>
                            	<input onMouseover="this.style.background='#333333';" onMouseout="this.style.background='#fbb710';" type="submit" value="제품추가" name="B1">
                         </div>
						</form>
					</div>
				</div>
			</div>
		</div>

	<!-- ##### Main Content Wrapper End ##### -->

<!-- ##### Footer Area Start ##### -->     
<%@ include file="/footer.jspf" %>     
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