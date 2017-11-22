<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
 
<title>Home </title>

<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="/WEB-INF/include/menu_user.jsp"></jsp:include>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">

				<div class="list-group">



					<a href="<c:url value='/user/home' />"
						class="list-group-item active">All Courses</a>



<%-- 					<c:forEach items="${courses}" var="courses"> --%>


<%-- 						<a href="<c:url value='/user/course-${courses.title}' />" --%>
<%-- 							class="list-group-item">${courses.title}</a> --%>






<%-- 					</c:forEach> --%>
					
					<c:set var="current_time" value="<%=new Date()%>" />
					<c:forEach items="${courses}" var="courses">
						<c:set var="isOpen" value="${current_time < courses.closedate and current_time > courses.opendate}" />
						<c:set var="isactive" value="${courses.active}" />
						<c:if test="${isOpen || isactive}">	
						<a href="<c:url value='/user/course-${courses.title}' />"
							class="list-group-item">${courses.title}</a>
							</c:if>
							</c:forEach>

				</div>
			</div>
			<div class="col-sm-8 text-left">
				<h1>Dear ${loggedinuser}, Welcome to our site.</h1>

				
				<hr>
				<h3>Επιλέξτε το μάθημα το οποίο παρακολουθείτε και στην συνέχεια
					υποβάλλετε την εργασία σας</h3>
				<!--       <p>Lorem ipsum...</p> -->
			</div>
<!-- 			<div class="col-sm-2 sidenav"> -->

<!-- 				<div class="well"> -->

<!-- 					<a href="https://www.google.gr/?gws_rd=ssl" -->
<!-- 						class="btn btn-default btn-lg"> <span -->
<!-- 						class="glyphicon glyphicon-search"></span> Search -->
<!-- 					</a> -->
<!-- 				</div> -->


<!-- 				<div class="well"> -->

<!-- 					<a href="https://webmail.noc.uoa.gr/src/login.php" -->
<!-- 						class="btn btn-info btn-lg"> <span -->
<!-- 						class="glyphicon glyphicon-envelope"></span> Webmail -->
<!-- 					</a> -->
<!-- 				</div> -->

<!-- 				<div class="well"> -->
<!-- 					<a href="https://gmail.com" class="btn btn-info btn-lg"> <span -->
<!-- 						class="glyphicon glyphicon-envelope"></span> Gmail -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>



</body>
</html>