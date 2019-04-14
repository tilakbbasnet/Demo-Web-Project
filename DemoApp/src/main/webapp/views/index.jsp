<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Demo App:Login</title>
</head>

<style type="text/css">
.btn {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 6px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  margin: 4px 2px;
  font-weight: bold;
  border-radius: 4px;
}

.textbox {
border-radius: 2px;
}

.signUpTable {
     border-collapse:separate; 
     border-spacing:0 15px;
}

</style>

<body class="mainbody">
<center>
<h2>Employee Management System</h2>

<form action="${pageContext.servletContext.contextPath}/userSignUp" method="POST">
<table class="signUpTable">

<tr><td>Are you new to Employee Management System ? Sign up !!</td></tr>

<tr>
<td><b>*First Name:</b> </td>
<td><input type="text" class="textbox" name="firstName" placeholder="First Name" value="${User.firstName}" required/></td>
</tr>

<tr>
<td><b>*Last Name:</b> </td>
<td><input type="text" class="textbox" name="lastName" placeholder="Last Name" value="${User.lastName}" required/></td>
</tr>

<!-- assining gender value of selected employee to variable -->
<c:set var="gender" value="${User.gender}"/>

<tr>
<td><b>*Gender: </b></td>
<td>
<input type="radio" value="Male" name="gender" <c:if test="${gender=='Male'}">checked</c:if> required/>Male
<input type="radio" value="Female" name="gender" <c:if test="${gender=='Female'}">checked</c:if>/>Female
<td>
</tr>

<tr>
<td><b>*Email:</b> </td>
<td><input type="text" class="textbox" name="email" placeholder="Email" value="${User.email}" required/></td>
</tr>

<tr>
<td><b>*Username:</b> </td>
<td><input type="text" class="textbox" name="userName" placeholder="Username" value="${User.userName}" required/></td>
</tr>

<tr>
<td><b>*Password:</b> </td>
<td><input class="textbox" type="password" name="password" placeholder="Password" value="${User.password}" required/></td>
</tr>

<tr>
<td><b>*Re-Enter Password:</b> </td>
<td><input class="textbox" type="password" name="confirmPassword" placeholder="Re-Enter Password" value="${User.confirmPassword}" required/></td>
</tr>

<tr/>

<tr>
<td>
<input type="Submit" Value="Sign Up" class="btn textbox"/>
<input type="button" class="btn textbox" id="btnLogin" value="Log In" onclick="location.href='${pageContext.servletContext.contextPath}/userLogin'"/>
</td>
</tr>
</table>
</form>

<b style="color:red;">${param.userSignInfo}</b>
</center>
</body>
</html>