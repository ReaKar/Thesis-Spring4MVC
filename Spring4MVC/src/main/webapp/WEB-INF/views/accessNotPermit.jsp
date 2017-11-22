<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>

<title>Registration Confirmation Page</title>
<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>
<body>
	<div class="generic-container">
		<%@include file="authheader.jsp"%>

		<div class="alert alert-info">${success}
		
		<strong>Note:</strong>  You will not be able to access the site until your account has been approved.
		
		</div>

		<p>You have login successfully.</p>
		
		<p>
			You may <a href="<c:url value='/login' />">login here</a>
		</p>
	</div>
</body>

</html>