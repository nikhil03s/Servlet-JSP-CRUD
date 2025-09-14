<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Form</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
</head>
<body class="container mt-4">

	<h2 class="mb-4">
		<c:choose>
			<c:when test="${formAction == 'update'}">Edit Employee</c:when>
			<c:otherwise>Add New Employee</c:otherwise>
		</c:choose>
	</h2>

	<form action="employees?action=${formAction}" method="post"
		class="needs-validation">
		<c:if test="${formAction == 'update'}">
			<input type="hidden" name="id" value="${employee.id}" />
		</c:if>

		<div class="mb-3">
			<label class="form-label">Name</label> <input type="text" name="name"
				value="${employee.name}" class="form-control" required />
		</div>

		<div class="mb-3">
			<label class="form-label">Email</label> <input type="email"
				name="email" value="${employee.email}" class="form-control" required />
		</div>

		<div class="mb-3">
			<label class="form-label">Department</label> <input type="text"
				name="department" value="${employee.department}"
				class="form-control" required />
		</div>
		
		<div class="mb-3">
			<label class="form-label">Salary</label> <input type="text"
				name="salary" value="${employee.salary}"
				class="form-control" required />
		</div>

		<button type="submit" class="btn btn-success">
			<c:choose>
				<c:when test="${formAction == 'update'}">Update</c:when>
				<c:otherwise>Save</c:otherwise>
			</c:choose>
		</button>
		<a href="employees?action=list" class="btn btn-secondary">Cancel</a>
	</form>
</body>
</html>