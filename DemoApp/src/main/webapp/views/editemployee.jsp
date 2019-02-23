<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include  page="header.jsp"/>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Demo App:Edit Employee</title>
</head>

<style type="text/css">
.empList {
    float: left;
	position: relative;
  	top: 10px;
  	left: 25px;
}

.updatebtn {
	border: 1px solid black;
	border-radius : 4px;
	padding: 7px;
}

td input {
	border: 1px solid black;
    border-radius:3px;
}

.edittable { 
     border-collapse:separate; 
     border-spacing:0 15px; 
}
</style>

<body>
<div class="empList">
<a href="${pageContext.servletContext.contextPath}/refreshEmployeeList"> <strong>Back</strong> </a>
</div>

<center>
<h3> Update Employee Info</h3>
<form action="${pageContext.servletContext.contextPath}/editemployee" method="post">
<table class="edittable">

<tr>
<td>ID: </td>
<td><input type="number" name="id" value="${updatedEmployee.id}" readonly/>
</tr>

<tr>
<td>*Name: </td>
<td><input type="text" name="name" value="${updatedEmployee.name}" required/><td>
</tr>

<tr>
<td>*Email: </td>
<td><input type="text" name="email" value="${updatedEmployee.email}" required/><td>
</tr>

<!-- assining gender value of selected employee to variable -->
<c:set var="gender" value="${updatedEmployee.gender}"/>

<tr>
<td>*Gender: </td>
<td>
<input type="radio" value="Male" name="gender" <c:if test="${gender=='Male'}">checked</c:if> required/>Male
<input type="radio" value="Female" name="gender" <c:if test="${gender=='Female'}">checked</c:if> />Female
<td>
</tr>

<tr>
<td>*Age:</td> 
<td><input type="number" name="age" min="1" value="${updatedEmployee.age}" required/><td>
</tr>

<tr>
<td>*Salary: </td>
<td><input type="number" name="salary" min="1" value="${updatedEmployee.salary}" required/><td>
</tr>

<tr>
<td><input class="updatebtn" type="Submit" value="Update"/><td>
</tr>

</table>
</form>
</center>
</body>
</html>