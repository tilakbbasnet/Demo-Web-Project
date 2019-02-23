<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>

<style type="text/css">
.topbar {
width:100%;
   	background-color: blue;
   	height: 70px;
   	display: inline-block;
   	text-align: center;
}

.employeeList, .usermsg {
    color:white;
    font-size: 19px;
    float:left;
    font-weight: bold;
    margin-left: 15px;
    margin-top: 24px;
}

.heading {
    color: white;
    font-size: 18px;
    font-weight: bold;
    padding: 12px;
}

.logoutbtn {
	margin-top: -38;
    margin-right: 13;
    float: right;
	border: 1px solid black;
	border-radius : 5px;
	padding: 3px;
    width:100px;
}
</style>

<body>
<form action="${pageContext.servletContext.contextPath}/logout">
<div class="topbar">
<div class="usermsg">
Hello <b style="color:#47d61b;">${username}!</b>
</div>

<div class="heading">
Employye Management System <br>Version 1.0
</div>

<div>
<input class="logoutbtn" type="Submit" Value="Log Out"/>
</div>

</form>
</div>
</body>
</html>