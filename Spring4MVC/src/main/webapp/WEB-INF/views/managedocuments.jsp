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

	<jsp:include page="/WEB-INF/include/menu_user.jsp"></jsp:include>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Documents for project <i>${project.title}</i></span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<th>when</th>
							<th>Filename</th>
							<th>Filetype</th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documents}" var="doc" varStatus="counter">
							<tr>
								<td>${counter.index + 1}</td>
							
								<td>${doc.when}</td>
								<td>${doc.name}</td>
								<td>${doc.type}</td>
								<td><a
									href="<c:url value='/user/download-document-${project.idProject}-${doc.idSubmission}' />"
									class="btn btn-success custom-width">download</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Upload New Document</span>
			</div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="fileBucket"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Upload a
								document</label>
							<div class="col-md-7">
								<form:input type="file" path="file" id="file"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="file" class="help-inline" />
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload"
								class="btn btn-primary btn-sm">
						</div>
					</div>

				</form:form>
			</div>
		</div>

	</div>
</body>
</html>