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
   <jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
	<div class="generic-container">
		
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
<!-- 		<span class="well floatRight"> -->
<%-- 			Go To <a href="<c:url value='/admin/users' />">List of Users</a> --%>
<!-- 		</span> -->
	</div>
</body>

</html>