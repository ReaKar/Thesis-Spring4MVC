<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	
	<title>List of Courses</title>
	
	<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>
	
	<link href="<c:url value='/static/js/confirmation.js' />" rel="stylesheet"></link>
</head>

<body>
   <jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
   
	<div class="generic-container">
		
		<div class="panel panel-default">
			 
		  	<div class="panel-heading"><span class="lead">List of Courses </span></div>
		  	 <div style="overflow:scroll;height:260px;width:100%;overflow:auto"> <!-- egw t ebala ayo g scroll bar -->
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Semester</th>
				        <th>Title</th>
				        <th>Active</th>
				        <th>Opendate</th>
				        <th>Closedate</th>
				        <sec:authorize access="hasRole('admin')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('user')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${courses}" var="courses">
					<tr>
						<td>${courses.semester}</td>
						<td>${courses.title}</td>
						<td>${courses.active}</td>
						<td>${courses.opendate}</td>
						<td>${courses.closedate}</td>
						<!-- apo choose mexri choose toy lew poio koympi tha emfanistei analoga an t mathima einai active -->
						<c:choose>         
                       <c:when test="${courses.active=='false'}">
						
						<sec:authorize access="hasRole('admin')">
					  		<td><a href="<c:url value='/admin/active-courses-${courses.idCourse}' />" class="btn btn-success custom-width">Activated</a></td>
				        </sec:authorize>
				        </c:when>    
                       <c:otherwise>
				        <sec:authorize access="hasRole('admin')">
					  		<td><a href="<c:url value='/admin/inactive-courses-${courses.idCourse}' />" class="btn btn-danger custom-width">Deactivated</a></td>
				        </sec:authorize>
				        </c:otherwise>
                        </c:choose>
					    <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/edit-courses-${courses.idCourse}' />" class="btn btn-primary custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('admin')">
							<td><a href="<c:url value='/admin/delete-courses-${courses.idCourse}' />" onclick="return confirm('Are you sure you want to delete this course?')" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
        				
        				
					</tr>
					
				</c:forEach>
				
	    		</tbody>
	    		
	    	</table>
	    	<script type="text/javascript">
//     $('.confirmation').on('click', function () {
//         return confirm('Are you sure?');
//     });
$(function() {
    $('#someLink').click(function() {
        return confirm('Are you sure you want to delete this item?');
    });
});
</script>
		</div>
		<sec:authorize access="hasRole('admin')">
		 	<div class="well">
		 		<a href="<c:url value='/admin/createcourse' />">Create Course</a>
		 	</div>
	 	</sec:authorize> 
	 	</div>
   	</div>
</body>
</html>