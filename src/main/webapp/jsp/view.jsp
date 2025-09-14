<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Employee</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />

</head>
<body>
	<h2 class="mb-4">Employee Details</h2>

	<div class="card">
		<div class="card-body">
			<p>
				<strong>ID:</strong> ${employee.id}
			</p>
			<p>
				<strong>Name:</strong> ${employee.name}
			</p>
			<p>
				<strong>Email:</strong> ${employee.email}
			</p>
			<p>
				<strong>Department:</strong> ${employee.department}
			</p>
			<p>
				<strong>Salary:</strong> ${employee.salary}
			</p>
		</div>
	</div>

	<a href="employees?action=list" class="btn btn-primary mt-3">Back
		to List</a>

</body>
</html>