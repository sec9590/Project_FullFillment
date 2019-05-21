<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, member.*, product.*, waybill.*"%>
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
<title>Yellow Container | Home</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/main.css">
</head>

<body>
	<%@ include file="header.jspf"%>
		<div class="shop_sidebar_area">
			<div class="widget catagory mb-50">
				<!-- Widget Title -->

				<!--  Catagories  -->
				<div class="catagories-menu">
					<ul>
						<li><a href="product_list.jsp">ALL</a></li>
						<li><a href="chair.jsp">CHAIR</a></li>
						<li><a href="bed.jsp ">BED</a></li>
						<li class="active"><a href="tables.jsp">TABLES</a></li>
						<li><a href="storage.jsp">STORAGE CLOSET</a></li>
						<li><a href="light.jsp">LIGHT/LIVING</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- Product Catagories Area Start -->
		<div class="amado_product_area section-padding-100">
			<div class="container-fluid">

				<div class="row" style="margin-left: 15px; margin-bottom: 10px;">
					<h4>
						<span style="color: #fbb810; font-weight: bold">TABLES
					</h4>
				</div>

				<div class="row">

					<!-- Single Product Area -->
					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/13.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 40,000won</p>
									<h6>TETOS 책상</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/14.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 24,000won</p>
									<h6>KASY 커피테이블</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/15.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 720,000won</p>
									<h6>SIDON 원목테이블/벤치 숭카이나무 세트</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/16.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 40,000won</p>
									<h6>MKB 서랍협탁</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/17.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 16,000won</p>
									<h6>DUGED 사이드테이블</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img src="img/bg-img/18.jpg" alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>
									<p class="product-price">From 500,000won</p>
									<h6>PECRE 빈티지테이블 200x100</h6>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>



		</div>
	</div>

	  <%@ include file="footer.jspf" %>

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
