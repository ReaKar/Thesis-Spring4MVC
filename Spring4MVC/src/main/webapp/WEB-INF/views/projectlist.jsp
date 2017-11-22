<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html>

<head>
	
	<title>List of Projects</title>
	<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>
   <jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
	<div class="generic-container">
		
		<div class="panel panel-default">
			 
		  	<div class="panel-heading"><span class="lead">List of Projects </span></div>
		  	 <div style="overflow:scroll;height:260px;width:100%;overflow:auto"> <!-- egw t ebala ayo g scroll bar -->
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        
				        <th>Title</th>
				        <th>Opendate</th>
				        <th>Closedate</th>
				        <th>Course</th>
				        <sec:authorize access="hasRole('admin')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('user')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${project}" var="project">
					<tr>
						
						<td>${project.title}</td>
						<td>${project.opendate}</td>
						<td>${project.closedate}</td>
				
						<td>${project.idCourse.title}</td>
<%--                         	<td>${course.courses.title}</td> --%>
						
						
					    <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/edit-project-${project.idProject}' />" class="btn btn-primary custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/delete-project-${project.idProject}' />"  onclick="return confirm('Are you sure you want to delete this project?')" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
        				<sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/downloads-${project.idProject}' />" > View Submits</a></td>
				        </sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('admin')">
		 	<div class="well">
		 		<a href="<c:url value='/admin/createproject' />">Create Project</a>
		 	</div>
	 	</sec:authorize> 
	 	</div>
   	</div>
</body>