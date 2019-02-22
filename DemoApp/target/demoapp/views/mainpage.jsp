<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Demo App:Home Page</title>
</head>

<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
.addemp {
    float: left;
	position: relative;
  	top: 38px;
}
.maintable {
	position: relative;
  	top: 140px;
}

.filters, .welcomemsg {
	float: right;
	position: relative;
	right: 2px;
}
.filters {
  	position: relative;
  	top: 52px;
  	right: 0px;
  	left: 1px;
}

#filterbyid {
	padding-left :93px !important;
}

.textbox {
border-radius: 2px;
}

.welcometitle{
position: relative;
top: 20px;
color:blue;
}
</style>
<body>
<center>
<h1 class="welcometitle">Welcome to Employee Management System..</h1>

<div class="addemp">
<p> <b>Total No. of employees: ${totalemployee}</b> </p>
<a href="${pageContext.servletContext.contextPath}/addemployee"> <strong> Add Employee </strong> </a> <br/><br/>
<a href="${pageContext.servletContext.contextPath}/refreshEmployeeList"> <strong> Refresh Employee List </strong> </a>
</div>

<div class="filters">
<form id="filterbyid" action="${pageContext.servletContext.contextPath}/getemployeebyid" method="post">
<b>Find By ID:</b> <input class="textbox" type="text" name="id" placeholder="Enter ID"/> <br/>
</form>

<form action="${pageContext.servletContext.contextPath}/getemployeebyquery" method="post" >
<b>Find By Filter Condition:</b> <input class="textbox" type="text" name="subQuery" placeholder="Enter sub-query"/>
</form>
</div>

<form class="maintable">
<h2> Employee List</h2>

<table>
<tr>
<th>Employee ID</th>
<th>Full Name</th>
<th>Email</th>
<th>Gender</th>
<th>Age</th>
<th>Salary</th>
<th>Action</th>
<th>Action</th>
</tr>


<c:forEach items="${employeeList}" var="emp">
<tr>
<td>${emp.id}</td>
<td>${emp.name}</td>
<td>${emp.email}</td>
<td>${emp.gender}</td>
<td>${emp.age}</td>
<td>${emp.salary}</td>
<td><a href="${pageContext.servletContext.contextPath}/editemployee?id=${emp.id}">Edit</a></td>
<td><a href="${pageContext.servletContext.contextPath}/deleteemployee?id=${emp.id}">Delete</a></td>
</tr>
</c:forEach>

<tr><p style="color:#B10DC9;">${param.msg}</p></tr>
<tr><p style="color:#B10DC9;">${filtermsg}</p></tr>

</table>
</form>
</center>
</body>
</html>