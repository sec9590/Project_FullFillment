<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="member.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/list.css" rel="stylesheet">
	<title>메인 페이지</title>
</head>
<body>
	<div align="center">
	<h3>회원 명단</h3>
	${memberName} 회원님 반갑습니다. <br>
	<a href="BbsServlet?action=list&page=1">게시판</a>&nbsp;&nbsp;
	<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;
	<a href="/jspbook/member/MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<div style="text-align:right; width:660px;"><a href="fileServlet?action=member">회원 목록 다운로드</a></div>
	<table>
		<tr>
			<th width=60>아이디</th><th width=70>이름</th><th width=100>생년월일</th><th width=300>주소</th><th width=130>액션</th>
		</tr>
		<c:set var="mList" value="${requestScope.bbsMemberList}"/>
			<c:forEach var="bm" items="${mList}">
			<tr>
			<td align=center>${bm.id}</td>
			<td align=center>${bm.name }</td>
			<td align=center>${bm.birthday}</td>
			<td>${bm.address }</td>
			<td align=center>
				<button onclick="location.href='MemberProcServlet?action=update&id=${bm.id}'">수정</button>&nbsp;
				<button onclick="location.href='MemberProcServlet?action=delete&id=${bm.id}'">삭제</button>&nbsp;
			</td>
		</tr>
			</c:forEach>
	</table>
	
	<c:set var="pageList" value="${requestScope.pageList}"/>
		<c:forEach var="pageNo" items="${pageList}">
			${pageNo}
		</c:forEach>
		
	</div>
</body>
</html>