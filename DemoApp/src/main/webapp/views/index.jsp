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
}
</style>

<body class="mainbody">
<center>
<h2> Admin Login </h2>
<form action="${pageContext.servletContext.contextPath}/home" method="post">
<table>

<tr>
<td><b>Username:</b> </td>
<td><input type="text" class="textbox" name="username"/></td>
</tr>

<tr>
<td><b>Password:</b> </td>
<td><input class="textbox" type="password" name="password"/></td>
</tr>

<tr>
<td>
<input class="btn textbox" type="submit" value="Login"/>
</td>
</tr>
</table>

</form>
<b style="color:red;">${param.loginfailmsg}</b>
</center>
</body>
</html>