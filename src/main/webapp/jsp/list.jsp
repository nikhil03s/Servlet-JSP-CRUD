<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />

</head>
<body class="container mt-4">
	<h2 class="mb-4">Employee List</h2>
	<a href="employees?action=new" class="btn btn-primary mb-3">Add New
		Employee</a>

	<table class="table table-bordered table-striped">
		<thead class="table-dark">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Department</th>
				<th>Salary</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${employeeList}">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.name}</td>
					<td>${emp.email}</td>
					<td>${emp.department}</td>
					<td>${emp.salary}</td>
					<td><a href="employees?action=view&id=${emp.id}"
						class="btn btn-info btn-sm">View</a> <a
						href="employees?action=edit&id=${emp.id}"
						class="btn btn-warning btn-sm">Edit</a> <a
						href="employees?action=delete&id=${emp.id}"
						class="btn btn-danger btn-sm"
						onclick="return confirm('Are you sure you want to delete this employee?')">
							Delete </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>