<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	
	<title>User Registration Form</title>
	<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
	
</head>
<body>
   <jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
 	<div class="generic-container">
 	
  
		<div class="well lead">User Registration Form</div>
		
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="idUser" id="idUser"/>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="username">Username</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
							<form:input type="text" path="username" id="username" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="username" id="username" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="username" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password</label>
					<div class="col-md-7">
<%-- 						<c:choose> --%>
<%-- 					    <c:when test="${edit}"> --%>
<%-- 						<form:input type="password" path="password" id="password" class="form-control input-sm" disabled="true" /> --%>
<%-- 						</c:when> --%>
<%-- 							<c:otherwise> --%>
								<form:input type="password" path="password" id="password" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="password" class="help-inline"/>
								</div>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
						
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" id="option" multiple="false" itemValue="idRole" itemLabel="description" class="form-control input-sm" />
						<!-- allaksa to path kai to for m roles apo userprofiles -->
						<c:if test = "${roles == '2'}">
						<input type="hidden" id="userProfiles" name="description" value="guest"/>
						</c:if>
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>

					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='admin/users' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='admin/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>