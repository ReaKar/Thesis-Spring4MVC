<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value='/user/home'/>">
				<strong><sec:authentication property="principal.username" /></strong>
			</a>
		</div>	


		<ul class="nav navbar-nav">
			<li class="active"><a href="<c:url value='/user/home' />">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="/user/projects">Projects <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/user/all-projects' />">My Projects</a></li>
					
					
				</ul>
			</li>
<!-- 			<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 				data-toggle="dropdown" href="#">Informations <span class="caret"></span></a> -->
<!-- 				<ul class="dropdown-menu"> -->
<%-- 					<li><a href="<c:url value='/admin/courseslist' />">Announcement</a></li> --%>
<%-- 					<li><a href="<c:url value='/admin/courseslist' />">Links</a></li> --%>
					

<!-- 				</ul> -->
<!-- 			</li> -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Support <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/user/instructions' />">Instructions</a></li>
					
				</ul>
			</li>
			
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="<c:url value='/logout'/>"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
</nav>