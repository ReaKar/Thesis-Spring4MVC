<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
<title>Home</title>

<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="/WEB-INF/include/menu_user.jsp"></jsp:include>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<div class="list-group">
					<tbody>
						<h4 class="list-group-item-heading">
							<a href="<c:url value='/user/home' />"
								class="list-group-item active">All Courses</a>
						</h4>

						<h4>
							<a href="<c:url value='/user/course-${courses.title}' />"
								class=" list-group-item-dark">${courses.title}</a>
						</h4>
					</tbody>
				</div>
			</div>
			<div class="col-sm-8 text-left">

				<h2  class ="title-course">
					<strong>${courses.title}</strong>
				</h2>
				<h5>
					<strong>(${courses.semester}o Semester)</strong>
				</h5>

				<hr />
				<h4>Course Information</h4>
				<p>
					<strong>${courses.description}</strong>
				</p>

				<hr />

				<h4>Summary of current assignments</h4>


				<c:if test="${not empty courses}">
					<c:set var="x" scope="request" value="${courses.projectList.size() }" />
					<c:set var="current_time" value="<%=new Date()%>" />
				
					
				
                  
                  
		          
		            
					
					

					<c:forEach items="${courses.projectList}" var="project">
						<c:set var="isOpen" value="${current_time < project.closedate and current_time > project.opendate}" />
					   <c:set  var="sum" value="${project.closedate.time - current_time.time}" />
					    <fmt:parseNumber var="parsedDays" integerOnly="true" type="number" value="${(sum/(1000 * 60 * 60 * 24)) % 365}" />
					   
					    
					    <fmt:parseNumber var="parsedHours" integerOnly="true" type="number" value="${sum/(1000 * 60 * 60) % 24}" />
					   
					    
					    <fmt:parseNumber var="parsedMinutes" integerOnly="true" type="number" value="${sum/(1000 * 60) % 60}" />
					    
					    
					  		
					  					  
<%--   					  <c:if test="${sum > 0}"> --%>
<%-- 			  				<b class="timeRemaining"> <i> Remaining time: ${parsedDays} days and ${parsedHours}  --%>
<%-- 			  				 hours and ${parsedMinutes} minutes </i></b> --%>
<%--   					  </c:if> --%>
					   						
                 
						
						<c:if test="${isOpen}">					
						<h3 class="assignment_open">Assignment ${x}
						    <c:if test="${sum > 0}">
			  				<h5 class="timeRemaining"> <i> Remaining time: ${parsedDays} days and ${parsedHours} 
			  				 hours and ${parsedMinutes} minutes </i></h5>
  					  </c:if>
						
						 </h3> 
						</c:if>
						<c:if test="${not isOpen}">
						<h3 class="assignment_closed">Assignment ${x}</h3>
						</c:if>
						<h4>${project.title}</h4> 
					
						
						<p>
							Open date: <b>${project.opendate}</b>
						</p>
						<p>
							Close date: <b>${project.closedate}</b>

							<c:if test="${current_time < project.opendate}">
								<b class="soon"><i>SOON AVAILABLE</i></b>
							</c:if>
							
							<c:if test="${current_time > project.closedate}">
								<b class="expired" ><i>EXPIRED</i></b>
							</c:if>
						</p>

						<pre>																								
							<p>${project.description}</p>
						</pre>

						<c:if test="${isOpen}">
							<p>
								<a href="<c:url value='/user/manage-documents-${project.idProject}' />">Manage
									your documents for this assignment and submit your project</a>
							</p>
						</c:if>
						
						<c:if test="${not isOpen}">
							<p>
								<a href="<c:url value='/user/view-documents-${project.idProject}' />">View
									your documents for this assignment</a>
							</p>
						</c:if>

						<hr />

						<c:set var="x" scope="request" value="${x-1}" />
					</c:forEach>
				</c:if>




				


			</div>
	
		</div>
	</div>



</body>
</html>