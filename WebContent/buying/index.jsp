<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, member.*, product.*, waybill.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yellow Container - buying(index)</title>
</head>
<body>

	<script type="text/javascript">		
	/* 	var set = '<c:out value="${requestScope.field}"/>';		 */
		document.location.href = '../OrdersProcServlet?action=buyinglist'
	</script>
</body>
</html>