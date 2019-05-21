<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
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
    <link rel="stylesheet" type="text/css" href="css/util.css">
</head>
<style>
	span { font-size:15pt; font-weight:bold; color:#fbb710;}
	ul { padding-top:10px;}
</style>
<body>   
    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="OrdersProcServlet?action=index"><img src="img/core-img/logo.png" alt=""></a>
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
                <div style="text-align:center">
	              
	                <%
	                	if(session.getAttribute(request.getAttribute("cookieId")+"memberName") != null){
	                %>
	                <%=session.getAttribute(request.getAttribute("cookieId")+"memberName")%>
					<a href="/project02/memberProcServlet?action=logout">로그아웃</a>
					<%
	                	}
					%>
				</div>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
              <ul>
                    <li class="active"><a href="OrdersProcServlet?action=index">HOME</a></li>
                    <%
                   	 	if(session.getAttribute(request.getAttribute("cookieId")+"memberName") == null){
                    %>
                    <li><a href="signup.jsp">SIGN UP</a></li>
                    <li><a href="login.jsp">LOGIN</a></li>
                     <li><a href="product_list.jsp">PRODUCT</a></li>
                    <%
                   	 	} else {
                   	 		String job = (String) session.getAttribute(request.getAttribute("cookieId")+"memberJob");
                   	 		if(job.equals("0")){
                    %>
                   		 <li><a href="OrdersProcServlet?action=productlist">ADMIN</a></li>
                   	<%
                   	 		} else if(job.equals("1")){
                   	 		
                   	%>
                   			<li><a href="OrdersProcServlet?action=buyinglist">BUYING</a></li>
                   			
           			<%
           	 				} else {
           			%>
                   			<li><a href="WaybillProcServlet?action=carrierlist">CARRIER</a></li>
                    <%
           	 				}
                   	 		}
                    %>
                   
                </ul>
            </nav>
                <div style="margin-top:50px; margin-left:-40px; width:250px; padding: 10px 10px 10px 10px;">
                <span>재고부족</span>
                <ul>
                <%	
                	ProductDAO pDao = new ProductDAO();
                	List<ProductDTO> list = pDao.BuyingALL();
                	for(ProductDTO pDto : list){
                	
                %>
					<li style="font-size:10pt;">＊ <%=pDto.getP_name() %></li>
					<%
					}
					%>
                </ul>
                </div>
                        
        </header>
        <!-- Header Area End -->

         <!-- Product Catagories Area Start -->
        <div class="products-catagories-area clearfix">
            <div class="amado-pro-catagory clearfix">

                <!-- Single Catagory -1 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/1.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 300,000won</p>
                            <h4>DANCI 1인소파 F269 </h4>

                        </div>
                    </a>
                </div>
                 <!-- Single Catagory -2 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/2.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 180,000won</p>
                            <h4>LILLBERG 1인소파</h4>
                        </div>
                    </a>
                </div>
                   <!-- Single Catagory -2 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/2.JPG" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 180,000won</p>
                            <h4>LILLBERG 1인소파</h4>
                        </div>
                    </a>
                </div>       
                
                <!-- Single Catagory -3 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/3.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 120,000won</p>
                            <h4>DUHO 의자</h4>
                        </div>
                    </a>
                </div>
                
                
                <!-- Single Catagory -4 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/4.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 300,000won</p>
                            <h4>BONESEN 2인 소파베드</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -5 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/5.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 300,000won</p>
                            <h4>BUGOK 3인소파</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -6 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/6.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 20,000won</p>
                            <h4>MAKA 에펠의자</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -7 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/7.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 170,000won</p>
                            <h4>MARINO 분리형 이층침대 트윈싱글</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -8 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/8.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 170,000won</p>
                            <h4>PLEVEN/FIERA 원목침대</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -9 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/9.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 120,000won</p>
                            <h4>FRUGA 원목침대 더블</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -10 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/10.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 140,000won</p>
                            <h4>BERGEN 데이베드 침대 슈퍼싱글</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -11 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/11.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 270,000won</p>
                            <h4>DROI 원목이층침대 트윈싱글</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -12 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/12.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 63,000won</p>
                            <h4>NINO 원목유아침대 60x120</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -13 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/13.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 40,000won</p>
                            <h4>TETOS 책상</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -14 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/14.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 24,000won</p>
                            <h4>KASY 커피테이블</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -15 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/15.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 720,000won</p>
                            <h4>SIDON 원목테이블/벤치 숭카이나무 세트</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -16 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/16.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 40,000won</p>
                            <h4>MKB 서랍협탁</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -17 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/17.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 16,000won</p>
                            <h4>DUGED 사이드테이블</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -18 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/18.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 500,000won</p>
                            <h4>PECRE 빈티지테이블 200x100</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -19 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/19.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 20,000won</p>
                            <h4>LEITER 선반 600 넓은 4단</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -20 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/20.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 60,000won</p>
                            <h4>MKB 캐비넷 가로양문 TV스탠드</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -21 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/21.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 280,000won</p>
                            <h4>ESENI 선반/행거</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -22 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/22.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 200,000won</p>
                            <h4>KRUSS 선반</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -23 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/23.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 500,000won</p>
                            <h4>JIYA 원목TV스탠드 망고나무</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -24 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/24.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 30,000won</p>
                            <h4>SAMONY 캔들워머 다이얼</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -25 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/25.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 18,000won</p>
                            <h4>RUSTA 장스탠드 실버</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -26 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/26.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 50,000won</p>
                            <h4>RETRO 펜던트등 꽃병모양</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -27 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/27.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 30,000won</p>
                            <h4>PLUID 펜던트등 꽃병모양</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -28 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/28.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 26,000won</p>
                            <h4>KULJAM 펫베드 타원 60x50</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -29 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/29.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 300,000won</p>
                            <h4>TIANO 전신거울 56x165</h4>
                        </div>
                    </a>
                </div>

                <!-- Single Catagory -30 -->
                <div class="single-products-catagory clearfix">
                    <a href="#">
                        <img src="img/bg-img/30.jpg" alt="">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <p>From 90,000won</p>
                            <h4>ANTHOR 담요 150x170 다크그레이</h4>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- ##### Main Content Wrapper End ##### -->


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
