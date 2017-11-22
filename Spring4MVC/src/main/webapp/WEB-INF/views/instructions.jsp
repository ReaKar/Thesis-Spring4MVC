<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
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



					<c:forEach items="${courses}" var="courses">


						<a href="<c:url value='/user/course-${courses.title}' />"
							class="list-group-item">${courses.title}</a>






					</c:forEach>

				</div>
			</div>
			<div class="col-sm-8 text-left">
				<h1  class="header">
            Submission Instructions
             </h1>

				<p>Αφού έχετε εισέλθει στο σύστημα :</p>
				<p>1. Θα επιλέξετε το μάθημα το οποίο παρακολουθείτε απο την διαθέσιμη λίστα μαθημάτων.</p>
			    <p>2. Στην κατάλληλη εργασία για την οποία θέλετε να υποβάλλετε την ασκησή σας θα πατήστε το link upload project.</p>
			    <p>3. Ανεβάστε την ασκησή σας σε μορφή .zip ή tar και στην συνέχεια πατήστε submit. </p>
			    <p>3. Στην συνέχεια θα μπορείτε να δείτε την άσκηση που υποβάλατε, αλλά και να κάνετε όσες τροποποιήσεις θέλετε ξανα
			    υποβάλοντας την ασκησή σας. </p>
			    
			    <p> ΠΡΟΣΟΧΗ! Έχετε δικαίωμα να υποβάλλετε την εργασία σας όσες φορές θέλετε αλλά θα ληφθεί υπόψην μόνο η τελευταία υποβολη </p>
			    
			    <p> ΟΙ ΕΡΓΑΣΙΕΣ ΜΠΟΡΟΥΝ ΝΑ ΥΠΟΒΛΗΘΟΥΝ ΑΠΟΚΛΕΙΣΤΙΚΑ ΕΝΤΟΣ ΤΟΥ ΧΡΟΝΙΚΟΥ ΔΙΑΣΤΗΜΑΤΟΣ ΠΟΥ ΕΧΕΙ ΟΡΙΣΘΕΙ
			    </p>
			    <p> Καμία εκπρόθεσμη υποβολή δεν θα γίνεται δεκτή
			    </p>
			    
			   
			    
			    
			    
			    
			    
				
				
				
				
				<hr>
			
				
			</div>

		</div>
	</div>



</body>
</html>