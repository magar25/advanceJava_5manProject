<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="container">

	<%@include file="include/nav.jsp" %>
		
		
		<h2>Register New shopping centers</h2>
		<br> <br>
		<form action="register" method="post">
			<div class="ml-3 ">
				<label class="form-label">New Shopping Center Name</label> <input
					type="text" name="shopping" class="form-control" id="shopping"
					required>

			</div>
			<br>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

</body>
</html>