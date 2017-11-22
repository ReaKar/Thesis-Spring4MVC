<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="current_time" value="<%=new Date()%>" />

<html>

<head>
<title>Administration</title>

<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>

	<div class="col-sm-8 text-left">
        
		<h1>Welcome</h1>
		<h2 align="left"> ${current_time }</h2>		
		<p></p>
		<hr>
<!-- 		<h3> -->
<%-- 			<a href="<c:url value='/admin/courseslist' />">List Of Courses</a> --%>
<!-- 		</h3> -->
<!-- 		<!--       <p>Lorem ipsum...</p> --> 
	</div>
	




  	


</body>
</html>