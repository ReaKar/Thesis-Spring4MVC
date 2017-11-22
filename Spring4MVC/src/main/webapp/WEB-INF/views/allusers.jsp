<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<title>List of All Users</title>
	
	<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>

  
	<div class="generic-container">
		
		
		<div class="panel panel-default">
		
		  	<div class="panel-heading"><span class="lead">List of All Users </span><h3 style="text-align:right"> Total number of Users : ${number}</h3> </div>
		  
		  	 <div style="overflow:scroll;height:260px;width:100%;overflow:auto"> <!-- egw t ebala ayo g scroll bar -->
			<table class="table table-hover">
		 	
	    		<thead>
		      		<tr>
				        <th>Lastname</th>
				        <th>Firstname</th>
				        <th>Email</th>
				        <th>Username</th>
				        <sec:authorize access="hasRole('admin')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('user')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.lastName}</td>
						<td>${user.firstName}</td>
						<td>${user.email}</td>
						<td>${user.username}</td>
						
					    <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/edit-user-${user.username}' />" class="btn btn-primary custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/delete-user-${user.username}' />"  onclick="return confirm('Are you sure you want to delete this user?')" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('admin')">
		 	<div class="well">
		 	
		 		<a href="<c:url value='/admin/newuser' />">Add New User    </a> 
		 		<div class=text-right> 
							<a href="<c:url value='/admin/delete-all' />"  onclick="return confirm('ATTENTION! Are you sure you want to delete all users?')" class="btn btn-danger custom-width">Delete All</a>
				       </div>
		 		
		 		
		 	</div>
		 		
	 	</sec:authorize>
	 </div>
   	</div>
</body>
</html>