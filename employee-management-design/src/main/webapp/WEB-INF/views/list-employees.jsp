<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>Welcome to Employee Management System</title>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<h2 style="text-align: center">Employee Management System</h2>
		<hr>

		<!-- Add a search form -->
		<form action="/employee-management-design/search" class="form-inline">

			<!-- Add a button -->
			<a href="/employee-management-design/showFormForAdd"
				class="btn btn-primary btn-sm mb-3"> Add Employee </a>
				<input type="search" name="name" placeholder="Name"
				class="form-control-sm ml-5 mr-2 mb-3" />

			<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>
			<a href="/employee-management-design/sort" class="btn btn-primary btn-sm mb-3 mx-auto">Sort Employees</a>
			<a href="/employee-management-design/logout" class="btn btn-primary btn-sm mb-3 mx-auto">Logout</a>
				

		</form>
		
		<table class="table table-bordered table-striped">
			<thead class="thead-dark" style="text-align: center">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody style="text-align: center">
				<c:forEach items="${Employee}" var="tempEmployee">
					<tr>
						<td><c:out value="${tempEmployee.firstName}" /></td>
						<td><c:out value="${tempEmployee.lastName}" /></td>
						<td><c:out value="${tempEmployee.email}" /></td>
						<td style="text-align: center">
							<!-- Add "update" button/link --> <a
							href="/employee-management-design/showFormForUpdate?EmployeeId=${tempEmployee.id}"
							class="btn btn-primary btn-sm"> Update </a> <a
							href="/employee-management-design/delete?EmployeeId=${tempEmployee.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
		

	</div>


</body>
</html>