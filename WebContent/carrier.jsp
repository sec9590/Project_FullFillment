<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Yellow Container : Carrier</title>
	
	<!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">
    <style>
	th{text-align : center;}
	h4 { margin-left:15px; margin-bottom:20px; } 
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
                <div style="text-align:center">
	                ${memberName}
					<a href="/project02/memberProcServlet?action=logout">로그아웃</a>
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
                <div style="text-align:center">
	                ${memberName}
					<a href="/project02/memberProcServlet?action=logout">로그아웃</a>
				</div>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
                  <li><a href="index.jsp">HOME</a></li>                 
                  <li class="active"><a href="carrier.jsp">CARRIER</a></li>  
                </ul>
            </nav>        
            </header>        
        
       <div class="amado_product_area section-padding-100" style="margin:auto">
	    <div class="row">
	     <h4>운송내역</h4>
	        <!-- Single Product Area -->
	        <div class="col-12 col-sm-6 col-md-12 col-xl-15">
	            <div class="single-product-wrapper">
	                  <table class="table table-hover" >
	                      <thead>
	                          <tr>
	                              <th>송장번호</th>
                                                  <th>수취인</th>
                                                  <th>전화번호</th>
                                                  <th>주소</th>
                                                  <th>제품코드</th>
                                                  <th>제품명</th>
                                                  <th>수량</th>
	                          </tr>
	                      </thead>
                          <tbody>
                              <tr>
                               
                                  <td></td>
                                  <td></td>
                                  <td></td>
                                  <td></td>
                                  <td></td>
                                  <td></td>
                                  <td></td>
                              </tr>
                            
                          </tbody>
	             	</table>            
            
                    </div>
                </div>
            </div>
        </div>
        <!-- Product Details Area End -->
    </div>
    <!-- ##### Main Content Wrapper End ##### -->
   
      <!-- ##### Footer Area Start ##### -->
	<footer class="footer_area clearfix" style="padding:10px; position:absolute; bottom:0; width:100%">
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