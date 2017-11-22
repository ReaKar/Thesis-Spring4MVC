 <%@ page language="java" contentType="text/html; charset=UTF-8" 
  	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Course Form</title>
<jsp:include page="/WEB-INF/include/stylesheets.jsp"></jsp:include>


</head>

<body>
	<jsp:include page="/WEB-INF/include/menu_admin.jsp"></jsp:include>
	<div class="generic-container">


		<div class="well lead">Create Course</div>
		<form:form method="POST" modelAttribute="courses"
			class="form-horizontal" accept-charset="UTF-8">
			<form:input type="hidden" path="idCourse" id="idCourse" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="semester">Semester</label>
					<div class="col-md-7">
						<form:input type="text" path="semester" id="semester"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="semester" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="title">Title</label>
					<div class="col-md-7">
						<form:input type="text" path="title" id="title"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="title" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="description">Description</label>
					<div class="col-md-7">

						<form:input type="text" path="description" id="description"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="description" class="help-inline" />
						</div>

					</div>
				</div>
			</div>

<!-- 			<div class="row"> -->
<!-- 				<div class="form-group col-md-12"> -->
<!-- 					<label class="col-md-3 control-lable" for="filepath">Filepath</label> -->
<!-- 					<div class="col-md-7"> -->
<%-- 						<form:input type="text" path="filepath" id="filepath" --%>
<%-- 							class="form-control input-sm" /> --%>
<!-- 						<div class="has-error"> -->
<%-- 							<form:errors path="filepath" class="help-inline" /> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="active">Active</label>
					<div class="col-md-7">
					<form:select path="active" id="active">
                   <option value="false">False</option>
                   <option value="true">True</option>          
<!--              edw t ekana commobox -->
                          </form:select>
						<div class="has-error">
							<form:errors path="active" class="help-inline" />
						</div>
<%-- 						<form:input type="text" path="active" id="active" --%>
<%-- 							class="form-control input-sm" /> --%>
<!-- 						<div class="has-error">  edw t eixa san text  -->
<%-- 							<form:errors path="active" class="help-inline" /> --%>
<!-- 						</div> -->
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="opendate">Opendate</label>
					<div class="col-md-7">
						<%-- 					<div class="input-group d
						ate"  id=" > --%>
						<%-- 						<form:input type="text" path="opendate" id="opendate" --%>
						<!-- 							class="form-control input-sm" placeholder="YYYY-MM-DD" /> -->

						<div class='input-group date' id='opendate'>
							<form:input type='text' class="form-control"
								path="opendate" placeholder="YYYY-MM-DD HH:mm" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>

						<div class="has-error">
							<form:errors path="opendate" class="help-inline" />
						</div>

						<script type="text/javascript">
							$(function() {
								$('#opendate').datetimepicker({format: 'YYYY-MM-DD HH:mm'});
							});
						</script>


					</div>
				</div>
			</div>




			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="closedate">Closedate</label>
					<div class="col-md-7">
						<div class='input-group date' id='closedate'>
							<form:input type='text' class="form-control"
								path="closedate" placeholder="YYYY-MM-DD HH:mm" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>

						<div class="has-error">
							<form:errors path="closedate" class="help-inline" />
						</div>

						<script type="text/javascript">
							$(function() {
								$('#closedate').datetimepicker({format: 'YYYY-MM-DD HH:mm'});
							});
						</script>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/admin/courseslist' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Create"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/admin/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>






		</form:form>
	</div>
</body>
</html>