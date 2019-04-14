<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include  page="header.jsp"/>

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
  	top: -10px;
}
.maintable {
	position: relative;
  	top: 90px;
}
.emplist {
  	width: 100%;
  	margin-bottom: 80px;
}

.filters, .welcomemsg {
	float: right;
	position: relative;
	right: 2px;
}
.filters {
  	position: relative;
  	right: 0px;
  	top: -5px;
  	left: 1px;
}

#filterbyid {
	padding-left :93px !important;
	margin-top :30px !important;
}

.textbox {
border-radius: 2px;
}

.welcometitle{
position: relative;
top: 20px;
color:blue;
}

.refresh {
    float: right;
    margin-right: 30px;
}

table td, th{
	text-align: center;
	padding: 5px;
}

#excelbtn, #savebtn {
	margin-right: 45px;
    float: right;
	border: 1px solid black;
	border-radius : 5px;
	padding: 4px;
    width:100px;
    background-color: green;
    color: white;
    font-size: 14px;
    margin-bottom: 12px;
}
}
</style>
<body>
<center>

<div class="addemp">
<input type="button" id="excelbtn" value="Excel" onclick="location.href='${pageContext.servletContext.contextPath}/excelGeneration'"/>
<p> <b>Total No. of employees: ${totalemployee}</b> </p>
<input id="savebtn" type="button" value="Add Employee" onclick="location.href='${pageContext.servletContext.contextPath}/addemployee'"/> <br/><br/>
</div>

<div class="filters">
<a class="refresh" href="${pageContext.servletContext.contextPath}/refreshEmployeeList"> <strong> Refresh</strong> </a>
<form id="filterbyid" action="${pageContext.servletContext.contextPath}/getemployeebyid" method="post">
<b>Find By ID:</b> <input class="textbox" type="text" name="id" placeholder="Enter ID" value="${filteredId}" required/> <br/>
</form>

<form action="${pageContext.servletContext.contextPath}/getemployeebyquery" method="post" >
<b>Find By Filter Condition:</b> <input class="textbox" type="text" name="subQuery" placeholder="Enter sub-query" value="${filterCond}" required/>
</form>
</div>

<form class="maintable">
<h2> Employee List</h2>

<table class="emplist">
<tr>
<th>S.No.</th>
<th>Employee ID</th>
<th>Full Name</th>
<th>Email</th>
<th>Gender</th>
<th>Age</th>
<th>Salary</th>
<th>Action</th>
<th>Action</th>
</tr>


<c:set var="count" value="0" scope="page"/>
<c:forEach items="${employeeList}" var="emp">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr>
<td>${count}</td>
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