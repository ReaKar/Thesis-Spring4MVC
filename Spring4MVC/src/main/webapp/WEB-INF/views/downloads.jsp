<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Home</title>

<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Documents for project <i>${project.title}</i></span>
			</div>
			<div class="tablecontainer">
					  	 <div style="overflow:scroll;height:260px;width:100%;overflow:auto"> <!-- egw t ebala ayo g scroll bar -->
			
				<table class="table table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<th>Filepath</th>
							<th>Date of Submission</th>
							
							<th>LastName</th>
							<th>Project</th>
						
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documents}" var="doc" varStatus="counter">
							<tr>
								<td>${counter.index + 1}</td>
								<td>${doc.filepath}</td>
								<td>${doc.when}</td>
								
								<td>${doc.user.lastName}</td>
								<td>${doc.project.title}</td>
								
								<td><a
									href="<c:url value='/admin/download-document-${project.idProject}-${doc.idSubmission}' />"
									class="btn btn-success custom-width">Download</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		

				
			</div>
		

	
</body>
</html>