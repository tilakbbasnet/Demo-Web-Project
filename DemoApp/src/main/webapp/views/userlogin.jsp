<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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

.mainbody{
position: relative;
top: 90px;
border-style: solid;
border-color : blue;
width: 550px;
height: 360px;
margin:0 auto;
display: grid;
}

.logintable { 
     border-collapse:separate; 
     border-spacing:0 15px;
}

.btnAdmin{
text-align: center;
background-color: blue;
color: white;
border-radius: 2px;
padding: 8px;
float: right;
margin-right : 5px;
}

#deptLogin {
	margin-top:35px;
	color: blue;
}
</style>

<center>
<h2>Employee Management System</h2>

<div>
<form action="${pageContext.servletContext.contextPath}/adminHome" method="POST">
<input type="Submit" class="btn btnAdmin" id="btnAdminLogin" value="Admin" />
</form>
</div>

<body class="mainbody">
<h2 id="deptLogin"> Department Login </h2>

<form action="${pageContext.servletContext.contextPath}/home" method="post">
<table class="logintable">

<tr>
<td><b>Username:</b> </td>
<td><input type="text" class="textbox" name="userName" placeholder="Username" value="${param.userName}" required/></td>
</tr>

<tr>
<td><b>Password:</b> </td>
<td><input class="textbox" type="password" name="password" placeholder="Password" value="${param.password}" required/></td>
</tr>

<tr>
<td></td>
<td><input type="checkbox" name="rememberMe" checked>Remember Me</td>
</tr>

<tr>
<td>
<input class="btn textbox" type="submit" value="Login"/>
</td>
</tr>
<b style="color:red;">${param.loginInfoMessage}</b>

</table>

</form>
</body>
</center>
</html>