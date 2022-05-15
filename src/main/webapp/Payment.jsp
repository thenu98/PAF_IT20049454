<%@page import="model.Pay"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/product.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payment Management</h1>

				<form id="formProduct" name="formProduct" method="post" action="Payment.jsp">


						Full Name: <input id="fullName" name="fullName" type="text"
						class="form-control form-control-sm"> 
						
						<br>NIC: <input id="NIC" name="NIC" type="text"
						class="form-control form-control-sm"> 
						
						<br>Amount: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> 
						
						<br>Date: <input id="date" name="date" type="date"
						class="form-control form-control-sm"> 
						
						<br>Bank Name: <input id="bankName" name="bankName" type="text"
						class="form-control form-control-sm"> 
						
						<br>Debit Card: <input id="debitCard" name="debitCard" type="text"
						class="form-control form-control-sm"> 
						
						<br>OTP Number: <input id="otpNumber" name="otpNumber" type="text"
						class="form-control form-control-sm"> 
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidProjectIDSave" name="hidProjectIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProjectGrid">
					<%
						Pay projectObj = new Pay();
						out.print(projectObj.readPayment());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
