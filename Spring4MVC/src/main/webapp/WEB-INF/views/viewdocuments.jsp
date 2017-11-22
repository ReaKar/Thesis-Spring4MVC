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
    <div class="generic-container">
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Documents for project <i>${project.title}</i></span></div>
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
                            <td><a href="<c:url value='/download-document-${user.idUser}-${submit.idSubmission}' />" class="btn btn-success custom-width">download</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
   
    
    </div>
</body>
</html>