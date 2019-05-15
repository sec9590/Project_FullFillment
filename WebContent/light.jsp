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
                        <li><a href="chair.jsp">CHAIR</a></li>
                        <li><a href="bed.jsp ">BED</a></li>
                        <li><a href="tables.jsp">TABLES</a></li>
                        <li><a href="storage.jsp">STORAGE CLOSET</a></li>
                        <li class="active"><a href="light.jsp">LIGHT/LIVING</a></li>
                    </ul>
                </div>
            </div>
            </div>
         <!-- Product Catagories Area Start -->
        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">

                <div class="row" style="margin-left:15px; margin-bottom:10px;">
                	<h4><span style="color: #fbb810; font-weight: bold">LIGHT/LIVING</h4>
                </div>

                <div class="row">

                    <!-- Single Product Area -->
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/24.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 30,000won</p>
                                        <h6>SAMONY 캔들워머 다이얼</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/25.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 18,000won</p>
                                        <h6>RUSTA 장스탠드 실버</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/26.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 50,000won</p>
                                        <h6>RETRO 펜던트등 꽃병모양</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/27.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 30,000won</p>
                                        <h6>PLUID 펜던트등 꽃병모양</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/28.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 26,000won</p>
                                        <h6>KULJAM 펫베드 타원 60x50</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/29.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 300,000won</p>
                                        <h6>TIANO 전신거울 56x165</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/bg-img/30.jpg" alt="">
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">From 90,000won</p>
                                        <h6>ANTHOR 담요 150x170 다크그레이</h6>
                                </div>
                            </div>
                        </div>
                    </div>



                </div>
                </div>
                </div>
                </div>

   <!-- ##### Footer Area Start ##### -->
	<footer class="footer_area clearfix" style="padding:10px; position:absolute; margin-bottom:0; width:100%">
	<div class="container" style="text-align:center">		
				<span style="color:white">					
						Copyright &copy;<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | YeonA & SeEun & MinJi
						</span>
					
			</div>			
	</footer>
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
