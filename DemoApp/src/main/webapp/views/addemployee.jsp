<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include  page="header.jsp"/>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Demo App:Employee Registration</title>
</head>

<style type="text/css">
.empList {
    float: left;
	position: relative;
  	top: 10px;
  	left: 25px;
}

td input {
	border: 1px solid black;
    border-radius:3px;
}

.inputtable { 
     border-collapse:separate; 
     border-spacing:0 15px; 
}

.registerbtn {
	border: 1px solid black;
	border-radius : 4px;
	padding: 7px;
}
</style>

<body>
<div class="empList">
<a href="${pageContext.servletContext.contextPath}/refreshEmployeeList"> <strong>Back</strong> </a>
</div>

<center>
<h3> Employee Registration</h3>
<form action="${pageContext.servletContext.contextPath}/addemployee" method="POST">
<table class="inputtable">

<tr>
<td>ID:</td>
<td><input type="number" name="id" value="0" required/> &nbsp;&nbsp;(Employee id is auto-generated so leave 0 for new employee as for now<br> OR enter valid employee id to update info)</td>
</tr>

<tr>
<td>*Name: </td>
<td><input type="text" name="name" required/><td>
</tr>

<tr>
<td>*Email: </td>
<td><input type="text" name="email" required/><td>
</tr>

<tr>
<td>*Gender: </td>
<td>
<input type="radio" value="Male" name="gender" checked required/>Male
<input type="radio" value="Female" name="gender"/>Female
<td>
</tr>

<tr>
<td>*Age:</td> 
<td><input type="number" name="age" min="1" required/><td>
</tr>

<tr>
<td>*Salary: </td>
<td><input type="number" name="salary" min="1" required/><td>
</tr>

<tr>
<td><input class="registerbtn" type="Submit" value="Save"/><td>
</tr>

</table>
</form>
</center>
</body>
</html>