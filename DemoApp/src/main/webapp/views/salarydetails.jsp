<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include  page="header.jsp"/>

<!DOCTYPE html PUBLIC="-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="STYLESHEET" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
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
.salaryTable {
	position: relative;
  	top: 90px;
}
.listSalary {
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

#excelbtn{
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
<script type="text/javascript">
function postData(url = ``, data = {}) {
  // Default options are marked with *
    return fetch(url, {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, cors, *same-origin
        cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
        credentials: "same-origin", // include, *same-origin, omit
        headers: {
            "Content-Type": "application/json",
            // "Content-Type": "application/x-www-form-urlencoded",
        },
        redirect: "follow", // manual, *follow, error
        referrer: "no-referrer", // no-referrer, *client
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    })
    .then(response => response.json()); // parses response to JSON
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Demo App:Admin Dashboard</title>
</head>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>


<script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>



<body>

<center>
<h2> Salary Details</h2>
<h4 style="color:red">Data Not Available !!</h4>
</center>
<table id="salaryTable" class="display" style="width:100%">

<script language="javascript">
populateData();

function populateData(){
var table;
postData('localhost:8080/rest/salary/mm',{client:'qq'}).then(resp => {
table = $('#salaryTable').DataTable({
	processing : true,
    data : resp.data,
    columns: resp.columns[0].map(x => {return { "title" : x }})
  });
});
}
</script>
</table>
</body>
</html>