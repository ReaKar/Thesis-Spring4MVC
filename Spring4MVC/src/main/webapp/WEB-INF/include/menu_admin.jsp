<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value='/admin/home'/>"><strong>${loggedinuser}</strong></a>
		</div>


		<ul class="nav navbar-nav">
			<li class="active"><a href="<c:url value='/admin/home' />">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/admin/allusers' />">Manage Users</a></li>
					<li><a href="<c:url value='/admin/users' />">Accept Users</a></li>
					<li><a href="<c:url value='/admin/newuser' />">Create User</a></li>
				</ul>
			</li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Courses <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/admin/courseslist' />">Manage Courses</a></li>
					<li><a href="<c:url value='/admin/createcourse' />">Create Course</a></li>

				</ul>
			</li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Projects <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/admin/projects' />">Manage Projects</a></li>
					<li><a href="<c:url value='/admin/createproject' />">Create Project</a></li>
				</ul>
			</li>
			
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="<c:url value='/logout'/>"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
</nav>


