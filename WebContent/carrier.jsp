<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" href="<c:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css"/>" type="text/css"/>

	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
	<script src="<c:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.mtz.monthpicker.js"/>"></script>
    
    <script>
    var currentYear = (new Date()).getFullYear();
    var startYear = currentYear-10;
    var options = {

            startYear: startYear,

            finalYear: currentYear,

            pattern: 'yyyy-mm',

            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']

    };

    $('#schMonth').monthpicker(options);
    


    </script>
    <style>
	th{text-align : center;}
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
        
       <div class="amado_product_area section-padding-100 clearfix" style="margin:auto">
	    <div class="row">
	     <h4><span style="color:#fbb810; font-weight:bold">${memberName}</span>님 운송내역</h4>
			<br>
			<form action="WaybillProcServlet?action=carrierlist&field=${memberField}" method="post">
					<input type="text" id="schMonth" name="dateInventory" value="#" style="border-bottom:1px solid #cccccc;">
					<input type="submit" style="background-color: #fbb810; border: none" value="검색"> 
					</form>
	        <!-- Single Product Area -->
	        <div class="col-12 col-sm-6 col-md-12 col-xl-15">
	            <div class="single-product-wrapper">
	                  <table class="table table-hover" >
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
                            <c:set var="clist" value="${requestScope.carrierList}" />
							<c:forEach var="carr" items="${clist}">
								<tr height="30">
									<td>${carr.w_id}</td>
									<td><a
										href="OrdersProcServlet?action=detail&name=${carr.o_name}&id=${carr.o_id}">${carr.o_id}</a>
									</td>
									<td>${carr.o_name}</td>
									<td>${carr.o_tel}</td>
									<td>${carr.o_address}</td>
									<td>${carr.w_time}</td>
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