<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
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
                <div style="text-align:center">
	                ${memberName}
	                <%
	                	if(session.getAttribute("memberName") != null){
	                %>
					<a href="/project02/memberProcServlet?action=logout">로그아웃</a>
					<%
	                	}
					%>
				</div>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
              <ul>
                    <li><a href="index.jsp">HOME</a></li>
                    <%
                   	 	if(session.getAttribute("memberName") == null){
                    %>
                    <li><a href="signup.jsp">SIGN UP</a></li>
                    <li><a href="login.jsp">LOGIN</a></li>
                    <%
                   	 	} else {
                   	 		String job = (String) session.getAttribute("memberJob");
                   	 		if(job.equals("0")){
                    %>
                   		 <li><a href="OrdersProcServlet?action=productlist">ADMIN</a></li>
                   	<%
                   	 		} else if(job.equals("1")){
                   	 		
                   	%>
                   			<li><a href="OrdersProcServlet?action=buyinglist&field=${memberField}">BUYING</a></li>
                   			
           			<%
           	 				} else {
           			%>
                   			<li><a href="WaybillProcServlet?action=carrierlist&field=${memberField}">CARRIER</a></li>
                    <%
           	 				}
                   	 		}
                    %>
                    <li class="active"><a href="product_list.jsp">PRODUCT</a></li>
                </ul>
            </nav>            
        </header>
        <!-- Header Area End -->
        <div class="shop_sidebar_area">
			<div class="widget catagory mb-50">
                <!-- Widget Title -->

                <!--  Catagories  -->
                <div class="catagories-menu">
                    <ul>
                    	<li><a href="product_list.jsp">ALL</a></li>
                        <li class="active"><a href="chair.jsp">CHAIR</a></li>
                        <li><a href="bed.jsp ">BED</a></li>
                        <li><a href="tables.jsp">TABLES</a></li>
                        <li><a href="storage.jsp">STORAGE CLOSET</a></li>
                        <li><a href="light.jsp">LIGHT/LIVING</a></li>
                    </ul>
                </div>
            </div>
            </div>
         <!-- Product Catagories Area Start -->
        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">

                <div class="row" style="margin-left:15px; margin-bottom:10px;">
                	<h4><span style="color: #fbb810; font-weight: bold">CHAIR</h4>
                </div>

                <div class="row">

                    <!-- Single Product Area -->
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/1.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 300,000won</p>
                                        <h6>DANC 1인 쇼파 F269</h6>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/2.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 180,000won</p>
                                        <h6>LILLBERG 1인소파</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/3.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 120,000won</p>
                                        <h6>DUHO 의자</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/4.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 300,000won</p>
                                        <h6>BONESEN 2인 소파베드</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/5.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 300,000won</p>
                                        <h6>BUGOK 3인소파</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/6.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 20,000won</p>
                                        <h6>MAKA 에펠의자</h6>
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
